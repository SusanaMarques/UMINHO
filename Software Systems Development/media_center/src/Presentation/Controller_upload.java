package Presentation;

import Business.MC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;

public class Controller_upload {

    private MC model;
    private View view;


    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }

    @FXML
    private void handleButtonAction_closepopup (ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "Utilizador_Registado.fxml"));
        Parent root = l.load();
        Controller_Regist control = l.getController();
        control.setM(model);
        control.setV(view);
        control.setText(model.getNome());
        this.view.printPage((Node) event.getSource(),root);
    }
}
