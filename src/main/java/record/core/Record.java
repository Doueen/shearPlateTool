package record.core;

import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-28 10:00
 */
public interface Record {

    /**
     * �õ���¼ʱ��
     * @return ��¼��ϸʱ��
     */
    LocalDateTime getRecordTimeObject();

    /**
     * ���ü�¼ʱ�䣬����ת��Ϊstring���м�¼
     * @param localDateTime ��¼ʱ��
     */
    void setRecordTimeObject(LocalDateTime localDateTime);

    /**
     *  ���ü�¼���ݣ��ַ���ֱ�����ã�����������Դ·��
     * @param data ��¼����
     */
    void setData(Object data);

    /**
     *  �õ���¼����
     * @return ��¼����
     */
    Object getData();

    /**
     * ��ʼ����¼�������洢��¼
     * @param data ��Ҫ�洢������
     * @param filePath ��¼�洢λ��
     */
    void initRecord(Object data,String filePath);

    /**
     * ���ü�¼����
     * @param recordType ��¼����
     */
    void setRecordType(RecordType recordType);

    /**
     * �õ���¼����
     * @return ��¼��������
     */
    RecordType getRecordType();
}
