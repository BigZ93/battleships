package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private String clientAddress;
    private Socket client;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port, 1, InetAddress.getLocalHost());
    }

    public void start() throws IOException {
        client = server.accept();
        clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
    }

    public void send(String msg) throws IOException{
        OutputStream os = client.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);
        out.println(msg);
        out.flush();
    }

    public String listen() throws IOException{
        //for testing java+java on local host
        /*String data = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while((data = in.readLine()) != null) {
            System.out.println("\r\nClient's response " + clientAddress + ": " + data);
            break;
        }
        return data;*/

        //for playing java+python on 2 pcs
        int count;
        DataInputStream in=new DataInputStream(new BufferedInputStream(client.getInputStream()));
        DataOutputStream out=new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        byte[] buffer=new byte[4];
        String data="";
        while((count=in.read(buffer))>0){
            out.write(buffer, 0, count);
            data=new String(buffer);
            System.out.println("\r\nClient's response " + clientAddress + ": " + data);
            break;
        }
        return data;
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

    public void disconnect() throws IOException{
        client.close();
        server.close();
        System.out.println("Client disconnected, server closed");
    }
}
