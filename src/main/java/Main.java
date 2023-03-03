import clipboard.ClipboardManager;
import com.google.gson.Gson;
import record.RecordConfigs;
import record.core.Record;
import record.core.StringRecord;
import sync.socket.ClipboardClientManager;
import sync.socket.ClipboardServerCenter;
import sync.socket.MessageListener;
import sync.socket.Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:33
 */
public class Main {

    public static void main(String[] args)  {

        // JVM关闭自动保存
        Runtime.getRuntime().addShutdownHook(new Thread(RecordConfigs::saveRecords));
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(() -> {
            while (true) {
                RecordConfigs.addRecord(ClipboardManager.getClipboardRecord());
                TimeUnit.SECONDS.sleep(2);
            }
        });


        // 注册剪切板服务器
        try {
            ClipboardServerCenter serverCenter=new ClipboardServerCenter();
            serverCenter.registerServerCenter(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 初始化客户端
        ClipboardClientManager.init("localhost",8080);


    }


}