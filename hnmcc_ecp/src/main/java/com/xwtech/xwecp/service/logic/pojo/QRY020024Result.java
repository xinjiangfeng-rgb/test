
package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY020024Result extends BaseServiceInvocationResult implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private List<Apportionment> apportionments;
    
    public List<Apportionment> getApportionments()
    {
        return apportionments;
    }
    
    public void setApportionments(List<Apportionment> apportionments)
    {
        this.apportionments = apportionments;
    }
    
}
