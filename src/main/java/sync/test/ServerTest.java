package sync.test;

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
    public static void main(String[] args) {

        MessageListener listener= message -> System.out.println("接收到客户端消息:"+message);
        Server server=new Server(1111,listener);
        server.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                server.broadcastAll(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}