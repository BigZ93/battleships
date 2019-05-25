package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene3Controller implements Initializable{
    @FXML
    private void quitGame(ActionEvent event){
        try{
            Main.disconnect();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage gameWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gameWindow.setScene(menuScene);
        gameWindow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}
}
