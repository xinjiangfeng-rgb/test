package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;


public class GroupMemberInfo implements Serializable
{
	private String groupMemberMsidsn;

	private String groupMemberName;

	private String groupMemberShort;

	private String groupMemberStatus;

	private String groupMemberStartDate;

	private String groupMemberEndDate;
	
	private String channelId;
	
	private String desc;

	public void setGroupMemberMsidsn(String groupMemberMsidsn)
	{
		this.groupMemberMsidsn = groupMemberMsidsn;
	}

	public String getGroupMemberMsidsn()
	{
		return this.groupMemberMsidsn;
	}

	public void setGroupMemberName(String groupMemberName)
	{
		this.groupMemberName = groupMemberName;
	}

	public String getGroupMemberName()
	{
		return this.groupMemberName;
	}

	public void setGroupMemberShort(String groupMemberShort)
	{
		this.groupMemberShort = groupMemberShort;
	}

	public String getGroupMemberShort()
	{
		return this.groupMemberShort;
	}

	public void setGroupMemberStatus(String groupMemberStatus)
	{
		this.groupMemberStatus = groupMemberStatus;
	}

	public String getGroupMemberStatus()
	{
		return this.groupMemberStatus;
	}

	public void setGroupMemberStartDate(String groupMemberStartDate)
	{
		this.groupMemberStartDate = groupMemberStartDate;
	}

	public String getGroupMemberStartDate()
	{
		return this.groupMemberStartDate;
	}

	public void setGroupMemberEndDate(String groupMemberEndDate)
	{
		this.groupMemberEndDate = groupMemberEndDate;
	}

	public String getGroupMemberEndDate()
	{
		return this.groupMemberEndDate;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}