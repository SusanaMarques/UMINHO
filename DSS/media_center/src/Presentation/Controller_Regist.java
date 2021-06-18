package Presentation;
import Business.*;
import Business.Exceptions.ConteudoDuplicadoException;
import Business.Exceptions.FormatoDesconhecidoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class Controller_Regist {

    private MC model;
    private View view;

    @FXML
    private Text username;


    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }

    public void setText(String a){
        this.username.setText(a);
    }


    /**
     * método que trata do evento: clique no botão de uma música
     * este método inicia o player
     * @param event
     */

    @FXML
    private void handleButtonAction_Pessoal(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "pessoal.fxml"));
        Parent root = l.load();
        Controller_pessoal pl = l.getController();
        pl.setM(model);
        pl.setV(view);

        this.view.printPage((Node) event.getSource(),root);



    }

    @FXML
    private void handleButtonAction_Geral(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "geral.fxml"));
        Parent root = l.load();
        Controller_geral pl = l.getController();
        pl.setM(model);
        pl.setV(view);
        this.view.printPage((Node) event.getSource(),root);
    }



    /**
     * método que trata do evento: upload de conteúdo
     * @param event
     */
    @FXML
    private void handleButtonAction_upload(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FileChooser fileChooser;
        fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        try {
            this.model.uploadConteudo(file.toURI().toURL().toExternalForm());
            FXMLLoader l = new FXMLLoader(getClass().getResource("Upload_Sucesso.fxml"));
            Parent root = l.load();
            Controller_upload up = l.getController();
            up.setM(this.model);
            up.setV(view);
            this.view.printPage((Node) event.getSource(), root);
        }
        catch (FormatoDesconhecidoException e){
            FXMLLoader l=new FXMLLoader(getClass().getResource( "Formato_Desconhecido.fxml"));
            Parent root = l.load();
            Controller_upload up = l.getController();
            up.setM(this.model);
            up.setV(view);
            this.view.printPage((Node) event.getSource(), root);

        }
        catch (ConteudoDuplicadoException e) {
            FXMLLoader l=new FXMLLoader(getClass().getResource( "Conteudo_Existente.fxml"));
            Parent root = l.load();
            Controller_upload up = l.getController();
            up.setM(this.model);
            up.setV(view);
            this.view.printPage((Node) event.getSource(), root);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }



    /**
     * método que trata do evento: clique no botão logout na página inicial do registado
     * @param event
     */
    @FXML
    @SuppressWarnings("Duplicates")
    private void handleButtonAction_logout_registado(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "mediacenter.fxml"));
        Parent root = l.load();
        model.terminarSessao();
        Controller c = l.getController();
        c.setM(model);
        c.setV(view);
        this.view.printPage((Node) event.getSource(),root);
    }

    @FXML private void initialize () {

    }


}
