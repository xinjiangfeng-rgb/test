package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY202002Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class ElectronicInvoiceDetailsInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_202002";
    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY202002Result result = new QRY202002Result();
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

            if(obj!=null&&!"".equals(obj)){
                String gdfnsrmc = obj.getString("GDFNSRMC");
                String ghfdz = obj.getString("GHFDZ");
                String invoiceNumber = obj.getString("INVOICENUMBER");
                String gdfnsrsbh = obj.getString("GDFNSRSBH");
                String ghfyhzh = obj.getString("GHFYHZH");
                String totalAmount = obj.getString("TOTALAMOUNT");
                String ghfdhhm = obj.getString("GHFDHHM");
                String createTime = obj.getString("CREATETIME");
                String invoiceCode = obj.getString("INVOICECODE");
                result.setGdfnsrmc(gdfnsrmc);
                result.setGhfdz(ghfdz);
                result.setInvoiceNumber(invoiceNumber);
                result.setGdfnsrsbh(gdfnsrsbh);
                result.setGhfyhzh(ghfyhzh);
                result.setTotalAmount(totalAmount);
                result.setGhfdhhm(ghfdhhm);
                result.setCreateTime(createTime);
                result.setInvoiceCode(invoiceCode);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            }else{
                result.setErrorMessage(respDesc);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
        } catch (Exception e) {
            logger.error("电子发票详情", e);
        }
        return result;
    }
}
