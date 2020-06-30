package com.xwtech.xwecp.service.logic.client_impl.common;

import com.alibaba.fastjson.JSONObject;

public interface IBusinessManagementService {

    /**
     * 获取需要用于加密的rsa私钥和desc加密标识
     * @return
     */
    JSONObject getRsaPriAndDescKey();

    /**
     *4.2.通过短信验证码确认办理业务
     */
    JSONObject  handlingBusinessbySMcode (String orderId, String smsCode, String operCode);

    /**
     * 直接进行活动办理
     * @param
     * @return
     */
    JSONObject activityHandling (String svcNum, String offerId, String operCode);

    /**
     *4.4.获取办理营销活动的短信验证码
     * @param
     * @return
     */
    JSONObject getSmsCodeActivities (String svcNum, String offerId);



}
