package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY201997Result;
import com.xwtech.xwecp.service.logic.pojo.QRY201997ResultList;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryInvoiceInformationInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_201997";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY201997Result result = new QRY201997Result();
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
                JSONArray resultList = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<QRY201997ResultList> qry201997ResultList = new ArrayList<>();
                for (int i = 0; i < resultList.size(); i++) {
                    JSONObject obj = (JSONObject) resultList.get(i);
                    QRY201997ResultList list = new QRY201997ResultList();
                    String serviceNmae = obj.getString("SERVICENAME");
                    String invoicetyle = obj.getString("INVOICETYPE");
                    String paymentId = obj.getString("PAYMENTID");
                    String printFlag = obj.getString("PRINTFLAG");
                    String createtime = obj.getString("CREATETIME");
                    String payAmount = obj.getString("PAYAMOUNT");
                    String InvoiceInsId = obj.getString("INVOICEINSID");
                    list.setServiceName(serviceNmae);
                    list.setInvoiceType(invoicetyle);
                    list.setPrintFlag(printFlag);
                    list.setCreateTime(createtime);
                    list.setPayAmount(payAmount);
                    list.setPaymentId(paymentId);
                    list.setInvoiceInsId(InvoiceInsId);
                    qry201997ResultList.add(list);
                }
                result.setQry201997ResultList(qry201997ResultList);
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
            logger.error("发票查询接口", e);
        }
        return result;
    }
}
