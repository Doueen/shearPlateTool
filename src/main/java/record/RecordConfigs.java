package record;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:39
 */
public class RecordConfigs {
    private static final String RECORD_PATH="./clipboardRecord";
    private static final String EXT=".record";
    /**
     * 记录多少条之后存储到硬盘
     */
    private static final int RECORD_COUNT_MAX =5;
    /**
     * 自动保存时间间隔
     */
    private static final int AUTO_SAVE_INTERVALS=5;
    private static  ConcurrentHashMap<LocalDate,Records> clipboardRecordsMap;
    /**
     * 新添加记录条数
     */
    private static int recordCount=0;


    static {
        clipboardRecordsMap=new ConcurrentHashMap<>();
        if(!Files.exists(Paths.get(RECORD_PATH))){
            try {
                Files.createDirectory(Paths.get(RECORD_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public static void saveRecords(){
      //  System.out.println("saveRecords....");
        clipboardRecordsMap.forEach((k,v)->{
            Path path=Paths.get(RECORD_PATH+ File.separator+k.toString()+EXT);
            try {
                Files.write(path,v.serialize().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }


    public static void addRecord(Record<String > record){
        if (clipboardRecordsMap.entrySet().stream().anyMatch(item-> record.getRecordTimeObject().toLocalDate().compareTo(item.getKey())==0)) {
            clipboardRecordsMap.forEach((k,v)->{
                if(k.compareTo(record.getRecordTimeObject().toLocalDate())==0){
                    try {
                        v.addRecord(record);
                        recordCount++;
                    } catch (Exception ignore) {
                    }
                }
            });
        }
        else {
            Records records=new Records(LocalDate.now());
            try {
                records.addRecord(record);
                recordCount++;
            } catch (Exception ignore) {
            }
            clipboardRecordsMap.put(LocalDate.now(),records);
        }
        autoSaveByRecordCount();
    }

    private static void autoSaveByRecordCount(){
        if(recordCount>=RECORD_COUNT_MAX){
            saveRecords();
            recordCount=0;
        }
    }

    private static void autoSaveRecordsByTime(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(RecordConfigs::saveRecords,1,AUTO_SAVE_INTERVALS, TimeUnit.SECONDS);
    }
}