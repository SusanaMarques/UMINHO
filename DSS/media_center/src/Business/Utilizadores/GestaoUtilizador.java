package Business.Utilizadores;

import Business.Exceptions.CategoriaIgualException;
import Business.Exceptions.CredenciaisInvalidasException;
import Business.Media.Conteudo;
import Business.Media.Playlist;
import Database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GestaoUtilizador
{
    /** Administradores do media center **/
    private Map<String, Administrador> admins = new AdministradorDAO();
    /** Utilizadores registados no media center **/
    private Map<String, UtilizadorRegistado> users = new UtilizadorRegistadoDAO();
    /** Playlists de musica exitentes no media center **/
    private Map<Integer, Playlist> playlistsMusicas = new PlaylistMusicaDAO();
    /** Playlists de video exitentes no media center **/
    private Map<Integer, Playlist> playlistsVideos = new PlaylistVideoDAO();
    /** Mapa da categoria das musicas quando alteradas pelos utilizadores **/
    private Map<Integer,Map<Integer,String>> categoriasMusica = new CategoriaMusicaDAO();
    /** Mapa da categoria das videos quando alteradas pelos utilizadores **/
    private Map<Integer,Map<Integer,String>> categoriasVideo = new CategoriaVideoDAO();

    /** Método que incia a sessão
     * @param email    Email do utilizador a autenticar
     * @param pass     Password do utilizador a autenticar
     * @param idOp     Identificador do tipo de utilizador
     */
    public Utilizador iniciarSessao(String email, String pass, int idOp) throws CredenciaisInvalidasException {
        Utilizador u;
        if(idOp == 1) u = admins.get(email);
            else u = users.get(email);
            boolean e = u.getEmail().equals(email);
            boolean p = u.getPassword().equals(pass);
        if( !(e && p) ) throw new CredenciaisInvalidasException();
        return u;
    }

     /** Método que devolve os utilizadores
      * @param idU      Id de utlizador
      * @param idTipo   Identificador do tipo de utilizador
      */
    public Utilizador getUser(int idU,int idTipo){
        Utilizador ret;
        if (idTipo == 2) ret = users.get(idU);
        else ret = admins.get(idU);
        return ret;
    }

    /** Método que atualiza a biblioteca pessoal
     * @param c    Conteudo a acrescentar
     * @param t    Identificador do tipo de Conteudo
     * @param u    Utilizdor a atualizar
     **/
    public void addBibiliotecaPessoal(Conteudo c, char t, UtilizadorRegistado u) {
        if(t == 'm') {
            Playlist p = playlistsMusicas.get(u.getIdBibliotecaMusica());
            p.setIdUser(u.getId());
            ArrayList<Integer> l = p.getlst();
            l.add(c.getId());
            p.setLst(l);
            playlistsMusicas.put(u.getIdBibliotecaMusica(),p);

        }
        if(t=='v') {
            Playlist p =  playlistsVideos.get(u.getIdBibliotecaVideo());
            p.setIdUser(u.getId());
            ArrayList<Integer> l = p.getlst();
            l.add(c.getId());
            p.setLst(l);
            playlistsVideos.put(u.getIdBibliotecaVideo(),p);

        }
    }


    /** Método que apresenta uma playlist de videos
     * @param idPlaylist      Id da playlist
     * @return                List com todos os videos da playlist
     */
    public Playlist getPlaylistVideo( int idPlaylist) {
        return playlistsVideos.get(idPlaylist);
    }


    /** Método que apresenta uma playlist de musicas
     * @param idPlaylist      Id da playlist
     * @return                List com todas as musicas da playlist
     */
    public Playlist getPlaylistMusica( int idPlaylist) {
        return playlistsMusicas.get(idPlaylist);
    }



    /** Método que altera a categoria de uma musica de um utilizador
     * @param idCont   Id da musica a alterar
     * @param newCat   Nova categoria da musica
     * @param idU      Id do utilizador
     */
    public void alterarCategoriaM(String newCat, int idCont,int idU) throws CategoriaIgualException {
        Map<Integer,String> cats = null;
        if(categoriasMusica.containsKey(idCont))
            cats = categoriasMusica.get(idCont);
        else cats = new HashMap<>();
        if(cats.containsKey(idU)) if(cats.get(idU).equals(newCat)) throw new CategoriaIgualException();
        else cats.remove(idU);
        cats.put(idU,newCat);
        categoriasMusica.put(idCont,cats);
    }

    /** Método que altera a categoria de um video de um utilizador
     * @param idCont   Id do video a alterar
     * @param newCat   Nova categoria do video
     * @param idU      Id do utilizador
     */
    public void alterarCategoriaV(String newCat, int idCont,int idU) throws CategoriaIgualException {
        Map<Integer,String> cats = null;
        if(categoriasMusica.containsKey(idCont))
            cats = categoriasVideo.get(idCont);
           else cats=new HashMap<>();
        if(cats.containsKey(idU))
            if(cats.get(idU).equals(newCat)) throw new CategoriaIgualException();
            else cats.remove(idU);
        cats.put(idU,newCat);
        categoriasVideo.put(idCont,cats);
    }

    /** Método que retornar o id da playlist pessoal de musicas do utilizador atual
     * @param idUtilizadorAtual     Id do utilizador atual
     * @return                      id da playlist pessoal de musicas do utilizador atual
     */
    public int getPlaylistPessoalM(int idUtilizadorAtual) {
        UtilizadorRegistado u = users.get(idUtilizadorAtual);
        return u.getIdBibliotecaMusica();
    }

    /** Método que retornar o id da playlist pessoal de videos do utilizador atual
     * @param idUtilizadorAtual     Id do utilizador atual
     * @return                      Id da playlist pessoal de musicas do utilizador atual
     */
    public int getPlaylistPessoalV(int idUtilizadorAtual) {
        UtilizadorRegistado u = users.get(idUtilizadorAtual);
        return u.getIdBibliotecaVideo();
    }

    /** Método que organiza a playlist pessoal de musicas do utilizador atual, incluindo categorias alteradas
     * @param idU                   Id do utilizador atual
     * @param idBib                 Id da biblioteca geral do utilizador
     * @return                      map de musicas da playlist pessoal e as suas categorias
     */
    public Map<Integer,String> getPlaylistPessoalM(int idU, int idBib){
        List<Integer> bibIds = playlistsMusicas.get(idBib).getlst();
        Map<Integer,String> idCats = new HashMap<>();
        for(int id : bibIds){
            if(categoriasMusica.containsKey(id)) {
                if(categoriasMusica.get(id).containsKey(idU)) idCats.put(id,categoriasMusica.get(id).get(idU)); else idCats.put(id,null);
            }else idCats.put(id,null);
        }
        return idCats;
    }

    /** Método que organiza a playlist pessoal de videos do utilizador atual, incluindo categorias alteradas
     * @param idU                   Id do utilizador atual
     * @param idBib                 Id da biblioteca geral do utilizador
     * @return                      map de videos da playlist pessoal e as suas categorias
     */
    public Map<Integer,String> getPlaylistPessoalV(int idU, int idBib){
        List<Integer> bibIds = playlistsVideos.get(idBib).getlst();
        Map<Integer,String> idCats = new HashMap<>();
        for(int id : bibIds){
            if(categoriasVideo.containsKey(id)) {
                if(categoriasVideo.get(id).containsKey(idU)) {
                    System.out.println("1"); idCats.put(id,categoriasVideo.get(id).get(idU)); System.out.println("2");
                } else idCats.put(id,null);
            }else idCats.put(id,null);
        }
        return idCats;
    }
}
