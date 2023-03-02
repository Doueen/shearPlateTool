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


    public ImageRecord(LocalDateTime recordTime) {
        super(recordTime);
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