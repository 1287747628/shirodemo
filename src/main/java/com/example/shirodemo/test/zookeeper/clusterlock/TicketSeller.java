package com.example.shirodemo.test.zookeeper.clusterlock;

import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TicketSeller {

    private static final Logger logger = LoggerFactory.getLogger(TicketSeller.class);

    public void sell(){
        logger.info("sell start");
        try {
            int sleepMillis = (int) (Math.random() * 2000);
            Thread.sleep(sleepMillis);
        } catch (Exception e) {
            logger.error("", e);
        }
        logger.info("sell end");
    }


    public void sellTicketWithLock() throws KeeperException, InterruptedException, IOException {
        LockSample lock = new LockSample();
        lock.acquireLock();
        sell();
        lock.releaseLock();
    }

    public static void main(String[] args) {
        try {
            TicketSeller ticketSeller = new TicketSeller();
            for(int i=0;i<100;i++) {
                ticketSeller.sellTicketWithLock();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

}
