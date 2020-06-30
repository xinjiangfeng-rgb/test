package com.xwtech.xwecp.transport.util;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.lilystudio.util.StringReader;

import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.util.RandomString;

public class MergeTeletext {
	private static final Logger logger = LoggerFactory.getLogger(MergeTeletext.class);
	
	/**
	 * 组装发送至BOSS的报文
	 * @param text 模板
	 * @param param 模板参数
	 * @return
	 */
    public static String mergeTeletext(String text, List<RequestParameter> param){
    	VelocityEngine engine = new VelocityEngine(); // 初始化并取得Velocity引擎
        try {
			engine.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        String mergeTeletext = "";
        VelocityContext context = new VelocityContext(); // 生成数据容器对象
        
        // 这里需要设置数据的值
        if (param != null)
        {
            // 开始加载报文头
            context.put("request_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            context.put("request_seq",RandomString.createRandom());
            context.put("t", "\t");
            // 结束加载报文头
            for (int i = 0; i < param.size(); i++){
                context.put(param.get(i).getParameterName(), param.get(i).getParameterValue());
            }
            
            StringWriter writer = new StringWriter(); // 设置接收模板数据的输出流
            try{
                // 匹配替换参数
                boolean flag = engine.evaluate(context, writer, "", new StringReader(text));
                if (flag)
                {
                    mergeTeletext = writer.toString();
                }
                
                String lineLength = String.format("% 5d",
                    getWordCountCode(mergeTeletext.substring(mergeTeletext.indexOf("FFFFFFFF") + 8),"GBK") + 120); // 设置请求报文长度
                mergeTeletext = mergeTeletext.replaceAll("-----", lineLength);
                // mergeTeletext = this.evaluateFunctions(mergeTeletext);
            }
            catch (Exception e)
            {
                logger.error("替换摸板信息出错！！！", e);
                return null;
            }
        }
        return mergeTeletext.trim();
    }
    
    private static int getWordCountCode(String s,String code){
		try {
			return s.getBytes(code).length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
