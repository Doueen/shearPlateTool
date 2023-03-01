package sync.websocket;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-01 19:53
 */
@ServerEndpoint("/echo")
public class SimpleWebSocketServer {

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText("Server Echo: " + message);
    }

}

