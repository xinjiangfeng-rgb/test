package com.xwtech.xwecp.Jedis;


import java.io.IOException;

/**
 * Created by lv on 2016/4/24.
 */
public interface Function<K, T> {
    T callBack(K k) throws IOException;
}
