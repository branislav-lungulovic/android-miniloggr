package info.androidminiloggr;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import info.androidminiloggr.examples.ExampleClass;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "AndroidManifest.xml", packageName = "info.androidminiloggr")
public class LoggerTest {

    private static final Logger logger = Logger.getLogger(LoggerTest.class.getName());

    private static final Object[] messageArguments = {"Message arguments: string: ", "string", ", int:", 1
            , ", short:", (short) 2, ", long:", (long) 3, ", float:", 4.44f, " , double:", 5.55d, ", boolean:", true, ", char:", 'A', ", char[]: ", new char[]{'A', 'B', 'C'}};

    private static final String messageArgumentString = "Message arguments: string: string, int:1, short:2, long:3, float:4.44 , double:5.55, boolean:true, char:A, char[]: ABC";

    private static final String testMessage = "This is test message";

    private static final String throwableMessage = "throw throwable";

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
    }

    @Test
    public void test_v_message() {
        logger.v(testMessage);
        assertLogged(Log.VERBOSE, LoggerTest.class.getName(), testMessage);
    }

    @Test
    public void test_v_message_throwable() {
        Throwable th = new Throwable(throwableMessage);
        logger.v(testMessage, th);
        assertLogged(Log.VERBOSE, LoggerTest.class.getName(), testMessage, th);
    }

    @Test
    public void test_v_message_arguments() {
        logger.v("Message arguments: string: ", "string", ", int:", 1, ", short:", (short) 2, ", long:", (long) 3, ", float:", 4.44f, " , double:", 5.55d, ", boolean:", true, ", char:", 'A', ", char[]: ", new char[]{'A', 'B', 'C'});
        assertLogged(Log.VERBOSE, LoggerTest.class.getName(), messageArgumentString);
    }

    @Test
    public void test_d_message() {
        logger.d(testMessage);
        assertLogged(Log.DEBUG, LoggerTest.class.getName(), testMessage);
    }


    @Test
    public void test_d_message_arguments() {
        logger.d(messageArguments);
        assertLogged(Log.DEBUG, LoggerTest.class.getName(), messageArgumentString);
    }

    @Test
    public void test_i_message() {
        logger.i(testMessage);
        assertLogged(Log.INFO, LoggerTest.class.getName(), testMessage);
    }

    @Test
    public void test_i_message_throwable() {
        Throwable th = new Throwable(throwableMessage);
        logger.i(testMessage, th);
        assertLogged(Log.INFO, LoggerTest.class.getName(), testMessage, th);
    }

    @Test
    public void test_i_message_arguments() {
        logger.i(messageArguments);
        assertLogged(Log.INFO, LoggerTest.class.getName(), messageArgumentString);
    }

    @Test
    public void test_w_message() {
        logger.w(testMessage);
        assertLogged(Log.WARN, LoggerTest.class.getName(), testMessage);
    }

    @Test
    public void test_w_message_throwable() {
        Throwable th = new Throwable(throwableMessage);
        logger.w(testMessage, th);
        assertLogged(Log.WARN, LoggerTest.class.getName(), testMessage, th);
    }

    @Test
    public void test_w_throwable() {
        Throwable th = new Throwable(throwableMessage);
        logger.w(th);
        assertLogged(Log.WARN, LoggerTest.class.getName(), th);
    }


    @Test
    public void test_w_message_arguments() {
        logger.w(messageArguments);
        assertLogged(Log.WARN, LoggerTest.class.getName(), messageArgumentString);
    }

    @Test
    public void test_e_message() {
        logger.e(testMessage);
        assertLogged(Log.ERROR, LoggerTest.class.getName(), testMessage);
    }

    @Test
    public void test_e_message_throwable() {
        Throwable th = new Throwable(throwableMessage);
        logger.e(testMessage, th);
        assertLogged(Log.ERROR, LoggerTest.class.getName(), testMessage, th);
    }

    @Test
    public void test_e_message_arguments() {
        logger.e(messageArguments);
        assertLogged(Log.ERROR, LoggerTest.class.getName(), messageArgumentString);
    }

    @Test
    public void test_wtf_message() {
        logger.wtf(testMessage);
        assertLogged(Log.ASSERT,LoggerTest.class.getName(), testMessage);
    }

    @Test
    public void test_wtf_message_throwable() {
        Throwable th = new Throwable(throwableMessage);
        logger.wtf(testMessage, th);
        assertLogged(Log.ASSERT,LoggerTest.class.getName(), testMessage, th);
    }

    @Test
    public void test_wtf_message_arguments() {
        logger.wtf(messageArguments);
        assertLogged(Log.ASSERT,LoggerTest.class.getName(), messageArgumentString);
    }

    @Test
    public void test_debug_is_off() {
        Logger.init(false);
        logger.d(testMessage);
        assertTrue(ShadowLog.getLogs().isEmpty());
        Logger.init(true);
    }

    @Test
    public void test_logger_without_tag() {
        Logger localLogger = Logger.getLogger();
        localLogger.d(testMessage);
        assertLogged(Log.DEBUG,LoggerTest.class.getName(), testMessage);

    }
    @Test
    public void test_example_class() {
        ExampleClass ec = new ExampleClass();
        ec.exampleSimpleDebug();
        ec.exampleDebugWithArrayOfArguments();
        assertLogged(Log.DEBUG,ExampleClass.class.getName(), "This is debug message");

    }

    private void assertLogged(int type, String tag, String msg) {
        ShadowLog.LogItem lastLog = ShadowLog.getLogs().get(0);
        if (lastLog != null) {
            assertEquals(type, lastLog.type);
            if (lastLog.msg != null) assertTrue(lastLog.msg.contains(msg));
            if (lastLog.tag != null) assertEquals(tag, lastLog.tag);
        }
    }

    private void assertLogged(int type, String tag, String msg, Throwable thr) {
        ShadowLog.LogItem lastLog = ShadowLog.getLogs().get(0);
        if (lastLog != null) {
            assertEquals(type, lastLog.type);
            if (lastLog.msg != null) assertTrue(lastLog.msg.contains(msg));
            if (lastLog.tag != null) assertEquals(tag, lastLog.tag);
            assertTrue(lastLog.throwable.toString().contains(thr.toString()));
        }
    }

    private void assertLogged(int type, String tag, Throwable th) {

        ShadowLog.LogItem lastLog = ShadowLog.getLogs().get(0);
        if (lastLog != null) {
            assertEquals(type, lastLog.type);
            if (lastLog.tag != null) assertEquals(tag, lastLog.tag);
            assertTrue(lastLog.throwable.toString().contains(th.toString()));
        }
    }

}
