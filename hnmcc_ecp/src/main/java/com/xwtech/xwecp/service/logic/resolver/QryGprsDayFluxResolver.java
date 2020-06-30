package com.xwtech.xwecp.service.logic.resolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ITeletextResolver;
import com.xwtech.xwecp.service.logic.pojo.GprsDayFlux;
import com.xwtech.xwecp.service.logic.pojo.QRY040064Result;

public class QryGprsDayFluxResolver implements ITeletextResolver
{
    private static final Logger logger = LoggerFactory.getLogger(QryGprsDayFluxResolver.class);

    public QryGprsDayFluxResolver()
    {
    }

    public void resolve(BaseServiceInvocationResult result, Object bossResponse, List<RequestParameter> param)
        throws Exception
    {
    	QRY040064Result res = (QRY040064Result)result;
        try
        {
        	SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
    		Date startDate = df.parse(getParameter(param, "startDate"));
    		Date endDate = df.parse(getParameter(param, "endDate"));
    	    int days= (int)((endDate.getTime()-startDate.getTime())/60/60/24/1000);
    		List<String> datePeriodList = new ArrayList<String>();
    		Calendar cl =Calendar.getInstance();
    		cl.setTime(endDate);
    		int inputDayOfYear = cl.get(Calendar.DAY_OF_YEAR);
    		List<GprsDayFlux>  gprsDayFluxList = new ArrayList<GprsDayFlux>();
    		GprsDayFlux gprsDayFlux = new GprsDayFlux();
        	if("0".equals(res.getResultCode())){
        		if(inputDayOfYear!=res.getGprsDayFluxList().size())
        		{
        			for(int i=days ; i >=0  ; i--){
            			cl.set(Calendar.DAY_OF_YEAR, inputDayOfYear-i);
            			datePeriodList.add(df.format(cl.getTime()));
            		}
            		List<GprsDayFlux>  tempList = res.getGprsDayFluxList();
            		boolean flag = false;
            		for(String obj:datePeriodList){
            			gprsDayFlux = new GprsDayFlux();
            			if(tempList !=null && tempList.size()>0)
            			{
            				for(GprsDayFlux dayflux:tempList){
                				if(obj.equals(dayflux.getDayNum())){
                					gprsDayFlux = dayflux;
                					flag = true;
                				}
                			}
            			}
            			if(flag){
            				gprsDayFluxList.add(gprsDayFlux);
            				flag = false;
            			}else{
            				gprsDayFlux.setDayNum(obj);
            				gprsDayFluxList.add(gprsDayFlux);
            			}
            		}
            		res.setGprsDayFluxList(gprsDayFluxList);
        		}

    		}else{
    			for(int i=days ; i >=0  ; i--){
        			cl.set(Calendar.DAY_OF_YEAR, inputDayOfYear-i);
        			datePeriodList.add(df.format(cl.getTime()));
        		}
    			for(String obj:datePeriodList){
        			gprsDayFlux = new GprsDayFlux();
        			gprsDayFlux.setDayNum(obj);
        			gprsDayFluxList.add(gprsDayFlux);
        		}
        		res.setGprsDayFluxList(gprsDayFluxList);
        		res.setResultCode("0");
    		}


        }
        catch (Exception e)
        {
            logger.error("", e);
        }
    }
    /**
	 * 获取参数值
	 * @param params
	 * @param name
	 * @return
	 */
	protected String getParameter(List<RequestParameter> params, String name)
	{
		if (params != null && params.size() > 0) {
			for(int i = 0; i<params.size(); i++)
			{
				RequestParameter param = params.get(i);
				if(param.getParameterName().equals(name))
				{
					return String.valueOf(param.getParameterValue());
				}
			}
		}

		return null;
	}

}