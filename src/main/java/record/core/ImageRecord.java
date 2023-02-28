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
     * 记录图片地址
     */
    private String data;

    public ImageRecord(LocalDateTime recordTime) {
        super(recordTime);
    }

    /**
     * 设置记录数据，字符串直接设置，否则设置资源路径
     *
     * @param data 记录数据
     */
    @Override
    public void setData(Object data) {
        this.data = (String) data;
    }

    /**
     * 得到记录数据
     *
     * @return 记录数据
     */
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
        try {
            ImageIO.write((RenderedImage) data,"png",new File(filePath));
        } catch (IOException ignore) {
        }
    }


}
