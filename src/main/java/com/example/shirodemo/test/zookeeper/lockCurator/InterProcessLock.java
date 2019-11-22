package com.example.shirodemo.test.zookeeper.lockCurator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterProcessLock {

    private static final Logger logger = LoggerFactory.getLogger(InterProcessLock.class);

    public static void main(String[] args) {
        CuratorFramework zkClient = getZkClient();
        String lockPath = "/locks";
        InterProcessMutex mutex = new InterProcessMutex(zkClient, lockPath);
        for (int i = 1; i < 11; i++) {
            Thread thread=new Thread(new CompeteThread(i, mutex));
            thread.setName("myThread-" + i);
            thread.start();
        }
    }

    private static CuratorFramework getZkClient() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
        CuratorFramework zkClient = CuratorFrameworkFactory.builder().connectString("172.16.6.111:2181")
                .sessionTimeoutMs(5000).connectionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        zkClient.start();
        return zkClient;
    }

    private static class CompeteThread implements Runnable {
        private Integer threadFlag;
        private InterProcessMutex lock;

        public CompeteThread(Integer threadFlag, InterProcessMutex lock) {
            this.threadFlag = threadFlag;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.acquire();
                logger.info("Thread#{} acquire lock", threadFlag);
                Thread.sleep(5000);
            } catch (Exception e) {
                logger.error("", e);
            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        }
    }

}
