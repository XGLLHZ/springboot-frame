package org.huangzi.main.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: XGLLHZ
 * @date: 2019/11/7 15:29
 * @description: redis 工具类
 */
@Component
public class RedisUtil {

    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断 key 是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除 key
     * @param key 可传一个，也可传多个
     */
    public void deleteKey(String... key) {
        if (key != null && key.length > 0) {
            redisTemplate.delete(Arrays.asList(key));
        }
    }

    /**
     * 设置缓存过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean setTime(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存过期时间
     * @param key
     * @return
     */
    public long getTime(String key) {
        return key == null ? null : redisTemplate.getExpire(key);
    }

    /**
     * 存入
     * @param key
     * @param object
     * @return
     */
    public boolean setValue(String key, Object object) {
        try {
            redisTemplate.opsForValue().set(key, object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 存入并设置过期时间
     * @param key
     * @param object
     * @param time 秒，若 time > 0，则为无限期
     * @return
     */
    public boolean setValue(String key, Object object, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, object, time,  TimeUnit.SECONDS);
            } else {
                setValue(key, object);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入 list
     * @param key
     * @param value
     * @return
     */
    public boolean setListValue(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取 list
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> getListVaule(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 存入 list 并设置时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean setListValue(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                setTime(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 模糊匹配 key
     * @param key
     * @return
     */
    public List<String> getListKey(String key) {
        //match()：此方法，若参数为 "key"，在会匹配到所有以 "key" 开头的数据
        ScanOptions scanOptions = ScanOptions.scanOptions().match(key).build();
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = Objects.requireNonNull(factory).getConnection();
        Cursor<byte[]> cursor = redisConnection.scan(scanOptions);
        List<String> list = new ArrayList<>();
        while (cursor.hasNext()) {
            list.add(new String(cursor.next()));
        }
        RedisConnectionUtils.releaseConnection(redisConnection, factory);
        return list;
    }

}

