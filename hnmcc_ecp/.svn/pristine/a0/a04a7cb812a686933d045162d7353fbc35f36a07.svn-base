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

public class QueryDetailedBillInformationInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_241038";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY241038Result result = new QRY241038Result();
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
                String newBalance = obj.getString("NEWBALANCE");
                String custName = obj.getString("CUSTNAME");
                String reserveBalance = obj.getString("RESERVEBALANCE");
                String acount = obj.getString("ACOUNT");
                String busiCode = obj.getString("BUSICODE");
                String startTime = obj.getString("STARTTIME");
                JSONObject subjectlistObj = obj.getJSONObject("SUBJECTLIST");
                if(subjectlistObj!=null){
                    SubjectList subjectList = new SubjectList();
                    JSONArray subject = subjectlistObj.getJSONArray("subject");
                    List<SubjectBean> subjectBeanList = new ArrayList<>();
                    for (int i = 0; i < subject.size(); i++) {
                        JSONObject subjectObj = (JSONObject) subject.get(i);
                        SubjectBean subjectBean = new SubjectBean();
                        String discountsSum = subjectObj.getString("discountsSum");
                        String totalSum = subjectObj.getString("totalSum");
                        String receiptsSum = subjectObj.getString("receiptsSum");
                        String subjectName = subjectObj.getString("subjectName");
                        subjectBean.setDiscountsSum(discountsSum);
                        subjectBean.setTotalSum(totalSum);
                        subjectBean.setReceiptsSum(receiptsSum);
                        subjectBean.setSubjectName(subjectName);
                        subjectBeanList.add(subjectBean);
                    }
                    subjectList.setSubject(subjectBeanList);
                    result.setSubjectList(subjectList);
                }
                JSONObject costinfolistObj = obj.getJSONObject("COSTINFOLIST");
                if(costinfolistObj!=null){
                    CostinfoList costinfoList = new CostinfoList();
                    JSONArray costInfo = costinfolistObj.getJSONArray("costInfo");
                    List<CostInfoBean>  costInfoBeanList=new ArrayList<>();
                    for (int i = 0; i < costInfo.size(); i++) {
                        JSONObject costInfoObj = (JSONObject) costInfo.get(i);
                        CostInfoBean costInfoBean = new CostInfoBean();
                        JSONArray monthInAccount = costInfoObj.getJSONArray("monthInAccount");
                        List<MonthInAccountBean> monthInAccountBeans = new ArrayList<>();
                        for (int j = 0; j < monthInAccount.size(); j++) {
                            JSONObject monthInAccountObj = (JSONObject) monthInAccount.get(j);
                            MonthInAccountBean monthInAccountBean = new MonthInAccountBean();
                            String monthInAccountName = monthInAccountObj.getString("monthInAccountName");
                            String monthInAccountFee = monthInAccountObj.getString("monthInAccountFee");
                            monthInAccountBean.setMonthInAccountName(monthInAccountName);
                            monthInAccountBean.setMonthInAccountFee(monthInAccountFee);
                            monthInAccountBeans.add(monthInAccountBean);
                        }

                        JSONArray lastMonthBalance = costInfoObj.getJSONArray("lastMonthBalance");
                        List<LastMonthBalanceBean> lastMonthBalanceBeans = new ArrayList<>();
                        for (int j = 0; j < lastMonthBalance.size(); j++) {
                            JSONObject lastMonthBalanceObj = (JSONObject) lastMonthBalance.get(j);
                            LastMonthBalanceBean  lastMonthBalanceBean = new LastMonthBalanceBean();
                            String lastMonthBalanceName = lastMonthBalanceObj.getString("lastMonthBalanceName");
                            String lastMonthBalanceFee = lastMonthBalanceObj.getString("lastMonthBalanceFee");
                            lastMonthBalanceBean.setLastMonthBalanceName(lastMonthBalanceName);
                            lastMonthBalanceBean.setLastMonthBalanceFee(lastMonthBalanceFee);
                            lastMonthBalanceBeans.add(lastMonthBalanceBean);
                        }
                        JSONArray monthOutExpend = costInfoObj.getJSONArray("monthOutExpend");
                        List<MonthOutExpendBean> monthOutExpendBeans = new ArrayList<>();
                        for (int j = 0; j < monthOutExpend.size(); j++) {
                            JSONObject lastMonthBalanceObj = (JSONObject) monthOutExpend.get(j);
                            MonthOutExpendBean  monthOutExpendBean = new MonthOutExpendBean();
                            String monthOutExpendName = lastMonthBalanceObj.getString("monthOutExpendName");
                            String monthOutExpendFee = lastMonthBalanceObj.getString("monthOutExpendFee");
                            monthOutExpendBean.setMonthOutExpendName(monthOutExpendName);
                            monthOutExpendBean.setMonthOutExpendFee(monthOutExpendFee);
                            monthOutExpendBeans.add(monthOutExpendBean);
                        }
                        JSONArray nowMonthBalance = costInfoObj.getJSONArray("nowMonthBalance");
                        List<NowMonthBalanceBean> nowMonthBalanceBeans = new ArrayList<>();
                        for (int j = 0; j < nowMonthBalance.size(); j++) {
                            JSONObject nowMonthBalanceObj = (JSONObject) nowMonthBalance.get(j);
                            NowMonthBalanceBean  nowMonthBalanceBean = new NowMonthBalanceBean();
                            String nowMonthBalanceName = nowMonthBalanceObj.getString("nowMonthBalanceName");
                            String nowMonthBalanceFee = nowMonthBalanceObj.getString("nowMonthBalanceFee");
                            nowMonthBalanceBean.setNowMonthBalanceName(nowMonthBalanceName);
                            nowMonthBalanceBean.setNowMonthBalanceFee(nowMonthBalanceFee);
                            nowMonthBalanceBeans.add(nowMonthBalanceBean);
                        }
                        costInfoBean.setMonthInAccount(monthInAccountBeans);
                        costInfoBean.setLastMonthBalance(lastMonthBalanceBeans);
                        costInfoBean.setMonthOutExpend(monthOutExpendBeans);
                        costInfoBean.setNowMonthBalance(nowMonthBalanceBeans);
                        costInfoBeanList.add(costInfoBean);
                    }
                    costinfoList.setCostInfo(costInfoBeanList);
                    result.setCostinfoList(costinfoList);
                }
                result.setNewBalance(newBalance);
                result.setCustName(custName);
                result.setReserveBalance(reserveBalance);
                result.setAcount(acount);
                result.setBusiCode(busiCode);
                result.setStartTime(startTime);
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
            logger.error("明细账单信息接口", e);
        }
        return result;
    }
}
