package com.example.shirodemo.test.concurrent;

import com.example.shirodemo.test.designmode.single.SingleDemo;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author jocken
 * @date 2020/7/23
 */
public class ThreadPoolTest {

    private static final int THREAD_NUM = 100;

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("SingleDemo-%d").build();
        ExecutorService threadPool = new ThreadPoolExecutor(10, 100, 60L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024), threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        //
        for (int i = 0; i < THREAD_NUM; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " :" + SingleDemo.getInstance());
            });
        }
        threadPool.shutdown();
    }

}
