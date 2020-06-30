package com.xwtech.xwecp.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPhoneNumByIMSIService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryPhoneNumByIMSIClientImpl;
import com.xwtech.xwecp.service.logic.pojo.MGroupInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY040098Result;

public class QRY040098Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY040098Test.class);

	public static void main(String[] args) throws Exception {

		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("12");
		lic.setUserMobile("18236464534");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18236464534");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("ddr_city", "12");
		ic.addContextParameter("user_id", ""); // 2056200011182291
		lic.setContextParameter(ic);
		IQueryPhoneNumByIMSIService co = new QueryPhoneNumByIMSIClientImpl();

		String filePath = "F:\\IMSI.txt";
		try {
			String encoding="GBK";
			File file=new File(filePath);
			if(file.isFile() && file.exists()){ //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int i=1;
				while((lineTxt = bufferedReader.readLine()) != null){
					i++;
					QRY040098Result re = co.queryPhoneNum(lineTxt,"");
					if (re != null) {
						//logger.info(" === re.getPhoneNum() === " + re.getPhoneNum());
						System.out.println(lineTxt+"|"+re.getPhoneNum());
					}
					//System.out.println(i+"|"+lineTxt);
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
}