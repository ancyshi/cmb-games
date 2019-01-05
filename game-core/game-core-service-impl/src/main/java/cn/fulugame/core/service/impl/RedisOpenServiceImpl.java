package cn.fulugame.core.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * redis操作封装类
 */
@Slf4j
@Service
public class RedisOpenServiceImpl {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Getter
    @Autowired
    private ObjectMapper om;

    /**
     * 写入缓存，永不过期
     *
     * @param key       缓存键
     * @param value     缓存值
     */
    public void set(String key, Object value) {
        String s = null;
        try {
            s = om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(key, s);
    }

    /**
     * 写入缓存，永不过期
     *
     * @param cacheName 缓存名
     * @param key       缓存键
     * @param value     缓存值
     */
    public void set(String cacheName, String key, Object value) {
        String s = null;
        try {
            s = om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(buildKey(cacheName, key), s);
    }

    /**
     * 写入缓存，永不过期
     *
     * @param cacheName 缓存名
     * @param key       缓存键
     * @param value     缓存值
     */
    public void set(String cacheName, int key, Object value) {
        String s = null;
        try {
            s = om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(buildKey(cacheName, key), s);
    }

    /**
     * 写入缓存，永不过期
     *
     * @param cacheName 缓存名
     * @param key       缓存键
     * @param value     缓存值
     */
    public void set(String cacheName, long key, Object value) {
        String s = null;
        try {
            s = om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(buildKey(cacheName, key), s);
    }

    private String buildKey(String cacheName, String key) {
        return cacheName + ":" + key;
    }

    private String buildKey(String cacheName, int key) {
        return cacheName + ":" + key;
    }

    private String buildKey(String cacheName, long key) {
        return cacheName + ":" + key;
    }

    /**
     * 读取缓存
     *
     * @param cacheName 缓存名
     * @param key       缓存键
     */
    public <T> T get(String cacheName, String key, Class<T> type) {

        String o = redisTemplate.opsForValue().get(buildKey(cacheName, key));
        if (o != null) {
            try {
                return om.readValue(o, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取缓存
     *
     * @param cacheName 缓存名
     * @param key       缓存键
     */
    public <T> T get(String cacheName, int key, Class<T> type) {

        String o = redisTemplate.opsForValue().get(buildKey(cacheName, key));
        if (o != null) {
            try {
                return om.readValue(o, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取缓存
     *
     * @param cacheName 缓存名
     * @param key       缓存键
     */
    public <T> T get(String cacheName, long key, Class<T> type) {

        String o = redisTemplate.opsForValue().get(buildKey(cacheName, key));
        if (o != null) {
            try {
                return om.readValue(o, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取缓存
     *
     * @param key 缓存Key
     */
    public <T> T get(String key, Class<T> type) {

        String o = redisTemplate.opsForValue().get(key);
        if (o != null) {
            try {
                return om.readValue(o, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T> T get(String cacheName, String key, Class<? extends Collection> type, Class javaClass) {

        String o = redisTemplate.opsForValue().get(buildKey(cacheName, key));
        if (o != null) {
            try {
                JavaType javaType = om.getTypeFactory().constructCollectionType(type,javaClass);
                return om.readValue(o, javaType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取缓存的所有key缓存
     *
     * @param cacheName 缓存名
     */
    public Set<String> keys(String cacheName) {
        return redisTemplate.keys(cacheName);
    }

    /**
     * 删除缓存
     *
     * @param cacheName 缓存名
     * @param key       缓存键
     */
    public void delete(String cacheName, String key) {
        redisTemplate.delete(buildKey(cacheName, key));
    }

    /**
     * 删除缓存
     *
     * @param key       缓存键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除缓存
     *
     * @param cacheName 缓存名
     * @param keys      缓存键集合
     */
    public void delete(String cacheName, Collection<String> keys) {
        List<String> collect = keys.stream().map(s -> buildKey(cacheName, s)).collect(Collectors.toList());
        redisTemplate.delete(collect);
    }


    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param cacheName 缓存名
     * @param key
     * @param loader    如果没有找到则执行该函数，缓存结果并返回
     */
    public <T> T get(String cacheName, String key, Class<T> type, Function<String, Object> loader) {
        T data = get(cacheName, key, type);
        if (data == null) {
            Object obj = loader.apply(key);
            set(cacheName, key, obj);
        }
        return data;
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param cacheName 缓存名
     * @param key
     * @param timeout   过期时间
     * @param unit      时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                  秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     * @param loader    如果没有找到则执行该函数，缓存结果并返回
     */
    public <T> T get(String cacheName, String key, Class<T> type, long timeout, TimeUnit unit, Function<String, Object> loader) {
        T data = get(cacheName, key, type);
        if (data == null) {
            Object obj = loader.apply(key);
            setEx(cacheName, key, obj, timeout, unit);
        }
        return data;
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String cacheName, String key) {
        return redisTemplate.hasKey(buildKey(cacheName, key));
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(String cacheName, String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(buildKey(cacheName, key), timeout, unit);
    }

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key
     * @param unit
     * @return
     */
    public Long getExpire(String cacheName, String key, TimeUnit unit) {
        return redisTemplate.getExpire(buildKey(cacheName, key), unit);
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key
     * @param value
     * @param timeout 过期时间
     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    public void setEx(String cacheName, String key, Object value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(buildKey(cacheName, key), om.writeValueAsString(value), timeout, unit);
        } catch (JsonProcessingException e) {
            log.error("setEx",e);
        }
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key
     * @param value
     * @param timeout 过期时间
     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    public void setEx(String key, Object value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, om.writeValueAsString(value), timeout, unit);
        } catch (JsonProcessingException e) {
            log.error("setEx",e);
        }
    }

    /**
     * 添加元素到变量中同时指定元素的分值。
     * @param key
     * @param value
     * @param score
     * @return
     */
    public boolean zSetAdd(String key, String value, double score){
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 获取变量指定区间的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set zSetRange(String key, long start, long end){
        Set zSetValue = redisTemplate.opsForZSet().range(key, start, end);
        log.info("通过range(K key, long start, long end)方法获取指定区间的元素:" + zSetValue);
        return zSetValue;
    }

    /**
     * 根据设置的score获取区间值
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set rangeByScore(String key, double min, double max){
        Set zSetValue = redisTemplate.opsForZSet().rangeByScore(key, min, max);
        System.out.println("通过rangeByScore(K key, double min, double max)方法根据设置的score获取区间值:" + zSetValue);
        return zSetValue;
    }


    /**
     *  获取变量中元素的索引,下标开始位置为0。(排名)
     * @param key
     * @param value
     * @return
     */
    public long zSetRank(String key, String value){
        try {
            return redisTemplate.opsForZSet().rank(key, value) + 1;
        } catch (Exception e){
            log.info("当前查询zSetRank的key:{}, value:{}，不存在结果", key, value);
            return 0L;
        }
    }

    /**
     * 获取待续排名，实际真实排名
     * @param key
     * @param value
     * @return
     */
    public long zSetReverseRank(String key, String value){
        try {
            return redisTemplate.opsForZSet().reverseRank(key, value) + 1;
        } catch (Exception e){
            log.info("当前查询zSetReverseRank的key:{}, value:{}，不存在结果", key, value);
            return 0L;
        }
    }

    /**
     * 获取元素的分值
     * @param key
     * @param value
     * @return
     */
    public double zSetScore(String key, String value){
        try {
            return redisTemplate.opsForZSet().score(key, value);
        } catch (Exception e){
            log.info("当前查询zSetScore的key:{}, value:{}，不存在结果", key, value);
            return 0;
        }
    }

    /**
     * 获取变量中元素的个数
     * @param key
     * @return
     */
    public long zSetCard(String key){
        long zCard = redisTemplate.opsForZSet().zCard(key);
        System.out.println("通过zCard(K key)方法获取变量的长度:" + zCard);
        return zCard;
    }

    /**
     * 修改变量中的元素的分值（递增delta）
     * @param key
     * @param value
     * @param delta
     * @return
     */
    public boolean zSetIncrementScore(String key, String value, double delta){
        double incrementScore = redisTemplate.opsForZSet().incrementScore(key, value, delta);
        System.out.print("通过incrementScore(K key, V value, double delta)方法修改变量中的元素的分值:" + incrementScore);
        double score = redisTemplate.opsForZSet().score(key, value);
        System.out.print(",修改后获取元素的分值:" + score);
        return true;
    }

    /**
     * 倒序排列指定分值区间元素
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set zSetReverseRangeByScore(String key, double min, double max){
        Set zSetValue = redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
        System.out.println("通过reverseRangeByScore(K key, double min, double max)方法倒序排列指定分值区间元素:" + zSetValue);
        return zSetValue;
    }

    /**
     * 索引倒序排列指定区间元素。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set zSetReverseRange(String key, long start, long end){
        Set zSetValue = redisTemplate.opsForZSet().reverseRange(key, start, end);
        System.out.println("通过reverseRange(K key, long start, long end)方法倒序排列元素:" + zSetValue);
        return zSetValue;
    }

}
