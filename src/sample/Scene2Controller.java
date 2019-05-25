package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Scene2Controller implements Initializable{
    @FXML
    private Label msgField;

    @FXML
    private Button go;

    @FXML
    private Button fire;

    @FXML
    private GridPane enemySea;

    @FXML
    private GridPane yourSea;

    @FXML
    private Button[][] buttons = new Button[10][10];

    @FXML
    private Label[][] labels = new Label[10][10];

    private Game game = new Game();
    private int x2;
    private int y2;
    private int result;
    private boolean shotFlag;
    private boolean end;

    //#4E49EF empty
    //#B3B0B0 filled
    //#F53232 hit
    //#FFFFFF miss

    @FXML
    public void gameOver(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("scene3.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage gameWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gameWindow.setScene(menuScene);
        gameWindow.show();
    }

    @FXML
    public void getShot() throws IOException{
        String coord = Main.listen();
        char c = coord.charAt(0);
        int x = Character.getNumericValue(c);
        c = coord.charAt(1);
        int y = Character.getNumericValue(c);
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
                showMsg("DEFEAT, click FINISH to proceed");
                go.setText("FINISH");
                go.setDisable(false);
                end=true;
                break;
        }
        Main.sendResult(result);
        if (end == false) {
            fire.setDisable(false);
        }
    }

    @FXML
    public void shoot(ActionEvent event){
        Node source = (Node) event.getSource();
        int x = GridPane.getColumnIndex(source);
        int y = GridPane.getRowIndex(source);
        System.out.println(x+" "+y);
        Button btn = (Button) event.getSource();
        Paint p = btn.getBackground().getFills().get(0).getFill();
        String c = p.toString();
        if(c.equals("0x4e49efff") && shotFlag==false) {
            x2=x;
            y2=y;
            shotFlag=true;
            msgField.setText("Click WAIT to wait for response");
            String r = "";
            try {
                Main.sendCoordinates(x, y);
                r = Main.listen();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            result = Integer.parseInt(r);
            changeColorEnemySea(x, y, result);;
            switch (result) {
                case 0:
                    showMsg("MISS");
                    break;
                case 1:
                    showMsg("HIT");
                    break;
                case 2:
                    showMsg("VICTORY, click FINISH to proceed");
                    go.setText("FINISH");
                    fire.setDisable(true);
                    go.setDisable(false);
                    end=true;
                    break;
            }
        }
    }

    @FXML
    public void waiting(ActionEvent event){ //for WAIT button
        fire.setDisable(true);
        try {
            getShot();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        shotFlag=false;
    }

    @FXML
    public void startListening(ActionEvent event){  //for START button
        if (end == false) {
            msgField.setText("waiting...");
            try {
                getShot();
            } catch (IOException e) {
                e.printStackTrace();
            }
            shotFlag = false;
            go.setDisable(true);
        }
        else {
            try {
                gameOver(event);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void showMsg(String s){
        msgField.setText(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                buttons[i][j]=new Button();
                buttons[i][j].setStyle("-fx-background-color: #4E49EF");
                buttons[i][j].setPrefWidth(28);
                buttons[i][j].setPrefHeight(28);
                enemySea.add(buttons[i][j], i, j);
                enemySea.setHalignment(buttons[i][j], HPos.CENTER);
                enemySea.setValignment(buttons[i][j], VPos.CENTER);
                buttons[i][j].setOnAction(this::shoot);
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
        if(Main.getRole()==true){
            msgField.setText("Click START");
            fire.setDisable(true);
            shotFlag=true;
        }
        else{
            msgField.setText("Take a shot");
            go.setDisable(true);
            shotFlag=false;
        }
        end=false;
    }

    @FXML
    public void changeColorEnemySea(int i, int j, int color){
        //0 - miss, 1 - hit
        switch (color) {
            case 0:
                buttons[i][j].setStyle("-fx-background-color: #FFFFFF");
                break;
            case 1:
                buttons[i][j].setStyle("-fx-background-color: #F53232");
                break;
        }
    }

    @FXML
    public void changeColorYourSea(int i, int j, int color){
        //0 - miss, 1 - hit, 2 - filled
        switch (color) {
            case 0:
                labels[i][j].setStyle("-fx-background-color: #FFFFFF");
                break;
            case 1:
                labels[i][j].setStyle("-fx-background-color: #F53232");
                break;
            case 2:
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
                    changeColorYourSea(x+j, y, 2);
                }
                else{
                    changeColorYourSea(x, y+j, 2);
                }
            }
        }
    }
}
