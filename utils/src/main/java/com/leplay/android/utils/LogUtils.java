package com.leplay.android.utils;


import android.support.annotation.IntDef;
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

    public static final int V = 0x01;
    public static final int D = 0x02;
    public static final int I = 0x04;
    public static final int W = 0x08;
    public static final int E = 0x10;
    public static final int WTF = 0x20;

    @IntDef({V, D, I, W, E, WTF})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    private static int sLogFilter = V;    // log过滤器
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

    public static void v(String tag, String message) {
        print(V, tag, message);
    }
    public static void v(String message) {
        v(sGlobalTag, message);
    }
    public static void v(String message, Object... args) {
        v(sGlobalTag, message, args);
    }
    public static void v(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = formatMessage(message, args);
        }
        v(tag, message);
    }
    public static void v(String tag, Throwable t) {
        v(tag, getStackTraceString(t));
    }


    public static void d(String tag, String message) {
        print(D, tag, message);
    }
    public static void d(String message) {
        d(sGlobalTag, message);
    }
    public static void d(String message, Object... args) {
        d(sGlobalTag, message, args);
    }
    public static void d(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = formatMessage(message, args);
        }
        d(tag, message);
    }
    public static void d(String tag, Throwable t) {
        d(tag, getStackTraceString(t));
    }


    public static void i(String tag, String message) {
        print(I, tag, message);
    }
    public static void i(String message) {
        i(sGlobalTag, message);
    }
    public static void i(String message, Object... args) {
        i(sGlobalTag, message, args);
    }
    public static void i(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = formatMessage(message, args);
        }
        i(tag, message);
    }
    public static void i(String tag, Throwable t) {
        i(tag, getStackTraceString(t));
    }

    public static void w(String tag, String message) {
        print(W, tag, message);
    }
    public static void w(String message) {
        w(sGlobalTag, message);
    }
    public static void w(String message, Object... args) {
        w(sGlobalTag, message, args);
    }
    public static void w(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = formatMessage(message, args);
        }
        w(tag, message);
    }
    public static void w(String tag, Throwable t) {
        w(tag, getStackTraceString(t));
    }

    public static void e(String tag, String message) {
        print(E, tag, message);
    }
    public static void e(String message) {
        e(sGlobalTag, message);
    }
    public static void e(String message, Object... args) {
        e(sGlobalTag, message, args);
    }
    public static void e(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = formatMessage(message, args);
        }
        e(tag, message);
    }
    public static void e(String tag, Throwable t) {
        e(tag, getStackTraceString(t));
    }

    public static void wtf(String tag, String message) {
        print(WTF, tag, message);
    }
    public static void wtf(String message) {
        wtf(sGlobalTag, message);
    }
    public static void wtf(String message, Object... args) {
        wtf(sGlobalTag, message, args);
    }
    public static void wtf(String tag, String message, Object... args) {
        if (args.length > 0) {
            message = formatMessage(message, args);
        }
        wtf(tag, message);
    }
    public static void wtf(String tag, Throwable t) {
        wtf(tag, getStackTraceString(t));
    }

    private static void print(final int type, final String tag, String msg) {
        if (!sEnable || type<sLogFilter) {
            return;
        }
        switch (type) {
            case V:
                Log.v(tag, msg);
                break;
            case D:
                Log.d(tag, msg);
                break;
            case I:
                Log.i(tag, msg);
                break;
            case W:
                Log.w(tag, msg);
                break;
            case E:
                Log.e(tag, msg);
                break;
            case WTF:
                Log.wtf(tag, msg);
                break;
        }
    }
}