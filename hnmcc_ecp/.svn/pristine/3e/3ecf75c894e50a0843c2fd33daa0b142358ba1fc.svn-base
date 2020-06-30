package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BillAccountResult;
import com.xwtech.xwecp.service.logic.pojo.PayResultList;
import com.xwtech.xwecp.service.logic.pojo.QRY420034Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class BillAccountInformationInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_420034";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY420034Result result = new QRY420034Result();
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
                JSONArray billAccountList = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<BillAccountResult> billAccountResultList = new ArrayList<>();
                for (int i = 0; i < billAccountList.size(); i++) {
                    if (billAccountList != null) {
                        JSONObject billAccountListListObj = (JSONObject) billAccountList.get(i);
                        BillAccountResult billAccountResult = new BillAccountResult();
                        if (billAccountListListObj != null) {
                            String balance = billAccountListListObj.getString("BALANCE");
                            String curIncome = billAccountListListObj.getString("CUR_INCOME");
                            String lastRemain = billAccountListListObj.getString("LAST_REMAIN");
                            String billPayout = billAccountListListObj.getString("BILL_PAYOUT");
                            String acctId = billAccountListListObj.getString("ACCT_ID");
                            String otherPayout = billAccountListListObj.getString("OTHER_PAYOUT");
                            String curRefundMent = billAccountListListObj.getString("CUR_REFUNDMENT");
                            String resultDesc = billAccountListListObj.getString("RESULTDESC");
                            String curReturn = billAccountListListObj.getString("CUR_RETURN");
                            String spePaymentId = billAccountListListObj.getString("SPE_PAYMENT_ID");
                            String curUsableBalance = billAccountListListObj.getString("CUR_USABLE_BALANCE");
                            String resultCode = billAccountListListObj.getString("RESULTCODE");
                            String acctDesc = billAccountListListObj.getString("ACCT_DESC");
                            billAccountResult.setBalance(balance);
                            billAccountResult.setCurIncome(curIncome);
                            billAccountResult.setLastRemain(lastRemain);
                            billAccountResult.setBillPayout(billPayout);
                            billAccountResult.setAcctId(acctId);
                            billAccountResult.setOtherPayout(otherPayout);
                            billAccountResult.setCurRefundMent(curRefundMent);
                            billAccountResult.setResultDesc(resultDesc);
                            billAccountResult.setCurReturn(curReturn);
                            billAccountResult.setSpePaymentId(spePaymentId);
                            billAccountResult.setCurUsableBalance(curUsableBalance);
                            billAccountResult.setResultCode(resultCode);
                            billAccountResult.setAcctDesc(acctDesc);
                            billAccountResultList.add(billAccountResult);
                        }
                    }
                }
                result.setBillAccountResults(billAccountResultList);
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
            logger.error("4.4.账单账户信息查询接口", e);
        }
        return result;
    }
}
