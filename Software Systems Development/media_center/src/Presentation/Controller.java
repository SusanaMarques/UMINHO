package Presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Business.MC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;


public class Controller implements Initializable
{

    private MC model = new MC();
    private View view = new View();

    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }


    /**
     * método que trata do evento: clique no botão convidado na página inicial
     * @param event
     */
    @FXML
    private void handleButtonAction_Conv(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "convidado.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);

    }

    /**
     * método que trata do evento clique: no botão admin na página inicial
     * @param event
     */
    @FXML
    private void handleButtonAction_Admin(ActionEvent event) throws IOException {

        //indica que o utilizador é um administrador
        this.model.setUserT(1);
        FXMLLoader l=new FXMLLoader(getClass().getResource( "login.fxml"));
        Parent root = l.load();
        setmodel_view(l);
        this.view.printPage((Node) event.getSource(),root);


    }

    /**
     * método que trata do evento: clique no botão registado na página inicial
     * @param event
     */
    @FXML
    private void handleButtonAction_Reg(ActionEvent event) throws IOException {
        //indica que o utilizador é um registado
        this.model.setUserT(2);
        FXMLLoader l=new FXMLLoader(getClass().getResource( "login.fxml"));
        Parent root = l.load();
        setmodel_view(l);
        this.view.printPage((Node) event.getSource(),root);


    }

    //sets model e view no Controller_login
    /**
     * método que inicia o model e a view do Controller_login com o model e a view do Controller
     * set model e view do Controller_login
     * @param l
     */
    private void setmodel_view(FXMLLoader l){
        Controller_login control = l.getController();
        control.setModell(model);
        control.setVieww(view);
    }



    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb)
    {
    }

}
