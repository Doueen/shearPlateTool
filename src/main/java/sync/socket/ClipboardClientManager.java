package sync.socket;


import record.RecordConfigs;
import record.core.DefaultRecord;
import record.core.Record;
import sync.socket.message.RecordMessage;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-03 1:28
 */
public class ClipboardClientManager {
    private static Client client;



    private static  MessageListener listener = new MessageListener() {
        @Override
        public void onMessageReceived(String message) {
           // System.out.println("ClipboardClientManager receive message, parsec message;record message:"+ message);
            // 解析
            RecordMessage recordMessage = RecordMessage.parseMessage(message);
            //TODO 写入到剪切板

            // 添加到剪切板记录
            RecordConfigs.addRecord(recordMessage.getRecord());
        }
    };

    public static void setListener(MessageListener listener) {
        ClipboardClientManager.listener = listener;
    }

    public static void init(String ip, int port) {
        client = new Client(ip, port, listener);
    }

    public static void sendRecord(Record record) {
        // 客户端未初始化不进行任何操作
        if (client == null) {
            return;
        }
        RecordMessage message = new RecordMessage(client.getId(), (DefaultRecord) record);
        client.sendMessage(message.serialize());
    }
}