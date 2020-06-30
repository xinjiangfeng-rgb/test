package com.xwtech.xwecp.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil
{
    public static final String BOSS_CODE = "0000";
    
    /** 逻辑接口成功编码 */
    public static final String LOGIC_SUCESS = "0";
    
    /** 逻辑接口失败编码 */
    public static final String LOGIC_ERROR = "1";
    
    static String HEAD_END = "FFFFFFFF";
    
    static String SPLIT_TAB = "\t";
    
    static String SPLIT_ENT = "\n";
    
    static String SPLIT_C = "\\|";
    
    private static MessageUtil INSTANCE = new MessageUtil();
    
    private MessageUtil()
    {
    }
    
    public static Message parse(String text)
    {
        return INSTANCE.parseText(text);
    }
    
    public Message parseText(String text)
    {
        if (null == text || text.length() == 0)
        {
            return null;
        }
        Message message = new Message();
        String headTxt = text.substring(0, text.indexOf(HEAD_END) + 8);
        String bodyTxt = text.substring(text.indexOf(HEAD_END) + 8);
        String resCode = new String(headTxt.getBytes(), 66, 4);
        String errMsg = "";
        if(!"0000".equals(resCode)&&StringUtils.isNotBlank(bodyTxt)){
        	errMsg = bodyTxt;
        	errMsg = errMsg.replaceAll(" ", "");
        }
        message.head = new Head(headTxt,errMsg);
        message.body = new Body(bodyTxt);
        return message;
    }
    
    public class Message
    {
        private Head head;
        
        private Body body;
        
        public Message()
        {
        }
        
        public Head getHead()
        {
            return this.head;
        }
        
        public Body getBody()
        {
            return this.body;
        }
        
    }
    
    public class Head
    {
        public Head(String headTxt,String bodyTxt)
        {
            this.headTxt = headTxt;
            this.errMsg = bodyTxt;
        }
        
        String headTxt = "";
        
        String errMsg = "";
        
        String code;
        
        String desc;
        
        public String getCode()
        {
            return new String(headTxt.getBytes(), 66, 4);
        }
        
        public String getDesc()
        {
        	System.out.println(this.errMsg);
            return StringUtils.isNotBlank(this.errMsg)?this.errMsg:new String(headTxt.getBytes(), 70, 42).trim();
        }
        
        public String toString()
        {
            return headTxt;
        }
    }
    
    public class Body
    {
        public Body(String bodyTxt)
        {
            this.bodyTxt = bodyTxt;
        }
        
        String bodyTxt = "";
        
        public String toString()
        {
            return this.bodyTxt;
        }
        
        public List<String[]> asList()
        {
            List<String[]> columnList = new ArrayList<String[]>();
            if (null == bodyTxt || bodyTxt.length() == 0)
            {
                return columnList;
            }
            
            String[] rows = bodyTxt.split(SPLIT_ENT);
            if (rows.length >= 1)
            {
                for (int i = 0; i < rows.length; i++)
                {
                    String row = "";
//                    if (i == 0 && rows[i].split(SPLIT_C).length > 0)
//                    {
//                        row = rows[i].split(SPLIT_C)[0];
//                    }
//                    else
//                    {
//                        row = rows[i];
//                    }
//                    columnList.add(row.split(SPLIT_TAB));

                    row = rows[i];
                    columnList.add(row.split(SPLIT_TAB));
                }
            }
            
            return columnList;
        }
        
        public String[] asArray()
        {
            return bodyTxt.split(SPLIT_TAB);
        }
    }
    
    public static void main(String[] args)
    {
    	String str = "MHF2015030320011199940036XZT01 HNYD00201503032001120001  1 248 1  0010                                          FFFFFFFF sdaqwdwq";
        Message message = MessageUtil.parse("MHF2015030320011199940036XZT01 HNYD00201503032001120001  1 248 1  0010                                          FFFFFFFF sdaqwdwq");
        List<String[]> strList = message.getBody().asList();
        System.out.println(strList.size());
        System.out.println(message.getHead().getCode());
        System.out.println(message.getHead().getDesc());
        System.out.println(new String(str.getBytes(),37,14));
    }
}
