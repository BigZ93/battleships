package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class Main extends Application {
    private static boolean role;
    private static Server sv;
    private static ClientSocket cl;

    public static void itsServer(){
        role = true;
    }

    public static void itsClient(){
        role = false;
    }

    public static boolean getRole(){
        return role;
    }

    public static void sendCoordinates(int x, int y) throws IOException {
        String s1=Integer.toString(x);
        String s2=Integer.toString(y);
        if(role==true){
            sv.send(s1+s2);
        }
        else
        {
            cl.send(s1+s2);
        }
    }
    public static void sendResult(int r) throws IOException{
        String s=Integer.toString(r);
        if(role==true){
            sv.send(s);
        }
        else
        {
            cl.send(s);
        }
    }

    public static String listen() throws IOException{
        if(role==true){
            return sv.listen();
        }
        else
        {
            return cl.listen();
        }
    }

    public static void host(int port) throws IOException{
        sv = new Server(port);
        System.out.println("\r\nRunning Server: " + "Host=" + sv.getSocketAddress().getHostAddress() + " Port=" + sv.getPort());
        sv.start();
    }

    public static boolean connect(String ip, int port) throws IOException{
        cl = new ClientSocket(InetAddress.getByName(ip), port);
        System.out.println("\r\nConnected to Server: " + cl.getAddress());
        return true;
    }

    public static void disconnect()throws IOException{
        if(role==true){
            sv.disconnect();
        }
        else
        {
            cl.disconnect();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
