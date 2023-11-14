package exception;

public class BrowserNotSupportedException extends Exception{
    public BrowserNotSupportedException(String browserName) {
        super(String.format("Браузер %s не поддерживается",browserName));
    }


}
