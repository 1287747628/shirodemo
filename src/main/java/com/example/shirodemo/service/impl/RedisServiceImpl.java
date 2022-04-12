package com.example.shirodemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.shirodemo.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    private volatile boolean redisStatus = true;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        ConnectionMonitor monitor = new ConnectionMonitor(logger, redisTemplate);
        monitor.start();
    }

    @Override
    public String get(String key) {
        if (!redisStatus) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, Class<T> cls) {
        if (!redisStatus) {
            return null;
        }
        String value = redisTemplate.opsForValue().get(key);
        return JSON.parseObject(value, cls);
    }

    @Override
    public Map<Object, Object> getMap(String key) {
        if (!redisStatus) {
            return null;
        }
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public String getMap(String key, String field) {
        if (!redisStatus) {
            return null;
        }
        Object obj = redisTemplate.opsForHash().get(key, field);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @Override
    public boolean put(String key, Object obj) {
        return put(key, obj, -1);
    }

    @Override
    public boolean put(String key, Object obj, int seconds) {
        try {
            if (!redisStatus) {
                return false;
            }
            redisTemplate.opsForValue().set(key, JSON.toJSONString(obj));
            if (seconds > 0) {
                redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    @Override
    public boolean putMap(String key, Map<String, String> map) {
        return putMap(key, map, -1);
    }

    @Override
    public boolean putMap(String key, Map<String, String> map, int seconds) {
        try {
            if (!redisStatus) {
                return false;
            }
            redisTemplate.opsForHash().putAll(key, map);
            if (seconds > 0) {
                redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    @Override
    public boolean putMap(String key, String field, String value) {
        return putMap(key, field, value, -1);
    }

    @Override
    public boolean putMap(String key, String field, String value, int seconds) {
        try {
            if (!redisStatus) {
                return false;
            }
            redisTemplate.opsForHash().put(key, field, value);
            if (seconds > 0) {
                redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    @Override
    public Long increment(String key) throws Exception {
        if (!redisStatus) {
            throw new RuntimeException("redis connection refuse.");
        }
        return redisTemplate.boundValueOps(key).increment(1);
    }

    @Override
    public boolean expire(String key, int seconds) {
        if (!redisStatus) {
            throw new RuntimeException("redis connection refuse.");
        }
        if (seconds > 0) {
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            return true;
        }
        throw new RuntimeException("seconds error.");
    }

    class ConnectionMonitor extends Thread {
        private Logger logger;
        private StringRedisTemplate redisTemplate;

        ConnectionMonitor(Logger logger, StringRedisTemplate redisTemplate) {
            this.logger = logger;
            this.redisTemplate = redisTemplate;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(30000L);
                    redisTemplate.hasKey("heartbeat");
                    redisStatus = true;
                    logger.info(">>> redis is running");
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        }
    }

}
