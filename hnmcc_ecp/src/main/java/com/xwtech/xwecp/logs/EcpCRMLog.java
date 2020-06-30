package com.xwtech.xwecp.logs;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ecp掉用boss接口的日志
 */
public class EcpCRMLog implements Log {
    public EcpCRMLog() {
    }

    /**
     * 自增id
     */
    private Long id;

    @Override
    public int getLogState() {
        return 2;
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
     * 耗时
     */
    private Long totalTime;
    /**
     * 请求结果code
     */
    private String resultCode;
    /**
     * 请求结果msg
     */
    private String errorMsg;
    /**
     * boss请求报文id
     */
    private String bossAccId;
    /**
     * boss接口编码
     */
    private String bossId;

    private String bisCode;//产品编码
    private String serIp;//ECP主机ip
    private String serPort;//ECP主机端口
    private String bossIp;//BOSS主机ip
    private String bossProt;//boss主机端口
    private String userMobile;
    private String userBrand;
    private String userCity;
    private String retType;
    private String reqText;
    private String resText;
    private String isError;
    private String errorCode;
    private String pageNum;
    private String recNum;
    private String serialNum;
    private String jfserialNum;

    public EcpCRMLog(String tranceId,String accessId,String accessTimeStr,String responseTimeStr,Long totalTime,
                     String resultCode,String errorMsg,String bossAccId,String bossId,String bisCode,
                     String serIp,String serPort,String bossIp,String bossProt,String userMobile,String userBrand,
                     String userCity,String retType,String reqText,String resText,String isError,String errorCode,
                     String pageNum,String recNum,String serialNum,String jfserialNum){
        this.tranceId = tranceId;
        this.accessId = accessId;
        this.accessTimeStr = accessTimeStr;
        this.responseTimeStr = responseTimeStr;
        this.totalTime = totalTime;
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.bossAccId = bossAccId;
        this.bossId = bossId;
        this.bisCode = bisCode;
        this.serIp = serIp;
        this.serPort = serPort;
        this.bossIp = bossIp;
        this.bossProt = bossProt;
        this.userMobile = userMobile;
        this.userBrand = userBrand;
        this.userCity = userCity;
        this.retType = retType;
        this.reqText = reqText;
        this.resText = resText;
        this.isError = isError;
        this.errorCode = errorCode;
        this.pageNum = pageNum;
        this.recNum = recNum;
        this.serialNum = serialNum;
        this.jfserialNum = jfserialNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranceId() {
        return tranceId;
    }

    public void setTranceId(String tranceId) {
        this.tranceId = tranceId;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
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

    public String getBossAccId() {
        return bossAccId;
    }

    public void setBossAccId(String bossAccId) {
        this.bossAccId = bossAccId;
    }

    public String getBossId() {
        return bossId;
    }

    public void setBossId(String bossId) {
        this.bossId = bossId;
    }

    public String getBisCode() {
        return bisCode;
    }

    public void setBisCode(String bisCode) {
        this.bisCode = bisCode;
    }

    public String getSerIp() {
        return serIp;
    }

    public void setSerIp(String serIp) {
        this.serIp = serIp;
    }

    public String getSerPort() {
        return serPort;
    }

    public void setSerPort(String serPort) {
        this.serPort = serPort;
    }

    public String getBossIp() {
        return bossIp;
    }

    public void setBossIp(String bossIp) {
        this.bossIp = bossIp;
    }

    public String getBossProt() {
        return bossProt;
    }

    public void setBossProt(String bossProt) {
        this.bossProt = bossProt;
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

    public String getRetType() {
        return retType;
    }

    public void setRetType(String retType) {
        this.retType = retType;
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

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getRecNum() {
        return recNum;
    }

    public void setRecNum(String recNum) {
        this.recNum = recNum;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getJfserialNum() {
        return jfserialNum;
    }

    public void setJfserialNum(String jfserialNum) {
        this.jfserialNum = jfserialNum;
    }
}
