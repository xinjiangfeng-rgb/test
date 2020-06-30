package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMobileService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryUserInfoService;
import com.xwtech.xwecp.service.logic.pojo.QRY010003Result;
import com.xwtech.xwecp.service.logic.pojo.QRY040001Result;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class QueryMobileServiceImpl implements IQueryMobileService {


    /**
     * 查询是否河南移动手机号。查询成功返回手机号所属CITY。失败或者不是河南移动手机号CITY无值
     *
     * @param mobile
     * @return
     */
    @Override
    public QRY010003Result queryMobile(String mobile) {
        QRY010003Result ret = new QRY010003Result();

        try {

            IQueryUserInfoService co = new QueryUserInfoServiceClientImpl();
            QRY040001Result infoRet = co.queryUserInfo(mobile);

            if (infoRet != null && StringUtils.isNotBlank(infoRet.getRegion_id())) {
                ret = new QRY010003Result();
                ret.setCity(infoRet.getRegion_id());
                ret.setResultCode("0");
                ret.setBossCode(infoRet.getBossCode());
                ret.setErrorMessage(infoRet.getErrorMessage());
                return ret;
            }else if (infoRet != null && "2012".equals(infoRet.getBossCode())){
                ret.setResultCode("1");
                ret.setBossCode(infoRet.getBossCode());
                ret.setErrorMessage(infoRet.getErrorMessage());
                return ret;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("phone", mobile);
        paramMap.put("__cmd", "QRY010003");
        BaseServiceInvocationResult rs = null;
        try {
            rs = RemoteCaller.callRemote(paramMap);

            return (QRY010003Result) rs;

        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
