package sync.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 13:22
 */
public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private MessageListener listener;

    public Client(MessageListener listener) {
        this.listener = listener;
        try {
            socket = new Socket("localhost", 8080);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to the server.");
           Receiver receiver=new Receiver();
           receiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private class Receiver extends Thread {
        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    listener.onMessageReceived(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
