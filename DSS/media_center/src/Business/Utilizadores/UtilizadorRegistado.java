package Business.Utilizadores;

public class UtilizadorRegistado extends Utilizador
{
    /** id da bibliteca pessoal de musicas do utilizador **/
    private int idBibliotecaMusica;
    /** id da bibliteca pessoal de videos do utilizador **/
    private int idBibliotecaVideo;

    /**
     * Construtor da classe UtilizadorRegistado sem parâmetros
     */
    public UtilizadorRegistado()
    {
        super();
        this.idBibliotecaMusica = -7;
        this.idBibliotecaVideo = this.idBibliotecaMusica;
    }

    /**
     * Construtor da classe UtilizadorRegistado com paramêtros
     * @param id                    id do administrador
     * @param nome                  nome do administrador
     * @param email                 email do administrador
     * @param password              password do administrador
     * @param idBibliotecaMusica    id da bibliteca pessoal de musicas do utilizador
     * @param idBibliotecaVideo     id da bibliteca pessoal de videos do utilizador
     */
    public UtilizadorRegistado(int id, String nome, String email, String password, int idBibliotecaMusica, int idBibliotecaVideo)

    {
        super(id,nome,email,password);
        this.idBibliotecaMusica = idBibliotecaMusica;
        this.idBibliotecaVideo = idBibliotecaVideo;
    }

    /**
     * Construtor por cópia da classe UtilizadorRegistado
     * @param u     Utilizador
     */
    public UtilizadorRegistado(UtilizadorRegistado u)
    {

        super(u.getId(), u.getNome(), u.getEmail(), u.getPassword());
        this.idBibliotecaMusica = u.getIdBibliotecaMusica();
        this.idBibliotecaVideo = u.getIdBibliotecaVideo();
    }

    /**
     * Método que devolve o id da biblioteca pessoal de videos do utilizador
     * @return     Id da biblioteca pessoal de Videos do utilizador.
     */
    public int getIdBibliotecaVideo(){return this.idBibliotecaVideo;}

    /**
     * Método que devolve o id da biblioteca pessoal de musica do utilizador
     * @return     Id da biblioteca pessoal de musica do utilizador.
     */
    public int getIdBibliotecaMusica(){
       return this.idBibliotecaMusica;}

    /**
     * Método que altera o id da biblioteca pessoal de musica do utilizador
     * @param id    Novo id da biblioteca pessoal de musica
     */
    public void setIdBibliotecaMusica(int id){this.idBibliotecaMusica = id;

       }
    /**
     * Método que altera o id da biblioteca pessoal de video do utilizador
     * @param id    Novo id da biblioteca pessoal de video
     */
    public void setIdBibliotecaVideo(int id){this.idBibliotecaVideo = id;}


    /**
     * Construtor por cópia da classe UtilizadorRegistado
     * @return    Cópia do utilizador
     */
    public UtilizadorRegistado clone() {
        UtilizadorRegistado u =  new  UtilizadorRegistado(this);
        return u;
    }

}
