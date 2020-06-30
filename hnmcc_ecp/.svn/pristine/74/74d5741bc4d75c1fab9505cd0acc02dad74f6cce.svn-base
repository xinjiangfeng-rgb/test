package com.iman.cbh.bossinterface.transport;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {
	  private static Queue<String> queue = new ConcurrentLinkedQueue();
	  private static MessageQueue instance;

	  public static MessageQueue getInstance()
	  {
	    if (instance == null)
	    {
	      instance = new MessageQueue();
	    }
	    return instance;
	  }

	  public boolean add(String msg)
	  {
	    if ((msg == null) || ("".equals(msg.trim())))
	    {
	      return true;
	    }

	    return queue.add(msg.trim());
	  }

	  public String poll()
	  {
	    return (String)queue.poll();
	  }
}
