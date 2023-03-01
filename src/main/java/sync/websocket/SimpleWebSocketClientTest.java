package sync.websocket;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-01 19:56
 */
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

public class SimpleWebSocketClientTest {

    @Test
    public void testClient() throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/websocket/echo";
        Session session = container.connectToServer(SimpleWebSocketClient.class, URI.create(uri));
        session.getBasicRemote().sendText("Hello Server");
        Thread.sleep(1000);
        session.close();
    }

}

