package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.communication.RemoteCallContext;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.PkgConsume;
import com.xwtech.xwecp.service.logic.pojo.QRY170411Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class QueryPkgConsumeInvocation extends BaseInvocation implements ILogicalService{
	private static final Logger logger = LoggerFactory.getLogger(QueryPkgConsumeInvocation.class);

	@Override
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
			List<RequestParameter> params) {

		QRY170411Result res = new QRY170411Result();
		List<PkgConsume> retList = new ArrayList<PkgConsume>();
		try{

			String rspXml = "";
			String reqXml = this.bossTeletextUtil.mergeTeletext("cc_queryPkgConsume", params);
			RemoteCallContext city = this.generateCity(params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, "ac_gsmcdr_query_501", city));

			logger.debug(" ======   ======\n" + rspXml);
			if (null != rspXml && !"".equals(rspXml)){
				Message bossMessage = MessageUtil.parse(rspXml);
				if (null != bossMessage)
				{
					if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode()))
					{
						res.setResultCode(MessageUtil.LOGIC_SUCESS);
						res.setBossCode(bossMessage.getHead().getCode());
						res.setErrorMessage(bossMessage.getHead().getDesc());

						List<String[]> rowList = bossMessage.getBody().asList();


						PkgConsume pkgConsume = null;
						//						if(StringUtils.isNotBlank(filterParam)){
						  int size = rowList.size();
						for(int i =0; i < size; i++){
							pkgConsume = new PkgConsume();
							String values[] = rowList.get(i);
							if(i == 0 ){
								pkgConsume.setOtherFee(values[0]);
								pkgConsume.setOwnFee(values[1]);
								pkgConsume.setOutTotleFee(values[2]);
								pkgConsume.setOutDeductTime(values[3]);
								pkgConsume.setFixTotleFee(values[4]);
								pkgConsume.setFixDeductTime(values[5]);
								pkgConsume.setAcctItemTypeName(values[6]);
								pkgConsume.setReceAmount(values[7]);
								if( values.length >=9){
									pkgConsume.setForViceCard(values[8]);
								}
								
							}else{
								pkgConsume.setAcctItemTypeName(values[0]);
								pkgConsume.setReceAmount(values[1]);
							}
							retList.add(pkgConsume);
						}
						res.setPkgConsume(retList);
					
					}
					else
					{
						res.setResultCode(MessageUtil.LOGIC_ERROR);
						res.setBossCode(bossMessage.getHead().getCode());
						res.setErrorMessage(bossMessage.getHead().getDesc());
					}
				}
				else
				{
					res.setResultCode(MessageUtil.LOGIC_ERROR);
					res.setErrorMessage("请求CRM接口失败.");
				}
			}
		}catch(Exception e){
			 logger.error("", e);
			 retList = null;
		}
		
		return res;
	}

}
