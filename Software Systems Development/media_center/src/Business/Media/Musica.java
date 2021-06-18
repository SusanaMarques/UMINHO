package Business.Media;

public class Musica extends Conteudo
{
    /** artista da música **/
    private String artista;

    /**
     * Construtor da classe Musica sem parâmetros
     */
    public Musica()
    {
        super();
        this.artista = "N/D";
    }

    /**
     * Construtor da classe Musica com paramêtros
     * @param id          id do conteudo
     * @param nome        nome do conteudo
     * @param duracao     duracao do conteudo
     * @param formato     formato do conteudo
     * @param categoria   categoria do conteudo
     * @param artista     artista do conteúdo
     */
    public Musica(int id, String nome, double duracao, String formato, String categoria, String artista)
    {
        super(id,nome,duracao,formato,categoria);
        this.artista = artista;
    }

    /**
     * Construtor por cópia da classe Musica
     * @param m     Musica
     */
    public Musica(Musica m)
    {
        super(m.getId(), m.getNome(), m.getDuracao(), m.getFormato(), m.getCategoria());
        this.artista = m.getArtista();
    }

    /**
     * Método que devolve o artista da musica em questão
     * @return     Artista da musica.
     */
    public String getArtista(){return this.artista;}

    /**
     * Método que altera o artista da musica em questão
     * @param a    Novo artista da musica
     */
    public void setArtista(String a){this.artista = a;}

    /**
     * Construtor por cópia da classe Musica
     * @return    Cópia do objeto Musica
     */
    public Musica clone() {
        Musica m =  new  Musica(this);
        return m;
    }
}
