package exception;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 15:51
 */
public class RecordException extends RuntimeException {
    public RecordException() {
    }

    public RecordException(String message) {
        super(message);
    }
}