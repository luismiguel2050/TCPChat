package dev.luismiguel2050;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader socketIn;
    private BufferedReader clientIn;


    public Client(String hostName, int port) {

        try {
            socket = new Socket(hostName, port);
            System.out.println("connection established with " + socket);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendAndReceive() {

        try {
             out = new PrintWriter(socket.getOutputStream(), true);
             socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             clientIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {

                String message = clientIn.readLine();

                out.println(message);
                if(message.equals("/exit")){
                    System.out.println("Shutting down. Bye");
                    socket.close();
                    System.exit(0);
                }
                message = socketIn.readLine();
                System.out.println(message);

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 4000);
        client.sendAndReceive();
    }
}
