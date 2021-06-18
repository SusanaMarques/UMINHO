package Presentation;

import Business.MC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class Controller_credenciais {


    private MC model;
    private View view;

    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }


    @FXML
    private void handleButtonAction_credenciais_erro (ActionEvent event) throws IOException {
        FXMLLoader l = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = l.load();
        Controller_login control = l.getController();
        control.setModell(model);
        control.setVieww(view);
        this.view.printPage((Node) event.getSource(), root);
    }

}
