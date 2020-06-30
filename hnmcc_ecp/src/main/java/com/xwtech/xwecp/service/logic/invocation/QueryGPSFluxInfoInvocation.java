package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.FluxUsedInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY040048Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class QueryGPSFluxInfoInvocation extends BaseInvocation implements
		ILogicalService {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryGPSFluxInfoInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;
	
	private String REQUEST_BOSSTELETEXT_1 = "ac_agetgprsflux_922055";//流量明细

	private String REQUEST_BOSSTELETEXT_2 = "ac_agetgprsflux_717";//流量月查询
	
	private String REQUEST_BOSSTELETEXT_3 = "cc_totalgprsflux_69";//流量类包月查询接口
	public QueryGPSFluxInfoInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		}
	
	
	public BaseServiceInvocationResult executeService(String accessId,ServiceConfig config, List<RequestParameter> params)
	{
		QRY040048Result result = new QRY040048Result();
		String reqXml = "";
		String rspXml = "";
		try
		{
			params.add(new RequestParameter("itemId", ""));
			//流量明细
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_3, params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_3, this.generateCity(params)));			
		     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
		      {
		            result.setBossCode(LOGIC_ERROR);
		            result.setErrorCode(LOGIC_ERROR);
		            return result;
		      }		         
		     Message bossMsg = MessageUtil.parse(rspXml);	            
		     result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
		     result.setBossCode(bossMsg.getHead().getCode());
		     result.setErrorMessage(bossMsg.getHead().getDesc());	
		     List<FluxUsedInfo> fluxsUsedList = parseMonthFluxs(bossMsg.getBody().asList());
//		     List<FluxUsedInfo> fluxsUsedList = parseFluxsDet(bossMsg.getBody().asList());
		     //流量月查询接口中获取流量概况
				reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_2, params);
				rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_2, this.generateCity(params)));			
			     logger.info("******** Boss返回数据为*****　" + rspXml);		     
			     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
			      {
			            result.setBossCode(LOGIC_ERROR);
			            result.setErrorCode(LOGIC_ERROR);
			            return result;
			      }		         
			      bossMsg = MessageUtil.parse(rspXml);	            
			     result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
			     result.setBossCode(bossMsg.getHead().getCode());
			     result.setErrorMessage(bossMsg.getHead().getDesc());
		         String resStr = bossMsg.getBody().toString();
		         //获取月流量json数据
		         fluxsUsedList = parseFluxs(fluxsUsedList,resStr);
		         result.setFluxUsedInfoList(fluxsUsedList);
		       
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}
	/**
	 * 解析流量月查询接口中获取流量概况
	 * @param str
	 * @return
	 */
	private List<FluxUsedInfo> parseFluxs(List<FluxUsedInfo> fluxsUsedList,String str){
		FluxUsedInfo fluxUsed;
		String[] temp = str.split("\t");
		JSONArray array = JSONArray.fromObject(temp[4]);
		JSONObject jsonObj,jsonTemp;
		if(array==null || array.size()==0){
			return fluxsUsedList;
		}
		int i=0;
		while(true){
			jsonObj = (JSONObject)array.get(i);	
			if(i==array.size()-1){
				fluxUsed = new FluxUsedInfo();
				fluxUsed.setFreeResUsed(convertStr(jsonObj.getString("FREE_RES_USED")));
				fluxUsed.setFreeRes(convertStr(jsonObj.getString("FREE_RES")));
				fluxUsed.setItemName(jsonObj.getString("ITEM_NAME"));
				fluxUsed.setUnitDes("KB");
				fluxUsed.setSubDate(jsonObj.getString("FREE_RES_USED_BFB"));
				fluxsUsedList.add(fluxUsed);
				break;
			}else{
				while(true){
					if(i==array.size()-1){
						break;
					}
					jsonTemp = (JSONObject)array.get(i++);
					if(jsonObj.getString("ITEM_NAME").equals(jsonTemp.getString("ITEM_NAME"))){
						jsonObj = jsonTemp;
					}else{
						fluxUsed = new FluxUsedInfo();
						fluxUsed.setFreeResUsed(convertStr(jsonObj.getString("FREE_RES_USED")));
						fluxUsed.setFreeRes(convertStr(jsonObj.getString("FREE_RES")));
						fluxUsed.setItemName(jsonObj.getString("ITEM_NAME"));
						fluxUsed.setUnitDes("KB");
						fluxUsed.setSubDate(jsonObj.getString("FREE_RES_USED_BFB"));
						fluxsUsedList.add(fluxUsed);
						break;
					}
				}
			}
			
		}		
		return fluxsUsedList;
		
	}
	
	private String convertStr(String str){
		String res = String.format("%f%n",(Float.parseFloat(str.replace("MB", ""))*1024));
		res = res.substring(0,res.lastIndexOf("."));
		return res;
	} 
	/**
	 * 解析流量明细报文
	 * @param str
	 * @return
	 */
	
	private List<FluxUsedInfo> parseFluxsDet(List<String[]> str){
		List<FluxUsedInfo> fluxsDetList = new ArrayList<FluxUsedInfo>();
		FluxUsedInfo fluxUsed;
		for(String[] temp:str){
			fluxUsed = new FluxUsedInfo();
			fluxUsed.setFreeResUsed(temp[0]);
			fluxUsed.setFreeResLeft(temp[1]);
			fluxUsed.setFreeRes(temp[2]);
			fluxUsed.setItemId(temp[3]);
			fluxUsed.setSubDate(temp[6]);
			fluxUsed.setItemName(temp[4]);
			fluxUsed.setUnitDes(temp[5]);
			fluxsDetList.add(fluxUsed);
		}
		
		return fluxsDetList;
		
	}
	
	/**
	 * 解析流量明细报文
	 * @param str
	 * @return
	 */
	
	private List<FluxUsedInfo> parseMonthFluxs(List<String[]> str){
		List<FluxUsedInfo> fluxsDetList = new ArrayList<FluxUsedInfo>();
		FluxUsedInfo fluxUsed;
		for(String[] temp:str){
			if("KB".equals(temp[2]))
			{
				fluxUsed = new FluxUsedInfo();
				fluxUsed.setFreeResUsed(convertStr(temp[4]));
				fluxUsed.setFreeResLeft(convertStr(temp[5]));
				fluxUsed.setFreeRes(convertStr(temp[3]));
				fluxUsed.setItemId(temp[0]);
				fluxUsed.setItemName(temp[1]);
				fluxUsed.setUnitDes(temp[2]);
				fluxsDetList.add(fluxUsed);			
			}
		}
		
		return fluxsDetList;
		
	}
	
}
