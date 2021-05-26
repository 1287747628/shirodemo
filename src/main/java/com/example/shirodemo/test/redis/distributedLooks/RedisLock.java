package com.example.shirodemo.test.redis.distributedLooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * 通过设置key获取锁，不可重入锁
 *
 * @author jocken
 * @date 2021/4/25
 */
@Service
public class RedisLock {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final long LOCK_TIME_0UT = 3 * 1000;
    private static final long GET_LOCK_TIME_OUT = 30 * 1000;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key, String value) {
        long start = System.currentTimeMillis();
        try {
            for (; ; ) {
                Boolean rst = redisTemplate.opsForValue().setIfAbsent(key, value, LOCK_TIME_0UT, TimeUnit.MILLISECONDS);
                if (rst != null && rst) {
                    return true;
                }
                // 循环等待，直到超过最大等待时间，返回失败
                long l = System.currentTimeMillis() - start;
                if (l > GET_LOCK_TIME_OUT) {
                    return false;
                }
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     * @return
     */
    public boolean unlock(String key, String value) {
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                "   return redis.call('del',KEYS[1]) " +
                "else" +
                "   return 0 " +
                "end";
        try {
            Object rst = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(@Nullable RedisConnection conn) throws DataAccessException {
                    if (conn == null) {
                        return "0";
                    }
                    return conn.eval(script.getBytes(), ReturnType.INTEGER, 1, key.getBytes(), value.getBytes());
                }
            });
            if (rst != null && "1".equals(rst.toString())) {
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

}
