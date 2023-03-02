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
     * 初始化记录，单独存储记录
     *
     * @param data     需要存储的数据
     * @param filePath 记录存储位置
     */
    @Override
    public void initRecord(Object data, String filePath) {
        try {
            ImageIO.write((RenderedImage) data,"png",new File(filePath));
        } catch (IOException ignore) {
        }
    }


}