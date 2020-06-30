package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.Customers;
import com.xwtech.xwecp.service.logic.pojo.QRY300012Result;
import com.xwtech.xwecp.service.logic.pojo.ResultObject;
import com.xwtech.xwecp.service.logic.pojo.ReturnInfos;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryCustomerInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_300012";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY300012Result result = new QRY300012Result();
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
            JSONObject resultObj= resultObjAll.getJSONObject("result");
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
                ReturnInfos returnInfos = new ReturnInfos();
                JSONArray infoJSONArray = returnInfo.getJSONArray("list");
                List<Customers> customersList = new ArrayList<>();
                for (int i = 0; i < infoJSONArray.size(); i++) {
                    JSONObject listObj = (JSONObject) infoJSONArray.get(i);
                    Customers customers = new Customers();
                    String id = listObj.getString("id");
                    String interestsName = listObj.getString("interestsName");
                    String orderTime = listObj.getString("orderTime");
                    String selectSubServiceName = listObj.getString("selectSubServiceName");
                    String status = listObj.getString("status");
                    String receiveTime = listObj.getString("receiveTime");
                    customers.setId(id);
                    customers.setInterestsName(interestsName);
                    customers.setOrderTime(orderTime);
                    customers.setSelectSubServiceName(selectSubServiceName);
                    customers.setStatus(status);
                    customers.setReceiveTime(receiveTime);
                    customersList.add(customers);
                }
                returnInfos.setList(customersList);
                result.setReturnInfo(returnInfos);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("客服查询接口", e);
        }
        return result;
    }
}
