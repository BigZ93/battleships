package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private String clientAddress;
    private Socket client;

    public Server() throws IOException {
        this.server = new ServerSocket(2019, 1, InetAddress.getLocalHost());
    }

    public void start() throws IOException {
        client = server.accept();
        clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
    }

    public void send(String msg) throws IOException{
        //String msg;
        //Scanner scanner = new Scanner(System.in);
        //msg = scanner.nextLine();
        OutputStream os = client.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);
        out.println(msg);
        out.flush();
    }

    public String listen() throws IOException{
        String data = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while((data = in.readLine()) != null) {
            System.out.println("\r\nClient's response " + clientAddress + ": " + data);
            break;
        }
        //int r=Integer.getInteger(data);
        return data;
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

}
