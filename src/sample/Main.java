package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.util.Scanner;

public class Main extends Application {
    private static boolean role;



    public static Server sv;
    public static ClientSocket cl;

    //public static Connection sct;

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
        //sct = new Connection();
        Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Scanner x=new Scanner(System.in);
        String s=x.nextLine();
        if (s.equals("s")) {
            sv = new Server();
            System.out.println("\r\nRunning Server: " + "Host=" + sv.getSocketAddress().getHostAddress() + " Port=" + sv.getPort());
            sv.start();
        } else if (s.equals("c")) {
            cl = new ClientSocket(InetAddress.getByName("192.168.1.233"), 2019);
            System.out.println("\r\nConnected to Server: " + cl.socket.getInetAddress());
            cl.start();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
