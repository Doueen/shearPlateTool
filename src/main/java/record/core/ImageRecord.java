package record.core;


import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-28 10:17
 */
public class ImageRecord extends DefaultRecord {


    {
        setRecordType(RecordType.IMAGE);
    }

    /**
     * ��¼ͼƬ��ַ
     */
    private String data;

    public ImageRecord(LocalDateTime recordTime) {
        super(recordTime);
    }

    /**
     * ���ü�¼���ݣ��ַ���ֱ�����ã�����������Դ·��
     *
     * @param data ��¼����
     */
    @Override
    public void setData(Object data) {
        this.data = (String) data;
    }

    /**
     * �õ���¼����
     *
     * @return ��¼����
     */
    @Override
    public Object getData() {
        return data;
    }

    /**
     * ��ʼ����¼�������洢��¼
     *
     * @param data     ��Ҫ�洢������
     * @param filePath ��¼�洢λ��
     */
    @Override
    public void initRecord(Object data, String filePath) {
        try {
            ImageIO.write((RenderedImage) data,"png",new File(filePath));
        } catch (IOException ignore) {
        }
    }


}
