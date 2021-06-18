package Business.Media;

public class Conteudo
{
    /** Id do conteudo **/
    private int id;
    /** Nome do conteudo **/
    private String nome;
    /** Duração do conteudo **/
    private double duracao;
    /** Formato do conteudo **/
    private String formato;
    /** Categoria do conteudo **/
    private String categoria;

    /**
     * Construtor da classe Conteudo sem parâmetros
     */
    public Conteudo()
    {
        this.id = 0;
        this.nome = "ND";
        this.duracao = 0;
        this.formato = "";
        this.categoria ="ND";
    }

    /**
     * Construtor da classe Conteudo com paramêtros
     * @param id          id do conteudo
     * @param nome        nome do conteudo
     * @param duracao     duracao do conteudo
     * @param formato     formato do conteudo
     * @param categoria   categoria do conteudo
     */
    public Conteudo(int id, String nome, double duracao, String formato, String categoria)
    {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.formato = formato;
        this.categoria = categoria;
    }

    /**
     * Construtor por cópia da classe Conteudo
     * @param c     Conteudo
     */
    public Conteudo(Conteudo c)
    {
        this.id = c.getId();
        this.nome = c.getNome();
        this.duracao = c.getDuracao();
        this.formato = c.getFormato();
        this.categoria = c.getCategoria();
    }

    /**
     * Método que devolve o id do conteudo
     * @return     Id do conteudo.
     */
    public int getId(){return this.id;}

    /**
     * Método que devolve o nome do conteudo
     * @return     Nome do conteudo
     */
    public String getNome(){return this.nome;}

    /**
     * Método que devolve a duracao do conteudo
     * @return     Duracao do conteudo
     */
    public double getDuracao(){return this.duracao;}

    /**
     * Método que devolve o formato do conteudo
     * @return     Formato do conteudo
     */
    public String getFormato(){return this.formato;}

    /**
     * Método que devolve a categoria do conteudo
     * @return     Categoria do conteudo
     */
    public String getCategoria(){return this.categoria;}

    /**
     * Método que altera o id do conteudo
     * @param id    Novo id do conteudo
     */
    public void setId(int id){
        this.id = id;}

    /**
     * Método que altera o nome do conteudo
     * @param n    Novo nome do conteudo
     */
    public void setNome(String n){this.nome = n;}

    /**
     * Método que altera a duracao do conteudo.
     * @param d    Nova duracao do conteudo
     */
    public void setDuracao(double d){this.duracao = d;}

    /**
     * Método que altera o formato do conteudo
     * @param f    Novo formato do conteudo
     */
    public void setFormato(String f){this.formato = f;}

    /**
     * Método que altera a categoria do conteudo
     * @param c   Nova categoria do conteudo
     */
    public void setCategoria(String c){this.categoria = c;}

    /**
     * Método que cria a cópia de um objeto
     * @return    Cópia do conteúdo
     */
    public Conteudo clone() {
        Conteudo c=  new Conteudo(this);
        return c;
    }

    /**
     * Método equals da classe Conteudo
     * @param o   Objecto
     * @return    true caso os objetos sejam iguais, false caso contrário
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        Conteudo c = (Conteudo) o;

        return c.getId() == this.id && c.getNome().equals(this.nome) && this.duracao == c.getDuracao() &&
                c.getFormato().equals(this.formato) && c.getCategoria().equals(this.categoria);
    }

    /**
     * Devolve String com a informação da classe Conteudo
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ");
        sb.append(this.id);
        sb.append(" \n");
        sb.append("Nome: ");
        sb.append(this.nome);
        sb.append(" \n");
        sb.append("Duração: ");
        sb.append(this.duracao);
        sb.append(" \n");
        sb.append("Formato: ");
        sb.append(this.formato);
        sb.append(" \n");
        sb.append("Categoria: ");
        sb.append(this.categoria);
        sb.append(" \n");

        return sb.toString();
    }

}
