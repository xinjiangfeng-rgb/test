package com.xwtech.xwecp.service.logic.pojo;

/**
 * 主副卡资源信息查询接口子集Bean
 */
public class QryMainSubResourceBean {
    private String itemId;//科目Id
    private String itemName;//科目名称
    private String prodId;//产品编号
    private String doneCode;//流水号
    private String freeRes;//总量
    private String freeResUsed;//使用量
    private String freeResRemain;//剩余量
    private String validDate;//生效日期
    private String expireDate;//失效日期
    private String unitdes;//单位
    private String freeResType;//类型名称
    private String recordtype;//免费资源类型
    private String tw;//超出套餐流量
    private String priority;//流量优先级
    private String shared;//是否可共享   PRODUCT_ITEM_ID
    private String productItemId;
    private String productName;
    private String productDesc;

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(String productItemId) {
        this.productItemId = productItemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(String doneCode) {
        this.doneCode = doneCode;
    }

    public String getFreeRes() {
        return freeRes;
    }

    public void setFreeRes(String freeRes) {
        this.freeRes = freeRes;
    }

    public String getFreeResUsed() {
        return freeResUsed;
    }

    public void setFreeResUsed(String freeResUsed) {
        this.freeResUsed = freeResUsed;
    }

    public String getFreeResRemain() {
        return freeResRemain;
    }

    public void setFreeResRemain(String freeResRemain) {
        this.freeResRemain = freeResRemain;
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

    public String getUnitdes() {
        return unitdes;
    }

    public void setUnitdes(String unitdes) {
        this.unitdes = unitdes;
    }

    public String getFreeResType() {
        return freeResType;
    }

    public void setFreeResType(String freeResType) {
        this.freeResType = freeResType;
    }

    public String getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

}
