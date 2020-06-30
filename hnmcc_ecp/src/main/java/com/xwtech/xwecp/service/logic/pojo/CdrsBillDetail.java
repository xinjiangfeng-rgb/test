package com.xwtech.xwecp.service.logic.pojo;

public class CdrsBillDetail
{
    /**
     * 时间
     */
    private String start_time;
    
    /**
     * 费用类型
     */
    private String bill_flag;
    
    /**
     * 通信费(元)
     */
    private String charge;
    
    /**
     * 统计信息
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