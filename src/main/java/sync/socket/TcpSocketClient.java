package sync.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * @author ZhangHongzheng
 * @description socket客户端
 * @create 2022-11-01 17:12
 */
public class TcpSocketClient implements Serializable {

    /**
     * 客户端程序
     */
    public void client() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wait to connect to the server!");
        Socket socket = new Socket("192.168.97.238", 1111);
        System.out.println("Connecting to the server succeeded!");
        while (true) {
            // 给服务端发信息
            System.out.print("please enter:");
            String s = scanner.next();
            if ("out".equals(s)) {
                break;
            }
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = new byte[1024];

            // 读一下服务端发来的信息
            InputStream inputStream = socket.getInputStream();
            int read = inputStream.read(bytes);
            System.out.println("server:" + new String(bytes, 0, read, StandardCharsets.UTF_8));
        }
    }

    public static void main(String[] args) throws IOException {
        TcpSocketClient tcpSocketServer = new TcpSocketClient();
        tcpSocketServer.client();
    }
}