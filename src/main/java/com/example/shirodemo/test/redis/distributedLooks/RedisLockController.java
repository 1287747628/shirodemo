package com.example.shirodemo.test.redis.distributedLooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jocken
 * @date 2021/4/26
 */
@RestController
@RequestMapping("/redis")
public class RedisLockController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisLock redisLock;

    private int count = 0;

    @RequestMapping("/testLock")
    public String testLock() throws InterruptedException {
        count = 0;
        int clientCount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        long start = System.currentTimeMillis();
        String lockKey = "lock_key";
        String lockValue = "lock_value";
        for (int i = 0; i < clientCount; i++) {
            executorService.execute(() -> {
                String l_value = lockValue + Thread.currentThread().getName();
                try {
                    if (redisLock.lock(lockKey, l_value)) {
                        count++;
                    }
                } finally {
                    redisLock.unlock(lockKey, l_value);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.info("执行线程数:{},总耗时:{},count数为:{}", clientCount, end - start, count);
        return String.valueOf(count);
    }

}
