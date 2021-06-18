package Business;
import Business.Exceptions.CategoriaIgualException;
import Business.Exceptions.ConteudoDuplicadoException;
import Business.Exceptions.CredenciaisInvalidasException;
import Business.Exceptions.FormatoDesconhecidoException;
import Business.Media.Conteudo;
import Business.Media.GestaoConteudo;
import Business.Media.Musica;
import Business.Media.Video;
import Business.Utilizadores.GestaoUtilizador;
import Business.Utilizadores.Utilizador;
import Business.Utilizadores.UtilizadorRegistado;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.ID3v24Handler;
import org.apache.tika.parser.mp3.ID3v2Frame;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.parser.mp4.MP4Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class  MC
{
    /** Instancia da gestao de conteudo**/
    private GestaoConteudo gc = new GestaoConteudo();
    /** Instancia da gestao de utilizador**/
    private GestaoUtilizador gu = new GestaoUtilizador();
    /** Id do utilizador atual**/
    private int idUtilizadorAtual;
    /** Identificador do tipo de utilizador             Administrador : 1                Utilizador Registado : 2   **/
    private int idType;


    /** Método que inicia uma sessão
     * @param mail    Email do utilizador a autenticar
     * @param pass    Password do utilizador a autenticar
     */
    public void iniciarSessao(String mail, String pass) throws CredenciaisInvalidasException {
        if(mail == null||pass == null || mail.isEmpty() || pass.isEmpty()) throw new CredenciaisInvalidasException();
        Utilizador u = gu.iniciarSessao(mail, pass, idType);
        idUtilizadorAtual= u.getId();

    }

    /** Método que  termina uma sessão **/
    public void terminarSessao() {
        idUtilizadorAtual = -1;
        idType = -1;
    }

    /** Método que retorna o nome do utilizador registado **/
    public String getNome() {
        Utilizador u= gu.getUser(idUtilizadorAtual,idType);
        return u.getNome();
    }


    /** Método que faz o upload de conteudo
     * @param p   Path do conteudo a fazer upload
     */
    public void uploadConteudo(String p) throws FormatoDesconhecidoException, IOException, ConteudoDuplicadoException, URISyntaxException, TikaException, SAXException {
        char t;
        Random  r = new Random();
        Conteudo c = new Conteudo();

        StringTokenizer tokens = new StringTokenizer( p,".");
        StringTokenizer tokensName = new StringTokenizer(tokens.nextToken(), "/");
        while(tokensName.countTokens()>1) tokensName.nextToken();
        String mp4Artist = tokensName.nextToken();

        String type = tokens.nextToken();
        UtilizadorRegistado u =(UtilizadorRegistado) gu.getUser(idUtilizadorAtual,idType);


        //Extrair metadados
        String album;
        String artist;
        String title;
        String categoria;
        double duracao ;
        URI res = new URL(p) .toURI();
        File origin = Paths.get(res).toFile();

        //Verificar formato
        if (type.equals("mp3")){
            ID3v24Handler m = extrairMetaMp3(origin);
            duracao = getDuration(origin);
            title = m.getTitle();
            artist = m.getArtist();
            categoria = m.getGenre();
            album = m.getAlbum();

            if(title == null) title= mp4Artist;
            if(artist == null) artist = "N/D";
            if(categoria == null) categoria = "N/D";
            if(album == null) album = "N/D";

            t = 'm';
            c = new Musica(r.nextInt(), title.substring(0,Math.min(title.length(),40)), duracao, "mp3", categoria.substring(0,Math.min(categoria.length(),40)), artist.substring(0,Math.min(artist.length(),40)));
        }
        else if (type.equals("mp4")){
            Metadata m = extrairMetaMp4(origin);
            duracao = Double.parseDouble(m.get("xmpDM:duration"));
            String realizador = m.get("xmpDM:artist");
            if (realizador== null) realizador="N/D";
            t='v';
            Video v = new Video();
            v.setId(r.nextInt());
            v.setNome(mp4Artist.substring(0,Math.min(mp4Artist.length(),40)));
            v.setFormato("mp4");
            v.setDuracao(duracao);
            v.setCategoria("N/D");
            v.setRealizador(realizador.substring(0,Math.min(realizador.length(),40)));
            c=v;
        }
        else throw new FormatoDesconhecidoException();

        //Verificar duplicaçoes
        int dupId = gc.verificaDuplicacoes(c,t);
        if(dupId == -1) {
            //Path building & copiar para a biblioteca
            String path=(new File("").getAbsolutePath())+"/Biblioteca/"+c.getId()+ (t=='m'? ".mp3":".mp4" );
            File newFile = new File(path);
            Files.copy(origin.toPath(),newFile.toPath());
        }
        else { c = gc.getConteudo(dupId,t); }

        //Adicionar a bibliotecas
        gc.addBibliotecaGeral(c,t,u, dupId);
        gu.addBibiliotecaPessoal(c, t,u);
        if(dupId!=-1) throw new ConteudoDuplicadoException();
    }

    /** Método que extrai os metadados dos ficheiros mp4
     * @param origin       File com a instancia do mp4 a extrair
     * @return             Instancia com os metadados do mp4
     */
    private Metadata extrairMetaMp4(File origin) throws TikaException, SAXException, IOException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(origin);
        ParseContext pcontext = new ParseContext();
        MP4Parser mp4Parser = new MP4Parser();
        mp4Parser.parse(inputstream, handler, metadata, pcontext);
        return metadata;
    }

    /** Método para extrair a duração das musicas
     * @param origin     File com a instancia do mp3 a extrair
     * @return           Duração do mp3
     */
    private double getDuration(File origin) throws TikaException, SAXException, IOException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(origin);
        ParseContext pcontext = new ParseContext();

        Mp3Parser  mp3Parser = new  Mp3Parser();
        mp3Parser.parse(inputstream, handler, metadata, pcontext);
        return ((Double.parseDouble(metadata.get("xmpDM:duration")))/60000);
    }

    /** Método que extrai os metadados dos ficheiros mp3
     * @param file     File com a instancia do mp3 a extrair
     * @return        Instancia do handler dos metadados do mp3
     */
    private ID3v24Handler extrairMetaMp3(File file) throws IOException, TikaException, SAXException {
        FileInputStream inputstream = new FileInputStream(file);
        ID3v24Handler ret = new ID3v24Handler((ID3v2Frame) ID3v2Frame.createFrameIfPresent(inputstream));
        return  ret;
    }

    /** Método que altera o tipo do utilizador que está a usar o sistema
     * @param idT    Tipo do utilizador
     */
    public void setUserT(int idT) {
        idType = idT;
    }

    /** Método que retorna o tipo do utilizador a usar o sistema
     * @return      Tipo do utilizador
     */
    public int getUserT() {
        return idType;
    }


    /** Método que apresenta a Biblioteca geral das Musicas
     * @return        Set com todas as instancias de musica da biblioteca geral
     */
    public Set<Musica> showMusicas(){
        return  gc.getBibliotecaMusica();
    }

    /** Método que apresenta a Biblioteca geral dos Videos
     * @return        Set com todas as instancias de video da biblioteca geral
     */
    public Set<Video> showVideos(){
        return  gc.getBibliotecaVideo();
    }


    /** Método que apresenta uma playlist de musicas
     * @param idPlaylist     Id da playlist
     * @return               List com todas as musicas da playlist
     */
    public Set<Musica> showMusicasPlaylist(int idPlaylist){
        Map<Integer,String> idCats = gu.getPlaylistPessoalM(idUtilizadorAtual,idPlaylist);
        Set<Musica> ret= new HashSet<>();
        Musica m = null;
        for(int id : idCats.keySet()){
            m =((Musica) gc.getConteudo(id,'m')).clone();
            if(idCats.get(id)!=null) m.setCategoria(idCats.get(id));
            ret.add(m);
        }
        return ret;
    }

    /** Método que apresenta uma playlist de videos
     * @param idPlaylist    Id da playlist
     * @return              List com todas os videos da playlist
     */
    public Set<Video> showVideosPlaylist(int idPlaylist){
        Map<Integer,String> idCats = gu.getPlaylistPessoalV(idUtilizadorAtual,idPlaylist);
        Set<Video> ret= new HashSet<>();
        Video v = null;
        for(int id : idCats.keySet()){
            v =((Video) gc.getConteudo(id,'v')).clone();
            if(idCats.get(id)!=null) v.setCategoria(idCats.get(id));
            ret.add(v);
        }
        return ret;
    }

    /** Método que devolve o id da playlist pessoal de musicas de um utilizador
     * @return             id da playlist pessoal de musicas de um utilizador
     */
    public int getidPessoalM(){ return gu.getPlaylistPessoalM(idUtilizadorAtual); }

    /** Método que devolve o id da playlist pessoal de videos de um utilizador
     * @return             id da playlist pessoal de videos de um utilizador
     */
    public int getidPessoalV(){ return gu.getPlaylistPessoalV(idUtilizadorAtual); }

    /** Método que altera a categoria de um conteudo de um utilizador
     * @param idCont    Id do conteudo a alterar
     * @param newCat    Nova categoria do conteudo
     * @param type      Tipo do conteudo a alterar: m para musicas e v para videos
     */
    public void alterarCategoria(String newCat, int idCont, char type) throws CategoriaIgualException {
        if(type=='m') gu.alterarCategoriaM(newCat,idCont,idUtilizadorAtual);
        if(type=='v') gu.alterarCategoriaV(newCat,idCont,idUtilizadorAtual);
    }

    /** Método que devolve o path de um conteúdo
     * @return          path de um conteúdo
     */
    public String getPath(char type, int idCont) throws MalformedURLException {
        File pathFinder = new File("");
        String s = pathFinder.toURI().toURL().toExternalForm()+"/Biblioteca/"+idCont;
        if(type == 'm') s+=".mp3";
        else s+=".mp4";
        return s;
    }

    public Conteudo getCont(int idm, char m) {
       return gc.getConteudo(idm,m);
    }
}

