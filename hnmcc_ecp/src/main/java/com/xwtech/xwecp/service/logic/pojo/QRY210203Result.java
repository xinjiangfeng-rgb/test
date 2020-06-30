package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY210203Result extends BaseServiceInvocationResult implements Serializable{
	
	/*private List<QRY210203ResultList> qRY210203ResultList;

	public List<QRY210203ResultList> getQRY210203ResultList() {
		return qRY210203ResultList;
	}

	public void setQRY210203ResultList(List<QRY210203ResultList> qRY210203ResultList) {
		qRY210203ResultList = qRY210203ResultList;
	}*/
	private String netAge;//网龄
	private String isExists;//是否存在标示  0 存在 1不存在
	private String groupId;//群组Id
	public String getNetAge() {
		return netAge;
	}
	public void setNetAge(String netAge) {
		this.netAge = netAge;
	}
	public String getIsExists() {
		return isExists;
	}
	public void setIsExists(String isExists) {
		this.isExists = isExists;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	

}
