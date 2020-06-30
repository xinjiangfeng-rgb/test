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

public class QueryRightsInterestsInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_300010";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY300010Result result = new QRY300010Result();
        List<Interests> list = new ArrayList<Interests>();
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
            JSONObject resultObj=  resultObjAll.getJSONObject("result");
            this.logger.info("******** Boss返回resultObj为*****　" + resultObj.toJSONString());
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
                this.logger.info("******** Boss返回returnInfo为*****" + returnInfo.toJSONString());
                ReturnInfoBeans returnInfoBeans=new ReturnInfoBeans();
                JSONArray listInfo= returnInfo.getJSONArray("list");
                List<RightList> rightLists=new ArrayList<>();
                for (int i = 0; i < listInfo.size(); i++) {
                    JSONObject listObj = (JSONObject) listInfo.get(i);
                    RightList rightList=new RightList();
                    String subscriptionId = listObj.getString("subscriptionId");
                    String bossCodes = listObj.getString("bossCode");
                    String interestsName = listObj.getString("interestsName");
                    String orderTime = listObj.getString("orderTime");
                    String selectSubServiceId = listObj.getString("selectSubServiceId");
                    String recvStatus = listObj.getString("recvStatus");
                    String recvMessage = listObj.getString("recvMessage");
                    String receiveTime = listObj.getString("receiveTime");
                    JSONArray interestArray= listObj.getJSONArray("interests");
                    rightList.setSubscriptionId(subscriptionId);
                    rightList.setBossCode(bossCodes);
                    rightList.setInterestsName(interestsName);
                    rightList.setOrderTime(orderTime);
                    rightList.setSelectSubServiceId(selectSubServiceId);
                    rightList.setRecvStatus(recvStatus);
                    rightList.setRecvMessage(recvMessage);
                    rightList.setReceiveTime(receiveTime);
                    List<Interests> interes=new ArrayList<>();
                    for (int j = 0; j < interestArray.size(); j++) {
                        JSONObject jsonObj = (JSONObject) interestArray.get(j);
                        Interests interests = new Interests();
                        String subServiceId = jsonObj.getString("subServiceId");
                        String subServiceName = jsonObj.getString("subServiceName");
                        String status = jsonObj.getString("status");
                        String dataEntry = jsonObj.getString("dataEntry");
                        interests.setSubServiceId(subServiceId);
                        interests.setSubServiceName(subServiceName);
                        interests.setStatus(status);
                        interests.setDataEntry(dataEntry);
                        interes.add(interests);
                    }
                    rightList.setInterests(interes);
                    rightLists.add(rightList);
                }
                returnInfoBeans.setList(rightLists);
                result.setReturnInfo(returnInfoBeans);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("权益查询", e);
        }
        return result;
    }
}
