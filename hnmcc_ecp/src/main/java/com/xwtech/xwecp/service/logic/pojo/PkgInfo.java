package com.xwtech.xwecp.service.logic.pojo;

public class PkgInfo {
	private String itemName;// 科目名称
	private String unitDes;// 单位
	private String freeResUsed;// 使用量
	private String freeResLeft;// 剩余量
	private String freeRes;// 总量
	private String itemId;// 科目ID
	private String subDate;// 沉淀时间
	private String itemType;// 类型
	private String validDate;//生效日期
	private String expireDate;//失效日期
	

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUnitDes() {
		return unitDes;
	}

	public void setUnitDes(String unitDes) {
		this.unitDes = unitDes;
	}

	public String getFreeResUsed() {
		return freeResUsed;
	}

	public void setFreeResUsed(String freeResUsed) {
		this.freeResUsed = freeResUsed;
	}

	public String getFreeResLeft() {
		return freeResLeft;
	}

	public void setFreeResLeft(String freeResLeft) {
		this.freeResLeft = freeResLeft;
	}

	public String getFreeRes() {
		return freeRes;
	}

	public void setFreeRes(String freeRes) {
		this.freeRes = freeRes;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSubDate() {
		return subDate;
	}

	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}
	

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "itemName：" + itemName + " unitDes：" + unitDes + " freeResUsed：" + freeResUsed + " freeResLeft：" + freeResLeft + " freeRes：" + freeRes + " itemId：" + itemId
				+ " subDate：" + subDate + " itemType: " + itemType+ " expireDate: " + expireDate+ " validDate: " + validDate;
	}

}