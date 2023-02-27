package record;

import com.google.gson.Gson;
import exception.RecordException;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:34
 */
public class Records {
    private LocalDate recordDate;
    private String headStr = "";
    private List<Record<?>> records = new ArrayList<>();

    public List<Record<?>> getRecords() {
        return records;
    }

    public void setRecords(List<Record<?>> records) {
        this.records = records;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getHeadStr() {
        return headStr;
    }

    public void setHeadStr(String headStr) {
        this.headStr = headStr;
    }

    public Records() {
    }

    public Records(LocalDate recordDate) {
        this.recordDate = recordDate;
    }


    public void addRecord(Record<String> record) {
            if (record.getData().equals(headStr)) {
                throw new RecordException("You cannot add the same record :" + headStr);
            } else {
                records.add(record);
                headStr = record.getData();
            }

    }

    public String serialize() {
      return toJson();
    }

    public String toYaml(){
        return new Yaml(new Constructor(Records.class)).dumpAs(this, Tag.MAP,null);
    }


    private String toJson(){
         return new Gson().toJson(this);
    }
}