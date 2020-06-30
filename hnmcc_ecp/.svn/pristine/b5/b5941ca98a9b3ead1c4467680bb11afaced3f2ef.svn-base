package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import com.xwtech.xwecp.service.logic.pojo.GommonBusiness;
import com.xwtech.xwecp.service.logic.pojo.QRY020001Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DelQryMWCPBussInvocation extends BaseInvocation implements
		ILogicalService {
	
	private static final Logger logger = LoggerFactory.getLogger(DelQryMWCPBussInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;
	//查询已订购梦网产品
	private String REQUEST_BOSSTELETEXT_2 = "ac_alreadymwcp_100801";
	
	public DelQryMWCPBussInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		}
	
	
	public BaseServiceInvocationResult executeService(String accessId,ServiceConfig config, List<RequestParameter> params)
	{
		QRY020001Result result = new QRY020001Result();
		List<GommonBusiness> reList = new ArrayList();
		List<BossParmDT> bList = null;
		List<GommonBusiness> list = new ArrayList();
		String bizId = "";
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
		String reqXml = "";
		String rspXml = "";
		try
		{
			//查询已开梦网产品
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_2, params);

			Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(REQUEST_BOSSTELETEXT_2, params);
			String sysParam = "";
			String busiParam = "";
			String type = StringTeletext.DEFAULT_REQ_TYPE;
			if(null!=remotingMap){
				sysParam = remotingMap.get("sysParam")==null?"":(String)remotingMap.get("sysParam");
				busiParam = remotingMap.get("busiParam")==null?"":(String)remotingMap.get("busiParam");
				type = remotingMap.get("type")==null?"0":(String)remotingMap.get("type");
			}

			String headTraceId = "";
			String headUserMobile = "";
			String headUserBrand = "";
			String headUserCity = "";
			String headPageNum = "";
			String headRecNum = "";
			String headSerialNum = "";
			String headJfserialNum = "";
			String headProdId = "";
			if (null != params && 0 < params.size()) {
				for (RequestParameter parameter : params) {
					String paramName = parameter.getParameterName();
					if ("header_traceId".equals(paramName)) {
						headTraceId = (String)parameter.getParameterValue();
					}else if ("header_usermobile".equals(paramName)) {
						headUserMobile = (String)parameter.getParameterValue();
					}else if ("header_userbrand".equals(paramName)) {
						headUserBrand = (String)parameter.getParameterValue();
					}else if ("header_usercity".equals(paramName)) {
						headUserCity = (String)parameter.getParameterValue();
					}else if ("header_pagenum".equals(paramName)) {
						headPageNum = (String) parameter.getParameterValue();
					} else if ("header_recnum".equals(paramName)) {
						headRecNum = (String) parameter.getParameterValue();
					} else if ("header_serialnum".equals(paramName)) {
						headSerialNum = (String) parameter.getParameterValue();
					} else if ("header_jfserialnum".equals(paramName)) {
						headJfserialNum = (String) parameter.getParameterValue();
					} else if ("header_prodId".equals(paramName)) {
						headProdId = (String)parameter.getParameterValue();
					}
				}
			}
			headTraceId = headTraceId == null ? "" : headTraceId;
			headUserMobile = headUserMobile == null ? "" : headUserMobile;
			headUserBrand = headUserBrand == null ? "" : headUserBrand;
			headUserCity = headUserCity == null ? "" : headUserCity;

			headPageNum = headPageNum == null ? "" : headPageNum;
			headRecNum = headRecNum == null ? "" : headRecNum;
			headSerialNum = headSerialNum == null ? "" : headSerialNum;
			headJfserialNum = headJfserialNum == null ? "" : headJfserialNum;
			headProdId = headProdId == null ? "" : headProdId;
			//rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_2, this.generateCity(params)));

			rspXml = (String)this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,reqXml, accessId,
					REQUEST_BOSSTELETEXT_2, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
					headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));

