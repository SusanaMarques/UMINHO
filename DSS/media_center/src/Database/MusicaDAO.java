package Database;

import Business.Media.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MusicaDAO implements Map<Integer, Musica> {
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
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM Musica");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) { s = rs.getInt(1); }
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
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
     * Método que verifica se o id de uma musica existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se a musica existir
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public boolean containsKey(Object o) {
        boolean res = false;
        try {
            c = Connect.connect();
            String sql = "SELECT idMusica FROM Musica WHERE idMusica = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, (Integer) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    /**
     * Método que verifica se uma determinada musica existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se a musica existir
     */
    @Override
    public boolean containsValue(Object o) {
        boolean res = false;
        Musica m = (Musica) o;
        try {
            c = Connect.connect();
            String sql = "SELECT count(*) FROM Musica WHERE nome = ? AND duracao = ? AND formato = ? AND categoria = ? AND artista = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, m.getNome());
            stm.setDouble(2, m.getDuracao());
            stm.setString(3, m.getFormato());
            stm.setString(4, m.getCategoria());
            stm.setString(5, m.getArtista());
            ResultSet rs = stm.executeQuery();
            rs.next();
            if((rs.getInt(1)) > 0) res = true;
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    /**
     * Método que retorna uma musica da base de dados
     * @param o    objeto em causa
     * @return     musica
     */
    @Override
    public Musica get(Object o) {
        Musica m = new Musica();

        try {
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Musica WHERE idMusica = ?");
            ps.setInt(1, (Integer) o);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setId(rs.getInt("idMusica"));
                m.setNome(rs.getNString("nome"));
                m.setDuracao(rs.getDouble("duracao"));
                m.setFormato(rs.getNString("formato"));
                m.setCategoria(rs.getNString("categoria"));
                m.setArtista(rs.getNString("artista"));
            }
        } catch (Exception e) { System.out.printf(e.getMessage()); } finally { try { Connect.close(c); } catch (Exception e) { System.out.printf(e.getMessage()); } }
        return m;
    }

    /**
     * Método que insere uma nova música na base de dados
     * @param k      id da musica
     * @param v      musica
     * @return
     */
    @Override
    public Musica put(Integer k, Musica v) {
        Musica m;

        if(this.containsKey(k)){ m = this.get(k); }
        else m = v;
        try{
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("INSERT INTO Musica(idMusica,Nome,Duracao,Formato, Categoria,Artista) VALUES (?,?,?,?,?,?)");
            ps.setInt(1,k);
            ps.setString(2,v.getNome());
            ps.setDouble(3,v.getDuracao());
            ps.setString(4,v.getFormato());
            ps.setString(5, v.getCategoria());
            ps.setString(6, v.getArtista());
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); } finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return m;
    }

    @Override
    public Musica remove(Object o) {
        throw new UnsupportedOperationException("Erro!");
    }

    /**
     * Método que insere várias musicas na base dados
     * @param map    Map de todas as musicas
     */
    @Override
    public void putAll(Map<? extends Integer, ? extends Musica> map) {
        for (Musica u : map.values()) {
            put(u.getId(), u);
        }
    }

    /**
     * Método que apaga todas as musicas existentes na base de dados
     * @throws NullPointerException   Não existe conexão com a base de dados
     */
    @Override
    public void clear() {
        try {
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Musica");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            try { Connect.close(c); } catch (Exception e) { System.out.printf(e.getMessage()); } }
    }

    /**
     * Método que retorna o conjunto de ids das musicas da base de dados
     * @return                        Set de chaves(ids das musicas )
     * @throws NullPointerException   Não existe conexão com a base de dados
     */
    @Override
    public Set<Integer> keySet() {
        Set<Integer> keys = null;

        try {
            c = Connect.connect();
            keys = new HashSet<>();
            PreparedStatement ps = c.prepareStatement("SELECT idMusica FROM Musica");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                keys.add(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            try { Connect.close(c); } catch (Exception e) { System.out.printf(e.getMessage()); } }
        return keys;
    }

    /**
     * Método que obtém uma coleção com todas as musicas do sistema
     * @return  coleção de todas as musicas existentes na base de dados
     */
    @Override
    public Collection<Musica> values() {
        Set<Musica> u = new HashSet<>();
        Set<Integer> keys = new HashSet<>(this.keySet());

        for (Integer k : keys) { u.add(this.get(k)); }
        return u;
    }

    /**
     * Método que obtém uma set com todas as musicas do sistema
     * @return    set de musicas existentes na base de dados
     */
    @Override
    public Set<Entry<Integer, Musica>> entrySet() {
        Set<Integer> keys = new HashSet<>(this.keySet());
        Map<Integer, Musica> map = new HashMap<>();

        for (Integer k : keys) { map.put(k, this.get(k)); }
        return map.entrySet();
    }
}