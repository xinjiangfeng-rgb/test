package com.xwtech.xwecp.service.logic.pojo;

/**
 * 5.1.2.	集团成员已订购流量查询
 */
public class MemberOrders {

    private String groupName;//集团名称
    private String memSrvpkgDesc;//操作名称
    private String validDate;//生效时间
    private String expireDate;//失效时间
    private String discount;//折扣

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemSrvpkgDesc() {
        return memSrvpkgDesc;
    }

    public void setMemSrvpkgDesc(String memSrvpkgDesc) {
        this.memSrvpkgDesc = memSrvpkgDesc;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
