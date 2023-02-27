package record;
import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:04
 */
public class Record<T> {


    /**
     * 记录时间
     */
    private  String recordTime;
    private T data;

    public Record(LocalDateTime recordTime) {
        this.recordTime=recordTime.toString();
    }


    public LocalDateTime getRecordTimeObject() {
        return LocalDateTime.parse(recordTime) ;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordTime=" + recordTime +
                ", data=" + data +
                '}';
    }
}