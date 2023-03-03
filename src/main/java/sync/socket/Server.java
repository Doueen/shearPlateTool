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
    private final List<ClientHandler> clients = new ArrayList<>();
    private final MessageListener listener;
    private final int port;

    public Server(int port, MessageListener listener) {
        this.listener = listener;
        this.port = port;
    }


    public void start(){
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server started,port:"+ port);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clients.add(clientHandler);
                    clientHandler.start();
                    System.out.println("Client connected, id:"+clientHandler.getClientId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"serverListenerThread").start();
    }


    public synchronized void broadcastAll(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void broadcastIgnoreById(String message,String id){
        for (ClientHandler client : clients) {
          String clientId=  client.getClientId();
            if(clientId.equals(id)){
                continue;
            }
            client.sendMessage(message);
        }
    }


    /**
     * 客户端处理器，处理连接到服务器的客户端
     */
    private class ClientHandler extends Thread {
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        // 得到连接到服务器上的客户端ip和port
        public String getClientId(){
            return socket.getLocalAddress().toString()+socket.getLocalPort();
        }

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
                System.out.println("client socket disconnect!:"+e.getMessage());
            }
        }


        public void sendMessage(String message) {
            out.println(message);
        }
    }


}