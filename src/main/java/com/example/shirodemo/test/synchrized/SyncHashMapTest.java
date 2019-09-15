package com.example.shirodemo.test.synchrized;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncHashMapTest implements Runnable {

    private static final String key = "orderKey";
    private Map<String, Integer> myMap;

    public SyncHashMapTest(Map<String, Integer> map) {
        this.myMap = map;
    }

    @Override
    public void run() {
        String threadKey = Thread.currentThread().getId() + key;
        synchronized (myMap) {
            System.out.println(">>>" + Thread.currentThread().getId());
            try {
                for (int i = 0; i < 10000; i++) {
                    Integer temp = myMap.get(threadKey);
                    if (temp == null) {
                        temp = 0;
                        myMap.put(threadKey, temp);
                    }
                    temp += 1;
                    myMap.put(threadKey, temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(">>>" + Thread.currentThread().getId());
        }
//        System.out.println(threadKey + ":" + myMap.get(threadKey));
    }

    private static class GetThreadTest implements Runnable {

        private Map<String, Integer> map = null;

        public GetThreadTest(Map<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    Set<String> keys = map.keySet();
                    for (String key : keys) {
                        System.out.println(key + ":" + map.get(key));
                    }
                    System.out.println(Thread.currentThread().getId() + "==========================================================");
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            SyncHashMapTest testRun = new SyncHashMapTest(myMap);
            threads.add(new Thread(testRun));
        }

//        SyncHashMapTest testRun = new SyncHashMapTest(myMap);
//        threads.add(new Thread(testRun));
        GetThreadTest testGet = new GetThreadTest(myMap);
        threads.add(new Thread(testGet));

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
        for (Thread t : threads) {
            fixedThreadPool.execute(t);
        }
        fixedThreadPool.shutdown();
    }

}
