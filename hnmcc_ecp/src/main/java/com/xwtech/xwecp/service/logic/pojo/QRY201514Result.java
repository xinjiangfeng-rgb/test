package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;
import java.util.List;

public class QRY201514Result extends BaseServiceInvocationResult implements Serializable {
    private String Flag;//是否订购有宽带
    private String KdName;//宽带名称
    private String KdRate;//宽带速率
    private String KdRentExpireDate;//宽带专项月租到期时间
    private String KdCreateDate;//宽带入网时间
    private String KdId;//宽带产品ID1
    private String TypeName;//订购的套餐信息
    private String KdState;//宽带状态
    private String KdType;//宽带类型
    private String IsHlwdsOffer;//是否订购魔百和
    private String IsHlwdsPlan;//是否订购魔百和优惠包
    private String KdCode;//基础套餐编号
    private String AdvFreeTotal;//宽带类专项月租预存金额
    private List<MarketingsChild> marketings;//宽带营销活动及到期时间
    private String kdStdAddr;//宽带标准地址
    private String SNHlwdsAccount;//省内互联网电视账号
    private String kdCommunityId;//宽带标准地址中第五级小区id
    private String esopCellId;//esop系统中小区编码
    private String accessMode;//宽带接入方式
    private String hlwdsprodid;//订购互联电视产品ID
    private String mbhYhbId;//魔百和优惠包ID
    private String mbhYhbName;//魔百和优惠包名称
    private String yjYhbId;//优家优惠包ID
    private String yjYhbName;//优家优惠包名称


    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getKdName() {
        return KdName;
    }

    public void setKdName(String kdName) {
        KdName = kdName;
    }

    public String getKdRate() {
        return KdRate;
    }

    public void setKdRate(String kdRate) {
        KdRate = kdRate;
    }

    public String getKdRentExpireDate() {
        return KdRentExpireDate;
    }

    public void setKdRentExpireDate(String kdRentExpireDate) {
        KdRentExpireDate = kdRentExpireDate;
    }

    public String getKdCreateDate() {
        return KdCreateDate;
    }

    public void setKdCreateDate(String kdCreateDate) {
        KdCreateDate = kdCreateDate;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getKdState() {
        return KdState;
    }

    public void setKdState(String kdState) {
        KdState = kdState;
    }

    public String getKdType() {
        return KdType;
    }

    public void setKdType(String kdType) {
        KdType = kdType;
    }

    public String getIsHlwdsOffer() {
        return IsHlwdsOffer;
    }

    public void setIsHlwdsOffer(String isHlwdsOffer) {
        IsHlwdsOffer = isHlwdsOffer;
    }

    public String getIsHlwdsPlan() {
        return IsHlwdsPlan;
    }

    public void setIsHlwdsPlan(String isHlwdsPlan) {
        IsHlwdsPlan = isHlwdsPlan;
    }

    public String getKdCode() {
        return KdCode;
    }

    public void setKdCode(String kdCode) {
        KdCode = kdCode;
    }

    public String getAdvFreeTotal() {
        return AdvFreeTotal;
    }

    public void setAdvFreeTotal(String advFreeTotal) {
        AdvFreeTotal = advFreeTotal;
    }

    public String getKdStdAddr() {
        return kdStdAddr;
    }

    public void setKdStdAddr(String kdStdAddr) {
        this.kdStdAddr = kdStdAddr;
    }

    public String getKdId() {
        return KdId;
    }

    public void setKdId(String kdId) {
        KdId = kdId;
    }

    public List<MarketingsChild> getMarketings() {
        return marketings;
    }

    public void setMarketings(List<MarketingsChild> marketings) {
        this.marketings = marketings;
    }

    public String getSNHlwdsAccount() {
        return SNHlwdsAccount;
    }

    public void setSNHlwdsAccount(String SNHlwdsAccount) {
        this.SNHlwdsAccount = SNHlwdsAccount;
    }

    public String getKdCommunityId() {
        return kdCommunityId;
    }

    public void setKdCommunityId(String kdCommunityId) {
        this.kdCommunityId = kdCommunityId;
    }

    public String getEsopCellId() {
        return esopCellId;
    }

    public void setEsopCellId(String esopCellId) {
        this.esopCellId = esopCellId;
    }

    public String getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(String accessMode) {
        this.accessMode = accessMode;
    }

    public String getHlwdsprodid() {
        return hlwdsprodid;
    }

    public void setHlwdsprodid(String hlwdsprodid) {
        this.hlwdsprodid = hlwdsprodid;
    }

    public String getMbhYhbId() {
        return mbhYhbId;
    }

    public void setMbhYhbId(String mbhYhbId) {
        this.mbhYhbId = mbhYhbId;
    }

    public String getMbhYhbName() {
        return mbhYhbName;
    }

    public void setMbhYhbName(String mbhYhbName) {
        this.mbhYhbName = mbhYhbName;
    }

    public String getYjYhbId() {
        return yjYhbId;
    }

    public void setYjYhbId(String yjYhbId) {
        this.yjYhbId = yjYhbId;
    }

    public String getYjYhbName() {
        return yjYhbName;
    }

    public void setYjYhbName(String yjYhbName) {
        this.yjYhbName = yjYhbName;
    }
}
