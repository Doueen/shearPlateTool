package record.core;

import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-28 10:00
 */
public interface Record {

    /**
     * 得到记录时间
     * @return 记录详细时间
     */
    LocalDateTime getRecordTimeObject();

    /**
     * 设置记录时间，建议转化为string进行记录
     * @param localDateTime 记录时间
     */
    void setRecordTimeObject(LocalDateTime localDateTime);

    /**
     *  设置记录数据，字符串直接设置，否则设置资源路径
     * @param data 记录数据
     */
    void setData(Object data);

    /**
     *  得到记录数据
     * @return 记录数据
     */
    Object getData();

    /**
     * 初始化记录，单独存储记录
     * @param data 需要存储的数据
     * @param filePath 记录存储位置
     */
    void initRecord(Object data,String filePath);

    /**
     * 设置记录类型
     * @param recordType 记录类型
     */
    void setRecordType(RecordType recordType);

    /**
     * 得到记录类型
     * @return 记录数据类型
     */
    RecordType getRecordType();
}
