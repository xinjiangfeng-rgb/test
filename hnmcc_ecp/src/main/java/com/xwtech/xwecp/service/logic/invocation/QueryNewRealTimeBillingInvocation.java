package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.CommunicateException;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.ErrorMapping;
import com.xwtech.xwecp.service.logic.pojo.FeeDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY010018Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.ParseXmlConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据meta文件设置的参数
 * 解析实时账单查询结果
 * @author 丁亮
 * Apr 19, 2011
 */
public class QueryNewRealTimeBillingInvocation extends BaseInvocation implements ILogicalService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QueryNewRealTimeBillingInvocation.class);
	private BossTeletextUtil bossTeletextUtil;
	private IRemote remote;
	private WellFormedDAO wellFormedDAO;
	private ParseXmlConfig config;
	private String REQUEST_BOSSTELETEXT = "ac_acqryrealtimebill_309";
	private String REQUEST_BOSSTELETEXT_920138 = "ac_acqryrealtimebill_920138";
	//private String REQUEST_BOSSTELETEXT_920131 = "ac_acqryrealtimebill_920131";
	private String REQUEST_BOSSTELETEXT_920131 = "ac_acqryrealtimebill_201603";
	private String TOTAL = "合计";
	
	public QueryNewRealTimeBillingInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
		this.config = new ParseXmlConfig();
	}

	@SuppressWarnings("unchecked")
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
		QRY010018Result result = new QRY010018Result();
		
		List<QRY010018Result> qry010018List = new ArrayList<QRY010018Result>();
		List<FeeDetail> feeDetailList = new ArrayList<FeeDetail>();
		FeeDetail feeDetail = null;
		QRY010018Result qry010018 = null;
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
        DecimalFormat df = new DecimalFormat("#0.00");
        float totalfee=0;
        String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);
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
            String[] temp=null;
            for(int i=0; i<resList.size()-1 ;i++){
            	
            	temp = resList.get(i);
            	if(temp.length==1){
            		qry010018 = new QRY010018Result();
            		qry010018.setMainprodName(temp[0]);//费用项目 如：套餐及固定费
            	}
            	if(temp.length == 2){
            		feeDetail = new FeeDetail();
            		if(TOTAL.equals(temp[0])){
            			qry010018.setTotalFee(temp[1]);//费用项目 合计金额
            			qry010018.setFeeDetailList(feeDetailList);
            			qry010018List.add(qry010018);//将对象添加到费用项目列表            			
            			feeDetailList = new ArrayList<FeeDetail>();
            		}else{
            			feeDetail = new FeeDetail();
            			feeDetail.setFeeName(temp[0]);//费用项目明细 如：*4G飞享套餐-58元套餐费
                		feeDetail.setFee(temp[1]);//费用项目明细 如：49.40
                		feeDetailList.add(feeDetail);  
            		}           		      		            		
            	}            	
            }
            //获取费用信息列表
            QRY010018Result res920131 = getFeeDetail(accessId, config, params);
            if("0".equals(res920131.getResultCode())){
            	feeDetailList = res920131.getFeeDetailList();
            	
            	result.setOtherPay(res920131.getOtherPay());
    			result.setGroupPay(res920131.getGroupPay());
				result.setOtherPay3(res920131.getOtherPay3());
				result.setOtherPay4(res920131.getOtherPay4());
            	
            	for(FeeDetail feeObj:feeDetailList){
            		//判断自有增值业务费
            		if("2263".equals(feeObj.getFeeTypeId())&&"0.00".equals(feeObj.getFee())){
            			qry010018 = new QRY010018Result();
            			qry010018.setMainprodName(feeObj.getFeeName());
            			qry010018.setTotalFee(feeObj.getFee());
            			qry010018List.add(qry010018);
            		}
            		//代收费业务费
            		if("2265".equals(feeObj.getFeeTypeId())&&"0.00".equals(feeObj.getFee())){
            			qry010018 = new QRY010018Result();
            			qry010018.setMainprodName(feeObj.getFeeName());
            			qry010018.setTotalFee(feeObj.getFee());
            			qry010018List.add(qry010018);
            		}
            		//代收费业务费
            		if("2265".equals(feeObj.getFeeTypeId())&&!"0.00".equals(feeObj.getFee())){
            			//获取代收费业务费详情列表
            			qry010018 = getCollectionCharges(accessId, config, params);
            			if("0".equals(qry010018.getResultCode())){

            				//代收费业务费金额
							String dsf = "0.00";
							if(null != qry010018.getTotalFee()){
								dsf = qry010018.getTotalFee();
								qry010018List.add(qry010018);
							}else{
								dsf = feeObj.getFee();
								qry010018.setMainprodName(feeObj.getFeeName());
								qry010018.setTotalFee(feeObj.getFee());
								qry010018List.add(qry010018);
							}
            				totalfee += Float.parseFloat(dsf);
            			}
            			else
            			{
							qry010018.setMainprodName(feeObj.getFeeName());
                			qry010018.setTotalFee(feeObj.getFee());
                			qry010018List.add(qry010018);
            			}            			
            		}
            		//其他费用 
//            		if("2267".equals(feeObj.getFeeTypeId())){
//            			qry010018 = new QRY010018Result();
//            			qry010018.setMainprodName(feeObj.getFeeName());
//            			qry010018.setTotalFee(feeObj.getFee());
//            			qry010018List.add(qry010018);
//            			//其他费用
//        				totalfee += Float.parseFloat(feeObj.getFee());
//            		}
            	}
            }
            
            if(resList!=null&&resList.size()>0){
            	temp = resList.get(resList.size()-1);
            }
            
            if(temp!=null){
            	if( temp.length == 2){
            		result.setTotalFee(df.format(totalfee+Float.parseFloat(temp[1]))); //费用总金额
            	}            	
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
	 * 代收费业务费用
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 */
	public QRY010018Result  getCollectionCharges(String accessId, ServiceConfig config, List<RequestParameter> params){
		QRY010018Result result = new QRY010018Result();
		List<FeeDetail> feeDetailList = new ArrayList<FeeDetail>();
		FeeDetail feeDetail = null;
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
		String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_920138, params);
		 try
	        {
	            String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_920138,
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
	            
	            List<String[]> resList = MessageUtil.parse(rspXml).getBody().asList();
	            String[] temp;
	            if(resList!=null&&resList.size()>0){
	            	
	            	result.setTotalFee(resList.get(0)[6]);
	            	result.setMainprodName("代收费业务费");
	            	
	            	for(int i=0;i<resList.size();i++){
	            		temp = resList.get(i);
		            	feeDetail = new FeeDetail();
		            	feeDetail.setFeeName(temp[1]);
		            	feeDetail.setFee(temp[5]);
		            	feeDetailList.add(feeDetail);
		            	
		            }
	            }
	            
	            result.setFeeDetailList(feeDetailList);
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
	 * 费用详情
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 */
	public QRY010018Result  getFeeDetail(String accessId, ServiceConfig config, List<RequestParameter> params){
		QRY010018Result result = new QRY010018Result();
		List<FeeDetail> feeDetailList = new ArrayList<FeeDetail>();
		FeeDetail feeDetail = null;
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
		String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_920131, params);
		 try
	        {
	            String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_920131,
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
	            if(resList!=null&&resList.size()>0){
	            	for(int i=0;i<resList.size();i++){
	            		temp = resList.get(i);
	            		if(i==0){
	            			result.setOtherPay(temp[4]);
	            			result.setGroupPay(temp[5]);
							result.setOtherPay3(temp[6]);//优惠费用
							result.setOtherPay4(temp[7]);//应缴费用
	            		}
		            	feeDetail = new FeeDetail();
		            	feeDetail.setFeeTypeId(temp[0]);
		            	feeDetail.setFeeName(temp[1]);
		            	feeDetail.setFee(temp[2]);
		            	feeDetailList.add(feeDetail);
		            }
	            }
	            
	            result.setFeeDetailList(feeDetailList);
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
	 * 设置结果信息
	 * @param res - 实体类
	 * @param resp_code - 返回代码
	 * @param xmlName - xml报文
	 */
	public void getErrInfo(QRY010018Result res, String resp_code, String xmlName) {
		ErrorMapping errDt = null; // 错误编码解析

		try {
			// 设置结果编码 0：成功 1：失败
			res.setResultCode("0000".equals(resp_code) ? "0" : "1");

			// 失败
			if (!"0000".equals(resp_code)) {
				// 解析错误信息
				errDt = this.wellFormedDAO.transBossErrCode("QRY010018", xmlName, resp_code);
				if (null != errDt) {
					res.setErrorCode(errDt.getLiErrCode()); 	// 设置错误编码
					res.setErrorMessage(errDt.getLiErrMsg()); 	// 设置错误信息
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public BossTeletextUtil getBossTeletextUtil() {
		return bossTeletextUtil;
	}

	public void setBossTeletextUtil(BossTeletextUtil bossTeletextUtil) {
		this.bossTeletextUtil = bossTeletextUtil;
	}

}
