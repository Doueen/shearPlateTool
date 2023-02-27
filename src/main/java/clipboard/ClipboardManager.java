package clipboard;

import record.Record;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:12
 */
public class ClipboardManager {
   static  Clipboard clipboard;


    /**
     * 得到剪切板一条文字记录
     */
    public static Record<String> getClipboardText() throws IOException, UnsupportedFlavorException {
        if (clipboard==null) {
             clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        Transferable  clipboardContents=clipboard.getContents(null);
        Record<String> record= new Record<>(LocalDateTime.now());
        if(clipboardContents.isDataFlavorSupported(DataFlavor.stringFlavor)){
            String content=(String) clipboardContents.getTransferData(DataFlavor.stringFlavor);
            record.setData(content);
           // System.out.println("save :"+content);
         }
        return record;
    }
}