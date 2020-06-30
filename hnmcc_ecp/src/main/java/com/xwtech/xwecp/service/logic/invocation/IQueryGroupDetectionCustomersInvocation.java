package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.GroupDetectionDAO;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY010004Result;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.ParseXmlConfig;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by 54344 on 2018/3/3.
 */
public class IQueryGroupDetectionCustomersInvocation extends BaseInvocation implements ILogicalService {



    private static final Logger logger = LoggerFactory.getLogger(BillQueryInvocation.class);

    private BossTeletextUtil bossTeletextUtil;

    private IRemote remote;

    private WellFormedDAO wellFormedDAO;
    private GroupDetectionDAO groupDetectionDAO;
    private ParseXmlConfig config;

    public IQueryGroupDetectionCustomersInvocation()
    {
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
        this.remote = (IRemote)(springCtx.getBean("bossRemote"));
        this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
        this.groupDetectionDAO = (GroupDetectionDAO)(springCtx.getBean("groupDetectionDAO"));
        this.config = new ParseXmlConfig();
    }


    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY010004Result ret = new QRY010004Result();

        try {
            String phone = "";
            for (RequestParameter p : params) {
                if (p.getParameterName().equals("phone")) {
                    phone = p.getParameterValue().toString();
                }
            }

            boolean  flag =  groupDetectionDAO.getAllTelNum(phone);
            if(flag){
                ret.setFlag("1");

            }else
            {
                ret.setFlag("0");
            }

            ret.setResultCode("0");
            ret.setBossCode("0000");
            ret.setErrorMessage("调用成功");
        } catch (Exception e) {
            e.printStackTrace();
            ret.setResultCode("1");
            ret.setErrorMessage(e.getMessage());
        }


        return ret;
    }
}
