package sync.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 13:20
 */

public class Server {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private MessageListener listener;
    private int port;

    public Server(int port, MessageListener listener) {
        this.listener = listener;
        this.port = port;
    }

    public Server(int port){
        this.port = port;
    }

    public void start(){
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server started.");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clients.add(clientHandler);
                    clientHandler.start();
                    System.out.println("Client connected.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"serverListenerThread").start();
    }


    public synchronized void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }


    private class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    if(listener!=null) {
                        listener.onMessageReceived(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }


}

