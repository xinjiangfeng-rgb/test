package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.MarketingsChild;
import com.xwtech.xwecp.service.logic.pojo.QRY201514Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class QryBroadbandInvocation2 extends BaseInvocation implements ILogicalService {

    private static final Logger logger = LoggerFactory.getLogger(QryBroadbandInvocation2.class);

    private String REQUEST_BOSSTELETEXT = "cc_201514";


    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
        QRY201514Result result = new QRY201514Result();
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
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }

            logger.info("bossCode" + bossCode);
            logger.info("bossDesc" + bossDesc);
            JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(rspXml).getJSONObject("result");
            parseBroadband(result, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void parseBroadband(QRY201514Result result, JSONObject obj) {

        String Flag = obj.getString("Flag");
        String KdName = obj.getString("KdName");
        String KdRate = obj.getString("KdRate");
        String KdRentExpireDate = obj.getString("KdRentExpireDate");
        String KdCreateDate = obj.getString("KdCreateDate");
        String KdId = obj.getString("KdId");
        String TypeName = obj.getString("TypeName");
        String KdState = obj.getString("KdState");
        String KdType = obj.getString("KdType");
        String IsHlwdsOffer = obj.getString("IsHlwdsOffer");
        String IsHlwdsPlan = obj.getString("IsHlwdsPlan");
        String KdCode = obj.getString("KdCode");
        String AdvFreeTotal = obj.getString("AdvFreeTotal");
        String kdStdAddr = obj.getString("kdStdAddr");
        String SNHlwdsAccount = obj.getString("SNHlwdsAccount");
        String kdCommunityId = obj.getString("kdCommunityId");
        String esopCellId = obj.getString("esopCellId");
        String accessMode = obj.getString("accessMode");
        String hlwdsprodid = obj.getString("hlwdsprodid");
        String mbhYhbId = obj.getString("mbhYhbId");
        String mbhYhbName = obj.getString("mbhYhbName");
        String yjYhbId = obj.getString("yjYhbId");
        String yjYhbName = obj.getString("yjYhbName");

        JSONArray marketings = obj.getJSONArray("marketings");
        //遍历
        JSONObject jsonObj;
        List<MarketingsChild> attrs = new ArrayList<MarketingsChild>();
        MarketingsChild attr;
        if (marketings.size() > 0) {
            for (int i = 0; i < marketings.size(); i++) {
                jsonObj = (JSONObject) marketings.get(i);
                attr = new MarketingsChild();
                attr.setCode("name");
                attr.setName(jsonObj.getString("code"));
                attr.setOptDate(jsonObj.getString("optDate"));
                attrs.add(attr);
            }
        }
        result.setFlag(Flag);
        result.setKdName(KdName);
        result.setKdRate(KdRate);
        result.setKdRentExpireDate(KdRentExpireDate);
        result.setKdCreateDate(KdCreateDate);
        result.setKdId(KdId);
        result.setTypeName(TypeName);
        result.setKdState(KdState);
        result.setKdType(KdType);
        result.setIsHlwdsOffer(IsHlwdsOffer);
        result.setIsHlwdsPlan(IsHlwdsPlan);
        result.setKdCode(KdCode);
        result.setAdvFreeTotal(AdvFreeTotal);
        result.setMarketings(attrs);
        result.setKdStdAddr(kdStdAddr);
        result.setSNHlwdsAccount(SNHlwdsAccount);
        result.setKdCommunityId(kdCommunityId);
        result.setEsopCellId(esopCellId);
        result.setAccessMode(accessMode);
        result.setHlwdsprodid(hlwdsprodid);
        result.setMbhYhbId(mbhYhbId);
        result.setMbhYhbName(mbhYhbName);
        result.setYjYhbId(yjYhbId);
        result.setYjYhbName(yjYhbName);
    }
}
