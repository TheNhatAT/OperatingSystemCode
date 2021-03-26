package socket_example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            //-- wait until a client makes a connection --
            clientSocket = serverSocket.accept();
            //-- make a print writer to print --
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            //-- make a buffer to read --
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String greeting = in.readLine();
//        if ("hello server".equals(greeting)) {
//            System.out.println("hello server");
//            out.println("hello client");
//        } else {
//            out.println("unrecognised greeting");
//        }
            while (true){
                System.out.println("client send: " + greeting);
                Scanner scanner = new Scanner(System.in);
               // System.out.println("enter mess");
                //String msgServer = scanner.nextLine();
                String msgServer = "hello client";
                out.println(msgServer);
                System.out.println("sever send: " + msgServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GreetServer server = new GreetServer();
        server.start(6666);
    }
}

