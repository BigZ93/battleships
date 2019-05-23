package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public ServerSocket server;
    public String clientAddress;
    public Socket client;

    public Server() throws Exception {
        this.server = new ServerSocket(2019, 1, InetAddress.getLocalHost()); //port:0
    }

    public void start() throws Exception {
        client = server.accept();
        clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
        while(true){
           listen();
           send();
        }
    }

    public void send() throws IOException{
        String msg;
        Scanner scanner = new Scanner(System.in);
        msg = scanner.nextLine();
        OutputStream os = client.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);
        out.println(msg);
        out.flush();
    }

    public void listen() throws IOException{
        String data = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while((data = in.readLine()) != null) {
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
            break;
        }
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

}
