package com.xwtech.xwecp.service.logic.resolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ITeletextResolver;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import com.xwtech.xwecp.service.logic.pojo.GommonBusiness;
import com.xwtech.xwecp.service.logic.pojo.QRY020001Result;

public class FindPackageBaseResolver implements ITeletextResolver {
	private static final Logger logger = LoggerFactory.getLogger(FindPackageBaseResolver.class);

	private WellFormedDAO wellFormedDAO;

	public FindPackageBaseResolver() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.wellFormedDAO = (WellFormedDAO) (springCtx
				.getBean("wellFormedDAO"));
	}

	public void resolve(BaseServiceInvocationResult result,
			Object bossResponse, List<RequestParameter> param) throws Exception {
		GommonBusiness dt = null;
		List<GommonBusiness> list = null;
		List<GommonBusiness> reList = new ArrayList();
		List<BossParmDT> bList = null;
		RequestParameter reqDT = null;
		String bizId = "";

		try {
			QRY020001Result ret = (QRY020001Result) result;
			list = ret.getGommonBusiness();

			if (null != param && param.size() > 0) {
				for (RequestParameter p : param) {
					if (p.getParameterName().equals("bizId")) {
						bizId = String.valueOf(p.getParameterValue());
						if (!"".equals(bizId)) {
							if("findzzcp".equals(bizId)){
								bizId = "ZZCP";
							}
							if("findmwcp".equals(bizId)){
								bizId = "MWCP";
							}
							bList = this.wellFormedDAO.getSubBossBaseParmList(bizId);
							break;
						}
					}
				}
			}

			if (null != list && list.size() > 0) {
				if (null != bList && bList.size() > 0) {
					for (GommonBusiness g : list) {
						boolean close = true;
						//判断过滤新免邮（100160000335）、飞信（400000075975）、和彩云（410000190431）
						if("100160000335".equals(g.getReserve1())||"400000075975".equals(g.getReserve1())||"410000190431".equals(g.getReserve1())){
							close = false;
						}else{
							for (BossParmDT bDt : bList) {
								g.setState(2);
								if (bDt.getParm2().equals(g.getReserve1()) ) {
									close = false;
									g.setId(bDt.getBusiNum());
									g.setName(bDt.getParm5());
									g.setReserve2(bizId);
									reList.add(g);
								}
							}
						}

						if (close) {
//							dt = new GommonBusiness();
//							dt.setId(bDt.getBusiNum());
//							dt.setName(bDt.getParm5());
							g.setState(1);
							g.setReserve2(bizId);
							reList.add(g);
						}
					}
				} else {
					reList = list;
				}
			} else {
//				if (null != bList && bList.size() > 0) {
//					for (BossParmDT bDt : bList) {
//						dt = new GommonBusiness();
//						dt.setId(bDt.getBusiNum());
//						dt.setName(bDt.getParm5());
//						dt.setReserve2(bizId);
//						dt.setState(1);
//						reList.add(dt);
//					}
//				} else {
//					dt = new GommonBusiness();
//					dt.setId(bizId);
//					dt.setReserve2(bizId);
//					dt.setState(1);
//					reList.add(dt);
//				}
			}

			ret.setGommonBusiness(reList);
		} catch (Exception e) {
			logger.error("查询业务列表失败.", e);
		}
	}
}