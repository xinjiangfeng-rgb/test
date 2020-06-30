package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 成员分发情况查询
 * @author Wangjiajia
 * 2015-09-17
 */
public class QRY040106Result extends BaseServiceInvocationResult
{
	private List<GroupMemberInfo> groupMemberInfo;

	public List<GroupMemberInfo> getGroupMemberInfo() {
		return groupMemberInfo;
	}

	public void setGroupMemberInfo(List<GroupMemberInfo> groupMemberInfo) {
		this.groupMemberInfo = groupMemberInfo;
	}
	

}