package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.*;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class SubscribedBusinesInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_420033";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY420033Result result = new QRY420033Result();
        List<PayResultList> list = new ArrayList<PayResultList>();
        String rspXml = "";
        try {
            BaseJsonQueryUtils util = new BaseJsonQueryUtils();
            util.setConfig(REQUEST_BOSSTELETEXT);
            rspXml = util.getResponseXML(accessId, config, params);
            util.distroy();
            logger.info("******** Boss返回数据为*****　" + rspXml);
            if (StringUtils.isEmpty(rspXml)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
            String bossCode = bossJson.getBossCode();
            String bossDesc = bossJson.getBossDesc();
            logger.info("bossCode" + bossCode);
            logger.info("bossDesc" + bossDesc);
            JSONObject resultObj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String respDesc = JSONObject.parseObject(rspXml).getString("respDesc");
            if (resultObj != null && !"".equals(resultObj)) {
                JSONArray businessList = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<BusinessResult> businessResultList = new ArrayList<>();
                for (int i = 0; i < businessList.size(); i++) {
                    if (businessList != null) {
                        JSONObject businessListObj = (JSONObject) businessList.get(i);
                        BusinessResult businessResult = new BusinessResult();
                        if (businessListObj != null) {
                            String expireDate = businessListObj.getString("EXPIRE_DATE");
                            String billingCycleId = businessListObj.getString("BILLINGCYCLEID");
                            String validType = businessListObj.getString("VALID_TYPE");
                            String validDate = businessListObj.getString("VALID_DATE");
                            String resultDesc = businessListObj.getString("RESULTDESC");
                            String createDate = businessListObj.getString("CREATE_DATE");
                            String staffCode = businessListObj.getString("STAFF_CODE");
                            String serviceName = businessListObj.getString("SERVICE_NAME");
                            String resultCode = businessListObj.getString("RESULTCODE");
                            String openSts = businessListObj.getString("OPEN_STS");
                            String openValidDate = businessListObj.getString("OPEN_VALID_DATE");
                            String serviceInstanceId = businessListObj.getString("SERVICE_INSTANCE_ID");
                            businessResult.setExpireDate(expireDate);
                            businessResult.setBillingCycleId(billingCycleId);
                            businessResult.setValidType(validType);
                            businessResult.setValidDate(validDate);
                            businessResult.setResultDesc(resultDesc);
                            businessResult.setCreateDate(createDate);
                            businessResult.setStaffCode(staffCode);
                            businessResult.setServiceName(serviceName);
                            businessResult.setResultCode(resultCode);
                            businessResult.setOpenSts(openSts);
                            businessResult.setOpenValidDate(openValidDate);
                            businessResult.setServiceInstanceId(serviceInstanceId);
                            businessResultList.add(businessResult);
                        }
                    }
                }
                result.setBusinessResults(businessResultList);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            } else {
                result.setErrorMessage(respDesc);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
        } catch (Exception e) {
            logger.error("已订购业务接口", e);
        }
        return result;
    }
}
