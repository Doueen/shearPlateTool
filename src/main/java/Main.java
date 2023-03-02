import clipboard.ClipboardManager;
import com.google.gson.Gson;
import record.RecordConfigs;
import record.core.Record;
import record.core.StringRecord;
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

    public static void main(String[] args) throws Exception {
        // JVM关闭自动保存
        Runtime.getRuntime().addShutdownHook(new Thread(RecordConfigs::saveRecords));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            while (true) {
                RecordConfigs.addRecord(ClipboardManager.getClipboardText());
                TimeUnit.SECONDS.sleep(2);
            }
        });

        MessageListener listener= message -> {
            StringRecord record =new Gson().fromJson(message, StringRecord.class);
            RecordConfigs.addRecord(record);
            System.out.println(record);
        };
         Server server=new Server(1111,listener);
         server.start();



    }


}