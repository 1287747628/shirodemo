package com.example.shirodemo.test.designmode.single;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 单例模式
 *
 * @author jocken
 * @date 2020-01-16
 */
public class SingleDemo {

    private static final int THREAD_NUM = 100;

    private SingleDemo() {

    }

    private static class SingleDemoHolder {
        private static final SingleDemo INSTANCE = new SingleDemo();
    }

    public static SingleDemo getInstance() {
        return SingleDemoHolder.INSTANCE;
    }

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("SingleDemo-%d").build();
        ExecutorService threadPool = new ThreadPoolExecutor(10, 100, 60L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        //
        for (int i = 0; i < THREAD_NUM; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " :" + SingleDemo.getInstance());
            });
        }
        threadPool.shutdown();
    }

}
