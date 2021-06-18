package Business.Utilizadores;

public class Administrador extends Utilizador
{
    /**
     * Construtor da classe Administrador sem parâmetros
     */
    public Administrador() {super(); }

    /**
     * Construtor da classe Administrador com paramêtros
     * @param id         id do administrador
     * @param nome       nome do administrador
     * @param email      email do administrador
     * @param password   password do administrador
     */
     public Administrador(int id, String nome, String email, String password)
     {
         super(id,nome,email,password);
     }

    /**
     * Construtor por cópia da classe Administrador
     * @param a     Administrador
     */
    public Administrador(Administrador a)
    {
        super(a.getId(), a.getNome(), a.getEmail(), a.getPassword());
    }


    /**
     * Método que altera a password de um utilizador
     * @return    Cópia do administrador
     */
    public Administrador clone() {
        Administrador a =  new Administrador(this);
        return a;
    }
}
