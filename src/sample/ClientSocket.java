package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClientSocket {
    public Socket socket;
    public Scanner scanner;
    public InetAddress address;
    public int port;

    public ClientSocket(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
        address = serverAddress;
        port = serverPort;
    }

    public void start() throws IOException {
        while(true){
            send();
            listen();
        }
    }

    public void send() throws IOException{
        String input;
        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        out.println(input);
        out.flush();
    }

    public void listen() throws IOException{
        String data = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while((data = in.readLine()) != null) {
            System.out.println("\r\nMessage from " + address + ": " + data);
            break;
        }
    }
}
