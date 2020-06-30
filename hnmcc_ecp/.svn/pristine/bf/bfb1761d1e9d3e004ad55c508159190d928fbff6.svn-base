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
// * 系统地市编码转boss地市编码
// * @author 吴宗德
// *
// */
//@Component
//public class ToBossCityExecutor extends AbstractFunctionExecutor
//{
//	private static final Logger logger = LoggerFactory.getLogger(ToBossCityExecutor.class);
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
//	public ToBossCityExecutor()
//	{
//		super("to_boss_city");
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
//				strRet = this.wellFormedDAO.getBossCity(parameter);
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
