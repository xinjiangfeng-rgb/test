package com.xwtech.xwecp.service.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.AppConfig;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.communication.RemoteCallContext;
import com.xwtech.xwecp.communication.RemoteImpl;
import com.xwtech.xwecp.dao.GroupDetectionDAO;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.log.PerformanceTracer;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.*;
import com.xwtech.xwecp.service.config.*;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.service.server.funcs.*;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.teletext.IExternalFunctionExecutor;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.xml.xpath.XPathFunctionResolver;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope( value = "prototype")
public class SocketServiceImpl extends BaseServerServiceImpl implements IService {
	private static final Logger logger = LoggerFactory.getLogger(SocketServiceImpl.class);

	protected ServiceConfig serviceConfig;

	protected List<RequestParameter> params;


	protected GroupDetectionDAO groupDetectionDAO;

	protected BossTeletextUtil teletextUtil;

	protected XPathFunctionResolver functionResolver;

	protected IBossResponseValidator bossResponseValidator;

	protected Map<String, String> expressionEvaluateBuffer = new HashMap<String, String>();

	private Map<String, IExternalFunctionExecutor> externalFunctionMap = new HashMap<String, IExternalFunctionExecutor>();

	public GroupDetectionDAO getGroupDetectionDAO() {
		return groupDetectionDAO;
	}

	public void setGroupDetectionDAO(GroupDetectionDAO groupDetectionDAO) {
		this.groupDetectionDAO = groupDetectionDAO;
	}

