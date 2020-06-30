package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
/**
 * 流量不清零查询
 * 
 * @author wangjiajia
 * 2015-11-11
 */
public class QRY040111Result extends BaseServiceInvocationResult
{
	private List<QRY040111ResultBean> qry040111beanList;

	public List<QRY040111ResultBean> getQry040111beanList() {
		return qry040111beanList;
	}

	public void setQry040111beanList(List<QRY040111ResultBean> qry040111beanList) {
		this.qry040111beanList = qry040111beanList;
	}


	
	
}