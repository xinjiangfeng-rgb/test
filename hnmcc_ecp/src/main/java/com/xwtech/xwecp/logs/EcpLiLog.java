package com.xwtech.xwecp.logs;

import com.alibaba.fastjson.annotation.JSONField;


public class EcpLiLog implements Log {

    private Integer id;
    /**
     * 访问链id
     */
    private String ftranceId;
    /**
     * 请求流水号
     */
    private String faccessId;
    /**
     * ecp接口编码
     */
    private String flogicNumber;
    /**
     * 请求时间
     */
    @JSONField(serialize = false)
    private Long faccessTime;
    private String faccessTimeStr;
    /**
     * 响应时间
     */
    @JSONField(serialize = false)
    private Long fresponseTime;
    private String fresponseTimeStr;
    /**
     * 总耗时
     */
    private Long ftotalTime;
    /**
     * 渠道标识
     */
    private String fchannelNum;
    /**
     * 渠道用户
     */
    private String fchannelUser;
    /**
     * 请求结果code
     */
    private String fresultCode;
    /**
     * 请求结果msg
     */
    private String ferrorMsg;
    /**
     * 手机号
     */
    private String fuserMobile;
    /**
     * 品牌
     */
    private String fuserBrand;
    /**
     * 地市
     */
    private String fuserCity;
    /**
     * clientIp
     */
    private String fclientIp;


    private String fclientPort;
    //opType
    private String fopType;
    //bizCode
    private String fbizCode;
    //clientAccessId
    private String fclientAccessId;
    //isError
    private String fisError;
    //errorCode
    private String ferrorCode;
    //reqText
    private String freqText;
    //resText
    private String fresText;
    //errorStack
    private String ferrorStack;
    //operId
    private String foperId;

    private String fserIP;
    private String fserProt;
    private String fbossIp;
    private String fbossPort;

