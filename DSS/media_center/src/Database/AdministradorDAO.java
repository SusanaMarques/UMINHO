package Database;

import Business.Utilizadores.Administrador;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AdministradorDAO implements Map<String, Administrador>
{
    private Connection c;

    /**
     * Método que retorna o número de entradas na base de dados
     * @return s                       número de entradas
     * @throws NullPointerException    Não há conexão com a base de dados
     */
    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM Administrador");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                s = rs.getInt(1);
            }
        }
        catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return s;
    }

    /**
     * Método que verifica se a base de dados está vazia
     * @return  True caso a base de dados esteja vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    /**
     * Método que verifica se o id de um determinado administrador existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se o administrador existir
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public boolean containsKey(Object o) {
        boolean res = false;
        try {
            c = Connect.connect();
            String sql = "SELECT idAdmin FROM Administrador WHERE idAdmin = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, (String) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    /**
     * Método que verifica se um determinado administrador existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se o administrador existir
     */
    @Override
    public boolean containsValue(Object o) {
        boolean res = false;

        if(o.getClass().getName().equals("Business.Utilizadores.Utilizador.Administrador")){
            Administrador a = (Administrador) o;
            int id = a.getId();
            Administrador admin = this.get(id);
            if(admin.equals(a)){ res = true; }
        }
        return res;
    }

    /**
     * Método que retorna um administrador da base de dados
     * @param o    Objeto em causa
     * @return     Administrador
     */
    @Override
    public Administrador get(Object o) {
        Administrador a = new Administrador();

        try{
            c = Connect.connect();
            PreparedStatement ps = null;
            if(o instanceof String) {
                ps = c.prepareStatement("SELECT * FROM Administrador WHERE email = ?");
                ps.setString(1, (String) o);
            }
            if(o instanceof Integer) {
                ps = c.prepareStatement("SELECT * FROM Administrador WHERE idAdministrador = ?");
                ps.setInt(1, (Integer) o);
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                a.setId(rs.getInt("idAdministrador"));
                a.setNome(rs.getNString("nome"));
                a.setEmail(rs.getNString("email"));
                a.setPassword(rs.getNString("password"));
            }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return a;
    }

    /**
     * Método que insere um novo administrador na base de dados
     * @param k      id do administrador
     * @param v      administrador
     * @return
     */
    @Override
    public Administrador put(String k, Administrador v) {
        Administrador admin;

        if(this.containsKey(k)){
            admin = this.get(k);
        }
        else admin = v;
        try{
            c = Connect.connect();

            PreparedStatement ps = c.prepareStatement("INSERT INTO Administrador (idAdministrador,Nome,Email,Password) VALUES (?,?,?,?)");
            ps.setString(1,k);
            ps.setString(2,v.getNome());
            ps.setString(3,v.getEmail());
            ps.setString(3,v.getPassword());
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return admin;
    }

    @Override
    public Administrador remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    /**
     * Método que insere vários administradores na base dados
     * @param map    Map de todos os administradores
     */
    @Override
    public void putAll(Map<? extends String, ? extends Administrador> map) {
        for(Administrador admin : map.values()) { put(admin.getEmail(), admin); }
    }

    /**
     * Método que apaga todos os administradores existentes na base de dados
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public void clear() {
        try{
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Administrador");
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
    }

    /**
     * Método que retorna o conjunto de ids dos administradores da base de dados
     * @return                        Set de chaves(ids dos administradores)
     * @throws NullPointerException   Não existe conexão com a base de dados
     */
    @Override
    public Set<String> keySet() {
        Set<String> keys = null;

        try{
            c = Connect.connect();
            keys = new HashSet<>();
            PreparedStatement ps = c.prepareStatement("SELECT idAdmin FROM Administrador");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ keys.add(rs.getString(1)); }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return keys;
    }

    /**
     * Método que obtém uma coleção com todos os administradores do sistema
     * @return  coleção de todos os administradores
     */
    @Override
    public Collection<Administrador> values()
    {
        Set<Administrador> admin = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());

        for(String k : keys){admin.add(this.get(k));}
        return admin;
    }

    /**
     * Método que obtém uma set com todos os administradores do sistema
     * @return    set de administradores do sistema
     */
    @Override
    public Set<Entry<String, Administrador>> entrySet()
    {
        Set<String> keys = new HashSet<>(this.keySet());
        Map<String, Administrador> map = new HashMap<>();

        for(String k : keys){ map.put(k,this.get(k));}
        return map.entrySet();
    }
}
