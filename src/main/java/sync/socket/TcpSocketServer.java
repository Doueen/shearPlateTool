package sync.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author ZhangHongzheng
 * @Description socket服务端
 * @create 2022-11-01 17:13
 */

public class TcpSocketServer {

    /**
     * 服务端程序
     */
    public void server() throws IOException {

        Scanner scanner = new Scanner(System.in);

        // 服务端监听 1111 端口
        ServerSocket serverSocket = new ServerSocket(1111);
        System.out.println("Wait for the connection");
        Socket client = serverSocket.accept();
        System.out.println(client.getInetAddress());
        System.out.println("The connection was successful!");
        while (true) {
            // 获取客户端输入流
            InputStream inputStream = client.getInputStream();
            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            // 客户端发来的消息
            System.out.println("client: " + new String(bytes, 0, read, StandardCharsets.UTF_8));

            // 给客户端发端消息
            System.out.print("Please enter:");
            String nextLine = scanner.next();
            if ("out".equals(nextLine)) {
                break;
            }
            client.getOutputStream().write(nextLine.getBytes(StandardCharsets.UTF_8));
        }
    }
    public static void main(String[] args)  {
        TcpSocketServer tcpSocketServer = new TcpSocketServer();
        try {
            tcpSocketServer.server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
