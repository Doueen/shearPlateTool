package clipboard;

import record.RecordConfigs;
import record.core.ImageRecord;
import record.core.Record;
import record.core.StringRecord;
import record.utils.ImageUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:12
 */
public class ClipboardManager {
    /**
     * 记录读取的上一张图片
     */
    static Image headImage;
    /**
     * 记录上一个字符串
     */
    static String headString;
    static Clipboard clipboard;


    /**
     * 得到剪切板一条记录
     */
    public static Record getClipboardText() throws IOException, UnsupportedFlavorException {
        if (clipboard == null) {
            clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        Transferable clipboardContents = clipboard.getContents(null);
        Record record = null;
        if (clipboardContents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            record = new StringRecord(LocalDateTime.now());
            String content = (String) clipboardContents.getTransferData(DataFlavor.stringFlavor);
            if(content.equals(headString)){
                return null;
            }else {
                headString=content;
            }
            record.setData(content);
        } else if (clipboardContents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            record = new ImageRecord(LocalDateTime.now());
            Image image = (Image) clipboardContents.getTransferData(DataFlavor.imageFlavor);
            if (headImage == null) {
                headImage = image;
            } else {
                if (ImageUtil.compareImages((BufferedImage) headImage, (BufferedImage) image)) {
                    return null;
                } else {
                    headImage = image;
                }
            }
            String imageFilePath = getImageName(RecordConfigs.RECORD_IMAGE_PATH + record.getRecordTimeObject());
            record.initRecord(image, imageFilePath);
            record.setData(imageFilePath);
        }
        return record;
    }

    private static String getImageName(String name) {
        return name.replace(":", "") + ".png";
    }


}