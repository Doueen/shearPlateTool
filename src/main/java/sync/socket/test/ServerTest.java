package sync.socket.test;

import sync.socket.MessageListener;
import sync.socket.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 14:19
 */
public class ServerTest {
    static Server server;
    public static void main(String[] args) {

        MessageListener listener=new MessageListener() {
            @Override
            public void onMessageReceived(String message) {
                System.out.println("接收到客户端消息:"+message);
            }
        };
        Server server=new Server(8080,listener);
        server.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                server.broadcast(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
