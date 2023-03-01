package sync.websocket;

import javax.websocket.*;
import java.net.URI;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-01 19:52
 */


@ClientEndpoint
public class SimpleWebSocketClient {

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Client Received: " + message);
    }

    public static void main(String[] args) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/echo";
        container.connectToServer(SimpleWebSocketClient.class, URI.create(uri));
        Session session = container.connectToServer(SimpleWebSocketClient.class, URI.create(uri));
        session.getBasicRemote().sendText("Hello Server");
        Thread.sleep(1000);
        session.close();
    }


}
