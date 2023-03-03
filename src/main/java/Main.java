import clipboard.ClipboardManager;
import com.google.gson.Gson;
import record.RecordConfigs;
import record.core.Record;
import record.core.StringRecord;
import sync.socket.ClipboardClientManager;
import sync.socket.ClipboardServerCenter;
import sync.socket.MessageListener;
import sync.socket.Server;

import javax.sound.sampled.Port;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:33
 */
public class Main {
    private static final String SERVER = "-server";
    private static final String CLIENT = "-client";
    private static final String ALL = "-a";
    private static String ip = "localhost";
    private static Integer port = 5238;

    public static void main(String[] args) {


        // JVM关闭自动保存
        Runtime.getRuntime().addShutdownHook(new Thread(RecordConfigs::saveRecords));
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(() -> {
            while (true) {
                RecordConfigs.addRecord(ClipboardManager.getClipboardRecord());
                TimeUnit.SECONDS.sleep(2);
            }
        });

        if (args.length > 0) {
            if (SERVER.equals(args[0])) {
                port = getPara(args, 1, port);
                registerServerCenter(port);
            } else if (CLIENT.equals(args[0])) {
                ip = getPara(args, 1, ip);
                port = getPara(args, 2, port);
                registerClient(ip, port);
            } else if (ALL.equals(args[0])) {
                ip = getPara(args, 1, ip);
                port = getPara(args, 2, port);
                registerServerCenter(port);
                registerClient(ip, port);
            }
        }
    }

    private static <T> T getPara(String[] args, int index, T origin) {
        if (args.length > index) {
            if (origin instanceof Integer) {
                return (T) ((Integer) Integer.parseInt(args[index]));
            } else {
                return (T) args[index];
            }
        }
        return origin;
    }

    private static void registerServerCenter(int port) {
        ClipboardServerCenter serverCenter = new ClipboardServerCenter();
        serverCenter.registerServerCenter(port);
        System.out.println("registerServerCenter,port:"+port);
    }

    private static void registerClient(String ip, int port) {
        ClipboardClientManager.init(ip, port);
        System.out.println(String.format("registerClient ip: %s, port: %s",ip,port));
    }


}