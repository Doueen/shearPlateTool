package sync.socket;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 14:16
 */

@FunctionalInterface
public interface MessageListener {
    /**
     *  �յ���Ϣ��Ļص�����
     * @param message �յ�����Ϣ
     */
    void onMessageReceived(String message);
}

