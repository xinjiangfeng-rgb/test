package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.CommunicateException;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.*;
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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 我的移动数据信息
 * 
 * @author wangjiajia
 *
 */
public class QueryMyBaseInfoInvocation extends BaseInvocation implements ILogicalService
{
	private static final Logger logger = LoggerFactory.getLogger(QueryMyBaseInfoInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;
	
	private WellFormedDAO wellFormedDAO;
	
	/**
     * 请求Boss的接口,实时余额
     */
    private static final String REQUEST_BOSSTELETEXT = "cc_currentbalance_01";
    /**
     * 请求Boss的接口,消费额
     */
    private static final String REQUEST_BOSSTELETEXT_1 = "ac_acqryrealtimebill_309";
    /**
     * 请求Boss的接口,流量使用流量
     */
    private static final String REQUEST_BOSSTELETEXT_2 = "ac_querygprspkgflux_69";
    /**
     * 请求Boss的接口,积分
     */
    private static final String REQUEST_BOSSTELETEXT_3 = "cc_cgetuseraccscore_770";
    
    private String REQUEST_BOSSTELETEXT_5 = "cc_totalgprsflux_69";//流量类包月查询接口
    
	private String REQUEST_BOSSTELETEXT_4 = "cc_totalgprsflux_200868";//流量免费实时查询
	private DecimalFormat decf = new DecimalFormat("##0.00");
	private SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddhhmmss");
    private SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public QueryMyBaseInfoInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		
	}
	
	@SuppressWarnings("unchecked")
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params)
	{
		QRY040099Result result = new QRY040099Result();
		try
		{
			//实时余额
			QRY010098Result  qry010098 = getBalance(accessId,config,params);
			//消费额
			QRY010018Result qry010018 = getTotalFee(accessId,config,params);
			//流量使用流量
			QRY040046Result qry040046 = getFeeResUsed(accessId,config,params);
			//积分
			QRY040002Result qry040002 = getScore(accessId,config,params);
			
			if(!LOGIC_SUCESS.equals(qry010098.getResultCode())){
				result.setResultCode(qry010098.getResultCode());
				result.setBossCode(qry010098.getBossCode());
				result.setErrorMessage(qry010098.getErrorMessage());
				return result;
			}
			if(!LOGIC_SUCESS.equals(qry010018.getResultCode())){
				result.setResultCode(qry010018.getResultCode());
				result.setBossCode(qry010018.getBossCode());
				result.setErrorMessage(qry010018.getErrorMessage());
				return result;
			}
			if(!LOGIC_SUCESS.equals(qry040046.getResultCode())){
				result.setResultCode(qry040046.getResultCode());
				result.setBossCode(qry040046.getBossCode());
				result.setErrorMessage(qry040046.getErrorMessage());
				return result;
			}
			if(!LOGIC_SUCESS.equals(qry040002.getResultCode())){
				result.setResultCode(qry040002.getResultCode());
				result.setBossCode(qry040002.getBossCode());
				result.setErrorMessage(qry040002.getErrorMessage());
				return result;
			}
			result.setResultCode(qry040002.getResultCode());
			result.setBossCode(qry040002.getBossCode());
			result.setBalance(qry010098.getCurrentBalance());
			float temp = 0l;
			for(PkgInfo fluxUsedInfo:qry040046.getPkgInfoList()){
				temp+=Float.parseFloat(fluxUsedInfo.getFreeResUsed().replace("MB", ""));
			}
			result.setFeeResUsed(new DecimalFormat("##0.00").format(temp));
			result.setScore(qry040002.getBalance());
			result.setTotalFee(qry010018.getTotalFee());
			return result;
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}
	/**
	 * 消费额
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 */
	public QRY010018Result getTotalFee(String accessId, ServiceConfig config, List<RequestParameter> params){
		
		QRY010018Result result = new QRY010018Result();		
		List<QRY010018Result> qry010018List = new ArrayList<QRY010018Result>();
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
        String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_1, params);
        try
        {
            String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT,
                this.generateCity(params)));
            
            if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
            {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }	
            String errCode = MessageUtil.parse(rspXml).getHead().getCode();
            String errDesc = MessageUtil.parse(rspXml).getHead().getDesc();
            
            List<String[]> resList = MessageUtil.parse(rspXml).getBody().asList();
            String[] temp;
            temp = resList.get(resList.size()-1);
            if(temp!=null & temp.length == 2){
            	result.setTotalFee(temp[1]); //费用总金额
            }
            result.setQry010018List(qry010018List);
            result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
            result.setBossCode(errCode);
            result.setErrorMessage(errDesc);
            
        }
        catch (CommunicateException e)
        {
            logger.error("", e);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return result;
	}
	/**
	 * 余额
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 */
	public QRY010098Result getBalance(String accessId, ServiceConfig config, List<RequestParameter> params){
		
		QRY010098Result result = new QRY010098Result();		
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
        String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);
        try
        {
            String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT,
                this.generateCity(params)));
            
            logger.info("******** Boss返回数据为*****　" + rspXml);
            
            
            if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
            {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }            	
            String errCode = MessageUtil.parse(rspXml).getHead().getCode();
            String errDesc = MessageUtil.parse(rspXml).getHead().getDesc();
            
            result.setCurrentBalance(new DecimalFormat("##0.00").format(Float.parseFloat(MessageUtil.parse(rspXml).getBody().toString())/100));            
            result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
            result.setBossCode(errCode);
            result.setErrorMessage(errDesc);            
        }
        catch (CommunicateException e)
        {
            logger.error("", e);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return result;
	}
	/**
	 * 流量使用量
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 */
	public QRY040046Result getFeeResUsed(String accessId, ServiceConfig config, List<RequestParameter> params){
	
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		QRY040046Result result = new QRY040046Result();		
		String reqXml = "";
		String rspXml = "";
		try
		{
			params.add(new RequestParameter("itemId", ""));
			params.add(new RequestParameter("queryMonth", df.format(new Date())));
			//流量明细
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_5, params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_5, this.generateCity(params)));			
		     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
		      {
		            result.setBossCode(LOGIC_ERROR);
		            result.setErrorCode(LOGIC_ERROR);
		            return result;
		      }		         
		     Message bossMsg = MessageUtil.parse(rspXml);	            
		     if(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) || "2002".equals(bossMsg.getHead().getCode())){
		    	  result.setResultCode(LOGIC_SUCESS);
		      }else{
		    	  result.setResultCode(LOGIC_ERROR);  
		      }
		     result.setBossCode(bossMsg.getHead().getCode());
		     result.setErrorMessage(bossMsg.getHead().getDesc());	
		     List<PkgInfo> fluxsUsedList=new ArrayList<PkgInfo>();
		     if(BOSS_SUCCESS.equals(bossMsg.getHead().getCode())){
		    	 fluxsUsedList = parseMonthFluxs(bossMsg.getBody().asList());
		     }
