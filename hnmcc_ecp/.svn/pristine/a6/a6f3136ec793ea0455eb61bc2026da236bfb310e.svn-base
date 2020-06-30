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

public class QueryCurrentPriceNewByPhoneInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_190425";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY190425Result result = new QRY190425Result();
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
            JSONObject obj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String respDesc = JSONObject.parseObject(rspXml).getString("respDesc");
            if (obj != null && !"".equals(obj)) {
                JSONArray soMemberDeal = obj.getJSONArray("SO_MEMBER_DEAL");
                List<CurrentPriceBean> CurrentPriceBeanList = new ArrayList<>();
                for (int i = 0; i < soMemberDeal.size(); i++) {
                    JSONObject jsonObject = (JSONObject) soMemberDeal.get(i);
                    CurrentPriceBean currentPriceBean = new CurrentPriceBean();
                    String prodCode = jsonObject.getString("PRODCODE");
                    String prodId = jsonObject.getString("PRODID");
                    String prodPrice = jsonObject.getString("PRODPRICE");
                    currentPriceBean.setProdCode(prodCode);
                    currentPriceBean.setProdId(prodId);
                    currentPriceBean.setProdPrice(prodPrice);
                    CurrentPriceBeanList.add(currentPriceBean);
                }
                result.setCurrentPrice(CurrentPriceBeanList);
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
            logger.error("能开接口根据手机查询套餐的价格", e);
        }
        return result;
    }
}
