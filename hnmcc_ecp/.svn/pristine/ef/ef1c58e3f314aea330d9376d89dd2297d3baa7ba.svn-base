package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY040109Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class QryContactGroupInvocation extends BaseInvocation
implements ILogicalService
{
private static final Logger logger = LoggerFactory.getLogger(QryContactGroupInvocation.class);
private BossTeletextUtil bossTeletextUtil;
private IRemote remote;
private String REQUEST_BOSSTELETEXT_1 = "cc_qrycontact_group";

public QryContactGroupInvocation()
{
  ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
  this.bossTeletextUtil = ((BossTeletextUtil)springCtx.getBean("bossTeletextUtil"));
  this.remote = ((IRemote)springCtx.getBean("bossRemote"));
  this.wellFormedDAO = ((WellFormedDAO)springCtx.getBean("wellFormedDAO"));
}

public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params)
{
  QRY040109Result result = new QRY040109Result();
  String reqXml = "";
  String rspXml = "";
  try
  {
    reqXml = this.bossTeletextUtil.mergeTeletext(this.REQUEST_BOSSTELETEXT_1, params);
    rspXml = (String)this.remote.callRemote(new DefaultServiceImpl.StringTeletext(reqXml, accessId, this.REQUEST_BOSSTELETEXT_1, generateCity(params)));
    if ((StringUtils.isEmpty(rspXml)) || (rspXml.getBytes().length < 120))
    {
      result.setBossCode("1");
      result.setErrorCode("1");
      return result;
    }
    MessageUtil.Message bossMsg = MessageUtil.parse(rspXml);
    if ("0000".equals(bossMsg.getHead().getCode())) {
      String[] resArray = bossMsg.getBody().asArray();
      if (resArray != null) {
        if (resArray.length == 1) {
          result.setResult(resArray[0]);
        } else if (resArray.length == 5) {
          result.setResult(resArray[0]);
          result.setGbillId(resArray[1]);
          result.setName(resArray[2]);
          result.setBillId(resArray[3]);
          result.setPName(resArray[4]);
        }
      }
    }
    result.setResultCode("0000".equals(bossMsg.getHead().getCode()) ? "0" : "1");
    result.setBossCode(bossMsg.getHead().getCode());
    result.setErrorMessage(bossMsg.getHead().getDesc());
  }
  catch (Exception e)
  {
    logger.error("", e);
  }
  return result;
}
}