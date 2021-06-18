package Business.Utilizadores;

public class Utilizador
{
    /** Id do utilizador **/
    private int id;
    /** nome do utilizador **/
    private String nome;
    /** email do utilizador **/
    private String email;
    /** password do utilizador **/
    private String password;

    /**
     * Construtor da classe Utilizador sem parâmetros
     */
    public Utilizador()
    {
        this.id = 0;
        this.nome = "N/D";
        this.email = "N/D";
        this.password = "N/D";
    }

    /**
     * Construtor da classe Utilizador com paramêtros
     * @param id        id do utilizador
     * @param nome      nome do utilizador
     * @param email     email do utilizador
     * @param password  password do utilizador
     */
    public Utilizador(int id, String nome, String email, String password)
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    /**
     * Construtor por cópia da classe Utilizador
     * @param u     Utilizador
     */
    public Utilizador(Utilizador u)
    {
        this.id = u.getId();
        this.nome = u.getNome();
        this.email = u.getEmail();
        this.password = u.getPassword();
    }

    /**
     * Método que devolve o id do utilizador
     * @return id do utilizador.
     */
    public int getId(){return this.id;}

    /**
     * Método que devolve o nome do utilizador
     * @return nome do utilizador
     */
    public String getNome(){return this.nome;}

    /**
     * Método que devolve o email do utilizador
     * @return email do utilizador
     */
    public String getEmail(){return this.email;}

    /**
     * Método que devolve a password do utilizador
     * @return password do utilizador
     */
    public String getPassword(){return this.password;}

    /**
     * Método que altera o id de um utilizador
     * @param id    Novo id do utilizador
     */
    public void setId(int id){this.id = id;}

    /**
     * Método que altera o nome de um utilizador
     * @param n    Novo nome do utilizador
     */
    public void setNome(String n){this.nome = n;}

    /**
     * Método que altera o nome de um utilizador
     * @param e    Novo nome do utilizador
     */
    public void setEmail(String e){this.email = e;}

    /**
     * Método que altera a password de um utilizador
     * @param p    Nova password do utilizador
     */
    public void setPassword(String p){this.password = p;}

    /**
     * Método que cria a cópia de um Utilizador
     * @return    Cópia do utilizador
     */
    public Utilizador clone() {
        Utilizador u =  new Utilizador(this);
        return u;
    }

    /**
     * Método equals da classe Utilizador
     * @param o   Objecto
     * @return    true caso os objetos sejam iguais, false caso contrário
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        Utilizador u = (Utilizador) o;

        return u.getId() == this.id && u.getNome().equals(this.nome) && u.getEmail().equals(this.email) && u.getPassword().equals(this.password);
    }

    /**
     * Devolve String com a informação da classe Utilizador
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ");
        sb.append(this.id);
        sb.append(" Nome: ");
        sb.append(this.nome);
        sb.append(" email: ");
        sb.append(this.email);
        sb.append(" \n");

        return sb.toString();
    }
}