//			rspXml = "MHF2016051415212660100801XZT01 HNYD00201605141521260001  1 781 4  0000调用成功                                  FFFFFFFF中国移动-手机阅读	手机阅读全球通阅读包	包月计费	5	MREAD	服务定购\t20150508233353\t20150508233353\t移动公司管理部门	698001	10000605	06		1		10000605	090508					G	手机阅读全球通阅读包"
//+"\n卓望信息	[郑州]WLAN自由套餐	包月计费	0	WLAN	服务定购	20150608164036	20150608164104	WAP营业厅	400000	PIAW00	06		1    PIAW00	001211			10086		L		[郑州]WLAN自由套餐"
//+"\n中国移动视频基地	和视频-V+喜乐包	包月计费	3	手机视频	服务定购	20150707183818	20150707183820	WAP营业厅	699019	1530830001	06   1530830001	001123					G		和视频-V+喜乐包"
//+"\n中国移动-手机报	新闻早晚报	包月计费	3	梦网彩信	服务定购	20160513210713	20160513210716	WAP营业厅	801234	110301	06	05	1    110301	000103					G	05	新闻早晚报";


			if(StringTeletext.DEFAULT_REQ_TYPE.equals(type)){
				if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120){
					result.setBossCode(LOGIC_ERROR);
					result.setErrorCode(LOGIC_ERROR);
					return result;
				}
				Message bossMsg = MessageUtil.parse(rspXml);
				if(!BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) || StringUtils.isBlank(bossMsg.getBody().toString())){
					result.setBossCode(LOGIC_ERROR);
					result.setErrorCode(LOGIC_ERROR);
					return result;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				List<String[]> resArray = bossMsg.getBody().asList();
				if(resArray !=null){
					int j=0;
					for(String[] temp:resArray){
						for(int i=0;i<temp.length;i++){
							logger.info("****************第"+j+"个梦网产品返回字段"+i+"*******"+temp[i]);
						}
						GommonBusiness gb = new GommonBusiness();
						gb = new GommonBusiness();
						gb.setId(temp[9]+"|"+temp[10]+"|"+temp[11]);//最后用这个，客户端不用修改代码
						gb.setName(temp[1]);
						gb.setReserve1(temp[9]+"|"+temp[10]+"|"+temp[11]);//spid|spcode|type
						gb.setBeginDate(df1.format(df.parse(temp[6])));
						list.add(gb);
						j++;
					}
				}

				if (null != params && params.size() > 0) {
					for (RequestParameter p : params) {
						if (p.getParameterName().equals("bizId")) {
							bizId = String.valueOf(p.getParameterValue());
							if (!"".equals(bizId)) {
								if ("findmwcp".equals(bizId)) {
									bizId = "MWCP";
								}
								bList = this.wellFormedDAO.getSubBossBaseParmList(bizId);
								break;
							}
						}
					}
				}

				if (null != list && list.size() > 0) {
					if (null != bList && bList.size() > 0) {
						for (GommonBusiness g : list) {
							boolean close = true;
							// 判断过滤新免邮（100160000335）、飞信（400000075975）、和彩云（410000190431）
							if ("100160000335".equals(g.getReserve1()) || "400000075975".equals(g.getReserve1())
									|| "410000190431".equals(g.getReserve1())) {
								close = false;
							} else {
								for (BossParmDT bDt : bList) {
									g.setState(2);
									if (bDt.getParm2().equals(g.getReserve1())) {
										close = false;
										g.setId(bDt.getBusiNum());
										g.setName(bDt.getParm5());
										g.setReserve2(bizId);
										reList.add(g);
									}
								}
							}

							if (close) {
								g.setState(1);
								g.setReserve2(bizId);
								reList.add(g);
							}
						}
					} else {
						reList = list;
					}
				}

				result.setGommonBusiness(reList);

				result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
				result.setBossCode(bossMsg.getHead().getCode());
				result.setErrorMessage(bossMsg.getHead().getDesc());
			}else if(StringTeletext.REMOTING_REQ_TYPE.equals(type)){
				logger.info("******** Boss返回数据为*****　" + rspXml);
				if (StringUtils.isEmpty(rspXml)){
					result.setBossCode(LOGIC_ERROR);
					result.setErrorCode(LOGIC_ERROR);
					return result;
				}
				MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
				String bossCode = bossJson.getBossCode();
				String bossDesc = bossJson.getBossDesc();
				if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
					result.setBossCode(LOGIC_ERROR);
					result.setErrorCode(LOGIC_ERROR);
					return result;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				JSONArray valueList = bossJson.getJSONArrayResult();
				for (int j = 0; j < valueList.size(); j++){
					JSONObject jsonObj = (JSONObject) valueList.get(j);
					GommonBusiness gb = new GommonBusiness();
					gb = new GommonBusiness();
					gb.setId(jsonObj.getString("企业代码")+"|"+jsonObj.getString("子业务代码")+"|"+jsonObj.getString("操作代码"));//最后用这个，客户端不用修改代码
					gb.setName(jsonObj.getString("子业务名称"));
					gb.setReserve1(jsonObj.getString("企业代码")+"|"+jsonObj.getString("子业务代码")+"|"+jsonObj.getString("操作代码"));//spid|spcode|type
					gb.setBeginDate(df1.format(df.parse(jsonObj.getString("生效时间"))));
					list.add(gb);
				}


				if (null != params && params.size() > 0) {
					for (RequestParameter p : params) {
						if (p.getParameterName().equals("bizId")) {
							bizId = String.valueOf(p.getParameterValue());
							if (!"".equals(bizId)) {
								if ("findmwcp".equals(bizId)) {
									bizId = "MWCP";
								}
								bList = this.wellFormedDAO.getSubBossBaseParmList(bizId);
								break;
							}
						}
					}
				}

				if (null != list && list.size() > 0) {
					if (null != bList && bList.size() > 0) {
						for (GommonBusiness g : list) {
							boolean close = true;
							// 判断过滤新免邮（100160000335）、飞信（400000075975）、和彩云（410000190431）
							if ("100160000335".equals(g.getReserve1()) || "400000075975".equals(g.getReserve1())
									|| "410000190431".equals(g.getReserve1())) {
								close = false;
							} else {
								for (BossParmDT bDt : bList) {
									g.setState(2);
									if (bDt.getParm2().equals(g.getReserve1())) {
										close = false;
										g.setId(bDt.getBusiNum());
										g.setName(bDt.getParm5());
										g.setReserve2(bizId);
										reList.add(g);
									}
								}
							}

							if (close) {
								g.setState(1);
								g.setReserve2(bizId);
								reList.add(g);
							}
						}
					} else {
						reList = list;
					}
				}

				result.setGommonBusiness(reList);

				result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
				result.setBossCode(bossCode);
				result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}	
}
