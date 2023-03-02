package record.core;

import record.core.DefaultRecord;

import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:04
 */
public class StringRecord extends DefaultRecord {

    {
        setRecordType(RecordType.TEXT);
    }

    public StringRecord(LocalDateTime recordTime) {
        super(recordTime);
    }
}