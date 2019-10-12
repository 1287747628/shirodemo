package com.example.shirodemo.test.zookeeper.nodeNative;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDemo implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && watchedEvent.getPath() == null) {
                countDownLatch.countDown();
            } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                try {
                    String value = new String(zk.getData(watchedEvent.getPath(), true, stat));
                    System.out.println("newValue:" + value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            String path = "/aaa";
            zk = new ZooKeeper("172.16.6.111:2181", 5000, new ZookeeperDemo());
            zk.addAuthInfo("digest", "appuser:appuser".getBytes());
            countDownLatch.await();
            //
            System.out.println("oldValue:" + new String(zk.getData(path, true, stat)));
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
