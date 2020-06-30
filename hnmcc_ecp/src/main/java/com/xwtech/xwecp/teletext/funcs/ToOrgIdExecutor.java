//package com.xwtech.xwecp.teletext.funcs;
//
//import org.slf4j.Logger;import org.slf4j.LoggerFactory;
//
//import com.xwtech.xwecp.dao.WellFormedDAO;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * 县市编码转组织机构ID编码
// * @author 杨光
// *
// */
//@Component
//public class ToOrgIdExecutor extends AbstractFunctionExecutor
//{
//	private static final Logger logger = LoggerFactory.getLogger(ToOrgIdExecutor.class);
//
//
//	@Resource(name = "wellFormedDAO")
//	private WellFormedDAO wellFormedDAO;
//
//	public WellFormedDAO getWellFormedDAO()
//	{
//		return wellFormedDAO;
//	}
//
//	public void setWellFormedDAO(WellFormedDAO wellFormedDAO)
//	{
//		this.wellFormedDAO = wellFormedDAO;
//	}
//
//	public ToOrgIdExecutor()
//	{
//		super("to_boss_orgid");
//	}
//
//	public String execute(String parameter)
//	{
//		String strRet = "";
//
//		try
//		{
//			if (!"".equals(parameter))
//			{
//				strRet = this.wellFormedDAO.getBossOrgid(parameter);
//			}
//		}
//		catch (Exception e)
//		{
//			logger.error("", e);
//			strRet = "";
//		}
//
//		return strRet;
//	}
//}
