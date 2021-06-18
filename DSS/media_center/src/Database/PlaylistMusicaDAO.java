package Database;

import Business.Media.Playlist;

import java.sql.*;
import java.util.*;


public class PlaylistMusicaDAO implements Map<Integer, Playlist>
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
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM PlaylistMusica");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) { s = rs.getInt(1);
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
    public boolean isEmpty() { return (this.size() == 0); }

    /**
     * Método que verifica se o id de uma playlist existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se a playlist existir
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public boolean containsKey(Object o) {
        boolean res = false;
        try {
            c = Connect.connect();
            String sql = "SELECT idPlaylist FROM PlaylistMusica WHERE idPlaylist = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, (Integer) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override
    public boolean containsValue(Object o) { throw new UnsupportedOperationException("Not Implemented"); }

    /**
     * Método que verifica se o id de uma determinada playlist existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se a playlist existir
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public Playlist get(Object o) {

        Playlist p = new Playlist();
        ArrayList<Integer> l = new ArrayList<>();


        try{
            c = Connect.connect();
            PreparedStatement ps = null;
            ps = c.prepareStatement("SELECT * FROM PlaylistMusica WHERE idPlaylist = ?");
            ps.setInt(1, (Integer) o);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                p.setIdPlaylist(rs.getInt("idPlaylist"));
                p.setNome(rs.getNString("nomePlaylist"));
                p.setIdUser(rs.getInt("idUtilizador"));
                int intt= rs.getInt("idMusica");
                l.add(intt);

            }
            p.setLst(l);
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return p;
    }

    /**
     * Método que insere uma nova playlist na base de dados
     * @param k      id da playlist
     * @param v      playlist
     * @return
     */
    @Override
    public Playlist put(Integer k, Playlist v){
        Playlist p = new Playlist();
        ArrayList<Integer> l = new ArrayList<>();

        if(this.containsKey(k)){ p = this.get(k);}
        else p = v;
        try{
            c = Connect.connect();

            PreparedStatement psd = c.prepareStatement("DELETE FROM PlaylistMusica WHERE idPlaylist = ? ");
            psd.setInt(1, k);
            psd.executeUpdate();

            PreparedStatement ps = c.prepareStatement("INSERT INTO PlaylistMusica (idPlaylist,nomePlaylist,idUtilizador, idMusica) VALUES (?,?,?,?)");
            ArrayList<Integer> lst = v.getlst();
            for(Integer i : lst)
            {
                ps.setInt(1,k);
                ps.setString(2, v.getNome());
                ps.setInt(3, v.getUser());
                ps.setInt(4, i);
                ps.executeUpdate();
            }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return p;
    }


    @Override
    public Playlist remove(Object o) { throw new UnsupportedOperationException("Not Implemented");}

    @Override
    public void putAll(Map<? extends Integer, ? extends Playlist> map) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public void clear() { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Collection<Playlist> values() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Set<Entry<Integer, Playlist>> entrySet() {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
