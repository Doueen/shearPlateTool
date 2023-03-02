package sync.socket.test;

import sync.socket.Client;
import sync.socket.MessageListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 14:19
 */
public class ClientTest {
    public static void main(String[] args) {
        Client client = new Client(message -> System.out.println("接收到服务端的消息："+message));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                client.sendMessage(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
