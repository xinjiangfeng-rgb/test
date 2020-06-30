package com.xwtech.xwecp.log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

public class PerformanceTracer
{
	private static final Logger	logger	= LoggerFactory.getLogger("PerformanceTracer");
	
	private static ThreadLocal<PerformanceTracer> threadInst = new ThreadLocal<PerformanceTracer>();
	
	protected StringBuffer informationTraceBuffer = new StringBuffer();
	
	protected long startTime = 0;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss:SSS");


	public static PerformanceTracer getInstance()
	{
		PerformanceTracer inst = null;
		if((inst = threadInst.get()) == null)
		{
			inst = new PerformanceTracer();
			inst.startTime = System.currentTimeMillis();
			threadInst.set(inst);
		}
		return inst;
	}
	
	public void init()
	{
		informationTraceBuffer = new StringBuffer();
		this.startTime = System.currentTimeMillis();
	}
	
	public void trace(String stage, long begin)
	{
		String threadName = Thread.currentThread().getName();
		long stageBegin = begin;
		long stageEnd = System.currentTimeMillis();
		long span = stageEnd - stageBegin;
		String warn = "";
		if(span > 400)
		{
			warn = "[WARN]";
		}
		String s = "["+stage+"][span="+(span)+"]"+warn+"\n";
		informationTraceBuffer.append(s);
	}
	
	public long addBreakPoint()
	{
		return System.currentTimeMillis();
	}
	
	public void log()
	{
		long endTime = System.currentTimeMillis();
		long span = endTime - startTime;
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		String dateStr = dateFormat.format(cal.getTime());
		if(span > 1000)
		{
			logger.debug("["+dateStr+"]\n" + informationTraceBuffer.toString());
			//System.out.println("["+dateStr+"]\n" + informationTraceBuffer.toString() + "\n\n");
		}
		else
		{
			logger.debug("["+dateStr+"]" + "请求处理时间小于1秒("+span+")!!");
			//System.out.println("["+dateStr+"]" + "请求处理时间小于1秒("+span+")!!\n\n");
		}
	}
}
