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

public class Scene1bController implements Initializable{
    @FXML
    private TextField ip;

    @FXML
    private TextField port;

    private String ipAddress;
    private int portNumber;

    @FXML
    public void connect(ActionEvent event) throws IOException{
        ipAddress = ip.getText();
        portNumber = Integer.parseInt(port.getText());
        boolean joined = Main.connect(ipAddress, portNumber);
        //Main.connect(ipAddress, portNumber);
        if(joined == true) {
            changeScene1bToScene2(event);
        }
    }

    @FXML
    private void changeScene1bToScene2(ActionEvent event) throws IOException {
        Parent seaParent = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        Scene seaScene = new Scene(seaParent);
        Stage gameWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gameWindow.setScene(seaScene);
        gameWindow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}
}
