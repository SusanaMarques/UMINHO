package Presentation;
import Business.MC;
import Business.Media.Musica;
import Business.Media.Video;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.Set;

public class Controller_geral {

    private MC model;
    private View view;

    private String nomeplaylist ="Geral";


    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }

    @FXML
    private TableView<Musica> table1;


    @FXML
    private TableColumn<Musica, String> nome_m;

    @FXML
    private TableColumn<Musica, String> artista;

    @FXML
    private TableColumn<Musica, String> cat_m;

    @FXML
    private TableView<Video> table2;


    @FXML
    private TableColumn<Video, String> nome_v;

    @FXML
    private TableColumn<Video, String > realizador;

    @FXML
    private TableColumn<Video, String> cat_v;


    /**
     * método que trata do evento: clique no botão de uma música na listView do convidado
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_Musica(MouseEvent event) throws IOException {

        Musica m =table1.getSelectionModel().selectedItemProperty().getValue();

        FXMLLoader l=new FXMLLoader(getClass().getResource( "opcoes.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);


        Controller_opt pl = l.getController();
        pl.setV(view);
        pl.setM(model);
        pl.setIdd(1);
        pl.setMusica(m);
        pl.setMus(model.showMusicas());
        pl.setnomeP(nomeplaylist);

    }

    /**
     * método que trata do evento: clique no botão de um video na listView do convidado
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_Video(MouseEvent event) throws IOException {

        Video v =table2.getSelectionModel().selectedItemProperty().getValue();

        FXMLLoader l=new FXMLLoader(getClass().getResource( "opcoes.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);


        Controller_opt pl = l.getController();
        pl.setV(view);
        pl.setM(model);
        pl.setIdd(2);
        pl.setVideo(v);
        pl.setVid(model.showVideos());



    }

    /**
     * método que trata do evento: clique no botão
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_ReproduzirSequencial(ActionEvent event) throws IOException {

        Musica m = table1.getItems().get(0);
        //inicialização do player
        Media media = new Media(model.getPath('m', m.getId()));
        MediaPlayer player = new MediaPlayer(media);
        player.play();

        FXMLLoader l = new FXMLLoader(getClass().getResource("player.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(), root);


        //set model e view do Player
        Player pl = l.getController();
        pl.setModel(model);
        pl.setV(view);
        pl.setMPlayer(player);
        pl.setText(m.getNome());
        pl.setMus(model.showMusicas());
        pl.setId(m.getId());
        pl.inic();
        pl.setAl(0);
        pl.setnomeP(nomeplaylist);
    }

    /**
     * método que trata do evento: clique no botão
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_ReproduzirSequencialVid(ActionEvent event) throws IOException {

        Video v = table2.getItems().get(0);
        //inicialização do player
        Media media = new Media(model.getPath('v', v.getId()));
        MediaPlayer player = new MediaPlayer(media);
        player.play();

        FXMLLoader l = new FXMLLoader(getClass().getResource("player_video.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(), root);


        //set model e view do Player
        PLayer_video pl = l.getController();
        pl.setV(view);
        pl.setMPlayer(player);
        pl.sett();
        pl.setModel(model);
        pl.setVid(model.showVideos());
        pl.setId(v.getId());
        pl.setAl(0);
    }



    /**
     * método que trata do evento: clique no botão
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_ReproduzirAleat(ActionEvent event) throws IOException {

        Musica m = table1.getItems().get(0);
        //inicialização do player
        Media media = new Media(model.getPath('m', m.getId()));
        MediaPlayer player = new MediaPlayer(media);
        player.play();

        FXMLLoader l = new FXMLLoader(getClass().getResource("player.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(), root);


        //set model e view do Player
        Player pl = l.getController();
        pl.setModel(model);
        pl.setV(view);
        pl.setMPlayer(player);
        pl.setText(m.getNome());
        pl.setMus(model.showMusicas());
        pl.setId(m.getId());
        pl.inic();
        pl.setAl(1);
        pl.setnomeP(nomeplaylist);
    }

    /**
     * método que trata do evento: clique no botão
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_ReproduzirAleatVid(ActionEvent event) throws IOException {

        Video v = table2.getItems().get(0);
        //inicialização do player
        Media media = new Media(model.getPath('v', v.getId()));
        MediaPlayer player = new MediaPlayer(media);
        player.play();

        FXMLLoader l = new FXMLLoader(getClass().getResource("player_video.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(), root);


        //set model e view do Player
        PLayer_video pl = l.getController();
        pl.setV(view);
        pl.setMPlayer(player);
        pl.sett();
        pl.setModel(model);
        pl.setVid(model.showVideos());
        pl.setId(v.getId());
        pl.setAl(1);
    }



    /**
     * método que trata do evento: clique no botão logout na página inicial do convidado
     * @param event
     */
    @FXML
    private void handleButtonAction_goback(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "Utilizador_Registado.fxml"));
        Parent root = l.load();
        Controller_Regist pl=l.getController();
        pl.setV(view);
        pl.setM(model);
        pl.setText(model.getNome());
        this.view.printPage((Node) event.getSource(),root);

    }




    /**
     * inicializa a biblioteca geral já com a lista das músicas e vídeos
     */
    @FXML private void initialize () {

        Platform.runLater(()-> {
            Set<Musica> mus = model.showMusicas();
            Set<Video> vid = model.showVideos();

            nome_m.setCellValueFactory(new PropertyValueFactory<>("nome"));
            artista.setCellValueFactory(new PropertyValueFactory<>("artista"));
            cat_m.setCellValueFactory(new PropertyValueFactory<>("categoria"));


            nome_v.setCellValueFactory(new PropertyValueFactory<>("nome"));
            realizador.setCellValueFactory(new PropertyValueFactory<>("realizador"));
            cat_v.setCellValueFactory(new PropertyValueFactory<>("categoria"));


            ObservableList<Musica> m = FXCollections.observableArrayList();
            m.addAll(mus);
            table1.getItems().setAll(m);
            table1.getSortOrder().add(nome_m);


            ObservableList<Video> v = FXCollections.observableArrayList();
            v.addAll(vid);
            table2.getItems().setAll(v);
            table2.getSortOrder().add(nome_v);

        });
    }


}
