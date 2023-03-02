package record;

import record.core.Record;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


/**
 * @author ZhangHongzheng
 * @description 存储记录配置文件
 * @create 2023-02-26 15:39
 */
public class RecordConfigs {
    public static final String RECORD_PATH="./clipboardRecord";
    public static final String RECORD_IMAGE_PATH="./clipboardRecord/image/";
    private static final String EXT=".record";
    /**
     * 记录多少条之后存储到硬盘
     */
    public static final int RECORD_COUNT_MAX =3;

    private static Records todayRecords;
    /**
     * 新添加记录条数
     */
    private static int recordCount=0;


    static {
        if(!Files.exists(Paths.get(RECORD_PATH))){
            try {
                Files.createDirectory(Paths.get(RECORD_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!Files.exists(Paths.get(RECORD_IMAGE_PATH))) {
            try {
                Files.createDirectory(Paths.get(RECORD_IMAGE_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public static void saveRecords(){
            Path path=Paths.get(RECORD_PATH+ File.separator+todayRecords.getRecordDate()+EXT);
        try {
            Files.write(path,todayRecords.serialize().getBytes(StandardCharsets.UTF_8));
        } catch (IOException ignore) {
        }


    }


    public static void addRecord(Record record){
        if(record==null){
            return;
        }
        if(todayRecords==null){
            todayRecords=new Records(LocalDate.now());
        }
        if (!todayRecords.getRecordDate().equals(LocalDate.now().toString())){
            todayRecords= new Records(LocalDate.now());
        }
        todayRecords.addRecord(record);
        // 添加完成写入到内存
        autoSaveByRecordCount();

    }


    private static void autoSaveByRecordCount(){
        recordCount++;
        if(recordCount>=RECORD_COUNT_MAX){
            saveRecords();
            recordCount=0;
        }
    }


}