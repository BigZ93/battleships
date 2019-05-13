package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static boolean role;
    private static Connection sct;

    public static void itsServer(){
        role = true;
    }

    public static void itsClient(){
        role = false;
    }

    public static boolean getRole(){
        return role;
    }

    public static void sendCoordinate(int x, int y){

    }
    public static void sendResult(int r){

    }

    public static int listen(){
        return 5;
    }

    public static boolean connect(String ip, int port){

        return true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        sct = new Connection();
        Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
