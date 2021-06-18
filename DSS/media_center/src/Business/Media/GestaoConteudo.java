package Business.Media;

import Business.Utilizadores.UtilizadorRegistado;
import Database.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestaoConteudo {

    /** Biblioteca geral de Musicas **/
    private Map<Integer, Musica> musicas = new MusicaDAO();
    /** Biblioteca geral de Videos **/
    private Map<Integer, Video> videos = new VideoDAO();
    /** Proprietarios das Musicas **/
    private Map<Integer, List<UtilizadorRegistado>> proprietariosMusica = new ProprietariosMusicaDAO();
    /** Proprietarios dos Videos **/
    private Map<Integer, List<UtilizadorRegistado>> proprietariosVideo = new ProprietariosVideoDAO();



    /**
     * Método que verifica duplicacoes de conteudo
     * @param c      conteudo a comparar
     * @param type   tipo do conteudo
     * @return       id do conteudo caso seja repetido, -1 caso não hajam repetiçoes
     */
    public int verificaDuplicacoes(Conteudo c, char type) {
        int ret = -2;
        if (type == 'm') {
            ret = (musicas.containsValue(c) ? (this.getValueM((Musica) c)) : -1);
        }
        if (type == 'v') {
            ret = (videos.containsValue(c) ? (this.getValueV((Video) c)) : -1);
        }
        return ret;
    }

    /**
     * Método que obtem um conteudo da biblioteca geral
     * @param idC   id do conteudo
     * @param type  tipo do conteudo a obter
     * @return      conteudo
     */
   public Conteudo getConteudo(int idC, char type){
       Conteudo ret= null;
       if(type=='m') ret=musicas.get(idC);
       
       if(type=='v') ret=videos.get(idC);
       return ret;
   }

     /**
     * Método que unifica o id de uma Musica repetido
     * @param c    conteudo a comparar
     * @return     id do conteudo
     */
    int getValueM(Musica c){
       Collection<Musica> ms = musicas.values();
       for(Musica m : ms){
           if(m.getArtista(). equals(c.getArtista()) && m.getCategoria().equals(c.getCategoria()) && m.getNome().equals(c.getNome()))
                return m.getId();
       }
       return -1;
    }

    /**
     * Método que unifica id de um Video repetido
     * @param c    conteudo a comparar
     * @return     id do conteudo
     */
    int getValueV(Video c){
        Collection<Video> vs = videos.values();
        for(Video v : vs){
            if(v.getRealizador(). equals(c.getRealizador()) && v.getCategoria().equals(c.getCategoria()) && v.getNome().equals(c.getNome()))
                 return v.getId();
        }
        return -1;
     }

    /**
     * Método que adiciona o conteudo à biblioteca geral e atualiza o seu proprietario
     * @param c     Conteudo a adicionar
     * @param tipo  Tipo do conteudo a adicionar
     * @param u     Utilizador que está a carregar o conteudo
     */
    public void addBibliotecaGeral(Conteudo c, char tipo, UtilizadorRegistado u, int dupId) {
        if (tipo == 'm') {
            if(dupId == -1)
            {
                musicas.put(c.getId(), (Musica) c);
                List<UtilizadorRegistado> prop = new ArrayList<>();
                prop.add(u);
                proprietariosMusica.put(c.getId(), prop);
            }
            else
            {
                List<UtilizadorRegistado> prop = proprietariosMusica.get(c.getId());
                prop.add(u);
                proprietariosMusica.put(c.getId(), prop);
            }

        } else {
            if (tipo == 'v') {
            if(dupId == -1){
                videos.put(c.getId(), (Video) c);
                List<UtilizadorRegistado> prop = new ArrayList<>();
                prop.add(u);
                proprietariosVideo.put(c.getId(), prop);
            }
            else
            {
                List<UtilizadorRegistado> prop = proprietariosVideo.get(c.getId());
                prop.add(u);
                proprietariosVideo.put(c.getId(), prop);
            }
        }
        }
    }

    /** Método que apresenta a Biblioteca geral dos Videos
     * @return Set com todas as instancias de video da biblioteca geral
     */
    public Set<Video> getBibliotecaVideo() {
        return ((Set) videos.values());
    }

    /** Método que apresenta a Biblioteca geral das Musicas
     * @return Set com todas as instancias de musica da biblioteca geral
     */
    public Set<Musica> getBibliotecaMusica() { return ((Set) musicas.values()); }
}