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

    /**
     * 记录数据或者数据的文件地址
     */
    private String data;

    public StringRecord(LocalDateTime recordTime) {
        super(recordTime);

    }


    @Override
    public Object getData() {
        return data;
    }

    /**
     * 初始化记录，单独存储记录
     *
     * @param data     需要存储的数据
     * @param filePath 记录存储位置
     */
    @Override
    public void initRecord(Object data, String filePath) {

    }


    @Override
    public void setData(Object data) {
        this.data = (String) data;
    }


}