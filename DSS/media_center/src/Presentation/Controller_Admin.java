package Presentation;
import Business.MC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;

public class    Controller_Admin {

    private MC model;
    private View view;


    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }


    /**
     * método que trata do evento: clique no botão logout na página inicial do admin
     * @param event
     */
    @FXML
    private void handleButtonAction_logout_admin(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "mediacenter.fxml"));
        Parent root = l.load();
        model.terminarSessao();
        Controller c = l.getController();
        c.setM(model);
        c.setV(view);
        this.view.printPage((Node) event.getSource(),root);
    }


}
