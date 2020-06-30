package com.xwtech.xwecp.service.logic.invocation;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.PkgInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY040046Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;


public class QueryTotalFluxInfoInvocation extends BaseInvocation implements
		ILogicalService {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryTotalFluxInfoInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;

//	private String REQUEST_BOSSTELETEXT_1 = "cc_totalgprsflux_200868";//流量免费实时查询
//	
//	private String REQUEST_BOSSTELETEXT_2 = "ac_agetgprsflux_717";//流量月查询
//	
//	private String REQUEST_BOSSTELETEXT_3 = "cc_totalgprsflux_69";//流量类包月查询接口
	
	private String REQUEST_BOSSTELETEXT_1 = "cc_totalgprsflux_201607";//流量免费实时查询
	
	private String REQUEST_BOSSTELETEXT_2 = "ac_agetgprsflux_717";//流量月查询
	
	private String REQUEST_BOSSTELETEXT_3 = "cc_totalgprsflux_201606";//流量类包月查询接口
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
	
	private SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddhhmmss");
    private SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private DecimalFormat decf = new DecimalFormat("##0.00");
	
	public QueryTotalFluxInfoInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		}
	
	
	public BaseServiceInvocationResult executeService(String accessId,ServiceConfig config, List<RequestParameter> params)
	{
		
		QRY040046Result result = new QRY040046Result();
		String reqXml = "";
		String rspXml = "";
		try
		{
			
			params.add(new RequestParameter("queryMonth", df.format(new Date())));
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
		     logger.info("接口返回code:"+bossMsg.getHead().getCode());
		     logger.info("接口返回message："+bossMsg.getHead().getDesc());
		     List<PkgInfo> fluxsUsedList = parseMonthFluxs(bossMsg.getHead().getCode(),bossMsg.getBody().asList());
//		     //流量月查询接口中获取流量概况
//				reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_2, params);
				//免费资源的免费流量
				reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_1, params);
				rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_2, this.generateCity(params)));			
			     logger.info("******** Boss返回数据为*****　" + rspXml);		     
			     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
			      {
			            result.setBossCode(LOGIC_ERROR);
			            result.setErrorCode(LOGIC_ERROR);
			            return result;
			      }		         
			      bossMsg = MessageUtil.parse(rspXml);	  
			      if(BOSS_SUCCESS.equals(bossMsg.getHead().getCode())||"2002".equals(bossMsg.getHead().getCode())){
			    	  result.setResultCode(LOGIC_SUCESS);
			      }else{
			    	  result.setResultCode(LOGIC_ERROR);
			      }
			     
			     result.setBossCode(bossMsg.getHead().getCode());
			     result.setErrorMessage(bossMsg.getHead().getDesc());