    public EcpLiLog(String ftranceId, String faccessId, String flogicNumber, String faccessTimeStr, String fresponseTimeStr, Long ftotalTime, String fchannelNum, String fchannelUser, String fresultCode, String ferrorMsg, String fuserMobile, String fuserBrand, String fuserCity, String fclientIp, String fclientPort, String fopType, String fbizCode, String fclientAccessId, String fisError, String ferrorCode, String freqText, String fresText, String ferrorStack, String foperId, String fserIP, String fserProt, String fbossIp, String fbossPort) {
        this.ftranceId = ftranceId;
        this.faccessId = faccessId;
        this.flogicNumber = flogicNumber;
        this.faccessTimeStr = faccessTimeStr;
        this.fresponseTimeStr = fresponseTimeStr;
        this.ftotalTime = ftotalTime;
        this.fchannelNum = fchannelNum;
        this.fchannelUser = fchannelUser;
        this.fresultCode = fresultCode;
        this.ferrorMsg = ferrorMsg;
        this.fuserMobile = fuserMobile;
        this.fuserBrand = fuserBrand;
        this.fuserCity = fuserCity;
        this.fclientIp = fclientIp;
        this.fclientPort = fclientPort;
        this.fopType = fopType;
        this.fbizCode = fbizCode;
        this.fclientAccessId = fclientAccessId;
        this.fisError = fisError;
        this.ferrorCode = ferrorCode;
        this.freqText = freqText;
        this.fresText = fresText;
        this.ferrorStack = ferrorStack;
        this.foperId = foperId;
        this.fserIP = fserIP;
        this.fserProt = fserProt;
        this.fbossIp = fbossIp;
        this.fbossPort = fbossPort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFtranceId() {
        return ftranceId;
    }

    public void setFtranceId(String ftranceId) {
        this.ftranceId = ftranceId;
    }

    public String getFaccessId() {
        return faccessId;
    }

    public void setFaccessId(String faccessId) {
        this.faccessId = faccessId;
    }

    public String getFlogicNumber() {
        return flogicNumber;
    }

    public void setFlogicNumber(String flogicNumber) {
        this.flogicNumber = flogicNumber;
    }

    public Long getFaccessTime() {
        return faccessTime;
    }

    public void setFaccessTime(Long faccessTime) {
        this.faccessTime = faccessTime;
    }

    public String getFaccessTimeStr() {
        return faccessTimeStr;
    }

    public void setFaccessTimeStr(String faccessTimeStr) {
        this.faccessTimeStr = faccessTimeStr;
    }

    public Long getFresponseTime() {
        return fresponseTime;
    }

    public void setFresponseTime(Long fresponseTime) {
        this.fresponseTime = fresponseTime;
    }

    public String getFresponseTimeStr() {
        return fresponseTimeStr;
    }

    public void setFresponseTimeStr(String fresponseTimeStr) {
        this.fresponseTimeStr = fresponseTimeStr;
    }

    public Long getFtotalTime() {
        return ftotalTime;
    }

    public void setFtotalTime(Long ftotalTime) {
        this.ftotalTime = ftotalTime;
    }

    public String getFchannelNum() {
        return fchannelNum;
    }

    public void setFchannelNum(String fchannelNum) {
        this.fchannelNum = fchannelNum;
    }

    public String getFchannelUser() {
        return fchannelUser;
    }

    public void setFchannelUser(String fchannelUser) {
        this.fchannelUser = fchannelUser;
    }

    public String getFresultCode() {
        return fresultCode;
    }

    public void setFresultCode(String fresultCode) {
        this.fresultCode = fresultCode;
    }

    public String getFerrorMsg() {
        return ferrorMsg;
    }

    public void setFerrorMsg(String ferrorMsg) {
        this.ferrorMsg = ferrorMsg;
    }

    public String getFuserMobile() {
        return fuserMobile;
    }

    public void setFuserMobile(String fuserMobile) {
        this.fuserMobile = fuserMobile;
    }

    public String getFuserBrand() {
        return fuserBrand;
    }

    public void setFuserBrand(String fuserBrand) {
        this.fuserBrand = fuserBrand;
    }

    public String getFuserCity() {
        return fuserCity;
    }

    public void setFuserCity(String fuserCity) {
        this.fuserCity = fuserCity;
    }

    public String getFclientIp() {
        return fclientIp;
    }

    public void setFclientIp(String fclientIp) {
        this.fclientIp = fclientIp;
    }

    public String getFclientPort() {
        return fclientPort;
    }

    public void setFclientPort(String fclientPort) {
        this.fclientPort = fclientPort;
    }

    public String getFopType() {
        return fopType;
    }

    public void setFopType(String fopType) {
        this.fopType = fopType;
    }

    public String getFbizCode() {
        return fbizCode;
    }

    public void setFbizCode(String fbizCode) {
        this.fbizCode = fbizCode;
    }

    public String getFclientAccessId() {
        return fclientAccessId;
    }

    public void setFclientAccessId(String fclientAccessId) {
        this.fclientAccessId = fclientAccessId;
    }

    public String getFisError() {
        return fisError;
    }

    public void setFisError(String fisError) {
        this.fisError = fisError;
    }

    public String getFerrorCode() {
        return ferrorCode;
    }

    public void setFerrorCode(String ferrorCode) {
        this.ferrorCode = ferrorCode;
    }

    public String getFreqText() {
        return freqText;
    }

    public void setFreqText(String freqText) {
        this.freqText = freqText;
    }

    public String getFresText() {
        return fresText;
    }

    public void setFresText(String fresText) {
        this.fresText = fresText;
    }

    public String getFerrorStack() {
        return ferrorStack;
    }

    public void setFerrorStack(String ferrorStack) {
        this.ferrorStack = ferrorStack;
    }

    public String getFoperId() {
        return foperId;
    }

    public void setFoperId(String foperId) {
        this.foperId = foperId;
    }

    public String getFserIP() {
        return fserIP;
    }

    public void setFserIP(String fserIP) {
        this.fserIP = fserIP;
    }

    public String getFserProt() {
        return fserProt;
    }

    public void setFserProt(String fserProt) {
        this.fserProt = fserProt;
    }

    public String getFbossIp() {
        return fbossIp;
    }

    public void setFbossIp(String fbossIp) {
        this.fbossIp = fbossIp;
    }

    public String getFbossPort() {
        return fbossPort;
    }

    public void setFbossPort(String fbossPort) {
        this.fbossPort = fbossPort;
    }

    @Override
    public int getLogState() {
        return 1;
    }
}
