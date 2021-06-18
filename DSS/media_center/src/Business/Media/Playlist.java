package Business.Media;

import java.util.ArrayList;

public class Playlist
{
    /** id da playlist **/
    private int idPlaylist;
    /** nome da playlist **/
    private String nome;
    /** id do utilizador dono da playlist **/
    private int idUser;
    /** lista de ids dos conteúdos contidos na playlist **/
    private ArrayList<Integer> lst;

    /**
     * Construtor da classe Playlist sem parâmetros
     */
    public  Playlist()
    {
        this.idPlaylist = 0;
        this.nome = "Biblioteca Pessoal";
        this.idUser = 0;
        this.lst = new ArrayList<>();
    }

    /**
     * Construtor da classe Playlist com paramêtros
     * @param idPlaylist          id da playlist
     * @param nome                nome da playlist
     * @param idUser              id do utilizador, dono da playlist
     * @param lst                 list de ids dos conteúdos contidos na playlist
     */
    public Playlist(int idPlaylist, String nome, int idUser, ArrayList<Integer> lst)
    {
        this.idPlaylist = idPlaylist;
        this.nome = nome;
        this.idUser = idUser;
        this.lst = lst;
    }

    /**
     * Construtor por cópia da classe Playlist
     * @param p       Playlist
     */
    public Playlist(Playlist p)
    {
        this.idPlaylist = p.getIdPlaylist();
        this.nome = p.getNome();
        this.idUser = p.getUser();
        this.lst = p.getlst();
    }

    /**
     * Método que devolve o id da playlist em questão
     * @return     id da playlist
     */
     public int getIdPlaylist(){return this.idPlaylist;}

    /**
     * Método que devolve o nome da playlist em questão
     * @return     nome da playlist
     */
     public String getNome(){return this.nome;}

    /**
     * Método que devolve o id do utilizador dono da playlist
     * @return     id do utilizador dono da playlist
     */
    public int getUser(){return this.idUser;}

    /**
     * Método que devolve a lista de ids do conteudo da playlist
     * @return     lista de ids do conteudo da playlist
     */
    public ArrayList<Integer> getlst(){
        ArrayList<Integer> l = new ArrayList<>();
        l.addAll(lst);
        return l;
    }

    /**
     * Método que altera o id da playlist
     * @param id    Novo id da playlist
     */
    public void setIdPlaylist(int id){this.idPlaylist = id;}

    /**
     * Método que altera o nome da playlist
     * @param n   Novo nome da playlist
     */
    public void setNome(String n){this.nome = n;}

    /**
     * Método que altera o id do utilizador dono da playlist
     * @param id    Novo id do utilizador dono da playlist
     */
    public void setIdUser(int id){this.idUser = id;}

    /**
     * Método que altera a lista de conteudos da playlist
     * @param l    Novo lista de conteudos da playlist
     */
    public void setLst(ArrayList<Integer> l){this.lst = l;}


    /**
     * Construtor por cópia da classe Playlist
     * @return    Cópia do objeto Playlist
     */
    public Playlist clone() {
        Playlist p =  new  Playlist(this);
        return p;
    }

}
