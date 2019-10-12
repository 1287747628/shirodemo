package com.example.shirodemo.test.zookeeper.lockNative;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LockSample {

    private static final Logger logger = LoggerFactory.getLogger(LockSample.class);

    private ZooKeeper zkClient;
    private static final String LOCK_ROOT_PATH = "/locks";
    private static final String LOCK_NODE_NAME = "lock_";
    private String lockPath;

    public LockSample() throws IOException {
        zkClient = new ZooKeeper("172.16.6.111:2181", 10000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.Disconnected) {
                    logger.error("lose connection!");
                }
            }
        });
    }

    public void acquireLock() throws KeeperException, InterruptedException {
        //创建锁节点
        createLock();
        //尝试获取锁
        attemptLock();
    }

    private void createLock() throws KeeperException, InterruptedException {
        // 创建根节点
        Stat stat = zkClient.exists(LOCK_ROOT_PATH, false);
        if (stat == null) {
            zkClient.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        // 创建有序的临时节点
        String lockPath = zkClient.create(LOCK_ROOT_PATH + "/" + LOCK_NODE_NAME, Thread.currentThread().getName().getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info("{} create node: {}", Thread.currentThread().getName(), lockPath);
        this.lockPath = lockPath;
    }

    private void attemptLock() throws KeeperException, InterruptedException {
        // 获取Lock所有子节点，按照节点序号排序
        List<String> lockPaths = zkClient.getChildren(LOCK_ROOT_PATH, false);
        Collections.sort(lockPaths);
        //
        int index = lockPaths.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));
        if (index == 0) {
            logger.info("{} get lock success, lockPath: {}", Thread.currentThread().getName(), lockPath);
            return;
        } else {
            String preLockPath = lockPaths.get(index - 1);
            Stat stat = zkClient.exists(LOCK_ROOT_PATH + "/" + preLockPath, watcher);
            // 假如前一个节点不存在了，比如说执行完毕，或者执行节点掉线，重新获取锁
            if (stat == null) {
                attemptLock();
            } else {
                // 阻塞当前进程，直到preLockPath释放锁，被watcher观察到，notifyAll后，重新acquireLock
                logger.info("waiting previous lock[{}] release", preLockPath);
                synchronized (watcher) {
                    watcher.wait();
                }
                attemptLock();
            }
        }
    }

    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            logger.info("previous lock[{}] release", watchedEvent.getPath());
            synchronized (this) {
                notifyAll();
            }
        }
    };

    public void releaseLock() throws KeeperException, InterruptedException {
        zkClient.delete(lockPath, -1);
        zkClient.close();
        logger.info("release lock[{}]", lockPath);
    }

}
