package com.dlg.util;

import lombok.Getter;

import java.util.concurrent.*;

public class ThreadPool {

    @Getter
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            4,
            5,
            10_000,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingQueue<>(5),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    System.out.println("threadPoolExecutor busy");
                }
            }
    );

    /**
     * 提交线程
     * @param task
     */
    public static void submit(Runnable task){
        threadPoolExecutor.submit(task);
    }

}
