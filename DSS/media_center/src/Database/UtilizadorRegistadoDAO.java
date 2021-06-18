package Database;

import Business.Utilizadores.UtilizadorRegistado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UtilizadorRegistadoDAO implements Map<String, UtilizadorRegistado>
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
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM UtilizadorRegistado");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                s = rs.getInt(1);
            }
        }
        catch (Exception e) { throw new NullPointerException(e.getMessage()); }
        finally { Connect.close(c); }
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
     * Método que verifica se o id de um determinado utilizador existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se o utilizador existir
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public boolean containsKey(Object o) {
        boolean res = false;
        try {
            c = Connect.connect();
            String sql = "SELECT idUtilizador FROM UtilizadorRegistado WHERE idUtilizador = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, (String) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    /**
     * Método que verifica se um determinado utilizador existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se o utilizador existir
     */
    @Override
    public boolean containsValue(Object o) {
        boolean res = false;

        if(o.getClass().getName().equals("Business.Utilizadores.UtilizadorRegistado")){
            UtilizadorRegistado u = (UtilizadorRegistado) o;
            int id = u.getId();
            UtilizadorRegistado ur = this.get(id);
            if(ur.equals(u)){ res = true; }
        }
        return res;
    }

    /**
     * Método que retorna um utilizador da base de dados
     * @param o    Objeto em causa
     * @return     Utilizador
     */
    @Override
    public UtilizadorRegistado get(Object o) {
        UtilizadorRegistado u = new UtilizadorRegistado();

        try{
            c = Connect.connect();
            PreparedStatement ps = null;
            if(o instanceof String) {
                ps = c.prepareStatement("SELECT * FROM UtilizadorRegistado WHERE email = ?");
                ps.setString(1, (String) o);
            }
            if(o instanceof Integer) {
                ps = c.prepareStatement("SELECT * FROM UtilizadorRegistado WHERE idUtilizador = ?");
                ps.setInt(1, (Integer) o);
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                u.setId(rs.getInt("idUtilizador"));
                u.setNome(rs.getNString("nome"));
                u.setEmail(rs.getNString("email"));
                u.setPassword(rs.getNString("password"));
                u.setIdBibliotecaMusica(rs.getInt("idBibliotecaMusica"));
                u.setIdBibliotecaVideo(rs.getInt("idBibliotecaVideo"));
            }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return u;
    }

    /**
     * Método que insere um utilizador na base de dados
     * @param k      id do utilizador
     * @param v      Utilizador
     * @return
     */
    @Override
    public UtilizadorRegistado put(String k, UtilizadorRegistado v) {
        UtilizadorRegistado u;

        if(this.containsKey(k)){
            u = this.get(k);
        }
        else u = v;
        try{
            c = Connect.connect();

            PreparedStatement ps = c.prepareStatement("INSERT INTO UtilizadorRegistado (idUtilizador,Nome,Email,Password, idBiblioteca) VALUES (?,?,?,?,?,?)");
            ps.setString(1,k);
            ps.setString(2,v.getNome());
            ps.setString(3,v.getEmail());
            ps.setString(3,v.getPassword());
            ps.setInt(4, v.getIdBibliotecaMusica());
            ps.setInt(5, v.getIdBibliotecaVideo());
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return u;
    }

    @Override
    public UtilizadorRegistado remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    /**
     * Método que insere vários utilizadores na base dados
     * @param map    Map de todos os utilizadores
     */
    @Override
    public void putAll(Map<? extends String, ? extends UtilizadorRegistado> map) {
        for(UtilizadorRegistado u : map.values()) { put(u.getEmail(), u); }
    }

    /**
     * Método que apaga todos os utilizadores existentes na base de dados
     * @throws NullPointerException    Não existe conexão com a base de dados
     */
    @Override
    public void clear() {
        try{
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM UtilizadorRegistado");
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); } finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
    }

    /**
     * Método que retorna o conjunto de ids dos utilizadores da base de dados
     * @return                        Set de chaves(ids dos  utilizadores)
     * @throws NullPointerException   Não existe conexão com a base de dados
     */
    @Override
    public Set<String> keySet() {
        Set<String> keys = null;

        try{
            c = Connect.connect();
            keys = new HashSet<>();
            PreparedStatement ps = c.prepareStatement("SELECT idUtilizador FROM UtilizadorRegistado");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ keys.add(rs.getString(1)); }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return keys;
    }

    /**
     * Método que obtém uma coleção com todos os utilizadores do sistema
     * @return  coleção de todos os utilizadores
     */
    @Override
    public Collection<UtilizadorRegistado> values()
    {
        Set<UtilizadorRegistado> u = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());

        for(String k : keys){u.add(this.get(k));}
        return u;
    }

    /**
     * Método que obtém uma set com todos os utilizadores do sistema
     * @return    set de utilizadores do sistema
     */
    @Override
    public Set<Entry<String, UtilizadorRegistado>> entrySet()
    {
        Set<String> keys = new HashSet<>(this.keySet());
        Map<String,UtilizadorRegistado> map = new HashMap<>();

        for(String k : keys){ map.put(k,this.get(k));}
        return map.entrySet();
    }
}
