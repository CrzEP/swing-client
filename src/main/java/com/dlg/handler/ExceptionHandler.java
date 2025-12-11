package com.dlg.handler;

import com.dlg.util.Pt;

/**
 * 线程异常捕获器
 *
 * 用法：
 * addExHandler 或者
 * Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
 *
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 直接打印
        Pt.error(t.getName(), e.getClass().getName());
        Pt.error(e.getMessage());
    }

    /**
     * 在线程下执行，添加线程异常捕获器
     */
    public static void addExHandler(){
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
    }

}
