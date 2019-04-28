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
import java.util.Scanner;
import java.util.Random;

public class Scene2Controller implements Initializable{
    @FXML
    private Label msgField;

    @FXML
    private GridPane enemySea;

    @FXML
    private GridPane yourSea;

    @FXML
    private Button[][] buttons = new Button[10][10];

    @FXML
    private Label[][] labels = new Label[10][10];

    private Game game = new Game();

    //#4E49EF empty
    //#B3B0B0 filled
    //#F53232 hit
    //#FFFFFF miss

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage gameWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gameWindow.setScene(menuScene);
        gameWindow.show();
    }

    //get shot
    @FXML
    public void test1(ActionEvent event) throws IOException {
        Random r=new Random();
        int x=r.nextInt(10);
        int y=r.nextInt(10);
        int result = game.getShot(x, y);
        changeColorYourSea(x, y, result);
        switch (result){
            case 0:
                showMsg("enemy MISSED");
                break;
            case 1:
                showMsg("enemy HIT");
                break;
            case 2:
                showMsg("DEFEAT");
                break;
        }
    }

    //shoot
    @FXML
    public void test(ActionEvent event) throws IOException {
        Random r=new Random();
        int x=r.nextInt(10);
        int y=r.nextInt(10);
        int result=1;
        changeColorEnemySea(x, y, result);
        switch (result){
            case 0:
                showMsg("MISS");
                break;
            case 1:
                showMsg("HIT");
                break;
            case 2:
                showMsg("VICTORY");
                break;
        }
    }

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

        int i=5;
        int j=5;
        int c=1;
        changeColorYourSea(i, j, c);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        for(int i=2; i<10; i++){
            for(int j=2; j<10; j++){
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
        drawShips();
    }

    @FXML
    public void changeColorEnemySea(int i, int j, int color){
        //1 - hit, 2 - miss
        switch (color) {
            case 1:
                buttons[i][j].setStyle("-fx-background-color: #F53232");
                break;
            case 2:
                buttons[i][j].setStyle("-fx-background-color: #FFFFFF");
                break;
        }
    }

    @FXML
    public void changeColorYourSea(int i, int j, int color){
        //1 - hit, 2 - miss, 3 - filled
        switch (color) {
            case 1:
                labels[i][j].setStyle("-fx-background-color: #F53232");
                break;
            case 2:
                labels[i][j].setStyle("-fx-background-color: #FFFFFF");
                break;
            case 3:
                labels[i][j].setStyle("-fx-background-color: #B3B0B0");
                break;
        }
    }

    public void drawShips(){
        int x;
        int y;
        boolean dir;
        int l;
        for(int i=0; i<5; i++){
            x=game.getShipInfo(i).getX();
            y=game.getShipInfo(i).getY();
            dir=game.getShipInfo(i).getDirection();
            l=game.getShipInfo(i).getLength();
            for(int j=0; j<l; j++){
                if(dir==true){
                    changeColorYourSea(x+j, y, 3);
                }
                else{
                    changeColorYourSea(x, y+j, 3);
                }
            }
        }
    }
}
