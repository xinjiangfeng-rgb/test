package com.xwtech.xwecp.web.action;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.quartz.DataConsistencyQtz;
import com.xwtech.xwecp.service.ServiceExecutor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 集团探测执行
 */
public class JttcAction  extends HttpServlet {

    private DataConsistencyQtz dataConsistencyQtz = null;

    public void init()
    {
        dataConsistencyQtz = XWECPApp.SPRING_CONTEXT.getBean(DataConsistencyQtz.class);
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {


        try
        {
            dataConsistencyQtz.dataMethod();

        }
        catch(Throwable e)
        {
           e.printStackTrace();
        }
    }

}
