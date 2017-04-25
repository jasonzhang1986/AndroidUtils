package com.leplay.android.utils;

import android.content.Context;

/**
 * <pre>
 *     author: JifengZhang
 *     email : zhangjifeng@le.com
 *     time  : 2017/04/20
 *     desc  : Utils初始化相关
 * </pre>
 */
public class Utils {
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void initialize(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
}
