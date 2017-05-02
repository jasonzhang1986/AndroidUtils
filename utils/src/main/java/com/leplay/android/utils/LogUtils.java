package com.leplay.android.utils;


import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     author: JifengZhang
 *     time  : 2017/4/24
 *     desc  : Log相关工具类
 * </pre>
 */
public final class LogUtils {
    private static final int MAX_LOG_LENGTH = 4000;
    private static final int MAX_TAG_LENGTH = 23;

    @IntDef({Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, Log.ERROR, Log.ASSERT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    @TYPE private static int sLogFilter = Log.VERBOSE;    // log过滤器
    private static String sGlobalTag = Utils.getContext().getString(Utils.getContext().getApplicationInfo().labelRes);
    private static boolean sEnable = true;

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void setGlobalTag(String tag) {
        sGlobalTag = tag;
    }

    public static void setEnable(boolean enable) {
        sEnable = enable;
    }
    public static void setLogFilter(@TYPE int logFilter) {
        sLogFilter = logFilter;
    }


    /**
     * Formats a log message with optional arguments.
     */
    private static String formatMessage(String message, Object[] args) {
        return String.format(message, args);
    }
    private static String getStackTraceString(Throwable t) {
        // Don't replace this with Log.getStackTraceString() - it hides
        // UnknownHostException, which is not what we want.
        StringWriter sw = new StringWriter(256);
        PrintWriter pw = new PrintWriter(sw, false);
        t.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    public static void v(@NonNull String message, Object... args) {
        prepareLog(Log.VERBOSE, null, message, args);
    }
    public static void v(Throwable t, @NonNull String message, Object... args) {
        prepareLog(Log.VERBOSE, t, message, args);
    }
    public static void v(Throwable t) {
        prepareLog(Log.VERBOSE, t, null);
    }

    public static void d(@NonNull String message, Object... args) {
        prepareLog(Log.DEBUG, null, message, args);
    }
    public static void d(Throwable t, @NonNull String message, Object... args) {
        prepareLog(Log.DEBUG, t, message, args);
    }
    public static void d(Throwable t) {
        prepareLog(Log.DEBUG, t, null);
    }

    public static void i(@NonNull String message, Object... args) {
        prepareLog(Log.INFO, null, message, args);
    }
    public static void i(Throwable t, @NonNull String message, Object... args) {
        prepareLog(Log.INFO, t, message, args);
    }
    public static void i(Throwable t) {
        prepareLog(Log.INFO, t, null);
    }

    public static void w(@NonNull String message, Object... args) {
        prepareLog(Log.WARN, null, message, args);
    }
    public static void w(Throwable t, @NonNull String message, Object... args) {
        prepareLog(Log.WARN, t, message, args);
    }
    public static void w(Throwable t) {
        prepareLog(Log.WARN, t, null);
    }

    public static void e(@NonNull String message, Object... args) {
        prepareLog(Log.ERROR, null, message, args);
    }
    public static void e(Throwable t, @NonNull String message, Object... args) {
        prepareLog(Log.ERROR, t, message, args);
    }
    public static void e(Throwable t) {
        prepareLog(Log.ERROR, t, null);
    }

    public static void wtf(@NonNull String message, Object... args) {
        prepareLog(Log.ASSERT, null, message, args);
    }
    public static void wtf(Throwable t, @NonNull String message, Object... args) {
        prepareLog(Log.ASSERT, t, message, args);
    }
    public static void wtf(Throwable t) {
        prepareLog(Log.ASSERT, t, null);
    }

    private static void prepareLog(@TYPE int type, Throwable t, String message, Object... args) {
        if (!sEnable || type<sLogFilter) {
            return;
        }
        String tag = sGlobalTag;
        if (message != null && message.length() ==0) {
            message = null;
        }
        if (message == null) {
            if (t == null) {
                return;
            }
            message = getStackTraceString(t);
        } else {
            if (args.length>0) {
                message = formatMessage(message, args);
            }
            if (t != null) {
                message += "\n" + getStackTraceString(t);
            }
        }

        log(type, tag, message);
    }


    private static void log(final int type, final String tag, String msg) {
        String showTag = tag;
        if (tag.length() > MAX_TAG_LENGTH) {
            showTag = tag.substring(0, MAX_TAG_LENGTH);
        }
        if (msg.length() > MAX_LOG_LENGTH) {
            // Split by line, then ensure each line can fit into Log's maximum length.
            for (int i = 0, length = msg.length(); i < length; i++) {
                int newline = msg.indexOf('\n', i);
                newline = newline != -1 ? newline : length;
                do {
                    int end = Math.min(newline, i + MAX_LOG_LENGTH);
                    String part = msg.substring(i, end);
                    if (type==Log.ASSERT) {
                        Log.wtf(tag, part);
                    } else {
                        Log.println(type, showTag, part);
                    }
                    i = end;
                } while (i < newline);
            }
        } else {
            if (type==Log.ASSERT) {
                Log.wtf(tag,msg);
            } else {
                Log.println(type, showTag, msg);
            }
        }
    }
}