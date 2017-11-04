package info.androidminiloggr;


import android.util.Log;

public class Logger {

    private final String tag;

    private static volatile boolean isDebuggable = BuildConfig.DEBUG;


    public static void init(boolean isDebuggableParam){
        isDebuggable =  isDebuggableParam;
    }

    public static Logger getLogger(String tag){

        return new Logger(tag);
    }

    public static Logger getLogger(){

        String tag = findClassName();
        return new Logger(tag);
    }

    private Logger(String tag) {
        this.tag = tag;
    }

    public void v(String msg) {
        Log.v(tag, msg);
    }

    public void v(String msg, Throwable tr) {
        Log.v(tag, msg, tr);
    }

    public void v(Object... messageArgs) {

            String message = createMessage(messageArgs);
            Log.v(tag, message);
    }

    public void d(String message) {
        if (isDebuggable) Log.d(tag, message);
    }

    public void d(Object... messageArgs) {

        if (isDebuggable) {
            String message = createMessage(messageArgs);
            Log.d(tag, message);
        }
    }

    public void i(String msg) {
        Log.i(tag, msg);
    }

    public void i(String msg, Throwable tr) {
        Log.i(tag, msg, tr);
    }

    public void i(Object... messageArgs) {

        String message = createMessage(messageArgs);
        Log.i(tag, message);
    }

    public void w(String msg) {
        Log.w(tag, msg);
    }

    public void w(String msg, Throwable tr) {
        Log.w(tag, msg, tr);
    }

    public void w(Throwable tr) {
        Log.w(tag, tr);
    }

    public void w(Object... messageArgs) {

        String message = createMessage(messageArgs);
        Log.w(tag, message);
    }

    public void e(String message) {
        Log.e(tag, message);
    }

    public void e(String message, Throwable tr) {
        Log.e(tag, message,tr);
    }

    public void e(Object... messageArgs) {
        String message = createMessage(messageArgs);
        Log.e(tag, message);
    }

    public void wtf(String msg) {
        Log.wtf(tag, msg);
    }

    public void wtf(String msg, Throwable tr) {
        Log.wtf(tag, msg, tr);
    }

    public void wtf(Object... messageArgs) {

        String message = createMessage(messageArgs);
        Log.wtf(tag, message);
    }

    private String createMessage(Object[] messageArgs) {
        StringBuilder message = new StringBuilder();
        for (Object messageArg : messageArgs) {
            String messagePart = castToString(messageArg);
            message.append(messagePart);
        }
        return message.toString();
    }

    private String castToString(final Object obj) {
        if(obj == null)return "null";
        if(obj instanceof char[])return String.valueOf((char[])obj);
        return obj.toString();
    }
    
    private static String findClassName(){
        String clsName = "";
        boolean rememberNextElement = false;
        for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
            if(rememberNextElement && !stackTraceElement.getClassName().equals(Logger.class.getName())){
                clsName = stackTraceElement.getClassName();
                break;
            }
            if(stackTraceElement.getClassName().equals(Logger.class.getName())){
                rememberNextElement = true;
            }
        }
        return clsName;
    }


}
