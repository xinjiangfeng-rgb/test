package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040113Result;

/**
 * 用户信用评级查询
 * @author wangjiajia
 * 2013-12-5
 */
public interface IQueryCreditGradeService {
	public QRY040113Result queryCreditGrade(String phoneNum)throws LIException;
}
