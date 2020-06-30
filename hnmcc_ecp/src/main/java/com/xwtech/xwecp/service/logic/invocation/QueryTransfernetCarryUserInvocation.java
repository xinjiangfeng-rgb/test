package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY180717Result;
import com.xwtech.xwecp.service.logic.pojo.TransfernetCarryUser;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryTransfernetCarryUserInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_180717";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY180717Result result = new QRY180717Result();

        List<TransfernetCarryUser> list = new ArrayList<TransfernetCarryUser>();
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
            JSONArray obj = resultObj.getJSONArray("SO_MEMBER_DEAL");
            for (int i=0;i<obj.size();i++){
                JSONObject jsonObj = (JSONObject) obj.get(i);
                TransfernetCarryUser transfernetCarryUser = new TransfernetCarryUser();
                String retCode = jsonObj.getString("RETCODE");
                String errMsg = jsonObj.getString("ERRMSG");
                String isHNMobile = jsonObj.getString("ISHNMOBILE");
                String numState = jsonObj.getString("NUMSTATE");
                String npState = jsonObj.getString("NPSTATE");
                String homeNet = jsonObj.getString("HOMENET");
                transfernetCarryUser.setRetCode(retCode);
                transfernetCarryUser.setErrMsg(errMsg);
                transfernetCarryUser.setIsHNMobile(isHNMobile);
                transfernetCarryUser.setNumState(numState);
                transfernetCarryUser.setNpState(npState);
                transfernetCarryUser.setHomeNet(homeNet);
                list.add(transfernetCarryUser);
            }
            result.setSoMemberDeal(list);
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("查询合伙人经理号码", e);
        }
        return result;
    }
}
