package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY040001Result extends BaseServiceInvocationResult implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 携入地市
     */
    private String areacode;
    
    /**
     * 服务号码
     */
    private String userinfo_svcnum;
    
    /**
     * 购机时间
     */
    private String userinfo_crtdate;
    
    /**
     * IMSI码
     */
    private String userinfo_devnum;
    
    /**
     * 基本产品资费实例名称
     */
    private String prodprcname;
    
    /**
     * 基本产品资费实例代码
     */
    private String userinfo_mainprodprcid;
    
    /**
     * 品牌名称
     */
    private String brandname;
    
    /**
     * 品牌代码
     */
    private String userinfo_brandid;
    
    /**
     * 基本账户名称
     */
    private String acctinfo_acctname;
    
    /**
     * 用户状态
     */
    private String stoptype_name;
    
    /**
     * 用户名称
     */
    private String user_name;
    
    /**
     * 归属地
     */
    private String region_id;
    
    public String getAreacode()
    {
        return areacode;
    }
    
    public void setAreacode(String areacode)
    {
        this.areacode = areacode;
    }
    
    public String getUserinfo_svcnum()
    {
        return userinfo_svcnum;
    }
    
    public void setUserinfo_svcnum(String userinfo_svcnum)
    {
        this.userinfo_svcnum = userinfo_svcnum;
    }
    
    public String getUserinfo_crtdate()
    {
        return userinfo_crtdate;
    }
    
    public void setUserinfo_crtdate(String userinfo_crtdate)
    {
        this.userinfo_crtdate = userinfo_crtdate;
    }
    
    public String getUserinfo_devnum()
    {
        return userinfo_devnum;
    }
    
    public void setUserinfo_devnum(String userinfo_devnum)
    {
        this.userinfo_devnum = userinfo_devnum;
    }
    
    public String getProdprcname()
    {
        return prodprcname;
    }
    
    public void setProdprcname(String prodprcname)
    {
        this.prodprcname = prodprcname;
    }
    
    public String getUserinfo_mainprodprcid()
    {
        return userinfo_mainprodprcid;
    }
    
    public void setUserinfo_mainprodprcid(String userinfo_mainprodprcid)
    {
        this.userinfo_mainprodprcid = userinfo_mainprodprcid;
    }
    
    public String getBrandname()
    {
        return brandname;
    }
    
    public void setBrandname(String brandname)
    {
        this.brandname = brandname;
    }
    
    public String getUserinfo_brandid()
    {
        return userinfo_brandid;
    }
    
    public void setUserinfo_brandid(String userinfo_brandid)
    {
        this.userinfo_brandid = userinfo_brandid;
    }
    
    public String getAcctinfo_acctname()
    {
        return acctinfo_acctname;
    }
    
    public void setAcctinfo_acctname(String acctinfo_acctname)
    {
        this.acctinfo_acctname = acctinfo_acctname;
    }
    
    public String getStoptype_name()
    {
        return stoptype_name;
    }
    
    public void setStoptype_name(String stoptype_name)
    {
        this.stoptype_name = stoptype_name;
    }
    
    public String getUser_name()
    {
        return user_name;
    }
    
    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }
    
    public String getRegion_id()
    {
        return region_id;
    }
    
    public void setRegion_id(String region_id)
    {
        this.region_id = region_id;
    }
    
}