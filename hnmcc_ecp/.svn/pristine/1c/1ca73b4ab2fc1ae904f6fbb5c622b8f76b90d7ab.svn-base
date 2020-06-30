package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY040006Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 密码修改
 * 
 * @author 吴宗德
 *
 */
public class ChangeUserPassInvocation extends BaseInvocation implements ILogicalService
{
	private static final Logger logger = LoggerFactory.getLogger(ChangeUserPassInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;
	
	private WellFormedDAO wellFormedDAO;
	
	/**
     * 请求Boss的接口
     */
    private static final String REQUEST_BOSSTELETEXT = "cc_csetpassword_554";
    
	public ChangeUserPassInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		
	}
	
	@SuppressWarnings("unchecked")
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params)
	{
		QRY040006Result result = new QRY040006Result();
		String reqXml = "";
		String rspXml = "";
		try
		{
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT, this.generateCity(params)));			
		     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
		      {
		            result.setBossCode(LOGIC_ERROR);
		            result.setErrorCode(LOGIC_ERROR);
		            return result;
		      }		            
		       String errCode = new String(rspXml.getBytes(), 66, 4);
		       String errDesc = new String(rspXml.getBytes(), 70, 42);		            
		       result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
		       result.setBossCode(errCode);
		       result.setErrorMessage(StringUtils.trim(errDesc));			
		}
		catch (Exception e)
		{
			logger.error("ChangeUserPassInvocation executeService error :", e);
		}
		return result;
	}
	/**
	@SuppressWarnings("unchecked")
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params)
	{
		QRY040006Result res = new QRY040006Result();
		String city = null;
		try
		{
			res.setResultCode(LOGIC_SUCESS);
			res.setErrorMessage("");
			
			int type = Integer.parseInt(this.getParameters(params, "type").toString());//1：手机账号 2：宽带账号
			
			city = (String)this.getParameters(params, "context_ddr_city");
			if (StringUtils.isBlank(city)) {
				String phoneNum = (String)this.getParameters(params, "phoneNum");
				String mu = getPhone(phoneNum);
				
				if (type == 2 && !"".equals(mu)) {
					city = internetCitysMap.get(mu);
				} else {
					//根据手机号码查询地市信息
					BaseResult userInfoResult = getCity(accessId, config, params);
					if (LOGIC_SUCESS.equals(userInfoResult.getResultCode())) {
						Map userInfoMap = (Map)userInfoResult.getReObj();
						
						city = (String)userInfoMap.get("city");
					} else {
						res.setResultCode(LOGIC_ERROR);
						res.setErrorCode(userInfoResult.getErrorCode());
						res.setErrorMessage(userInfoResult.getErrorMessage());
						return res;
					}
				}
			}
			
			this.setParameter(params, ServiceExecutor.ServiceConstants.USER_CITY, city);
			this.setParameter(params, "context_ddr_city", city);
			

			String bossTemplate = "cc_csetpassword_554";
			if (type == 2) {
				bossTemplate = "cc_cinternetuser_451";
			}

			//校验证件号码
			BaseResult changeUserPassResult = changeUserPass(accessId, config, params, bossTemplate);
			
			if(null != changeUserPassResult.getReObj())
			{
				res.setDefaultPwd((String)changeUserPassResult.getReObj());
			}
			
			if (!LOGIC_SUCESS.equals(changeUserPassResult.getResultCode())) {
				res.setResultCode(LOGIC_ERROR);
				res.setErrorCode(changeUserPassResult.getErrorCode());
				res.setErrorMessage(changeUserPassResult.getErrorMessage());
			}
			
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return res;
	}
	
	*/
	/**
	 * 密码修改
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 
	protected BaseResult changeUserPass(final String accessId, final ServiceConfig config, final List<RequestParameter> params, final String bossTemplate) {
		BaseResult res = new BaseResult();
		String reqXml = "";
		String rspXml = "";
		ErrorMapping errDt = null;
		try {
			reqXml = this.bossTeletextUtil.mergeTeletext(bossTemplate, params);

			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, bossTemplate, this.generateCity(params)));
			if (null != rspXml && !"".equals(rspXml))
			{
				Element root = this.getElement(rspXml.getBytes());
				Element content = root.getChild("content");
				String errCode = root.getChild("response").getChildText("resp_code");
				String errDesc = root.getChild("response").getChildText("resp_desc");
				int checkOld = Integer.parseInt(this.getParameters(params, "checkOld").toString());
				String defaultPwd = "";

				if (!BOSS_SUCCESS.equals(errCode))
				{
					errDt = this.wellFormedDAO.transBossErrCode("QRY040006", bossTemplate, errCode);
					if (null != errDt)
					{
						errCode = errDt.getLiErrCode();
						errDesc = errDt.getLiErrMsg();
					}
				}
				else if(0 == checkOld)
				{
					if(null != content.getChildText("netschoolvariantuser_user_password"))
					{
						defaultPwd = pattern.matcher(content.getChildText("netschoolvariantuser_user_password")).replaceAll("");
						res.setReObj(defaultPwd);	
					}
				}
				res.setResultCode(BOSS_SUCCESS.equals(errCode)?LOGIC_SUCESS:LOGIC_ERROR);
				res.setErrorCode(errCode);
				res.setErrorMessage(errDesc);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return res;
	}
	*/
	/**
	 * 查询用户信息
	 * 
	 * @param accessId
	 * @param config
	 * @param params
	 * @return
	 
	@SuppressWarnings("unchecked")
	protected BaseResult getCity(final String accessId, final ServiceConfig config, final List<RequestParameter> params) {
		BaseResult res = new BaseResult();
		String reqXml = "";
		String rspXml = "";
		try {
			reqXml = this.bossTeletextUtil.mergeTeletext("cc_cgetusercust_69", params);

			logger.debug(" ====== 查询用户信息请求报文 ======\n" + reqXml);

			rspXml = (String) this.remote.callRemote(new StringTeletext(reqXml, accessId, "cc_cgetusercust_69", super.generateCity(params)));
			logger.debug(" ====== 查询用户信息返回报文 ======\n" + rspXml);
			if (null != rspXml && !"".equals(rspXml)) {
				Element root = this.getElement(rspXml.getBytes());
				String errCode = root.getChild("response").getChildText("resp_code");
				String errDesc = root.getChild("response").getChildText("resp_desc");

				if (!BOSS_SUCCESS.equals(errCode)) {
					ErrorMapping errDt = this.wellFormedDAO.transBossErrCode("QRY040006", "cc_cgetusercust_69", errCode);
					if (null != errDt) {
						errCode = errDt.getLiErrCode();
						errDesc = errDt.getLiErrMsg();
					}
				}
				res.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
				res.setErrorCode(errCode);
				res.setErrorMessage(errDesc);
				if (null != errCode && (BOSS_SUCCESS.equals(errCode))) {
					Map retMap = new HashMap();
					
					XPath xpath = XPath.newInstance("/operation_out/content/user_info/user_city");
					String city = ((Element) xpath.selectSingleNode(root)).getText();
					xpath = XPath.newInstance("/operation_out/content/user_info/user_id");
					String userId = ((Element) xpath.selectSingleNode(root)).getText();
					retMap.put("city", city);
					retMap.put("userId", userId);
					//res.setReObj();
					res.setReObj(retMap);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return res;
	}
	
	private static String getPhone(String phone) {
		String mu = "";
		
		String [] arr = internetCitys.toString().split(",");
		
		if (arr != null && arr.length > 0) {
			for (int i = 0; i < arr.length; i ++) {
				if (phone.startsWith(arr[i])) {
					mu = arr[i];
					break;
				}
			}
		}
		
		return mu;
	}
	*/
}