package socket_example;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            //-- creat a socket and connect with other socket of port --
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        try {
            //-- "out" print resp mess in "in" (BufferedReader) --
            out.println(msg);
            //-- read the message is printed by server --
            String resp = in.readLine();
            return resp;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_theCorrect() throws IOException {
        GreetClient client = new GreetClient();
        client.startConnection("127.0.0.1", 6666);
        while(true) {
            Scanner scanner = new Scanner(System.in);
           // System.out.println("enter mess:");
           // String megSend = scanner.nextLine();
            String megSend = "hello server";
            String response = client.sendMessage(megSend);
            System.out.println("client send:" + megSend);
            System.out.println("server response: "+ response);
        }
    }
}
