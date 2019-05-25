package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClientSocket {
    private Socket socket;
    private Scanner scanner;
    private InetAddress address;
    private int port;

    public ClientSocket(InetAddress serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
        address = serverAddress;
        port = serverPort;
    }

    public void send(String msg) throws IOException{
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        out.println(msg);
        out.flush();
    }

    public String listen() throws IOException{
        String data = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while((data = in.readLine()) != null) {
            System.out.println("\r\nServer's response " + address + ": " + data);
            break;
        }
        return data;
    }

    public InetAddress getAddress() {
        return socket.getInetAddress();
    }

    public void disconnect() throws IOException{
        socket.close();
        System.out.println("Disconnected");
    }
}
