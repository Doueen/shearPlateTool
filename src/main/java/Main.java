import clipboard.ClipboardManager;
import record.RecordConfigs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(RecordConfigs::saveRecords,1,60, TimeUnit.SECONDS);

        while (true) {
            RecordConfigs.addRecord(ClipboardManager.getClipboardText());
            TimeUnit.SECONDS.sleep(2);
        }


    }
}