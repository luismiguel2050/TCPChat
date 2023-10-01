package dev.luismiguel2050;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket clientSocket;

    private PrintWriter out;
    private BufferedReader in;

    public Server(int port) {

        try{
            System.out.println("starting server...");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server is running on port " + port);
            System.out.println("waiting for connection...");
            clientSocket = serverSocket.accept();
            System.out.println("connection established with " + clientSocket);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void receiveAndSend() {

        while (true) {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String message = in.readLine();
                    System.out.println(message);
                    if(message.equals("/exit")){
                        System.out.println("server shutdown...");
                        System.exit(0);
                    }
                    out.println(message);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static void main(String[] args) {
        Server server = new Server(4000);
        server.receiveAndSend();
    }
}
