package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL200240Result;

public interface IInternationalBasicService {

	/**
	 *
	 * @param phoneNum 手机号码
	 * @param operMode	操作代码	ADD：开通 DEL：取消
	 * @param prodCustomParaType	产品类型代码
	 * @param addProdPrcId	资费实例代码 国际长途 国际漫游 国际漫游GPRS
	 * @param prodPrcPara	自定义参数	取消时无需填写 办理方式: 1 长期 2 短期 只能传 1,2
	 * @param optrId	操作员工号
	 * @param telephonist	话务员工号
	 * @param multPricePlanId	价格计划
	 * @param begdate	开始时间
	 * @param enddate	结束时间
	 * @param remindGPRS	国际漫游GPRS 提醒区间值
	 * @return
	 * @throws LIException
	 */
	public DEL200240Result internationalBasicBusinessTransaction(String phoneNum,
                                                                 String operMode,
                                                                 String prodCustomParaType,
                                                                 String addProdPrcId,
                                                                 String prodPrcPara,
                                                                 String optrId,
                                                                 String telephonist,
                                                                 String multPricePlanId,
                                                                 String begdate,
                                                                 String enddate,
                                                                 String remindGPRS) throws LIException;
}