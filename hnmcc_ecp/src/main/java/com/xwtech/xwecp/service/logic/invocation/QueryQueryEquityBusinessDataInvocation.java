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

public class QueryQueryEquityBusinessDataInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_300013";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY300013Result result = new QRY300013Result();

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
            JSONObject resultObjAll = JSONObject.parseObject(rspXml).getJSONObject("result");
            JSONObject resultObj=resultObjAll.getJSONObject("result");
            ResultObject resultObject = new ResultObject();
            String transactionId = resultObj.getString("transactionId");
            String transactionTime = resultObj.getString("transactionTime");
            String code = resultObj.getString("code");
            String message = resultObj.getString("message");
            resultObject.setTransactionId(transactionId);
            resultObject.setTransactionTime(transactionTime);
            resultObject.setCode(code);
            resultObject.setMessage(message);
            result.setResult(resultObject);
            JSONObject returnInfo = resultObjAll.getJSONObject("returnInfo");
            if(returnInfo!=null&&!returnInfo.equals("")){
                ReturnInfoBean returnInfoBean = new ReturnInfoBean();
                JSONArray listArry = returnInfo.getJSONArray("list");
                List<BusinessList> list = new ArrayList<>();
                for (int i = 0; i < listArry.size(); i++) {
                    JSONObject jsonObj = (JSONObject) listArry.get(i);
                    BusinessList businessList = new BusinessList();
                    String bossCodes = jsonObj.getString("bossCode");
                    String type = jsonObj.getString("type");
                    String servicePeriod = jsonObj.getString("servicePeriod");
                    String claimedPeriod = jsonObj.getString("claimedPeriod");
                    String effectTime = jsonObj.getString("effectTime");
                    String expireTime = jsonObj.getString("expireTime");
                    JSONArray subServices = resultObj.getJSONArray("subServices");
                    businessList.setBossCodes(bossCodes);
                    businessList.setType(type);
                    businessList.setServicePeriod(servicePeriod);
                    businessList.setClaimedPeriod(claimedPeriod);
                    businessList.setEffectTime(effectTime);
                    businessList.setExpireTime(expireTime);
                    List<SubService> subServiceList = new ArrayList<SubService>();
                    for (int j = 0; j < subServices.size(); j++) {
                        JSONObject subServiceObj = (JSONObject) subServices.get(j);
                        SubService subService = new SubService();
                        String subServiceId = subServiceObj.getString("subServiceId");
                        String subServiceName = subServiceObj.getString("subServiceName");
                        String interestsDesc = subServiceObj.getString("interestsDesc");
                        String status = subServiceObj.getString("status");
                        String iconUrl = subServiceObj.getString("iconUrl");
                        subService.setSubServiceId(subServiceId);
                        subService.setSubServiceName(subServiceName);
                        subService.setInterestsDesc(interestsDesc);
                        subService.setStatus(status);
                        subService.setIconUrl(iconUrl);
                        subServiceList.add(subService);
                    }
                    businessList.setSubServices(subServiceList);
                    list.add(businessList);
                }
                returnInfoBean.setList(list);
                result.setReturnInfo(returnInfoBean);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("权益业务局数据查询", e);
        }
        return result;
    }
}
