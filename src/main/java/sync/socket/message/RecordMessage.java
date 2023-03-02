package sync.socket.message;

import com.google.gson.Gson;
import record.core.DefaultRecord;
import record.core.Record;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-03 1:10
 */
public class RecordMessage {
    private String id;
    private DefaultRecord record;

    public RecordMessage(String id, DefaultRecord record) {
        this.id = id;
        this.record = record;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(DefaultRecord record) {
        this.record = record;
    }

   public String serialize(){
        return new Gson().toJson(this);
   }
    public static RecordMessage parseMessage(String jsonMessage) {
        return new Gson().fromJson(jsonMessage, RecordMessage.class);
    }

}