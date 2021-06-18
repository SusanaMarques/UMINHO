package Presentation;


import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class View {

    public View() {
    }


    public void printPage(Node node, Parent root) {

            Scene inic_c_Scene = new Scene (root, 1000, 700);
            //this line gets stage information
            Stage window = (Stage) node.getScene().getWindow();
            window.setScene(inic_c_Scene);
            window.show();

    }


}