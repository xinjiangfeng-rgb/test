package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 查询某用户号码的画像信息
 */
public class Attr extends BaseServiceInvocationResult {

    private String attrId;//属性ID
    private String attrName;//属性名称
    private String value;//属性值
    private String unit;//属性单位
    private String group;//分组信息，ID与名称以 “|”分隔

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "AttrList{" +
                "attrId='" + attrId + '\'' +
                ", attrName='" + attrName + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
