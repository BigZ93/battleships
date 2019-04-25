package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Scene2Controller implements Initializable{
    @FXML
    private Label msgField;

    @FXML
    private GridPane enemySea;

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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        addGridEvent();
    }

    private void addGridEvent() {
        enemySea.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.isPrimaryButtonDown()) {
                        System.out.println("PrimaryKey event");
                    }
                }
            });

        });

        enemySea.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            int x;
            int y;
            int remainderX;
            int remainderY;
            int cellX;
            int cellY;

            x = (int) e.getX();
            y = (int) e.getY();
            remainderX = x%10;
            remainderY = y%10;
            cellX = (x-remainderX)/10;
            cellY = (y-remainderY)/10;
            System.out.println(cellX + " " + cellY);
        });
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        msgField.setText("Hello World!");
    }
}