//		     List<FluxUsedInfo> fluxsUsedList = parseFluxsDet(bossMsg.getBody().asList());
		     //流量月查询接口中获取流量概况
				reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_4, params);
				rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_4, this.generateCity(params)));			
			     logger.info("******** Boss返回数据为*****　" + rspXml);		     
			     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
			      {
			            result.setBossCode(LOGIC_ERROR);
			            result.setErrorCode(LOGIC_ERROR);
			            return result;
			      }		         
			      bossMsg = MessageUtil.parse(rspXml);
			      
			      if(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) || "2002".equals(bossMsg.getHead().getCode())){
			    	  result.setResultCode(LOGIC_SUCESS);
			      }else{
			    	  result.setResultCode(LOGIC_ERROR);  
			      }
			    
			     result.setBossCode(bossMsg.getHead().getCode());
			     result.setErrorMessage(bossMsg.getHead().getDesc());
		         String resStr = bossMsg.getBody().toString();
		         //获取月流量json数据
//		         fluxsUsedList = parseFluxs(fluxsUsedList,resStr);
//		         result.setFluxUsedInfoList(fluxsUsedList);
		         if(BOSS_SUCCESS.equals(bossMsg.getHead().getCode())){
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
			fluxsUsedList.add(fluxUsed);
		}
		
		return fluxsUsedList;
	}
	
	/**
	 * 解析流量明细报文
	 * @param str
	 * @return
	 */
	
	private List<PkgInfo> parseMonthFluxs(List<String[]> str){
		List<PkgInfo> fluxsDetList = new ArrayList<PkgInfo>();
		PkgInfo fluxUsed;
		for(String[] temp:str){
			if("KB".equals(temp[2])){
				fluxUsed = new PkgInfo();
				fluxUsed.setFreeResUsed(temp[4]);
				fluxUsed.setFreeResLeft(temp[5]);
				fluxUsed.setFreeRes(temp[3]);
				fluxUsed.setItemId(temp[0]);
				fluxUsed.setItemName(temp[1]);
				fluxUsed.setUnitDes(temp[2]);
				fluxUsed.setExpireDate(temp[7]);
				fluxUsed.setValidDate(temp[6]);
				fluxsDetList.add(fluxUsed);	
			}									
		}
		
		return fluxsDetList;
		
	}
	private String convertStr(String str){
		String res = String.format("%f%n",(Float.parseFloat(str.replace("MB", ""))*1024));
		res = res.substring(0,res.lastIndexOf("."));
		return res;
	}
	/**
	 * 积分
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 */
	public QRY040002Result getScore(String accessId, ServiceConfig config, List<RequestParameter> params){
	
		QRY040002Result result = new QRY040002Result();		
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
        String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_3, params);
        try
        {
            String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT,
                this.generateCity(params)));
            
            if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
            {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            Message bossMsg = MessageUtil.parse(rspXml);	
            String errCode = MessageUtil.parse(rspXml).getHead().getCode();
            String errDesc = MessageUtil.parse(rspXml).getHead().getDesc();
            String[] temp = MessageUtil.parse(rspXml).getBody().asArray();
            result.setBalance(temp[0]);
            result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
            result.setBossCode(errCode);
            result.setErrorMessage(bossMsg.getHead().getDesc());            
        }
        catch (CommunicateException e)
        {
            logger.error("", e);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return result;
	}
	
}