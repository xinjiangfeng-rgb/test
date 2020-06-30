package com.xwtech.xwecp.logs;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ECP访问的日志实体
 */
public class EcpDBLog implements Log {
    public EcpDBLog() {
    }

    /**
     * 自增id
     */
    private Long id;

    @Override
    public int getLogState() {
        return 1;
    }

    public EcpDBLog(String tranceId, String accessId, String logicNumber, String accessTimeStr, String responseTimeStr,
                    Long totalTime, String channelNum, String channelUser, String resultCode, String errorMsg,
                    String userMobile, String userBrand, String userCity,String clientIp,String opType,String bizCode,
                    String clientAccessId,String isError,String errorCode,String reqText,String resText,String errorStack,String operId,
                    String clientPort,String serIP,String serProt,String crmIp,String crmPort) {
        this.tranceId = tranceId;
        this.accessId = accessId;
        this.logicNumber = logicNumber;
        this.accessTimeStr = accessTimeStr;
        this.responseTimeStr = responseTimeStr;
        this.totalTime = totalTime;
        this.channelNum = channelNum;
        this.channelUser = channelUser;
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.userMobile = userMobile;
        this.userBrand = userBrand;
        this.userCity = userCity;
        this.clientIp = clientIp;
        this.opType = opType;
        this.bizCode = bizCode;
        this.clientAccessId = clientAccessId;
        this.isError = isError;
        this.errorCode = errorCode;
        this.reqText = reqText;
        this.resText = resText;
        this.errorStack = errorStack;
        this.operId = operId;
        this.clientPort = clientPort;
        this.serIP = serIP;
        this.serProt = serProt;
        this.crmIp = crmIp;
        this.crmPort = crmPort;
    }

    /**
     * 访问链id
     */
    private String tranceId;
    /**
     * 请求流水号
     */
    private String accessId;
    /**
     * ecp接口编码
     */
    private String logicNumber;
    /**
     * 请求时间
     */
    @JSONField(serialize = false)
    private Long accessTime;
    private String accessTimeStr;
    /**
     * 响应时间
     */
    @JSONField(serialize = false)
    private Long responseTime;
    private String responseTimeStr;
    /**
     * 总耗时
     */
    private Long totalTime;
    /**
     * 渠道标识
     */
    private String channelNum;
    /**
     * 渠道用户
     */
    private String channelUser;
    /**
     * 请求结果code
     */
    private String resultCode;
    /**
     * 请求结果msg
     */
    private String errorMsg;
    /**
     * 手机号
     */
    private String userMobile;
    /**
     * 品牌
     */
    private String userBrand;
    /**
     * 地市
     */
    private String userCity;
    /**
     * clientIp
     */
    private String clientIp;

    //opType
    private String opType;
    //bizCode
    private String bizCode;
    //clientAccessId
    private String clientAccessId;
    //isError
    private String isError;
    //errorCode
    private String errorCode;
    //reqText
    private String reqText;
    //resText
    private String resText;
    //errorStack
    private String errorStack;
    //operId
    private String operId;



    private String clientPort;
    private String serIP;
    private String serProt;
    private String crmIp;
    private String crmPort;

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getSerIP() {
        return serIP;
    }

    public void setSerIP(String serIP) {
        this.serIP = serIP;
    }

    public String getSerProt() {
        return serProt;
    }

    public void setSerProt(String serProt) {
        this.serProt = serProt;
    }

    public String getCrmIp() {
        return crmIp;
    }

    public void setCrmIp(String crmIp) {
        this.crmIp = crmIp;
    }

    public String getCrmPort() {
        return crmPort;
    }

    public void setCrmPort(String crmPort) {
        this.crmPort = crmPort;
    }

    public String getTranceId() {
        return tranceId;
    }

    public void setTranceId(String tranceId) {
        this.tranceId = tranceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getLogicNumber() {
        return logicNumber;
    }

    public void setLogicNumber(String logicNumber) {
        this.logicNumber = logicNumber;
    }

    public Long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Long accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessTimeStr() {
        return accessTimeStr;
    }

    public void setAccessTimeStr(String accessTimeStr) {
        this.accessTimeStr = accessTimeStr;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseTimeStr() {
        return responseTimeStr;
    }

    public void setResponseTimeStr(String responseTimeStr) {
        this.responseTimeStr = responseTimeStr;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getChannelUser() {
        return channelUser;
    }

    public void setChannelUser(String channelUser) {
        this.channelUser = channelUser;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserBrand() {
        return userBrand;
    }

    public void setUserBrand(String userBrand) {
        this.userBrand = userBrand;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getClientAccessId() {
        return clientAccessId;
    }

    public void setClientAccessId(String clientAccessId) {
        this.clientAccessId = clientAccessId;
    }

    public String getIsError() {
        return isError;
    }

    public void setIsError(String isError) {
        this.isError = isError;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReqText() {
        return reqText;
    }

    public void setReqText(String reqText) {
        this.reqText = reqText;
    }

    public String getResText() {
        return resText;
    }

    public void setResText(String resText) {
        this.resText = resText;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }
}