//		         String resStr = bossMsg.getBody().toString();
//		         //获取月流量json数据
//		         fluxsUsedList = parseFluxs(fluxsUsedList,resStr);
			     if(BOSS_SUCCESS.equals(bossMsg.getHead().getCode())){
			    	//免费资源中的流量数据
			         fluxsUsedList = freeResource(fluxsUsedList,bossMsg.getBody().asList()); 
			     }
		         
		         result.setPkgInfoList(fluxsUsedList);
		       
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}
	/**
	 * 免费资源中的免费流量
	 * @param fluxsUsedList
	 * @param str
	 * @return
	 * @throws ParseException 
	 */
	private List<PkgInfo> freeResource(List<PkgInfo> fluxsUsedList,List<String[]> str) throws ParseException{
		PkgInfo fluxUsed;
		for(String[] temp:str){
			fluxUsed = new PkgInfo();
			fluxUsed.setFreeResUsed(temp[6].replace("MB", ""));
			fluxUsed.setFreeResLeft(temp[7].replace("MB", ""));
			fluxUsed.setFreeRes(temp[5].replace("MB", ""));
			fluxUsed.setItemId(temp[1]);
			fluxUsed.setItemName(temp[2]);
			fluxUsed.setUnitDes("KB");
			fluxUsed.setExpireDate(temp[9]);
			fluxUsed.setValidDate(temp[8]);
			if(temp.length==11){
				fluxUsed.setItemType(temp[10]);
			}else{
				fluxUsed.setItemType("");
			}
			fluxsUsedList.add(fluxUsed);
		}
		
		return fluxsUsedList;
	}
	
	/**
	 * 解析流量月查询接口中获取流量概况
	 * @param str
	 * @return
	 */
	private List<PkgInfo> parseFluxs(List<PkgInfo> fluxsUsedList,String str){
		PkgInfo fluxUsed;
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
				fluxUsed = new PkgInfo();
				fluxUsed.setFreeResUsed(jsonObj.getString("FREE_RES_USED"));
				fluxUsed.setFreeResLeft(optStr(jsonObj.getString("FREE_RES_USED"),jsonObj.getString("FREE_RES")));
				fluxUsed.setFreeRes(jsonObj.getString("FREE_RES"));
				fluxUsed.setItemName(jsonObj.getString("ITEM_NAME"));
				fluxUsed.setValidDate(df.format(new Date())+"00000000");
				fluxUsed.setExpireDate(getEndMonthDay()+"00000000");
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
						fluxUsed = new PkgInfo();						
						fluxUsed.setFreeResUsed(jsonObj.getString("FREE_RES_USED"));
						fluxUsed.setFreeResLeft(optStr(jsonObj.getString("FREE_RES_USED"),jsonObj.getString("FREE_RES")));
						fluxUsed.setFreeRes(jsonObj.getString("FREE_RES"));
						fluxUsed.setItemName(jsonObj.getString("ITEM_NAME"));
						fluxUsed.setValidDate(df.format(new Date())+"01000000");
						fluxUsed.setExpireDate(getEndMonthDay()+"01000000");
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
	 
	
	private String optStr(String used,String total){
		String res = String.valueOf(Float.parseFloat(total.replace("MB", ""))-Float.parseFloat(used.replace("MB", "")));
		return res+"MB";
	}
	
	private String getEndMonthDay(){
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		int i = cl.get(Calendar.MONTH);		
		cl.set(Calendar.MONTH,i+1);
		return df.format(cl.getTime());
	}
	
	/**
	 * 解析流量明细报文
	 * @param str
	 * @return
	 */
	
	private List<PkgInfo> parseMonthFluxs(String code,List<String[]> str){
		List<PkgInfo> fluxsDetList = new ArrayList<PkgInfo>();
		logger.info("包月为str："+str.size());
		if("0000".equals(code)){
			PkgInfo fluxUsed;
			for(String[] temp:str){
				if(null != temp && temp.length!=0){
					fluxUsed = new PkgInfo();
					fluxUsed.setFreeResUsed(temp[4]);
					fluxUsed.setFreeResLeft(temp[5]);
					fluxUsed.setFreeRes(temp[3]);
					fluxUsed.setItemId(temp[0]);
					fluxUsed.setItemName(temp[1]);
					fluxUsed.setUnitDes(temp[2]);
					fluxUsed.setExpireDate(temp[7]);
					fluxUsed.setValidDate(temp[6]);
					if(temp.length==9){
						fluxUsed.setItemType(temp[8]);
					}else{
						fluxUsed.setItemType("");
					}
					fluxsDetList.add(fluxUsed);	
				}
			}
		}
		return fluxsDetList;
		
	}
	
//	/**
//	 * 解析流量明细报文
//	 * @param str
//	 * @return
//	 */
//	
//	private List<PkgInfo> parseMonthFluxs(List<String[]> str){
//		List<PkgInfo> fluxsDetList = new ArrayList<PkgInfo>();
//		PkgInfo fluxUsed;
//		for(String[] temp:str){
//			fluxUsed = new PkgInfo();
//			fluxUsed.setFreeResUsed(temp[4]);
//			fluxUsed.setFreeResLeft(temp[5]);
//			fluxUsed.setFreeRes(temp[3]);
//			fluxUsed.setItemId(temp[0]);
//			fluxUsed.setItemName(temp[1]);
//			fluxUsed.setUnitDes(temp[2]);
//			fluxUsed.setExpireDate(temp[7]);
//			fluxUsed.setValidDate(temp[6]);
//			fluxsDetList.add(fluxUsed);							
//		}
//		
//		return fluxsDetList;
//		
//	}
}
