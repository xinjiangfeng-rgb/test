package com.xwtech.xwecp.quartz;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.Jedis.RedisClientNew;
import com.xwtech.xwecp.communication.transport.ResponseMessageQueue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
/**
 * 清除CRM响应消息中超时消息
 * @author Administrator
 *
 */
public class ClearResponseQueueQtz{

	private static Logger logger = LoggerFactory.getLogger(ClearResponseQueueQtz.class);
	private static final Logger chaoShiLogger = LoggerFactory.getLogger("chaoShiLogger");
	private static final int cleartimeout = 20000;

	//详单缓存处理
	private List<String> billDetailList;
	//账单缓存处理
	private List<String> billList;
	//简单查询接口缓存处理----缓存2H
	private List<String> normalListNew;

	private List<String> otherOne;

	private List<String> otherTwo;

	private RedisClientNew cache;

	public RedisClientNew getCache() {
		return cache;
	}

	public void setCache(RedisClientNew cache) {
		this.cache = cache;
	}

	public void clearResMethod(){
		logger.info("--------------ClearResponseQueueQtz start");
		List<String> timeOutKeyList = new ArrayList<String>();
		logger.info("ResponseMessageQueue ----size----"+ResponseMessageQueue.getInstance().getSize());
		Iterator it = ResponseMessageQueue.getInstance().isIterator();
		Calendar cl = Calendar.getInstance();
		try {
			while(it.hasNext()){
				String key = String.valueOf(it.next());
				String value = ResponseMessageQueue.getInstance().getValue(key);

				String rspTime = "";
				if(value.getBytes().length>=51){
					rspTime=new String(value.getBytes(),37,14);
					logger.info("--------------ClearResponseQueueQtz time:"+rspTime);
					cl.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(rspTime));
					logger.info("**************相应队列中的信息,时间差："+(System.currentTimeMillis()-cl.getTimeInMillis())+"||timeout:"+cleartimeout);
					//响应对列中的时间大于20秒
					if((System.currentTimeMillis()-cl.getTimeInMillis()) > cleartimeout){
//						chaoShiLogger.info(key+"|"+value);
						timeOutKeyList.add(key);
						//************新增begin
						//超时的相应报文记录都缓存之后再删除
						//根据key去缓存中找到请求报文，解析请求报文并组装响应报文的key
						try{
							if(null != cache.get(key)){
								String reqStr = cache.get(key).toString();

								if(null != reqStr && !"".equals(reqStr)){
									//解析请求参数，获取CRM接口编码，手机号或者唯一标识
									String crmRemote=new String(reqStr.getBytes(),19,6);
									String phoneNum="";
									String crmCache="";
									boolean reqflag=false;
									//缓存失效时间
									long expreInSeconds=15*60*1000;
									logger.info("crmRemote------------"+crmRemote);
									long beginTime, endTime;
									beginTime = endTime = System.currentTimeMillis();
									if(StringUtils.isNotBlank(crmRemote)&&!"100502".equals(crmRemote)){
										if(reqStr.getBytes().length>=131){
											phoneNum=new String(reqStr.getBytes(),120,11);
										}
										if(StringUtils.isNotBlank(phoneNum)){
											//详单缓存
											if(billDetailList.contains(crmRemote)){
												crmCache="CRM_RES_"+crmRemote+"_"+phoneNum+"_"+new String(reqStr.getBytes(),132,8)+"_"+new String(reqStr.getBytes(),141,8);
												reqflag = true;
												logger.info("cache---type----billDetailList");
											}else if(billList.contains(crmRemote)){
												crmCache="CRM_RES_"+crmRemote+"_"+phoneNum+"_"+new String(reqStr.getBytes(),132,6);
												reqflag = true;
												logger.info("cache---type----billList");
											}else if(normalListNew.contains(crmRemote)){
												crmCache="CRM_RES_"+crmRemote+"_"+phoneNum;
												reqflag = true;
												logger.info("cache---type----normalList");
											}else if(otherOne.contains(crmRemote)){
												crmCache="CRM_RES_"+crmRemote+"_"+phoneNum+"_"+new String(reqStr.getBytes(),132,10)+"_"+new String(reqStr.getBytes(),142,10);
												reqflag = true;
												logger.info("cache---type----otherOne");
											}
											logger.info("crmRemote------------"+crmCache);

											//获取CRM执行结果
											String resCode = new String(value.getBytes(),66,4);
											//结果为成功状态添加到缓存中
											if("0000".endsWith(resCode)&&reqflag){
												cache.add(crmCache, JSON.toJSONString(value), expreInSeconds);
												logger.info("cache---add----"+crmCache);
											}
										}
									}
								}
							}
							//************新增end
						}catch (Exception e){

						}
					}
				}
			}
			logger.info("ResponseMessageQueue --delete--size----"+timeOutKeyList.size());
			//删除响应对列中的超时信息
			for(String strKey:timeOutKeyList){
				ResponseMessageQueue.getInstance().getMsg(strKey);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("ClearResponseQueueQtz----"+e.getMessage());
		}
		logger.info("--------------ClearResponseQueueQtz end");
	}

	public List<String> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<String> billDetailList) {
		this.billDetailList = billDetailList;
	}

	public List<String> getBillList() {
		return billList;
	}

	public void setBillList(List<String> billList) {
		this.billList = billList;
	}

	public List<String> getOtherOne() {
		return otherOne;
	}

	public void setOtherOne(List<String> otherOne) {
		this.otherOne = otherOne;
	}

	public List<String> getNormalListNew() {
		return normalListNew;
	}

	public void setNormalListNew(List<String> normalListNew) {
		this.normalListNew = normalListNew;
	}

	public List<String> getOtherTwo() {
		return otherTwo;
	}

	public void setOtherTwo(List<String> otherTwo) {
		this.otherTwo = otherTwo;
	}
}
