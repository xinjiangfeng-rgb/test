package com.xwtech.xwecp.service.logic.pojo;

public class GsmBillDetail
{
    private String start_time;
    
    private String vplmn;
    
    private String call_type;
    
    private String opp_number;
    
    private String duration;
    
    private String toll_type;
    
    private String offer_name;
    
    private String free_res;
    
    private String charge;
    
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

    public String getVplmn()
    {
        return vplmn;
    }

    public void setVplmn(String vplmn)
    {
        this.vplmn = vplmn;
    }

    public String getCall_type()
    {
        return call_type;
    }

    public void setCall_type(String call_type)
    {
        this.call_type = call_type;
    }

    public String getOpp_number()
    {
        return opp_number;
    }

    public void setOpp_number(String opp_number)
    {
        this.opp_number = opp_number;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public String getToll_type()
    {
        return toll_type;
    }

    public void setToll_type(String toll_type)
    {
        this.toll_type = toll_type;
    }

    public String getOffer_name()
    {
        return offer_name;
    }

    public void setOffer_name(String offer_name)
    {
        this.offer_name = offer_name;
    }

    public String getFree_res()
    {
        return free_res;
    }

    public void setFree_res(String free_res)
    {
        this.free_res = free_res;
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