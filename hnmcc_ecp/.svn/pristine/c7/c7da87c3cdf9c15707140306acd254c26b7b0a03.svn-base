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


public class HistoricalBusinessUsageBillInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_420035";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY420035Result result = new QRY420035Result();
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
                JSONArray historyList = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<HistoryBusinessResult> historyBusinessResults = new ArrayList<>();
                for (int i = 0; i < historyList.size(); i++) {
                    if (historyList != null) {
                        JSONObject historyListListListObj = (JSONObject) historyList.get(i);
                        HistoryBusinessResult historyBusinessResult = new HistoryBusinessResult();
                        if (historyListListListObj != null) {
                            String itemName = historyListListListObj.getString("ITEM_NAME");
                            String keyNum = historyListListListObj.getString("KEY_NUM");
                            String freeResPriortity = historyListListListObj.getString("FREE_RES_PRIORTITY");
                            String forwardCycle = historyListListListObj.getString("FORWARD_CYCLE");
                            String propId = historyListListListObj.getString("PROP_ID");
                            String itemId = historyListListListObj.getString("ITEM_ID");
                            String cycle = historyListListListObj.getString("CYCLE");
                            String doneCode = historyListListListObj.getString("DONE_CODE");
                            String freeRes = historyListListListObj.getString("FREE_RES");
                            String freeResUsed = historyListListListObj.getString("FREE_RES_USED");
                            String lastFreeResUsed = historyListListListObj.getString("LAST_FREE_RES_USED");
                            String validDate = historyListListListObj.getString("VALID_DATE");
                            String expireDate = historyListListListObj.getString("EXPIRE_DATE");
                            String subDate = historyListListListObj.getString("SUB_DATE");
                            String remark = historyListListListObj.getString("REMARK");
                            String resultDesc = historyListListListObj.getString("RESULTDESC");
                            String resultCode = historyListListListObj.getString("RESULTCODE");
                            historyBusinessResult.setItemName(itemName);
                            historyBusinessResult.setKeyNum(keyNum);
                            historyBusinessResult.setFreeResPriortity(freeResPriortity);
                            historyBusinessResult.setForwardCycle(forwardCycle);
                            historyBusinessResult.setPropId(propId);
                            historyBusinessResult.setItemId(itemId);
                            historyBusinessResult.setCycle(cycle);
                            historyBusinessResult.setDoneCode(doneCode);
                            historyBusinessResult.setFreeRes(freeRes);
                            historyBusinessResult.setFreeResUsed(freeResUsed);
                            historyBusinessResult.setLastFreeResUsed(lastFreeResUsed);
                            historyBusinessResult.setValidDate(validDate);
                            historyBusinessResult.setExpireDate(expireDate);
                            historyBusinessResult.setSubDate(subDate);
                            historyBusinessResult.setRemark(remark);
                            historyBusinessResult.setResultDesc(resultDesc);
                            historyBusinessResult.setResultCode(resultCode);
                            historyBusinessResults.add(historyBusinessResult);
                        }
                    }
                }
                result.setHistoryBusinessResults(historyBusinessResults);
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
            logger.error("4.5.历史月份业务使用情况账单查询接口", e);
        }
        return result;
    }
}
