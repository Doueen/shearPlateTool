package record.core;

import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-28 10:18
 */
public abstract class DefaultRecord implements Record {

    public DefaultRecord(LocalDateTime recordTime) {
        this.recordTime = recordTime.toString();
    }

    /**
     * 记录时间
     */
    protected String recordTime;

    protected RecordType recordType;

    @Override
    public RecordType getRecordType() {
        return recordType;
    }

    @Override
    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    @Override
    public LocalDateTime getRecordTimeObject() {
        return LocalDateTime.parse(recordTime);
    }

    @Override
    public void setRecordTimeObject(LocalDateTime recordTime) {
        this.recordTime = recordTime.toString();
    }

    /**
     * 设置记录数据，字符串直接设置，否则设置资源路径
     *
     * @param data 记录数据
     */
    @Override
    public abstract void setData(Object data);

    /**
     * 得到记录数据
     *
     * @return 记录数据
     */
    @Override
    public abstract Object getData();


}
