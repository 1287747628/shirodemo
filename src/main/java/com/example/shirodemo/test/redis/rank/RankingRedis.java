package com.example.shirodemo.test.redis.rank;

import com.example.shirodemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 通过redis的sortSet实现排行榜，用于单维度排行；
 * 如果是多维度排行，设计算法得到多维度的权重分数，再对权重分数排序
 *
 * @author jocken
 * @date 2021/4/23
 */
@RestController
@RequestMapping("/redis")
public class RankingRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private RedisService redisService;

    @RequestMapping("/rank")
    private String getRank() {
        Set<String> sortList = redisTemplate.opsForZSet().range("page_rank", 0, -1);
        String sortRange = "";
        for (String sort : sortList) {
            sortRange += sort + ";";
        }
        return sortRange;
    }

}
