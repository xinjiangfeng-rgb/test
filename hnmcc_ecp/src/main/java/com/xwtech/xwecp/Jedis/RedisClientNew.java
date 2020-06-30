package com.xwtech.xwecp.Jedis;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.*;

public class RedisClientNew {
	
	private String host;
	private int port;
	private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	

    /**
     * 服务器信息列表
     */
    private List<Map<String,String>> serverList = null;
    /**
     * 初始化jedisPool
     */
    private JedisPool jedisPool = null;
    /**
     * redis分片
     */
    private List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
    
    /**
     * 字符集：UTF-8
     */
    private Charset UTF= Charset.forName("utf-8");
    /**
     * 初始化客户端 
     * @param serverFile redis服务器配置信息文件
     */
    public void init(){
    	System.out.println("<开始初始化redis>");
        initjedisPool();
        
        if(null == jedisPool)
        {
        	 System.out.println("<请确认redis服务器是否启动!>");
             return;
        }
    }

    /**
     * 初始化jedisPool。
     * 其存放的是所有服务器的连接
     */
    private void initjedisPool(){
        if (null == shards || shards.size() == 0){
            String host = null;
            Integer port = null;
            JedisShardInfo si = null;
            
            host = getHost();
            port = getPort();
            si = new JedisShardInfo(host, port);
            shards.add(si);
                
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(2048);
            config.setMaxWaitMillis(5000);
            jedisPool = new JedisPool(config, host,port,5000,password);
            
            System.out.println("redis 已启动");
        }
    }
    
    /**
     * 销毁连接池
     */
    public void destroy(){
        serverList = null;
        jedisPool.destroy();
    }
    
    /**
     * 将字符串值 value 关联到 key 。 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。
     * @param key 关键字
     * @param value 值
     * @return 状态码 OK
     * <br><b>Tips:</b>若要使用返回值，请做null值校验。和服务器建立连接多次失败，则返回null
     */    
    public String add(String key, String value){
    	Jedis jedis = null;
  
        String status = null;
        try{
        	jedis = jedisPool.getResource();
            status = jedis.set(key.getBytes(UTF), value.getBytes(UTF));
        }
        catch (Exception e){
          e.printStackTrace();
        }
        finally{
            jedisPool.returnResource(jedis);
        }
        return status;
    }
    
    public boolean add(String key, String value, long expireInMills){
        Jedis resource = null;
        try{
            resource = jedisPool.getResource();
            byte[] bytes = key.getBytes();
            if (bytes != null && bytes.length > 0){
                byte[] values = value.getBytes(UTF);
                String setExRes = resource.setex(bytes, (int) (expireInMills / 1000), values);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            jedisPool.returnResource(resource);
        }
        return true;
	}
    
    public String get(String key){
        Jedis jedis = null;
        String value = null;
        try{
            jedis = jedisPool.getResource();
            byte[] by = jedis.get(key.getBytes(UTF));
            if(null != by){
            	value = new String(by,UTF);
            }
        }catch (Exception e){
          e.printStackTrace();
        }finally{
        	jedisPool.returnResource(jedis);
        }
        return value;
    }
    /**
     * 删除给定的 key 。
     * <br>不存在的 key 会被忽略。
     * @param key 关键字
     * @return 被删除 key 的数量。
     * <br><b>Tips:</b>若要使用返回值，请做null值校验。和服务器建立连接多次失败，则返回null
     * 
     */
    public Long del(String key){
    	Jedis jedis = null;
        Long quantity = null;
        try{
        	jedis = jedisPool.getResource();
            quantity = jedis.del(key);
        }catch (Exception e){
        	  e.printStackTrace();
        }finally{
        	jedisPool.returnResource(jedis);
        }
        
        return quantity;
    }
    
    
	public String replace(String key, String value){
		if (this.get(key) != null){
			this.del(key);
		}
		return this.add(key, value);
	}
}