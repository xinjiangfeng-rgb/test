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

public class QueryPointsDueNewInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_180529";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY180529Result result = new QRY180529Result();
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
            JSONObject resultObj= JSONObject.parseObject(rspXml).getJSONObject("result");
            String respDesc= JSONObject.parseObject(rspXml).getString("respDesc");

            if(resultObj!=null){
                JSONArray deal = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<PointsDueNew> resultList = new ArrayList<>();
                for (int i = 0; i < deal.size(); i++) {
                    JSONObject scoreObj = (JSONObject) deal.get(i);
                    PointsDueNew pointsDueNew = new PointsDueNew();
                    String expDate = scoreObj.getString("ExpDate");
                    String scrtypeName= scoreObj.getString("ScrtypeName");
                    String curscr= scoreObj.getString("Curscr");
                    String effDate= scoreObj.getString("EffDate");
                    pointsDueNew.setEffDate(effDate);
                    pointsDueNew.setExpDate(expDate);
                    pointsDueNew.setCurscr(curscr);
                    pointsDueNew.setScrtypeName(scrtypeName);
                    resultList.add(pointsDueNew);
                }
                result.setPointsDueNewList(resultList);
                result.setErrorMessage(respDesc);
            }else{
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);

        } catch (Exception e) {
            logger.error("积分到期查询接口", e);
        }
        return result;
    }
}
