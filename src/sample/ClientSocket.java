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
        //for testing java+java on local host
        String data = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while((data = in.readLine()) != null) {
            System.out.println("\r\nServer's response " + address + ": " + data);
            break;
        }
        return data;

        //for playing java+python on 2 pcs
        /*int count;
        DataInputStream in=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream out=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        byte[] buffer=new byte[4];
        String data="";
        while((count=in.read(buffer))>0){
            out.write(buffer, 0, count);
            data=new String(buffer);
            System.out.println("\r\nServer's response " + address + ": " + data);
            break;
        }
        return data;*/
    }

    public InetAddress getAddress() {
        return socket.getInetAddress();
    }

    public void disconnect() throws IOException{
        socket.close();
        System.out.println("Disconnected");
    }
}
