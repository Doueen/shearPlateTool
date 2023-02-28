package record;

import com.google.gson.Gson;
import record.core.Record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:34
 */
public class Records {
    private final LocalDate recordDate;
    private final List<Record> records = new ArrayList<>();


    public Records(LocalDate recordDate) {
        this.recordDate = recordDate;
    }


    public void addRecord(Record record) {
        records.add(record);
    }

    public String serialize() {
        return toJson();
    }

    private String toJson() {
        return new Gson().toJson(this);
    }
}