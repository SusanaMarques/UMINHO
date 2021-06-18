package Presentation;
import Business.MC;
import Business.Media.Video;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class PLayer_video {

    private MC model;
    private View view;
    private MediaPlayer player;
    private int id;
    private int aleatorio; //sequencial=0;
    private Set<Video> va;

    List<Video> ml= new ArrayList<>();

    @FXML
    private Slider time;

    @FXML
    private Slider vol;

    @FXML
    private MediaView v;


    public void setM(MC m) {
        this.model = m;

    }

    public void setV(View v) {
        this.view = v;


    }

    public void setId(int x){
            this.id=x;
    }

    public void setModel(MC m){
            this.model=m;
    }

    public void setAl(int x){
        this.aleatorio=x;
    }

    public void setVid(Set<Video> vid){

        va=vid;

        for(Video m2: vid) {
            ml.add(m2);
        }
        ml.sort( new VideoComparator());

    }


    public void setMPlayer(MediaPlayer pl) {
        this.player = pl;
        player.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                updatesValues();
            }
        });

    }



    public void sett() {
        v.setMediaPlayer(player);
    }



    @FXML
    @SuppressWarnings("Duplicates")
    private void handleButtonAction_buttonreproduz(ActionEvent e) {
        Button b = (Button) e.getSource();
        MediaPlayer.Status status = player.getStatus(); //get status of player

        if (status == MediaPlayer.Status.PLAYING) {
            //If the status is Video playing
            player.pause();
        }

        if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
            player.play(); //Start
        }


    }


    @FXML
    @SuppressWarnings("Duplicates")
    private void handleButtonAction_proxima (ActionEvent e) throws IOException {
        player.pause();
        int i=0;
        Video v;

        for(; i<ml.size();i++){
            if(ml.get(i).getId()==id) break;
        }

        if((i % ml.size()) + 1 != ml.size()&& aleatorio==0) {
            v = ml.get((i % ml.size()) + 1);


            //inicialização do player
            Media media = new Media(model.getPath('v', v.getId()));
            MediaPlayer player = new MediaPlayer(media);
            player.play();

            FXMLLoader l = new FXMLLoader(getClass().getResource("player_video.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) e.getSource(), root);


            //set model e view do Player_video
            PLayer_video pl = l.getController();
            pl.setV(view);
            pl.setMPlayer(player);
            pl.sett();
            pl.setModel(model);
            pl.setVid(va);
            pl.setId(v.getId());
            pl.setAl(0);

        }

        else if(aleatorio==1){
            Random r = new Random(System.currentTimeMillis());
            v = ml.get(r.nextInt(ml.size()));


            //inicialização do player
            Media media = new Media(model.getPath('v', v.getId()));
            MediaPlayer player = new MediaPlayer(media);
            player.play();

            FXMLLoader l = new FXMLLoader(getClass().getResource("player_video.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) e.getSource(), root);


            //set model e view do Player_video
            PLayer_video pl = l.getController();
            pl.setV(view);
            pl.setMPlayer(player);
            pl.sett();
            pl.setModel(model);
            pl.setVid(va);
            pl.setId(v.getId());
            pl.setAl(1);
        }

        else {
            goback(e);
        }

    }

    @FXML
    @SuppressWarnings("Duplicates")
    private void handleButtonAction_anterior (ActionEvent e) throws IOException {
        player.pause();
        int i=0;
        Video v;

        for(; i<ml.size();i++){
            if(ml.get(i).getId()==id) break;
        }

        if((i % ml.size()) - 1 != -1 && aleatorio==0) {
            v = ml.get((i % ml.size()) - 1);


            //inicialização do player
            Media media = new Media(model.getPath('v', v.getId()));
            MediaPlayer player = new MediaPlayer(media);
            player.play();

            FXMLLoader l = new FXMLLoader(getClass().getResource("player_video.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) e.getSource(), root);


            //set model e view do Player_video
            PLayer_video pl = l.getController();
            pl.setV(view);
            pl.setMPlayer(player);
            pl.sett();
            pl.setModel(model);
            pl.setVid(va);
            pl.setId(v.getId());
        }

        else if(aleatorio==1) {
            Random r = new Random(System.currentTimeMillis());
            v = ml.get(r.nextInt(ml.size()));


            //inicialização do player
            Media media = new Media(model.getPath('v', v.getId()));
            MediaPlayer player = new MediaPlayer(media);
            player.play();

            FXMLLoader l = new FXMLLoader(getClass().getResource("player_video.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) e.getSource(), root);


            //set model e view do Player_video
            PLayer_video pl = l.getController();
            pl.setV(view);
            pl.setMPlayer(player);
            pl.sett();
            pl.setModel(model);
            pl.setVid(va);
            pl.setId(v.getId());
            pl.setAl(1);
        }
        else player.pause();

    }



    @FXML
    //Providing functionality to time slider
    private void handleButtonAction_timeslider(MouseEvent e) {
        player.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                updatesValues();
            }
        });
        player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));

    }

    // providing functionality to volume slider
    @FXML
    private void handleButtonAction_volume(MouseEvent e) {
        player.setVolume(vol.getValue() / 100); // It would set the volume

    }

    @FXML
    private void handleButton_soundoff(ActionEvent e) {
        if(player.getVolume()==0) {
            player.setVolume(vol.getValue() / 100);
        }
        else {
            player.setVolume(0);
        }

    }

    private void updatesValues() {
        Platform.runLater(new Runnable() {
            public void run() {
                time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
            }
        });
    }


    /**
     * método que trata do evento: clique no botão "retroceder" na página do player
     *
     * @param event
     */
    @FXML
    private void handleButtonAction_goback_player(ActionEvent event) throws IOException {
       goback(event);
    }

    @SuppressWarnings("Duplicates")
    private void goback(ActionEvent event) throws IOException {
        MediaPlayer.Status status = player.getStatus(); //get status of player
        if (status == MediaPlayer.Status.PLAYING) {
            //If the status is Video playing
            player.pause();
        }

        if (this.model.getUserT()==2) {
            FXMLLoader l = new FXMLLoader(getClass().getResource("Utilizador_Registado.fxml"));
            Parent root = l.load();

            // set model e view do Controller_Reg
            Controller_Regist control = l.getController();
            control.setM(model);
            control.setV(view);
            control.setText(model.getNome());
            this.view.printPage((Node) event.getSource(), root);
        }
        else {
            //retorna página inicial do convidado: não necessitamos do admin uma vez que este nao acessa o player
            FXMLLoader l = new FXMLLoader(getClass().getResource("convidado.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) event.getSource(), root);
        }

    }




    @FXML private void initialize () {

    }


}




