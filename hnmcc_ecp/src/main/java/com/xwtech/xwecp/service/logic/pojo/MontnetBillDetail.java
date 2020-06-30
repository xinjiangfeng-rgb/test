package com.xwtech.xwecp.service.logic.pojo;

public class MontnetBillDetail
{
    /**
     * 时间
     */
    private String start_time;
    
    /**
     * 使用方式
     */
    private String service_type;
    
    /**
     * 业务名称
     */
    private String busi_name;
    
    /**
     * 业务端口
     */
    private String service_code;
    
    /**
     * 业务端口
     */
    private String sp_name;
    
    /**
     * 企业代码
     */
    private String sp_code;
    
    /**
     * 费用类型
     */
    private String bill_flag;
    
    /**
     * 通信费(元)
     */
    private String charge;
    
    /**
     * 统计信息通信
     */
    private String total_info;

    private String retCode;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getStart_time()
    {
        return start_time;
    }
    
    public void setStart_time(String start_time)
    {
        this.start_time = start_time;
    }
    
    public String getService_type()
    {
        return service_type;
    }
    
    public void setService_type(String service_type)
    {
        this.service_type = service_type;
    }
    
    public String getBusi_name()
    {
        return busi_name;
    }
    
    public void setBusi_name(String busi_name)
    {
        this.busi_name = busi_name;
    }
    
    public String getService_code()
    {
        return service_code;
    }
    
    public void setService_code(String service_code)
    {
        this.service_code = service_code;
    }
    
    public String getSp_name()
    {
        return sp_name;
    }
    
    public void setSp_name(String sp_name)
    {
        this.sp_name = sp_name;
    }
    
    public String getSp_code()
    {
        return sp_code;
    }
    
    public void setSp_code(String sp_code)
    {
        this.sp_code = sp_code;
    }
    
    public String getBill_flag()
    {
        return bill_flag;
    }
    
    public void setBill_flag(String bill_flag)
    {
        this.bill_flag = bill_flag;
    }
    
    public String getCharge()
    {
        return charge;
    }
    
    public void setCharge(String charge)
    {
        this.charge = charge;
    }
    
    public String getTotal_info()
    {
        return total_info;
    }
    
    public void setTotal_info(String total_info)
    {
        this.total_info = total_info;
    }
    
}