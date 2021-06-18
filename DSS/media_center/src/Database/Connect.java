package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Classe que gere ligações à base de dados
 */

public class Connect
{
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    private static final String URL = "localhost";
    private static final String SCHEMA = "MC";

    /**
     * Método que estabelece a conexão com a base de dados
     * @throws ClassNotFoundException
     */
    public static Connection connect() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://"+URL+"/"+SCHEMA+"?autoReconnect=true&useSSL=false",USERNAME,PASSWORD);
    }

    /**
     * Método que fecha a conexão com a base de dados
     * @param c    Conexão com a base de dados
     */
    public static void close(Connection c)
    {
        try { if(c!=null && !c.isClosed()) { c.close(); } } catch (Exception e) { e.printStackTrace(); }
    }
}