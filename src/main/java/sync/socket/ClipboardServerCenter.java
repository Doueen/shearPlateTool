package sync.socket;

import com.google.gson.Gson;
import sync.socket.message.RecordMessage;

/**
 * @author ZhangHongzheng
 * @description 作为一个服务端收到将信息转发
 * @create 2023-03-03 0:47
 */
public class ClipboardServerCenter {
    private Server server;

    private final MessageListener listener = new MessageListener() {
        @Override
        public void onMessageReceived(String message) {
            RecordMessage recordMessage =RecordMessage.parseMessage(message);
            System.out.println("ClipboardServerCenter receive Message ID:"+recordMessage.getId() +" message:"+message);
            server.broadcastIgnoreById(message, recordMessage.getId());
        }
    };

    public void registerServerCenter(int port) {
        server = new Server(port, listener);
        server.start();
    }




}