package com.xwtech.xwecp.msg;


import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.JVM;
import com.thoughtworks.xstream.core.util.ClassLoaderReference;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.DefaultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServiceMessage
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceMessage.class);
	
	private MessageHead head;
	
	private MessageData data;
	
	private static XStream xstream = new XStream(new DomDriver());
	static {
		xstream.registerConverter(new FastJsonConverter(new DefaultMapper(new ClassLoaderReference(JSONObject.class.getClassLoader())), new JVM().bestReflectionProvider()));
	}

	static {
		xstream.registerConverter(new FastJsonConverter(new DefaultMapper(new ClassLoaderReference(JSONObject.class.getClassLoader())), (new JVM()).bestReflectionProvider()));
	}


	public MessageData getData()
	{
		return data;
	}

	public void setData(MessageData data)
	{
		this.data = data;
	}

	public MessageHead getHead()
	{
		return head;
	}

	public void setHead(MessageHead head)
	{
		this.head = head;
	}
	
	public static ServiceMessage fromXML(String xml)
	{
		Object obj = xstream.fromXML(xml);

		if(obj instanceof ServiceMessage)
		{
			return (ServiceMessage)(obj);
		}
		return null;
	}
	
	public String toString()
	{
		String xml = xstream.toXML(this); // serialize to XML
		return xml;		
	}
}
