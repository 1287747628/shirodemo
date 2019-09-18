package com.example.shirodemo.test.cycliBarriar;

import com.custom.mutil.DateUtil;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

public class TestCycliBarriar {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(4);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    cb.await();
                    System.out.println(">>> step1:" + DateUtil.format(new Date()));
                    Thread.sleep(2000);
                    cb.await();
                    System.out.println(">>> step2:" + DateUtil.format(new Date()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    cb.await();
                    System.out.println(">>> step1:" + DateUtil.format(new Date()));
                    Thread.sleep(5000);
                    cb.await();
                    System.out.println(">>> step2:" + DateUtil.format(new Date()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    cb.await();
                    System.out.println(">>> step1:" + DateUtil.format(new Date()));
                    Thread.sleep(4000);
                    cb.await();
                    System.out.println(">>> step2:" + DateUtil.format(new Date()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    cb.await();
                    System.out.println(">>> step1:" + DateUtil.format(new Date()));
                    Thread.sleep(3000);
                    cb.await();
                    System.out.println(">>> step2:" + DateUtil.format(new Date()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