	@Override
	public void initialize(ServiceConfig config, List<RequestParameter> params) throws ServiceException {
		this.serviceConfig = config;
		this.params = params;
		this.groupDetectionDAO = (GroupDetectionDAO) XWECPApp.SPRING_CONTEXT.getBean("groupDetectionDAO");
		this.remote = (IRemote) XWECPApp.SPRING_CONTEXT.getBean(RemoteImpl.class);
		this.wellFormedDAO = XWECPApp.SPRING_CONTEXT.getBean(WellFormedDAO.class);
		this.teletextUtil = (BossTeletextUtil) XWECPApp.SPRING_CONTEXT.getBean( BossTeletextUtil.class);
        this.bossResponseValidator = (IBossResponseValidator) XWECPApp.SPRING_CONTEXT.getBean(JSBossResponseValidatorImpl.class);

		this.externalFunctionMap.put("qry_business_typeof", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BusinessTypeExecutor.class));
		this.externalFunctionMap.put("qry_business_typeof2", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BusinessTypeExecutor2.class));
		this.externalFunctionMap.put("del_business_typeof", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2DelBusinessTypeExecutor.class));
		this.externalFunctionMap.put("del_business_typeof2", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2DelBusinessType2Executor.class));
		this.externalFunctionMap.put("del_business_typeof3", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2DelBusinessType3Executor.class));
		this.externalFunctionMap.put("del_business_typeof4", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2DelBusinessType4Executor.class));
		this.externalFunctionMap.put("check_now_month", XWECPApp.SPRING_CONTEXT.getBean(CheckNowMonthExecutor.class));
		// 根据配置的校验表达式, 校验参数
		this.validateInput();
	}

	@Override
	public BaseServiceInvocationResult execute(String accessId) throws ServiceException {
		PerformanceTracer pt = PerformanceTracer.getInstance();
		long n = 0;

		BaseServiceInvocationResult ret = null;
		if (this.serviceConfig != null) {
			if (serviceConfig.getImpl() != null && serviceConfig.getImpl().isDirectImpl()) {
				n = pt.addBreakPoint();
				ret = this.executeDirectly(accessId);
				pt.trace("直接执行实现的java类...", n);
				return ret;
			} else if (serviceConfig.getImpl() != null && (!serviceConfig.getImpl().isDirectImpl())) {
				n = pt.addBreakPoint();
				ret = this.executeByConfig(accessId);
				pt.trace("根据配置实现...", n);
				return ret;
			} else {
				throw new ServiceException("接口配置错误！");
			}
		}
		return null;
	}

	protected BaseServiceInvocationResult executeDirectly(String accessId) throws ServiceException {
		String implClass = this.serviceConfig.getImpl().getImplClass();
		System.out.println("==[WARN]==>直接写代码实现 :" + implClass);
		Class<?> clazz = null;
		ILogicalService service = null;
		try {
			PerformanceTracer pt = PerformanceTracer.getInstance();
			long n = pt.addBreakPoint();
			clazz = Class.forName(implClass);
			service = (ILogicalService) clazz.newInstance();
			pt.trace("实例化逻辑接口实现类...", n);
		} catch (ClassNotFoundException e) {
			logger.error("逻辑接口实现类[" + implClass + "]没有找到!");
			throw new ServiceException(e);
		} catch (InstantiationException e) {
			logger.error("逻辑接口实现类[" + implClass + "]实例化失败!");
			throw new ServiceException(e);
		} catch (IllegalAccessException e) {
			logger.error("逻辑接口实现类[" + implClass + "]实例化失败!");
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return service.executeService(accessId, this.serviceConfig, this.params);
	}

	protected BaseServiceInvocationResult executeByConfig(String accessId) throws ServiceException {
		PerformanceTracer pt = PerformanceTracer.getInstance();
		long starttime = System.currentTimeMillis();
		long n = 0;
		// 获取命令字
		String cmd = this.getServiceConfig().getCmd();
		// 根据正则表达式获取相匹配报文模板
		n = pt.addBreakPoint();
		TeletextMapping tm = this.getBossTeletextMapping();
		pt.trace("getBossTeletextMapping...", n);
		if (tm == null) {
			throw new ServiceException("找不到匹配的BOSS报文模板!");
		}

		// 根据报文模板初始化逻辑接口实现类
		if (tm.getDirectImplClass() != null && tm.getDirectImplClass().trim().length() > 0) {
			String implClass = tm.getDirectImplClass();
			Class<?> clazz = null;
			ILogicalService service = null;
			try {
				n = pt.addBreakPoint();
				clazz = Class.forName(implClass);
				service = (ILogicalService) clazz.newInstance();
				pt.trace("实例化逻辑接口实现类...", n);
			} catch (ClassNotFoundException e) {
				logger.error("逻辑接口实现类[" + implClass + "]没有找到!");
				throw new ServiceException(e);
			} catch (InstantiationException e) {
				logger.error("逻辑接口实现类[" + implClass + "]实例化失败!");
				throw new ServiceException(e);
			} catch (IllegalAccessException e) {
				logger.error("逻辑接口实现类[" + implClass + "]实例化失败!");
				throw new ServiceException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
			n = pt.addBreakPoint();
			BaseServiceInvocationResult ret = service.executeService(accessId, this.serviceConfig, this.params);
			pt.trace("执行逻辑接口实现类[" + implClass + "]...", n);
			return ret;
		}
		n = pt.addBreakPoint();

		// TODO -------------------------------------- /
		// 组BOSS报文
		String teletext = teletextUtil.mergeTeletext(tm.getTeletextTemplate(), this.params);
		/* 2016年11月24日 14:38:32 zzw 4085 添加jbossremoting json数据模版查询 start */
		Map<String, Object> remotingMap = teletextUtil.mergeRemoteTeletext(tm.getTeletextTemplate(), this.params);
		String sysParam = "";
		String busiParam = "";
		String type = StringTeletext.DEFAULT_REQ_TYPE;
		if (null != remotingMap) {

			sysParam = remotingMap.get("sysParam") == null ? "" : (String) remotingMap.get("sysParam");
			busiParam = remotingMap.get("busiParam") == null ? "" : (String) remotingMap.get("busiParam");
			type = remotingMap.get("type") == null ? "0" : (String) remotingMap.get("type");
		}
		/* 2016年11月24日 14:38:32 zzw 4085 添加jbossremoting json数据模版查询 end */
		pt.trace("组BOSS报文...", n);
		if (teletext != null) {
			logger.debug("准备请求BOSS：\n" + teletext);
			logger.debug("准备请求BOSS-->type:" + type + "json：\n" + sysParam + "\n" + busiParam);
			String bossResponse = null;
			try {
				// 2010年5月17日修改, BOSS分地市割接,
				// 需要将ServiceMessage.head中的userCity穿透到报文发送处,取固有参数fixed_ddr_city
				// 传入到remote call, 并且修改StringTeletext,增加扩展上下文参数
				RequestParameter userCityParam = this.getParameter(ServiceExecutor.ServiceConstants.USER_CITY);
				String userCity = userCityParam != null ? userCityParam.getParameterValue().toString() : "";
				RemoteCallContext remoteCallContext = new RemoteCallContext();
				remoteCallContext.setUserCity(userCity);
				// 修改结束(BOSS分地市割接)
				// 2010年7月28日修改, 掌厅接入, 根据来源渠道不同, 需要将来源渠道传递到remote call
				RequestParameter invokeChannelParam = this
						.getParameter(ServiceExecutor.ServiceConstants.INVOKE_CHANNEL);
				String invokeChannel = invokeChannelParam != null ? invokeChannelParam.getParameterValue().toString()
						: "";
				remoteCallContext.setInvokeChannel(invokeChannel);
				// 增加traceId
				String traceId = "";
				if (null != params && 0 < params.size()) {
					for (RequestParameter parameter : params) {
						String paramName = parameter.getParameterName();
						if ("context_traceId".equals(paramName)) {
							traceId = (String) parameter.getParameterValue();
							break;
						}
					}
				}
				traceId = traceId == null ? "" : traceId;
				remoteCallContext.setTraceId(traceId);

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
							headTraceId = (String) parameter.getParameterValue();
						} else if ("header_usermobile".equals(paramName)) {
							headUserMobile = (String) parameter.getParameterValue();
						} else if ("header_userbrand".equals(paramName)) {
							headUserBrand = (String) parameter.getParameterValue();
						} else if ("header_usercity".equals(paramName)) {
							headUserCity = (String) parameter.getParameterValue();
						} else if ("header_pagenum".equals(paramName)) {
							headPageNum = (String) parameter.getParameterValue();
						} else if ("header_recnum".equals(paramName)) {
							headRecNum = (String) parameter.getParameterValue();
						} else if ("header_serialnum".equals(paramName)) {
							headSerialNum = (String) parameter.getParameterValue();
						} else if ("header_jfserialnum".equals(paramName)) {
							headJfserialNum = (String) parameter.getParameterValue();
						} else if ("header_prodId".equals(paramName)) {
							headProdId = (String) parameter.getParameterValue();
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
				// 修改结束,掌厅接入的来源渠道传递
				// 发送BOSS请求
				n = pt.addBreakPoint();

				// *********** 测试代码 ***********************
				// bossResponse =
				// "MHF2015020515459115940032XZT01 HNYD00201502091714020081 1
				// 888 8 0000调用成功 FFFFFFFF2015-01-22
				// 14:16:13\t驻马店\t呼转主叫\t18703659581\t1分4秒\t省际长途\t4G飞享套餐-58元套餐\t0.00\t1.80|呼叫转移长途费:1.80元,国内漫游主叫通话费(不含港澳台):0.00元\n"
				// + "2015-01-22
				// 14:16:13\t驻马店\t无条件呼转\t15737110188\t1分4秒\t省际长途\t4G飞享套餐-58元套餐\t1.20\n"
				// + "2015-01-22
				// 16:43:26\t驻马店\t呼转主叫\t18703659581\t30秒\t省际长途\t4G飞享套餐-58元套餐\t0.00\n"
				// + "2015-01-22
				// 16:43:26\t驻马店\t无条件呼转\t15737110188\t30秒\t省际长途\t4G飞享套餐-58元套餐\t0.60\n"
				// + "2015-01-29
				// 11:15:18\t郑州\t主叫\t15838218188\t3分7秒\t本地\t4G飞享套餐-58元套餐\t免费通话时长:4分钟\t0.00\n"
				// + "2015-01-29
				// 11:21:44\t郑州\t主叫\t15838218188\t3分44秒\t本地\t4G飞享套餐-58元套餐\t免费通话时长:4分钟\t0.00\n"
				// + "2015-01-30
				// 20:23:35\t郑州\t主叫\t10086\t2秒\t本地\t4G飞享套餐-58元套餐\t0.00\n"
				// + "2015-02-02
				// 12:55:05\t郑州\t主叫\t10086\t12秒\t本地\t动感地带MO套餐2010版\t0.00\n";
				// bossResponse =
				// "MHF2015022717283587920500XZT01 HNYD00201502271728360001 1
				// 245 1 0000调用成功
				// FFFFFFFFQ\t18236464534\t20130108\t460026039646491\t4G飞享套餐-108元套餐（300分钟+1G流量）\t100166000231\t全球通\tG00\t测试卡\t正常\t测试卡\tQ";
				// bossResponse =
				// "MHF2015020515459115920500XZT01 HNYD00201503020923200081 1
				// 226 1 0000调用成功
				// FFFFFFFFA\t13838268859\t20110226\t460003825136137\t飞享套餐本地版-38元套餐\t100166000196\t全球通\tG00\t姚洁\t正常\t姚洁\tA";
				// bossResponse =
				// "MHF2015020515459115912114XZT01 HNYD00201503041100370081 1
				// 571 6 0000调用成功 FFFFFFFF短信签名尝鲜包\t100165000284\t2015-03-02
				// 16:17:50.0\t2099-12-31 23:59:59.0";

				// ********** 测试代码 ***********************
				// logger.info("------------ECP----------------------"+(System.currentTimeMillis()-starttime));
				// 正式请求CRM接口

				/* 2016年11月24日 14:38:32 zzw 4085 修改callRemote 调用 start */
				/*
				 * 老代码 bossResponse = (String)this.remote.callRemote(new
				 * StringTeletext(teletext, accessId, tm.getTeletextTemplate(),
				 * remoteCallContext));
				 */
				bossResponse = (String) this.remote.callRemote(
						new StringTeletext(sysParam, busiParam, type, teletext, accessId, tm.getTeletextTemplate(),
								remoteCallContext, headTraceId, headUserMobile, headUserBrand, headUserCity,
								headPageNum, headRecNum, headSerialNum, headJfserialNum, headProdId));
				// 912114 test QRY020001Test
				// bossResponse =
				// "{\"respCode\":\"00000\",\"respDesc\":\"调用成功!\",\"result\":{\"SO_MEMBER_DEAL\":[{\"EFFTIME\":\"2016-11-11
				// 10:22:07.0\",\"EXPTIME\":\"2016-11-30
				// 23:59:59.0\",\"PRODDESC\":\"包含100M国内流量，订购立即生效，当月有效，月底清零(本套餐内所含流量限当月使用完毕，不可结转、不可共享。可用于2/3/4G网络)\",\"PRODID\":\"100168000350\",\"PRODNAME\":\"100M流量赠送包\"},{\"EFFTIME\":\"2016-11-10
				// 18:52:57.0\",\"EXPTIME\":\"2099-12-31
				// 23:59:59.0\",\"PRODDESC\":\"50M@24\",\"PRODID\":\"100166000147\",\"PRODNAME\":\"手机宽带50M套餐\"},{\"EFFTIME\":\"2016-11-08
				// 17:09:35.0\",\"EXPTIME\":\"2099-12-31
				// 23:59:59.0\",\"PRODDESC\":\"爱家群组虚拟产品\",\"PRODID\":\"100167000084\",\"PRODNAME\":\"爱家群组\"},{\"EFFTIME\":\"2016-11-08
				// 16:58:28.0\",\"EXPTIME\":\"2016-11-30
				// 23:59:59.0\",\"PRODDESC\":\"包含100M国内流量，订购立即生效，当月有效，月底清零(本套餐内所含流量限当月使用完毕，不可结转、不可共享。可用于2/3/4G网络)\",\"PRODID\":\"100168000350\",\"PRODNAME\":\"100M流量赠送包\"},{\"EFFTIME\":\"2016-09-08
				// 18:27:06.0\",\"EXPTIME\":\"2017-03-07
				// 23:59:59.0\",\"PRODDESC\":\"港澳台三地3天流量包\",\"PRODID\":\"100168000385\",\"PRODNAME\":\"港澳台三地3天流量包\"},{\"EFFTIME\":\"2016-08-17
				// 03:01:56.0\",\"EXPTIME\":\"2017-08-15
				// 00:00:00.0\",\"PRODDESC\":\"国际漫游\",\"PRODID\":\"100166000221\",\"PRODNAME\":\"国际漫游\"},{\"EFFTIME\":\"2016-04-13
				// 05:50:23.0\",\"EXPTIME\":\"2099-12-31
				// 23:59:59.0\",\"PRODDESC\":\"手机上网70元包2G,超出套餐流量部分0.29元/M(本套餐内当月剩余流量可结转至次月月底前使用，在使用时，同一类型流量优先扣减上月结转流量。若您变更套餐，则原套餐剩余流量不再进行结转，新套餐流量不清零服务次月初生效。当月新开通套餐流量不清零服务也于次月生效)\",\"PRODID\":\"100168000005\",\"PRODNAME\":\"70元手机上网流量包\"}]}}";
				// 400065 test QRY040020Test
				// bossResponse =
				// "{\"respCode\":\"00000\",\"respDesc\":\"调用成功!\",\"result\":{\"SO_MEMBER_DEAL\":[{\"FREE_RES\":\"50\",\"FREE_RES_LEFT\":\"50\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"60001294\",\"ITEM_NAME\":\"4G飞享套餐-38A元套餐50分300M2015版新-免费通话时长\",\"RESOURCESCODE\":\"01\",\"RESOURCE_TYPE\":\"语音\",\"SUB_DATE\":\"20161122000000\",\"UNIT_DES\":\"分钟\"},{\"FREE_RES\":\"307200\",\"FREE_RES_LEFT\":\"307200\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"61001826\",\"ITEM_NAME\":\"4G飞享套餐-38A元套餐50分300M2015版新-免费流量\",\"RESOURCESCODE\":\"04\",\"RESOURCE_TYPE\":\"流量\",\"SUB_DATE\":\"20161122000000\",\"UNIT_DES\":\"KB\"}]}}";
				// 920012 test QRY040002Test
				// bossResponse =
				// "{\"respCode\":\"00000\",\"respDesc\":\"调用成功!\",\"result\":{\"SO_MEMBER_DEAL\":[{\"CURSCR\":\"0.00\"}]}}";
				// 920004 test QRY040101Test
				// bossResponse =
				// "{\"respCode\":\"00000\",\"respDesc\":\"调用成功!\",\"result\":{\"SO_MEMBER_DEAL\":[{\"USERINFO_CURRCREDIT\":\"600000\"}]}}";
				// 400026 test QRY040102Test
				// bossResponse =
				// "{\"respCode\":\"00000\",\"respDesc\":\"调用成功!\",\"result\":{\"SO_MEMBER_DEAL\":[{\"AREA_CODE\":\"371\",\"BALANCE\":\"325.00\",\"BRAND_ID\":\"100011100001\",\"B_CALL_DURATION\":\"0\",\"CALL_DURATION\":\"0\",\"CALL_DURATION_1\":\"0\",\"CALL_DURATION_2\":\"0\",\"CALL_DURATION_3\":\"0\",\"CALL_DURATION_4\":\"0\",\"CREDIT_RATING\":\"3\",\"CUR_RECE_AMOUNT\":\"0.00\",\"FEE_PER\":\"0%\",\"GENERAL_PAY\":\"0\",\"GPRS_FLOW\":\"0\",\"GPRS_FLOW_1\":\"0\",\"GPRS_FLOW_2\":\"0\",\"GPRS_FLOW_3\":\"0\",\"GPRS_FLOW_4\":\"0\",\"GPRS_FLOW_PER_1\":\"0\",\"GPRS_FLOW_PER_2\":\"0\",\"GPRS_FLOW_PER_3\":\"0\",\"GPRS_FLOW_PER_4\":\"0\",\"GPRS_FLOW_PER_5\":\"0\",\"GPRS_FLOW_PER_6\":\"0\",\"GPRS_FLOW_PER_7\":\"0\",\"GPRS_FLOW_PER_8\":\"0\",\"GREEN_PAY\":\"0\",\"HIS_MONTH_AMOUNT\":\"201608|0.00;201609|0.00;201610|0.00\",\"LAST_MONTH_RECE_AMOUNT\":\"0.00\",\"LOSE_PRANT_TREE\":\"0\",\"OFFER_ID\":\"100166000227\",\"OFFER_ID_1\":\"100168000257\",\"OFFER_ID_2\":\"100168000245\",\"OFFER_ID_3\":\"100168000248\",\"OFFER_NAME\":\"4G飞享套餐-88元B套餐（200分钟+700M流量）\",\"OFFER_NAME_1\":\"4G飞享套餐-18元套餐（100M）\",\"OFFER_NAME_2\":\"4G飞享套餐-38A元套餐（50分+300M）2015版新\",\"OFFER_NAME_3\":\"4G飞享套餐-58A元套餐（100分+500M）2015版新\",\"PRANT_TREE\":\"0\",\"REDUCE_CARBON\":\"0\",\"SMS_NUM\":\"0\",\"SMS_NUM_1\":\"0\",\"SMS_NUM_2\":\"0\",\"SMS_NUM_3\":\"0\",\"SMS_NUM_4\":\"0\",\"SPE_BALANCE\":\"10.00\",\"Z_CALL_DURATION\":\"0\"}]}}";
				// 201628 test QRY040206Test
				// bossResponse =
				// "{\"respCode\":\"00000\",\"respDesc\":\"调用成功!\",\"result\":{\"SO_MEMBER_DEAL\":[{\"EFF_DATE\":\"2016-11-01
				// 05:54:37\",\"EXP_DATE\":\"2099-12-31
				// 23:59:59\",\"OPT_CODE\":\"GJG9\",\"OPT_DATE\":\"2016-10-31
				// 23:59:59\",\"OPT_NAME\":\"积分计算-积分增加\",\"REMARK\":\"月积分计算\",\"SCORE\":\"127\",\"TYPE\":\"01：消费积分\"}]}}";
				/* 2016年11月24日 14:38:32 zzw 4085 修改callRemote 调用 end */

				// logger.info("------------ECP------------1----------"+(System.currentTimeMillis()-starttime));
				// logger.debug("BOSS响应: \n" + bossResponse);

				pt.trace("请求BOSS...", n);
			} catch (Exception e) {
				logger.error("请求BOSS失败[" + e.getMessage() + "]!");
				throw new ServiceException(e);
			}

			// 根据Boss返回报文，组装返回对象
			if (bossResponse != null && bossResponse.trim().length() > 0) {
				bossResponse = bossResponse.trim();
				// 创建返回对象
				String serviceResultRealClass = this.getResultClassName();
				Class<?> retClazz = null;
				BaseServiceInvocationResult result = null;
				try {
					n = pt.addBreakPoint();
					retClazz = Class.forName(serviceResultRealClass);
					result = (BaseServiceInvocationResult) (retClazz.newInstance());
					pt.trace("实例化返回对象...", n);
				} catch (ClassNotFoundException e) {
					logger.error("命令字[" + cmd + "]逻辑接口返回类[" + serviceResultRealClass + "], 没有找到!");
					throw new ServiceException(e);
				} catch (InstantiationException e) {
					logger.error("命令字[" + cmd + "]逻辑接口返回类[" + serviceResultRealClass + "], 实例化失败!");
					throw new ServiceException(e);
				} catch (IllegalAccessException e) {
					logger.error("命令字[" + cmd + "]逻辑接口返回类[" + serviceResultRealClass + "], 实例化失败!");
					throw new ServiceException(e);
				} catch (Exception e) {
					throw new ServiceException(e);
				}
				// 根据配置, 为返回对象设置默认值
				n = pt.addBreakPoint();
				this.setDefaultReturnValue(result);
				pt.trace("设置返回对象默认值...", n);
				try {
					// 根据配置的result mapping, 往返回对象里填值
					n = pt.addBreakPoint();

					String mapKey = (tm.getResultMappingId() == null || "".equals(tm.getResultMappingId().trim()))
							? tm.getTeletextTemplate() : tm.getResultMappingId();
					/*
					 * 2016年11月24日 15:16:46 zzw 4085 修改添加 jbossremoting
					 * 返回json数据解析 根据数据库type类型进行判断 start
					 */
					if (StringTeletext.DEFAULT_REQ_TYPE.equals(type)) {
						this.evaluateResult2Array(cmd, tm.getTeletextTemplate(), bossResponse, result, mapKey); // 2016年11月24日
						// 15:18:01之前就这一行代码
					} else if (StringTeletext.REMOTING_REQ_TYPE.equals(type)) {
						this.evaluateRemotingResult2Array(cmd, tm.getTeletextTemplate(), bossResponse, result, mapKey);
					}
					/*
					 * 2016年11月24日 15:16:46 zzw 4085 修改添加 jbossremoting
					 * 返回json数据解析 根据数据库type类型进行判断 end
					 */

					pt.trace("配...", n);
				} catch (Exception e) {
					throw new ServiceException(e);
				}
				// 如果配置了teletext resover, 再调用那个resolver往里面填值
				String resolverClassName = tm.getResolverClass();
				if (resolverClassName != null && resolverClassName.trim().length() > 0) {
					n = pt.addBreakPoint();
					Class<?> resolverClass = null;
					ITeletextResolver resolver = null;
					try {
						resolverClass = Class.forName(resolverClassName);
						resolver = (ITeletextResolver) (resolverClass.newInstance());
					} catch (ClassNotFoundException e) {
						logger.error("命令字[" + cmd + "]报文手动装配类[" + resolverClassName + "], 没有找到!");
						throw new ServiceException(e);
					} catch (InstantiationException e) {
						logger.error("命令字[" + cmd + "]逻辑接口返回类[" + resolverClassName + "], 实例化失败!");
						throw new ServiceException(e);
					} catch (IllegalAccessException e) {
						logger.error("命令字[" + cmd + "]逻辑接口返回类[" + resolverClassName + "], 实例化失败!");
						throw new ServiceException(e);
					} catch (Exception e) {
						throw new ServiceException(e);
					}
					try {
						resolver.resolve(result, bossResponse, this.params);
					} catch (Exception e) {
						throw new ServiceException(e);
					}
					pt.trace("调用resolver...", n);
				}
				return result;
			} else {
				throw new ServiceException("BOSS空响应!");
			}
		}
		return null;
	}

	/**
	 * 根据配置, 为返回对象设置默认值
	 *
	 * @param result
	 * @throws ServiceException
	 */
	private void setDefaultReturnValue(BaseServiceInvocationResult result) throws ServiceException {
		for (int i = 0; i < this.serviceConfig.getOutput().getOutputFields().size(); i++) {
			ServiceOutputField outputField = this.serviceConfig.getOutput().getOutputFields().get(i);
			String fieldName = outputField.getName();
			String defValue = outputField.getDefaultValue();
			if (defValue != null && defValue != "") {
				String dataType = outputField.getDataType();
				Object realVal = null;
				if (DataTypeConstants.STRING.equalsIgnoreCase(dataType)) {
					realVal = defValue;
				} else if (DataTypeConstants.INT.equalsIgnoreCase(dataType)) {
					defValue = defValue.trim();
					realVal = Integer.parseInt(defValue);
				} else if (DataTypeConstants.DOUBLE.equalsIgnoreCase(dataType)) {
					defValue = defValue.trim();
					realVal = Double.parseDouble(defValue);
				} else if (DataTypeConstants.LONG.equalsIgnoreCase(dataType)) {
					defValue = defValue.trim();
					realVal = Long.parseLong(defValue);
				} else if (DataTypeConstants.LIST.equalsIgnoreCase(dataType)) {
					realVal = new ArrayList<>();
				} else if (DataTypeConstants.CLASS.equalsIgnoreCase(dataType)) {
					realVal = new Object();
				} else {
					throw new ServiceException("数据类型不正确[" + dataType + "]!");
				}
				try {
					callSetter(result, fieldName, realVal, dataType);
				} catch (Exception e) {
					logger.error("设置默认值失败!");
					throw new ServiceException(e);
				}
			}
		}
	}

	private void evaluateResult2Array(String cmd, String bossFunctionId, String bossResponse,
									  BaseServiceInvocationResult result, String bossTemplateId) throws Exception {
		PerformanceTracer pt = PerformanceTracer.getInstance();
		long n = pt.addBreakPoint();
		pt.trace("array parse...", n);

		Stack<String> objStack = new Stack<String>();
		List<ServiceResultMapping> resultMapping = this.getServiceConfig().getImpl().getResultMapping(bossTemplateId);

		this.evaluateResult2Array(bossResponse, result, resultMapping, objStack);
	}

	/**
	 * 根据meta模板，解析boss报文与mapping对应信息
	 *
	 * @param item          报文节点
	 * @param obj           返回对象
	 * @param resultMapping 配置mapping
	 * @param objStack
	 * @throws Exception
	 */
	private void evaluateResult2Array(Object item, Object obj, List<ServiceResultMapping> resultMapping,
									  Stack<String> objStack) throws Exception {
		BaseServiceInvocationResult result = (BaseServiceInvocationResult) obj;

		Message bossMessage = MessageUtil.parse((String) item);
		if (null == bossMessage) {
			result.setResultCode(MessageUtil.LOGIC_ERROR);
			result.setBossCode("-9999");
			result.setErrorMessage("查询Boss接口失败.");
			return;
		}

		String bossCode = bossMessage.getHead().getCode();
		String bossDesc = bossMessage.getHead().getDesc();
		if (MessageUtil.BOSS_CODE.equals(bossCode)) {
			result.setResultCode(MessageUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossMessage.getBody().toString());

			if (resultMapping == null || resultMapping.size() == 0 || item == null) {
				return;
			}
			String value = "";
			for (int i = 0; i < resultMapping.size(); i++) {
				ServiceResultMapping resultMap = resultMapping.get(i);

				objStack.push(resultMap.getName());
				BeanFieldInfo outputField = this.getBeanFieldInfo(objStack);
				if (resultMap.getChildResultMapping() != null && resultMap.getChildResultMapping().size() > 0) {
					// 如果有子mapping, 表示其数据类型是list或是class
					if (DataTypeConstants.LIST.equalsIgnoreCase(outputField.getDataType())) {
						List<Object> listObj = (List<Object>) (callGetter(obj, resultMap.getName()));
						if (listObj == null) {
							listObj = new ArrayList<>();
							callSetter(obj, resultMap.getName(), listObj, DataTypeConstants.LIST);
						}

						List<String[]> valueList = bossMessage.getBody().asList();
						for (int j = 0; j < valueList.size(); j++) {
							String extensionClassName = this.getExtensionClassName(outputField.getClassName());
							Class<?> extensionClazz = Class.forName(extensionClassName);
							Object newObject = extensionClazz.newInstance();
							listObj.add(newObject);

							String[] objArray = valueList.get(j);
							for (ServiceResultMapping mapping : resultMap.getChildResultMapping()) {
								if (StringUtils.isNotEmpty(mapping.getIndex())) {
									int index = NumberUtils.toInt(mapping.getIndex());
									if (index < objArray.length) {
										value = objArray[index];
									} else {
										value = "";
									}

									objStack.push(mapping.getName());
									BeanFieldInfo fieldInfo = this.getBeanFieldInfo(objStack);
									if (null != fieldInfo) {
										callSetter(newObject, mapping.getName(), value, fieldInfo.getDataType());
									}
									objStack.pop();
								}
							}
						}
					} else if (DataTypeConstants.CLASS.equalsIgnoreCase(outputField.getDataType())) {
						String[] valueArray = bossMessage.getBody().asArray();
						String extensionClassName = this.getExtensionClassName(outputField.getClassName());
						Class<?> extensionClazz = Class.forName(extensionClassName);
						Object newObject = extensionClazz.newInstance();

						for (ServiceResultMapping mapping : resultMap.getChildResultMapping()) {
							if (StringUtils.isNotEmpty(mapping.getIndex())) {
								int index = NumberUtils.toInt(mapping.getIndex());
								value = valueArray[index];
								objStack.push(mapping.getName());
								BeanFieldInfo fieldInfo = this.getServiceConfig().getFieldInfoMap()
										.get(mapping.getName());
								if (null != fieldInfo) {
									callSetter(newObject, mapping.getName(), value, fieldInfo.getDataType());
								}
								objStack.pop();
							}
						}

						callSetter(obj, resultMap.getName(), newObject, DataTypeConstants.CLASS);
					}
				} else {
					String indexStr = resultMap.getIndex();
					if (StringUtils.isNotEmpty(indexStr)) {
						String[] columns = bossMessage.getBody().asArray();

						int indx = NumberUtils.toInt(indexStr);
						if (columns.length > indx) {
							value = columns[indx];
						}


						if (resultMap.getValueMapStr() != null && resultMap.getValueMapStr().trim().length() > 0) {
							// 做值转换
							Map<String, String> valueMap = resultMap.getValueMap();

							value = this.getMapedValue(valueMap, value);
						}

						if (null != value && null != outputField && null != outputField.getDataType()) {
							callSetter(obj, resultMap.getName(), value, outputField.getDataType());
						}
					}
				}
				objStack.pop();
			}
		} else {
			result.setResultCode(MessageUtil.LOGIC_ERROR);
			result.setBossCode(bossCode);
			result.setErrorMessage(bossDesc);
		}
	}

	/* 2016年11月24日 15:19:10 zzw 4085 添加解析jbossremoting 返回json数据解析 start */
	private void evaluateRemotingResult2Array(String cmd, String bossFunctionId, String bossResponse,
											  BaseServiceInvocationResult result, String bossTemplateId) throws Exception {
		PerformanceTracer pt = PerformanceTracer.getInstance();
		long n = pt.addBreakPoint();
		pt.trace("array parse...", n);

		Stack<String> objStack = new Stack<String>();
		List<ServiceResultMapping> resultMapping = this.getServiceConfig().getImpl().getResultMapping(bossTemplateId);

		this.evaluateRemotingResult2Array(bossResponse, result, resultMapping, objStack);
	}

	/**
	 * 根据meta模板，解析boss报文与mapping对应信息
	 *
	 * @param item          报文节点
	 * @param obj           返回对象
	 * @param resultMapping 配置mapping
	 * @param objStack
	 * @throws Exception
	 */
	private void evaluateRemotingResult2Array(Object item, Object obj, List<ServiceResultMapping> resultMapping,
											  Stack<String> objStack) throws Exception {
		BaseServiceInvocationResult result = (BaseServiceInvocationResult) obj;

		MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) item);
		if (null == bossJson) {
			result.setResultCode(MessageUtil.LOGIC_ERROR);
			result.setBossCode("-9999");
			result.setErrorMessage("查询Boss接口失败.");
			return;
		}

		String bossCode = bossJson.getBossCode();
		String bossDesc = bossJson.getBossDesc();
		if (MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
			result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());

			if (resultMapping == null || resultMapping.size() == 0 || item == null) {
				return;
			}
			String value = "";
			for (int i = 0; i < resultMapping.size(); i++) {
				ServiceResultMapping resultMap = resultMapping.get(i);

				objStack.push(resultMap.getName());
				BeanFieldInfo outputField = this.getBeanFieldInfo(objStack);
				if (resultMap.getChildResultMapping() != null && resultMap.getChildResultMapping().size() > 0) {
					// 如果有子mapping, 表示其数据类型是list或是class
					if (DataTypeConstants.LIST.equalsIgnoreCase(outputField.getDataType())) {
						List listObj = (List) (callGetter(obj, resultMap.getName()));
						if (listObj == null) {
							listObj = new ArrayList();
							callSetter(obj, resultMap.getName(), listObj, DataTypeConstants.LIST);
						}

						JSONArray valueList = bossJson.getJSONArrayResult();
						for (int j = 0; j < valueList.size(); j++) {
							String extensionClassName = this.getExtensionClassName(outputField.getClassName());
							Class extensionClazz = Class.forName(extensionClassName);
							Object newObject = extensionClazz.newInstance();
							listObj.add(newObject);

							JSONObject jsonObj = (JSONObject) valueList.get(j);
							for (ServiceResultMapping mapping : resultMap.getChildResultMapping()) {
								if (StringUtils.isNotEmpty(mapping.getJsonkey())) {
									value = jsonObj.getString(mapping.getJsonkey());
									objStack.push(mapping.getName());
									BeanFieldInfo fieldInfo = this.getBeanFieldInfo(objStack);
									if (null != fieldInfo) {
										callSetter(newObject, mapping.getName(), value, fieldInfo.getDataType());
									}
									objStack.pop();
								}
							}
						}
					} else if (DataTypeConstants.CLASS.equalsIgnoreCase(outputField.getDataType())) {
						JSONObject jsonObj = bossJson.getJSONObjectResult();
						String extensionClassName = this.getExtensionClassName(outputField.getClassName());
						Class extensionClazz = Class.forName(extensionClassName);
						Object newObject = extensionClazz.newInstance();

						for (ServiceResultMapping mapping : resultMap.getChildResultMapping()) {
							if (StringUtils.isNotEmpty(mapping.getJsonkey())) {
								value = jsonObj.getString(mapping.getJsonkey());
								objStack.push(mapping.getName());
								BeanFieldInfo fieldInfo = this.getServiceConfig().getFieldInfoMap()
										.get(mapping.getName());
								if (null != fieldInfo) {
									callSetter(newObject, mapping.getName(), value, fieldInfo.getDataType());
								}
								objStack.pop();
							}
						}

						callSetter(obj, resultMap.getName(), newObject, DataTypeConstants.CLASS);
					}
				} else {
					String jsonkey = resultMap.getJsonkey();
					if (StringUtils.isNotEmpty(jsonkey)) {
						JSONObject jsonObj = bossJson.getJSONArrayResult().getJSONObject(0);

						value = jsonObj.getString(jsonkey);

						if (resultMap.getValueMapStr() != null && resultMap.getValueMapStr().trim().length() > 0) {
							// 做值转换
							Map<String, String> valueMap = resultMap.getValueMap();

							value = this.getMapedValue(valueMap, value);
						}

						callSetter(obj, resultMap.getName(), value, outputField.getDataType());
					}
				}
				objStack.pop();
			}
		} else {
			result.setResultCode(MessageUtil.LOGIC_ERROR);
			result.setBossCode(bossCode);
			result.setErrorMessage(bossDesc);
		}
	}
	/* 2016年11月24日 15:19:10 zzw 4085 添加解析jbossremoting 返回json数据解析 end */

	private static Object callGetter(Object instance, String fieldName)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		char firstLetter = fieldName.charAt(0);
		firstLetter = Character.toUpperCase(firstLetter);
		String getterMethodName = "get" + firstLetter + fieldName.substring(1);
		Method m = instance.getClass().getMethod(getterMethodName, new Class[]{});
		return m.invoke(instance, new Object[]{});
	}

	private String getBeanFieldExpression(Stack objStack) {
		StringBuffer expression = new StringBuffer();
		for (int i = 0; i < objStack.size(); i++) {
			String name = (String) objStack.get(i);
			expression.append(name).append(".");
		}
		if (expression.length() > 0) {
			return expression.substring(0, expression.length() - 1);
		} else {
			return expression.toString();
		}
	}

	private String[] getEvaluateExpression(String expression) {
		if (expression.toLowerCase().startsWith("xpath(")) {
			expression = expression.substring(6, expression.length() - 1);
			return new String[]{"xpath", expression};
		}
		return new String[]{"UNKNOWN", ""};
	}

	private BeanFieldInfo getBeanFieldInfo(Stack objStack) {
		String expression = this.getBeanFieldExpression(objStack);
		return this.getServiceConfig().getFieldInfoMap().get(expression);
	}

	private static void callSetter(Object instance, String fieldName, Object value, String dataType)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		try {
			char firstLetter = fieldName.charAt(0);
			firstLetter = Character.toUpperCase(firstLetter);
			String setterMethodName = "set" + firstLetter + fieldName.substring(1);
			Class[] params = new Class[1];
			if (DataTypeConstants.STRING.equalsIgnoreCase(dataType)) {
				params[0] = String.class;
				Method m = instance.getClass().getMethod(setterMethodName, params);
				m.invoke(instance, value == null ? "" : value.toString());
			} else if (DataTypeConstants.INT.equalsIgnoreCase(dataType)) {
				params[0] = Integer.TYPE;
				Method m = instance.getClass().getMethod(setterMethodName, params);
				if (value != "" && value != null) {
					m.invoke(instance, Integer.valueOf("" + value));
				}
			} else if (DataTypeConstants.DOUBLE.equalsIgnoreCase(dataType)) {
				params[0] = Double.TYPE;
				Method m = instance.getClass().getMethod(setterMethodName, params);
				m.invoke(instance, Double.valueOf("" + value));
			} else if (DataTypeConstants.LONG.equalsIgnoreCase(dataType)) {
				params[0] = Long.TYPE;
				Method m = instance.getClass().getMethod(setterMethodName, params);

				// 邵琪修改-20100115，截取小数点前的数据
				if (value != null && value.toString().indexOf(".") > 0) {
					value = (value.toString()).substring(0, (value.toString()).indexOf("."));
				}
				// 邵琪修改-20100309，为空时不赋值
				if (("" + value).trim().length() > 0)
					m.invoke(instance, Long.valueOf("" + value));
			} else if (DataTypeConstants.LIST.equalsIgnoreCase(dataType)) {
				params[0] = List.class;
				Method m = instance.getClass().getMethod(setterMethodName, params);
				m.invoke(instance, (List) value);
			} else if (DataTypeConstants.CLASS.equalsIgnoreCase(dataType)) {
				params[0] = value.getClass();
				Method m = instance.getClass().getMethod(setterMethodName, params);
				m.invoke(instance, value);
			} else {
				throw new IllegalArgumentException("数据类型不支持[" + dataType + "]!");
			}
		} catch (Exception e) {
			logger.error("设置参数", e);
		}
	}

	/**
	 * 转换值 value-map="平台值:BOSS值;"
	 *
	 * @param valueMap
	 * @param value
	 * @return
	 */
	private String getMapedValue(Map<String, String> valueMap, String value) {
		if (value == null || value.trim().length() == 0) {
			return value;
		}
		String s = valueMap.get(value);
		// 如果没有配置相关的
		if (s == null) {
			// 看看有没有匹配"*"的
			s = valueMap.get("*");
			if (s != null) {
				return s;
			}
		} else {
			return s;
		}
		return value;
	}

	private String getExtensionClassName(String oName) {
		// 返回扩展类包名.类名", 例"com.xwtech.xwecp.service.logic.pojo.XXXX
		String pojoPackage = AppConfig.getConfigValue("service_impl", "extensionClassPackage");
		pojoPackage = pojoPackage == null ? "com.xwtech.xwecp.service.logic.pojo" : pojoPackage;
		return pojoPackage + "." + oName;
	}

	private String getResultClassName() {
		// 返回逻辑接口包名.命令字 + "Result",
		// 例"com.xwtech.xwecp.service.logic.pojo.UserInfoQueryResult
		String pojoPackage = AppConfig.getConfigValue("service_impl", "pojoPackage");
		pojoPackage = pojoPackage == null ? "com.xwtech.xwecp.service.logic.pojo" : pojoPackage;
		String cmd = this.getServiceConfig().getCmd();
		return pojoPackage + "." + cmd + "Result";
	}

	/**
	 * 根据正则表达式获取相匹配报文模板
	 * 此处匹配 <boss-teletext-mapping>模板配置里面的模板。
	 * 如果有多个模板根据match, param-name来进行匹配
	 *
	 * @return
	 */
	private TeletextMapping getBossTeletextMapping() {
		/**
		 * 2011-11-15 maofw 修改 获得渠道信息
		 */
		ServiceMessage requestServiceMessage = this.getServiceInfo().getServiceMessage();
		String channel = (requestServiceMessage == null || requestServiceMessage.getHead() == null) ? ""
				: requestServiceMessage.getHead().getChannel();
		/**
		 * end
		 */

		for (int i = 0; i < this.serviceConfig.getImpl().getTeletextMapping().size(); i++) {
			TeletextMapping m = this.serviceConfig.getImpl().getTeletextMapping().get(i);
			String paramName = m.getParamName();
			String match = m.getMatch();
			if (paramName == null || paramName.trim().length() == 0 || match == null || match.trim().length() == 0
					|| "*".equals(paramName.trim()) || "*".equals(match.trim())) {
				return m;
			}

			// 加入函数支持
			String paramValue = "";
			try {
				StringBuffer valBuf = new StringBuffer();
				boolean b = this.evaluateFunctions(paramName, valBuf);
				if (!b) {
					RequestParameter parameter = this.getParameter(paramName);
					if (parameter != null) {
						paramValue = parameter.getParameterValue().toString();
					}
				} else {
					paramValue = valBuf.toString();
				}
			} catch (Exception e) {
				logger.error("装配执行函数解析出错!", e);
			}

			// 将函数返回结果再和正则表达式作匹配
			if (paramValue.matches(match)) {
				return m;
			}
		}
		return null;
	}

	private boolean evaluateFunctions(String in, StringBuffer retBuf) throws Exception {
		if (expressionEvaluateBuffer.get(in) != null) {
			retBuf.append(expressionEvaluateBuffer.get(in));
			return true;
		}
		String pStr = "\\%.{2,200}[\\(.*\\)]++";
		String groupStr = "\\%(.*?\\()(.*)?\\)";
		Pattern groupPattern = Pattern.compile(groupStr);
		Pattern p = Pattern.compile(pStr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(in);
		StringBuffer buf = new StringBuffer();
		boolean flag = false;
		while (matcher.find()) {
			String tmp = matcher.group();
			Matcher m = groupPattern.matcher(tmp);
			if (m.find()) {
				if (m.groupCount() == 2) {
					flag = true;
					String functionName = m.group(1);
					functionName = functionName.substring(0, functionName.length() - 1); // 去掉结尾的"("
					String parameter = m.group(2);
					String value = "";
					if (parameter.startsWith("\"") && parameter.endsWith("\"")) // 如果直接是字串
					{
						parameter = parameter.substring(1, parameter.length() - 1); // 去掉两端的引号
						// 执行函数
						value = this.executeExternalFunction(functionName, parameter);
					} else if (parameter.matches(pStr)) // 如果参数本身又是一个函数
					{
						value = this.recursiveEvaluateFunction(functionName, parameter);
					} else
					// 如果括号里不是固定字串也不是函数, 则把这个当成输入变量处理
					{
						if (parameter.indexOf('|') > 0) {// 多个参数拼接
							String[] parameters = parameter.split("\\|");
							StringBuffer strBuf = new StringBuffer();
							for (int i = 0; i < parameters.length; i++) {
								RequestParameter rp = this.getParameter(parameters[i]);
								if (rp.getParameterValue() != null) {
									if (strBuf.length() != 0)
										strBuf.append("|");
									strBuf.append(rp.getParameterValue());
								}
								// parameter = rp.getParameterValue() == null ?
								// null : rp.getParameterValue().toString();
							}
							parameter = strBuf.toString();
						} else {// 唯一参数
							RequestParameter rp = this.getParameter(parameter);
							parameter = rp.getParameterValue() == null ? null : rp.getParameterValue().toString();
						}
						value = this.executeExternalFunction(functionName, parameter);
					}
					matcher.appendReplacement(buf, value);
				}
			}
		}
		matcher.appendTail(buf);
		retBuf.delete(0, retBuf.length());
		retBuf.append(buf);
		if (flag) {
			expressionEvaluateBuffer.put(in, retBuf.toString());
		}
		// return ret.replaceAll("\\\\$", "$");
		return flag;
	}

	private String recursiveEvaluateFunction(String functionName, String parameter) {
		if (parameter.startsWith("\"") && parameter.endsWith("\"")) {
			parameter = parameter.substring(1, parameter.length() - 1); // 去掉两端的引号
			return this.executeExternalFunction(functionName, parameter);
		} else {
			String groupStr = "\\%(.*?\\()(.*)?\\)";
			Pattern groupPattern = Pattern.compile(groupStr);
			Matcher m = groupPattern.matcher(parameter);
			if (m.find()) {
				if (m.groupCount() == 2) {
					String subName = m.group(1);
					subName = subName.substring(0, subName.length() - 1); // 去掉结尾的"("
					String subParam = m.group(2);
					return this.executeExternalFunction(functionName, recursiveEvaluateFunction(subName, subParam));
				}
			}
			return functionName + "(" + parameter + ")";
		}
	}

	private String executeExternalFunction(String functionName, String parameter) {
		IExternalFunctionExecutor functionExecutor = this.externalFunctionMap.get(functionName);
		if (functionExecutor != null) {
			return functionExecutor.execute(parameter);
		}
		logger.warn("未配置函数[" + functionName + "]!");
		return functionName + "(" + parameter + ")";
	}

	protected void validateInput() throws IllegalServiceInputException {
		for (int i = 0; i < this.serviceConfig.getInput().getParams().size(); i++) {
			ServiceInputParameter input = this.serviceConfig.getInput().getParams().get(i);
			String regular = input.getRegular();
			if (regular != null && regular.trim().length() > 0) {
				RequestParameter param = this.getParameter(input.getName());
				if (param != null) {
					Object val = param.getParameterValue();
					if (val != null) {
						String sVal = val.toString();
						if (!sVal.matches(regular)) {
							logger.error("参数校验失败!");
							throw new IllegalServiceInputException("请求参数校验失败, 命令字[" + this.getServiceConfig().getCmd()
									+ "],输入参数名[" + input.getName() + "], 输入值[" + val + "],验证方式[" + regular + "]!");
						}
					}
				} else {
					logger.error("参数校验失败!");
					throw new IllegalServiceInputException("请求参数校验失败, 命令字[" + this.getServiceConfig().getCmd()
							+ "],输入参数名[" + input.getName() + "], 输入值[(NULL)],验证方式[" + regular + "]!");
				}
			}
		}
	}

	private RequestParameter getParameter(String name) {
		for (int i = 0; i < this.params.size(); i++) {
			RequestParameter param = this.params.get(i);
			if (param.getParameterName().equals(name)) {
				return param;
			}
		}
		return null;
	}

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	public List<RequestParameter> getParams() {
		return params;
	}

	public void setParams(List<RequestParameter> params) {
		this.params = params;
	}

	public BossTeletextUtil getTeletextUtil() {
		return teletextUtil;
	}

	public void setTeletextUtil(BossTeletextUtil teletextUtil) {
		this.teletextUtil = teletextUtil;
	}

	public XPathFunctionResolver getFunctionResolver() {
		return functionResolver;
	}

	public void setFunctionResolver(XPathFunctionResolver functionResolver) {
		this.functionResolver = functionResolver;
	}

	public IBossResponseValidator getBossResponseValidator() {
		return bossResponseValidator;
	}

	public void setBossResponseValidator(IBossResponseValidator bossResponseValidator) {
		this.bossResponseValidator = bossResponseValidator;
	}

	public Map<String, String> getExpressionEvaluateBuffer() {
		return expressionEvaluateBuffer;
	}

	public void setExpressionEvaluateBuffer(Map<String, String> expressionEvaluateBuffer) {
		this.expressionEvaluateBuffer = expressionEvaluateBuffer;
	}

	public Map<String, IExternalFunctionExecutor> getExternalFunctionMap() {
		return externalFunctionMap;
	}

	public void setExternalFunctionMap(Map<String, IExternalFunctionExecutor> externalFunctionMap) {
		this.externalFunctionMap = externalFunctionMap;
	}

}
