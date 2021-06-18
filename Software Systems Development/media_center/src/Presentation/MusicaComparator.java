package Presentation;
import Business.Media.Musica;
import java.util.Comparator;

public class MusicaComparator implements Comparator<Musica> {
    public int compare (Musica a1, Musica a2) {
        return a1.getNome().compareTo(a2.getNome());
    }
}
