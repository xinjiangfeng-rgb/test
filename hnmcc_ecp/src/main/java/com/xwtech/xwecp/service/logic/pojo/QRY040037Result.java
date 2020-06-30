package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import java.util.List;
import java.util.ArrayList;
import com.xwtech.xwecp.service.logic.pojo.PackageInfoBean;

public class QRY040037Result extends BaseServiceInvocationResult
{
	/**
	 * 
	 */
	private String prodprcName;
	/**
	 * 基本产品资费实例代码
	 */
	private String prodprcId;
	/**
	 * 套餐生效时间
	 */
	private String useTime;
	/**
	 * 套餐资费编码
	 */
	private String offerCode;
	/**
	 * 套餐信息说明
	 */
	private String offerDesc;
	public String getProdprcName() {
		return prodprcName;
	}
	public void setProdprcName(String prodprcName) {
		this.prodprcName = prodprcName;
	}
	public String getProdprcId() {
		return prodprcId;
	}
	public void setProdprcId(String prodprcId) {
		this.prodprcId = prodprcId;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getOfferDesc() {
		return offerDesc;
	}
	public void setOfferDesc(String offerDesc) {
		this.offerDesc = offerDesc;
	}
	


}