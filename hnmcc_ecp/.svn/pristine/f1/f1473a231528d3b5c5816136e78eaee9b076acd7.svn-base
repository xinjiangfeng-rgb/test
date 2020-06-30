package com.xwtech.xwecp.communication.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;


public class ResponseMessageQueue
{
    private static final Logger logger = LoggerFactory.getLogger(ResponseMessageQueue.class);

    private static Map<String, String> rspMsg = new java.util.concurrent.ConcurrentHashMap<String, String>();
    
    private static ResponseMessageQueue instance = new ResponseMessageQueue();
    
    private ResponseMessageQueue()
    {
    }
    
    public static ResponseMessageQueue getInstance()
    {
        return instance;
    }
    
    /**
     * 推消息到消息池
     * 
     * @param msg
     */
    public void add(String msg)
    {
        rspMsg.put(new String(msg.getBytes(), 0, 25), msg);
    }
    
    /**
     * 从消息池读取消息
     * 
     * @return
     */
    public String getMsg(String key)
    {
        return rspMsg.remove(key);
    }
    
    public void clear()
    {
        rspMsg.clear();
    }
    
    public boolean isEmpty()
    {
        return rspMsg.isEmpty();
    }
    
    public Iterator isIterator()
    {
        return rspMsg.keySet().iterator();
    }
    
    public int getSize()
    {
        return rspMsg.size();
    }

    public String getValue(String key){
        return rspMsg.get(key);
    }
}
