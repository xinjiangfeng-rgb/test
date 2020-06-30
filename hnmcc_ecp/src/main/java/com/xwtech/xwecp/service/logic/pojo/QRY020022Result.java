package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY020022Result extends BaseServiceInvocationResult implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private List<MonthRent> monthRents;
    
    public List<MonthRent> getMonthRents()
    {
        return monthRents;
    }
    
    public void setMonthRents(List<MonthRent> monthRents)
    {
        this.monthRents = monthRents;
    }
    
}
