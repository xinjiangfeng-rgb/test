package com.xwtech.xwecp.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xwtech.xwecp.util.TimeoutUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by lv on 2016/4/24.
 */
public class RedisServiceForSty {
    private JedisPool jedisPool;

    public RedisServiceForSty(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public <T> T execute(Function<Jedis, T> function) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return function.callBack(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     *
     * @param key
     * @param value
     * @return
     */
    public long sadd(final String key, final String... value) {
        return execute(new Function<Jedis, Long>() {
            @Override
            public Long callBack(Jedis jedis) throws IOException {
                return jedis.sadd(key, value);
            }
        });
    }

    /**
     * 获取key的所有value
     *
     * @param key
     * @return
     */
    public Set<String> smembers(final String key) {
        return execute(new Function<Jedis, Set<String>>() {
            @Override
            public Set<String> callBack(Jedis jedis) throws IOException {
                return jedis.smembers(key);
            }
        });
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return execute(new Function<Jedis, Boolean>() {
            @Override
            public Boolean callBack(Jedis jedis) throws IOException {
                return jedis.exists(key);
            }
        });
    }

    /**
     * 批量移除成员
     * @param key
     * @param value
     * @return
     */
    public Long sRem(final String key,final String ... value) {
        return execute(new Function<Jedis, Long>() {
            @Override
            public Long callBack(Jedis jedis) throws IOException {
                return jedis.srem(key,value);
            }
        });
    }

    /**
     * Atomically return and remove the first (LPOP) or last (RPOP) element of the list. For example
     * if the list contains the elements "a","b","c" LPOP will return "a" and the list will become
     * "b","c".
     * <p/>
     * If the key does not exist or the list is already empty the special value 'nil' is returned.
     *
     * @param key
     * @return Bulk reply
     */
    public String lpop(final String key) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                return jedis.lpop(key);

            }
        });
    }

    public String set(final String key, final String value) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                return jedis.set(key, value);
            }
        });
    }

    public String set(final String key, final Object value) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                if (value instanceof String) {
                    return jedis.set(key, String.valueOf(value));
                } else {
                    return jedis.set(key, JSON.toJSONString(value, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty));
                }
            }
        });
    }

    public String get(final String key) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                return jedis.get(key);
            }
        });
    }

    public Long lpush(final String key, final String value) {
        return execute(new Function<Jedis, Long>() {
            @Override
            public Long callBack(Jedis jedis) throws IOException {
                return jedis.lpush(key, value);
            }
        });
    }

    public List<String> lrange(final String key, final int start, final int end) {
        return execute(new Function<Jedis, List<String>>() {
            @Override
            public List<String> callBack(Jedis jedis) throws IOException {
                return jedis.lrange(key, start, end);
            }
        });
    }

    public Long lrem(final String key, final Long count, final String value) {
        return execute(new Function<Jedis, Long>() {
            @Override
            public Long callBack(Jedis jedis) throws IOException {
                return jedis.lrem(key, count, value);
            }
        });
    }


    public String set(final String key, final String value, final long timeout, final TimeUnit unit) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                String result = jedis.set(key, value);
                jedis.expire(key, Long.valueOf(TimeoutUtils.toSeconds(timeout, unit)).intValue());
                return result;
            }
        });
    }

    public String set(final String key, final Object value, final long timeout, final TimeUnit unit) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                if (value instanceof String) {
                    String result = jedis.set(key, String.valueOf(value));
                    jedis.expire(key, Long.valueOf(TimeoutUtils.toSeconds(timeout, unit)).intValue());
                    return result;
                } else {
                    String result = jedis.set(key, JSON.toJSONString(value, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty));
                    jedis.expire(key, Long.valueOf(TimeoutUtils.toSeconds(timeout, unit)).intValue());
                    return result;
                }
            }
        });
    }


    public String set(final String key, final String value, final long timeout) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                String result = jedis.set(key, value);
                jedis.expire(key, Long.valueOf(timeout).intValue());
                return result;
            }
        });
    }

    public String set(final String key, final Object value, final long timeout) {
        return execute(new Function<Jedis, String>() {
            @Override
            public String callBack(Jedis jedis) throws IOException {
                if (value instanceof String) {
                    String result = jedis.set(key, String.valueOf(value));
                    jedis.expire(key, Long.valueOf(timeout).intValue());
                    return result;
                } else {
                    String result = jedis.set(key, JSON.toJSONString(value, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty));
                    jedis.expire(key, Long.valueOf(timeout).intValue());
                    return result;
                }
            }
        });
    }

    public Long expire(final String key, final long timeout, final TimeUnit unit) {
        return execute(new Function<Jedis, Long>() {
            @Override
            public Long callBack(Jedis jedis) throws IOException {
                return jedis.expire(key, Long.valueOf(TimeoutUtils.toSeconds(timeout, unit)).intValue());
            }
        });
    }

    public long del(final String key) {
        return execute(new Function<Jedis, Long>() {
            @Override
            public Long callBack(Jedis jedis) throws IOException {
                return jedis.del(key);
            }
        });
    }

}
