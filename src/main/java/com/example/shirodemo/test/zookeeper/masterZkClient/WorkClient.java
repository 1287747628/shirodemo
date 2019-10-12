package com.example.shirodemo.test.zookeeper.masterZkClient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkClient {

    private static final Logger logger = LoggerFactory.getLogger(WorkClient.class);

    private ZkClient zkClient;
    private IZkDataListener zkDataListener;
    private String MASTER_PATH = "/masterZkClient";
    private String clientName;
    private boolean isRunning;
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    public WorkClient(ZkClient zkClient, String clientName) {
        this.zkClient = zkClient;
        this.clientName = clientName;

        zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                // 删除时 5秒后重新选举
                executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        takeMaster();
                    }
                }, 5, TimeUnit.SECONDS);
            }
        };
    }

    private boolean takeMaster() {
        try {
            logger.info("{} compete masterZkClient", clientName);
            zkClient.create(MASTER_PATH, clientName, CreateMode.EPHEMERAL);
            logger.info("{} compete masterZkClient success", clientName);

            // 5秒后释放master
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    releaseMaster();
                }
            }, 5, TimeUnit.SECONDS);
            return true;
        } catch (ZkNodeExistsException ex) {
            // 若节点已存在 则已经被选举
            return false;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    private void releaseMaster() {
        zkClient.delete(MASTER_PATH);
    }

    public void start() {
        if (isRunning) {
            throw new RuntimeException(clientName + " server has running");
        }
        isRunning = true;
        //
        logger.info("{} server start",clientName);
        takeMaster();
        zkClient.subscribeDataChanges(MASTER_PATH, zkDataListener);
    }

    public void stop() {
        if (!isRunning) {
            throw new RuntimeException(clientName + " server has stop");
        }
        isRunning = false;
        //
        logger.info("{} server stop",clientName);
        zkClient.unsubscribeDataChanges(MASTER_PATH, zkDataListener);
        zkClient.close();
        executorService.shutdownNow();
    }

}
