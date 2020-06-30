//package com.xwtech.xwecp.communication.transport;
//
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
///**
// * 消息队列 User: yuanshoujing@gmail.com Date: 12-8-29 Time: 下午4:39
// */
//public class MessageQueue
//{
//    private static Queue<String> queue = new ConcurrentLinkedQueue<String>();
//
//    private static MessageQueue instance = new MessageQueue();
//
//    private MessageQueue()
//    {
//    }
//
//    public static MessageQueue getInstance()
//    {
//        return instance;
//    }
//
//    /**
//     * 推消息到消息池
//     *
//     * @param msg
//     */
//    public boolean add(String msg)
//    {
//        if (msg == null || "".equals(msg.trim()))
//        {
//            return true;
//        }
//
//        return queue.add(msg.trim());
//    }
//
//    /**
//     * 从消息池读取消息
//     *
//     * @return
//     */
//    public String poll()
//    {
//        return queue.poll();
//    }
//}
