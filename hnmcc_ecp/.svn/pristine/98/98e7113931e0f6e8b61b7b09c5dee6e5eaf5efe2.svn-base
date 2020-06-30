package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY180819Result;
import com.xwtech.xwecp.service.logic.pojo.QryMainSubResourceBean;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class QueryMainAndSubCardsInfoInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_180819";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY180819Result result = new QRY180819Result();
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
                JSONArray array = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<QryMainSubResourceBean> mainSubResourceBeans = new ArrayList<>();
                for (int i = 0; i < array.size(); i++) {
                    if (array != null) {
                        JSONObject mainSubResourceObj = (JSONObject) array.get(i);
                        QryMainSubResourceBean mainSubResourceBean = new QryMainSubResourceBean();
                        if (mainSubResourceObj != null) {
                            String itemId = mainSubResourceObj.getString("ITEM_ID");
                            String itemName = mainSubResourceObj.getString("ITEM_NAME");
                            String prodId = mainSubResourceObj.getString("PROD_ID");
                            String doneCode = mainSubResourceObj.getString("DONE_CODE");
                            String freeRes = mainSubResourceObj.getString("FREE_RES");
                            String freeResUsed = mainSubResourceObj.getString("FREE_RES_USED");
                            String freeResRemain = mainSubResourceObj.getString("FREE_RES_REMAIN");
                            String validDate = mainSubResourceObj.getString("VALID_DATE");
                            String expireDate = mainSubResourceObj.getString("EXPIRE_DATE");
                            String unitdes = mainSubResourceObj.getString("UNITDES");
                            String freeResType = mainSubResourceObj.getString("FREE_RES_TYPE");
                            String recordtype = mainSubResourceObj.getString("RECORDTYPE");
                            String tw = mainSubResourceObj.getString("TW");
                            String priority = mainSubResourceObj.getString("PRIORITY");
                            String shared = mainSubResourceObj.getString("SHARED");
                            String productItemId = mainSubResourceObj.getString("PRODUCT_ITEM_ID");
                            String productName = mainSubResourceObj.getString("PRODUCT_NAME");
                            String productDesc = mainSubResourceObj.getString("PRODUCT_DESC");
                            mainSubResourceBean.setItemId(itemId);
                            mainSubResourceBean.setItemName(itemName);
                            mainSubResourceBean.setProdId(prodId);
                            mainSubResourceBean.setDoneCode(doneCode);
                            mainSubResourceBean.setFreeRes(freeRes);
                            mainSubResourceBean.setFreeResUsed(freeResUsed);
                            mainSubResourceBean.setFreeResRemain(freeResRemain);
                            mainSubResourceBean.setValidDate(validDate);
                            mainSubResourceBean.setExpireDate(expireDate);
                            mainSubResourceBean.setUnitdes(unitdes);
                            mainSubResourceBean.setFreeResType(freeResType);
                            mainSubResourceBean.setRecordtype(recordtype);
                            mainSubResourceBean.setTw(tw);
                            mainSubResourceBean.setPriority(priority);
                            mainSubResourceBean.setShared(shared);
                            mainSubResourceBean.setProductItemId(productItemId);
                            mainSubResourceBean.setProductName(productName);
                            mainSubResourceBean.setProductDesc(productDesc);
                            mainSubResourceBeans.add(mainSubResourceBean);
                        }
                    }
                }
                result.setResult(mainSubResourceBeans);
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
            logger.error("主副卡资源信息查询接口", e);
        }
        return result;
    }
}
