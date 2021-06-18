package Presentation;
import Business.Media.Video;
import java.util.Comparator;

public class VideoComparator implements Comparator<Video> {
    public int compare (Video a1, Video a2) {
        return a1.getNome().compareTo(a2.getNome());
    }
}
