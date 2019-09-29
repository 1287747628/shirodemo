package com.example.shirodemo.service;

import java.util.Map;

public interface RedisService {

    String get(String key);

    <T> T get(String key, Class<T> cls);

    String getMap(String key, String field);

    Map<Object, Object> getMap(String key);

    boolean put(String key, Object obj);

    boolean put(String key, Object obj, int seconds);

    boolean putMap(String key, Map<String, String> map);

    boolean putMap(String key, Map<String, String> map, int seconds);

    boolean putMap(String key, String field, String value);

    boolean putMap(String key, String field, String value, int seconds);

    Long increment(String key) throws Exception;

    boolean expire(String key, int seconds) throws Exception;
}