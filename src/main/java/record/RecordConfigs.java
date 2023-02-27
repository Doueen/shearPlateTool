package record;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:39
 */
public class RecordConfigs {
    private static final String RECORD_PATH="./clipboardRecord";
    private static final String EXT=".record";
    private static final ConcurrentHashMap<LocalDate,Records> clipboardRecordsMap;


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
                    } catch (Exception ignore) {
                    }
                }
            });
        }
        else {
            Records records=new Records(LocalDate.now());

            try {
                records.addRecord(record);
            } catch (Exception ignore) {
            }
            clipboardRecordsMap.put(LocalDate.now(),records);
        }
    }
}