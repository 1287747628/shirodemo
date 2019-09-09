package com.example.shirodemo.test.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);

        return "call success done";
    }

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<String> rst = es.submit(new CallableTest());
        es.shutdown();
        while (true) {
            if (rst.isDone()) {
                String str = rst.get();
                System.out.println(">>> " + str);
                break;
            }
        }
    }

}
