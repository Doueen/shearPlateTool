package sync.websocket;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-01 19:54
 */
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.glassfish.tyrus.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleWebSocketServerTest {

    private static Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server("localhost", 8080, "/websocket",null, SimpleWebSocketServer.class);
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testServer() throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/websocket/echo";
        Session session = container.connectToServer(SimpleWebSocketClient.class, URI.create(uri));
        session.getBasicRemote().sendText("Hello Server");
        Thread.sleep(1000);
        session.close();
    }

}

