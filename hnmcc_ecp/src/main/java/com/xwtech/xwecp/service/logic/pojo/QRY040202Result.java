package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY040202Result extends BaseServiceInvocationResult {
	private List<VisibleYxhdList>  visibleYxhdList;

	public List<VisibleYxhdList> getVisibleYxhdList() {
		return visibleYxhdList;
	}

	public void setVisibleYxhdList(List<VisibleYxhdList> visibleYxhdList) {
		this.visibleYxhdList = visibleYxhdList;
	}
}