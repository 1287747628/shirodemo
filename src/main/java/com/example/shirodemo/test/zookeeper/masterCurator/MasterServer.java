package com.example.shirodemo.test.zookeeper.masterCurator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MasterServer {

    private static final Logger logger = LoggerFactory.getLogger(MasterServer.class);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<CuratorFramework> zkClients = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            final int index = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework zkClient = new MasterServer().schedule(index);
                        zkClients.add(zkClient);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                }
            });
        }
        //
        boolean running = true;
        while (running) {
            int i = 0;
            for (CuratorFramework zkClient : zkClients) {
                if (zkClient.isStarted()) {
                    Thread.sleep(5000);
                    break;
                }
                i++;
            }
            if (i == 5) {
                running = false;
            }
        }
        //
        executorService.shutdownNow();
    }

    private CuratorFramework schedule(int thread) throws Exception {
        CuratorFramework zkClient = this.getZkClient();
        LeaderLatch latch = new LeaderLatch(zkClient, "/master");
        latch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                try {
                    logger.info("Thread#{} is master", thread);
                    Thread.sleep(5000);
                    if (latch != null) {
                        //释放leadership
                        //CloseMode.NOTIFY_LEADER 节点状态改变时,通知LeaderLatchListener
                        logger.info("Thread#{} Server closed", thread);
                        latch.close(LeaderLatch.CloseMode.NOTIFY_LEADER);
                    }
                    if (zkClient != null) {
                        zkClient.close();
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }
            }

            @Override
            public void notLeader() {

            }
        });
        //
        latch.start();
        return zkClient;
    }

    private CuratorFramework getZkClient() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
        CuratorFramework zkClient = CuratorFrameworkFactory.builder().connectString("172.16.6.111:2181")
                .sessionTimeoutMs(5000).connectionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        zkClient.start();
        return zkClient;
    }

}
