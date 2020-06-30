package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.IZZCPBService;
import com.xwtech.xwecp.service.logic.pojo.DEL010029Result;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/***
 * 序号 数据名 描述 类型 大小 必选项 备注 <br/>
 * 1 SvcNum 号码 C 11 Y 号码<br/>
 * 2 AddProdPrcld 增值产品资费实例标识 C 20 Y 增值产品资费实例<br/>
 * 3 OperMode 操作类型 C 20 Y 操作类型:ADD添加/DEL退订<br/>
 * 4 Optrld 操作员工号 C 20 Y 操作员工号<br/>
 * 5 Telephonist 话务员工号 C 30 Y 话务员工号<br/>
 * 6 EffDate 生效日期 C 8 Y 生效日期<br/>
 * 7 ExpDate 失效日期 C 8 Y 失效日期<br/>
 * 
 */
public class ZengZhiChanPinServiceImpl extends BaseClientServiceImpl implements IZZCPBService {

	@Override
	public DEL010029Result transactZZCP(String phone, String bossCode, String proCode, int oprType) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("isTSYW", "No");
		if (StringUtils.isNotBlank(proCode) && oprType == 1) {
			mapParam.put("isTSYW", "TSYWCLL");// 如果传了产品编码，则默认其是特殊业务
			mapParam.put("id", proCode);// 特殊业务走前置处理或后置处理，id是产品编码，如话费提醒：HFTX_MZYC(每周一次),HFTX_MLTYC(每两天一次),HFTX_MTYC(每天一次)
		}
		mapParam.put("phoneNum", phone);
		mapParam.put("bossCode", bossCode);
		mapParam.put("oprType", oprType);
		mapParam.put("__cmd", "DEL010029");

		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			DEL010029Result del29Rs = new DEL010029Result();
			BeanUtils.copyProperties(del29Rs, rs);
			return del29Rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
