package sync.socket;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 14:16
 */

@FunctionalInterface
public interface MessageListener {
    /**
     *  收到消息后的回调方法
     * @param message 收到的消息
     */
    void onMessageReceived(String message);
}

