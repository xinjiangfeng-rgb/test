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

public class QueryMonthlyTrafficInvocation extends BaseInvocation implements ILogicalService {


    private String REQUEST_BOSSTELETEXT = "cc_040050";
    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY040050Result result = new QRY040050Result();
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
            String outResource = resultObj.getString("OUT_RESOURCE");
            String netPkgOut = resultObj.getString("NET_PKG_OUT");
            String gprsPkgUseHis = resultObj.getString("GPRS_PKG_USE_HIS");
            String inResource = resultObj.getString("IN_RESOURCE");
            String marketActivity = resultObj.getString("MARKET_ACTIVITY");
            String netPkg = resultObj.getString("NET_PKG");
            //第一个
            JSONArray gprsPkgUseHisList = JSONArray.parseArray(gprsPkgUseHis);
            List<GprsPkgUseHisBean> gprsPkgUseHisArray = new ArrayList<>();
            for (int i = 0; i < gprsPkgUseHisList.size(); i++) {
                JSONObject gprsPkgUseHisObj = (JSONObject) gprsPkgUseHisList.get(i);
                GprsPkgUseHisBean gprsPkgUseHisBean = new GprsPkgUseHisBean();
                String ratingRes = gprsPkgUseHisObj.getString("RATING_RES");
                String month = gprsPkgUseHisObj.getString("MONTH");
                String outRes = gprsPkgUseHisObj.getString("OUT_RES");
                gprsPkgUseHisBean.setRatingRes(ratingRes);
                gprsPkgUseHisBean.setMonth(month);
                gprsPkgUseHisBean.setOutRes(outRes);
                gprsPkgUseHisArray.add(gprsPkgUseHisBean);
            }

            //第二个
            JSONArray outResourceList = JSONArray.parseArray(outResource);
            List<OutResourceBean> OutResourceArray = new ArrayList<>();
            for (int i = 0; i < outResourceList.size(); i++) {
                JSONObject outResourceObj = (JSONObject) outResourceList.get(i);
                OutResourceBean outResourceBean = new OutResourceBean();
                String ratingRes = outResourceObj.getString("RATING_RES");
                String unit = outResourceObj.getString("UNIT");
                String outResDisPaly = outResourceObj.getString("OUT_RES_DISPLAY");
                String gprsAmount = outResourceObj.getString("GPRS_AMOUNT");
                String outRes = outResourceObj.getString("OUT_RES");
                String gprsResPricr = outResourceObj.getString("GPRS_RES_PRICR");
                String itemName = outResourceObj.getString("ITEM_NAME");
                outResourceBean.setRatingRes(ratingRes);
                outResourceBean.setUnit(unit);
                outResourceBean.setOutResDisPaly(outResDisPaly);
                outResourceBean.setGprsAmount(gprsAmount);
                outResourceBean.setOutRes(outRes);
                outResourceBean.setGprsResPricr(gprsResPricr);
                outResourceBean.setItemName(itemName);
                OutResourceArray.add(outResourceBean);
            }
            //第三个
            JSONArray inResourceList = JSONArray.parseArray(inResource);
            List<InResourceBean> inResourceArray = new ArrayList<>();
            for (int i = 0; i < inResourceList.size(); i++) {
                JSONObject inResourceObj = (JSONObject) inResourceList.get(i);
                InResourceBean inResourceBean = new InResourceBean();
                String freeRes = inResourceObj.getString("FREE_RES");
                String freeResUsedBfb = inResourceObj.getString("FREE_RES_USED_BFB");
                String freeResUsed = inResourceObj.getString("FREE_RES_USED");
                String itemName = inResourceObj.getString("ITEM_NAME");
                inResourceBean.setFreeRes(freeRes);
                inResourceBean.setFreeResUsedBfb(freeResUsedBfb);
                inResourceBean.setFreeResUsed(freeResUsed);
                inResourceBean.setItemName(itemName);
                inResourceArray.add(inResourceBean);
            }
            //第四个
            JSONArray marketActivityList = JSONArray.parseArray(marketActivity);
            List<MarketActivityBean> marketActivityArray = new ArrayList<>();
            for (int i = 0; i < marketActivityList.size(); i++) {
                JSONObject marketActivityObj = (JSONObject) marketActivityList.get(i);
                MarketActivityBean marketActivityBean = new MarketActivityBean();
                String freeRes = marketActivityObj.getString("FREE_RES");
                String freeResUsedBfb = marketActivityObj.getString("FREE_RES_USED_BFB");
                String freeResUsed = marketActivityObj.getString("FREE_RES_USED");
                String itemName = marketActivityObj.getString("ITEM_NAME");
                marketActivityBean.setFreeRes(freeRes);
                marketActivityBean.setFreeResUsedBfb(freeResUsedBfb);
                marketActivityBean.setFreeResUsed(freeResUsed);
                marketActivityBean.setItemName(itemName);
                marketActivityArray.add(marketActivityBean);
            }
            result.setOutResourceArray(OutResourceArray);
            result.setNetPkg(netPkg);
            result.setNetPkgOut(netPkgOut);
            result.setGprsPkgUseHisArray(gprsPkgUseHisArray);
            result.setInResourceArray(inResourceArray);
            result.setMarketActivityArray(marketActivityArray);
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("新流量月报", e);
        }
        return result;
    }
}
