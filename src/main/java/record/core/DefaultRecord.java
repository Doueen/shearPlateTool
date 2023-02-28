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
     * ��¼ʱ��
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
     * ���ü�¼���ݣ��ַ���ֱ�����ã�����������Դ·��
     *
     * @param data ��¼����
     */
    @Override
    public abstract void setData(Object data);

    /**
     * �õ���¼����
     *
     * @return ��¼����
     */
    @Override
    public abstract Object getData();


}
