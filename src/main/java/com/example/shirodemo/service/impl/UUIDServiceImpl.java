package com.example.shirodemo.service.impl;

import com.example.shirodemo.service.RedisService;
import com.example.shirodemo.service.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class UUIDServiceImpl implements UUIDService {

    private DateFormat df = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private RedisService redisService;

    @Override
    public Long fetchDailyUUID(String key, int length, Boolean haveDay) throws Exception {
        Calendar now = new GregorianCalendar();
        String day = df.format(now.getTime());
        key = key + "_" + day;
        Long num = redisService.increment(key);
        if (num == 1) {
            redisService.expire(key, (24 - now.get(Calendar.HOUR_OF_DAY)) * 3600 + 1800);
        }
        if (haveDay) {
            return createUUID(num, day, length);
        } else {
            return num;
        }
    }

    @Override
    public Long fetchUUID(String key, int length, Boolean haveDay) throws Exception {
        Calendar now = new GregorianCalendar();
        Long num = redisService.increment(key);
        if (haveDay) {
            String day = df.format(now.getTime());
            return createUUID(num, day, length);
        }else{
            return num;
        }
    }

    private Long createUUID(Long num, String day, int length) {
        String id = String.valueOf(num);
        if (id.length() < length) {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            nf.setMaximumIntegerDigits(length);
            nf.setMinimumIntegerDigits(length);
            id = nf.format(num);
        }
        return Long.parseLong(day + id);
    }

}
