package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene1Controller implements Initializable {
    @FXML
    private TextField port;

    private int portNumber;

    @FXML
    public void changeScene1ToScene1b(ActionEvent event) throws IOException {
        Main.itsClient();
        Parent connectionParent  = FXMLLoader.load(getClass().getResource("scene1b.fxml"));
        Scene connectionScene = new Scene(connectionParent);
        Stage gameWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gameWindow.setScene(connectionScene);
        gameWindow.show();
    }

    @FXML
    public void changeScene1ToScene2(ActionEvent event) throws IOException {
        Main.itsServer();
        if(port.getText().isEmpty()==false) {
            portNumber = Integer.parseInt(port.getText());
            Main.host(portNumber);
            Parent seaParent = FXMLLoader.load(getClass().getResource("scene2.fxml"));
            Scene seaScene = new Scene(seaParent);
            Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gameWindow.setScene(seaScene);
            gameWindow.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}
}
