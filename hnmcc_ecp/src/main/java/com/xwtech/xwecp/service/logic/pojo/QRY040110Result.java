package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
/**
 * 用户手机号查询用户的实名状态
 * 
 * @author wangjiajia
 * 2015-10-20
 */
public class QRY040110Result extends BaseServiceInvocationResult
{

	/**
	 * 实名状态
	 */
	private String authState;
	/**
	 * 实名状态名称
	 */
	private String authStateName;
	public String getAuthState() {
		return authState;
	}
	public void setAuthState(String authState) {
		this.authState = authState;
	}
	public String getAuthStateName() {
		return authStateName;
	}
	public void setAuthStateName(String authStateName) {
		this.authStateName = authStateName;
	}



	
	
}