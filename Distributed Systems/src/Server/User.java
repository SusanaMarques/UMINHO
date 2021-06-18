package Server;

public class User
{
    /** Username do Utilizador **/
    private String username;
    /** Password do Utilizador **/
    private String password;
    /** Buffer de mensagens do utilizador **/
    private MsgBuffer msg;

    /**
     * Construtor da classe User com parâmetros
     * @param username     Username do utilizador
     * @param password     Password do utilizador
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.msg = new MsgBuffer();
    }

    /**
     * Método que devolve o usernamedo utilizador
     * @return           Username do utilizador
     */
    public String getUsername() { return this.username; }

    /**
     * Método que devolve a password do utilizador
     * @return           Password do utilizador
     */
    public String getPassword() { return this.password; }

    /**
     * Método que devolve as notificações do utilizador
     * @return        Buffer de mensagens do utilizador
     */
    public MsgBuffer getMsg() { return this.msg; }

    /**
     * Método que atualiza a password do utilizador
     * @param p           Nova password do utilizador
     */
    public void setPassword(String p){ this.password = p; }

    /**
     * Método que atualiza as notificações do utilizador
     * @param m             Novo buffer de mensagens
     */
    public void setNotificacoes(MsgBuffer m){ this.msg = m; }

    /**
     * Função equals da classe User
     * @param o           Objecto
     * @return            Boolean
     */
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || (this.getClass() != o.getClass())) return false;
        User u = (User) o;
        return username.equals(u.username);
    }

    /**
     * Método que verifica se a password dada corresponde à password do utilizador
     * @param password    Password
     * @return            True se a password dada é igual, false caso contrário
     */
    public boolean verifyPassword(String password) { return this.password.equals(password); }

    /**
     * Método que escreve uma mensagem no buffer de mensagens do utilizador
     * @param n             Nova notificação do utilizador
     */
    public void notification(String n) { msg.write(n); }

    /**
     * Método que devolve a última notificação ao utilizador
     * @return           Última notificação ao utilizador
     */
    public String readNotification() throws InterruptedException{ return msg.read();}
}
