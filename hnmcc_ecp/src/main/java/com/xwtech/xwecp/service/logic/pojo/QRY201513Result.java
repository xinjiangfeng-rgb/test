package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 
 * @author Administrator
 *说明：13字段marketings字段为string类型的返回值，
 *string数组里存放了map容器，可以通过map容器获取14字段code 活动编号、
 * 15 字段name 活动名称、 
 * 16字段opaDate 活动到期时间
 */
public class QRY201513Result extends BaseServiceInvocationResult implements Serializable{
	private String SvcNum;//手机号码
	private String Flag;//是否订购有宽带
	private String KdName;//宽带名称
	private String KdRate;//宽带速率
	private String KdRentExpireDate;//宽带专项月租到期时间
	private String KdCreateDate;//宽带入网时间
	private String TypeName;//订购的套餐信息
	private String KdState;//宽带状态
	private String KdType;//宽带类型
	private String IsHlwdsOffer;//是否订购魔百和
	private String IsHlwdsPlan;//是否订购魔百和优惠包
	private String KdCode;//基础套餐编号
	private String AdvFreeTotal;//宽带类专项月租预存金额
	private String marketings;//宽带营销活动及到期时间
	private String code;//活动编号
	private String name;//活动名称
	private String opaDate;//活动到期时间
	public String getSvcNum() {
		return SvcNum;
	}
	public void setSvcNum(String svcNum) {
		SvcNum = svcNum;
	}
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
	public String getMarketings() {
		return marketings;
	}
	public void setMarketings(String marketings) {
		this.marketings = marketings;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpaDate() {
		return opaDate;
	}
	public void setOpaDate(String opaDate) {
		this.opaDate = opaDate;
	}

}
