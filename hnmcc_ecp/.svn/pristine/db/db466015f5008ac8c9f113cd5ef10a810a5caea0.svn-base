package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * <p>Title: QRY170911Result</p>
 * <p>Description: 实时流量查询接口，接受报文实体</p>
 * <p>Company: </p> 
 * @author wangtw
 * @date 2017年11月3日 下午2:11:54
 */
public class QRY170911Result extends BaseServiceInvocationResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String accessId; 
	private String bossCode;
	private String errorMessage;
	private String resultCode;
	private String tw  ;//超出套餐流量值
	/**
	 * 实时流量集合
	 */
	private List<CurrentStream> currentStream = new ArrayList<CurrentStream>();
	
	public String getTw() {
		return tw;
	}
	public void setTw(String tw) {
		this.tw = tw;
	}
	public List<CurrentStream> getCurrentStream() { 
		return currentStream;
	}
	public void setCurrentStream(List<CurrentStream> currentStream) {
		this.currentStream = currentStream;
	}
	public String getAccessId() {
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	public String getBossCode() {
		return bossCode;
	}
	public void setBossCode(String bossCode) {
		this.bossCode = bossCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
