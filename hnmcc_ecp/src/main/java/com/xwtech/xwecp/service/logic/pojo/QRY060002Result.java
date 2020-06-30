package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

public class QRY060002Result extends BaseServiceInvocationResult {
	private List<LableDetail> pkgInfoList = new ArrayList<LableDetail>();

	public List<LableDetail> getPkgInfoList() {
		return pkgInfoList;
	}

	public void setPkgInfoList(List<LableDetail> pkgInfoList) {
		this.pkgInfoList = pkgInfoList;
	}
}