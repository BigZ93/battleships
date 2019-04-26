package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class Scene2Controller implements Initializable{
    @FXML
    private Label msgField;

    @FXML
    private GridPane enemySea;

    @FXML
    private GridPane yourSea;

    @FXML
    private Button msg;

    @FXML
    private Button[][] buttons = new Button[10][10];

    @FXML
    private Label[][] labels = new Label[10][10];

    //#4E49EF empty
    //#B3B0B0 filled
    //#F53232 hit
    //#FFFFFF miss
    //*********** dev *************
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage gameWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gameWindow.setScene(menuScene);
        gameWindow.show();
    }
    //*********** dev *************
    @FXML
    public void changeMsg(ActionEvent event) throws IOException {
        String s = "Hello World!";
        showMsg(s);
    }

    @FXML
    private void showMsg(String s){
        msgField.setText(s);
    }

    @FXML
    private void changeColor(ActionEvent event) throws IOException {
        buttons[5][5].setStyle("-fx-background-color: #000000");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        for(int i=5; i<10; i++){
            for(int j=5; j<10; j++){
                buttons[i][j]=new Button();
                buttons[i][j].setStyle("-fx-background-color: #4E49EF");
                buttons[i][j].setPrefWidth(28);
                buttons[i][j].setPrefHeight(28);
                enemySea.add(buttons[i][j], i, j);
                enemySea.setHalignment(buttons[i][j], HPos.CENTER);
                enemySea.setValignment(buttons[i][j], VPos.CENTER);
            }
        }

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++) {
                labels[i][j]=new Label();
                labels[i][j].setStyle("-fx-background-color: #4E49EF");
                labels[i][j].setPrefWidth(28);
                labels[i][j].setPrefHeight(28);
                yourSea.add(labels[i][j], i, j);
                yourSea.setHalignment(labels[i][j], HPos.CENTER);
                yourSea.setValignment(labels[i][j], VPos.CENTER);
            }
        }
    }
}
