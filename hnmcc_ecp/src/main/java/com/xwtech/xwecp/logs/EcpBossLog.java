package com.xwtech.xwecp.logs;

import com.alibaba.fastjson.annotation.JSONField;

public class EcpBossLog  implements Log {
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
    private String ftraceId;
    /**
     * 请求流水号
     */
    private String faccessId;
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
     * 耗时
     */
    private Long ftotalTime;
    /**
     * 请求结果code
     */
    private String fresultCode;
    /**
     * 请求结果msg
     */
    private String ferrorMsg;
    /**
     * boss请求报文id
     */
    private String fbossAccId;
    /**
     * boss接口编码
     */
    private String fbossId;

    private String fbisCode;//产品编码
    private String fserIp;//ECP主机ip
    private String fserPort;//ECP主机端口
    private String fbossIp;//BOSS主机ip
    private String fbossProt;//boss主机端口
    private String fuserMobile;
    private String fuserBrand;
    private String fuserCity;
    private String fretType;
    private String freqText;
    private String fresText;
    private String fisError;
    private String ferrorCode;
    private String fpageNum;
    private String frecNum;
    private String fserialNum;
    private String fjfserialNum;

    public EcpBossLog( String ftraceId, String faccessId, String faccessTimeStr, String fresponseTimeStr, Long ftotalTime, String fresultCode, String ferrorMsg, String fbossAccId, String fbossId, String fbisCode, String fserIp, String fserPort, String fbossIp, String fbossProt, String fuserMobile, String fuserBrand, String fuserCity, String fretType, String freqText, String fresText, String fisError, String ferrorCode, String fpageNum, String frecNum, String fserialNum, String fjfserialNum) {
        this.ftraceId = ftraceId;
        this.faccessId = faccessId;
        this.faccessTimeStr = faccessTimeStr;
        this.fresponseTimeStr = fresponseTimeStr;
        this.ftotalTime = ftotalTime;
        this.fresultCode = fresultCode;
        this.ferrorMsg = ferrorMsg;
        this.fbossAccId = fbossAccId;
        this.fbossId = fbossId;
        this.fbisCode = fbisCode;
        this.fserIp = fserIp;
        this.fserPort = fserPort;
        this.fbossIp = fbossIp;
        this.fbossProt = fbossProt;
        this.fuserMobile = fuserMobile;
        this.fuserBrand = fuserBrand;
        this.fuserCity = fuserCity;
        this.fretType = fretType;
        this.freqText = freqText;
        this.fresText = fresText;
        this.fisError = fisError;
        this.ferrorCode = ferrorCode;
        this.fpageNum = fpageNum;
        this.frecNum = frecNum;
        this.fserialNum = fserialNum;
        this.fjfserialNum = fjfserialNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFtraceId() {
        return ftraceId;
    }

    public void setFtraceId(String ftraceId) {
        this.ftraceId = ftraceId;
    }

    public String getFaccessId() {
        return faccessId;
    }

    public void setFaccessId(String faccessId) {
        this.faccessId = faccessId;
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

    public String getFbossAccId() {
        return fbossAccId;
    }

    public void setFbossAccId(String fbossAccId) {
        this.fbossAccId = fbossAccId;
    }

    public String getFbossId() {
        return fbossId;
    }

    public void setFbossId(String fbossId) {
        this.fbossId = fbossId;
    }

    public String getFbisCode() {
        return fbisCode;
    }

    public void setFbisCode(String fbisCode) {
        this.fbisCode = fbisCode;
    }

    public String getFserIp() {
        return fserIp;
    }

    public void setFserIp(String fserIp) {
        this.fserIp = fserIp;
    }

    public String getFserPort() {
        return fserPort;
    }

    public void setFserPort(String fserPort) {
        this.fserPort = fserPort;
    }

    public String getFbossIp() {
        return fbossIp;
    }

    public void setFbossIp(String fbossIp) {
        this.fbossIp = fbossIp;
    }

    public String getFbossProt() {
        return fbossProt;
    }

    public void setFbossProt(String fbossProt) {
        this.fbossProt = fbossProt;
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

    public String getFretType() {
        return fretType;
    }

    public void setFretType(String fretType) {
        this.fretType = fretType;
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

    public String getFpageNum() {
        return fpageNum;
    }

    public void setFpageNum(String fpageNum) {
        this.fpageNum = fpageNum;
    }

    public String getFrecNum() {
        return frecNum;
    }

    public void setFrecNum(String frecNum) {
        this.frecNum = frecNum;
    }

    public String getFserialNum() {
        return fserialNum;
    }

    public void setFserialNum(String fserialNum) {
        this.fserialNum = fserialNum;
    }

    public String getFjfserialNum() {
        return fjfserialNum;
    }

    public void setFjfserialNum(String fjfserialNum) {
        this.fjfserialNum = fjfserialNum;
    }
}
