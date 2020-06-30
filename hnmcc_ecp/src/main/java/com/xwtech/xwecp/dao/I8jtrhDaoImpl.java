//package com.xwtech.xwecp.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import com.alibaba.fastjson.JSON;
//import com.xwtech.xwecp.util.StringUtil;
//
//public class I8jtrhDaoImpl extends BaseDAO implements II8jtrhDao {
//
//	@SuppressWarnings("unchecked")
//	public List<String> getI8TC() throws DAOException{
////		List<String> ids = (List<String>)(this.getCache().get("T_I8RHTC"));
//		List<String> ids = JSON.parseArray(this.getCache().get("T_I8RHTC"), String.class);
//		if(ids == null) {
//			ids = new ArrayList<String>();
//			List<Map<String,Object>> ret = (List<Map<String,Object>>)this.getJdbcTemplate().queryForList("SELECT F_PRODID FROM T_I8RHTC");
//			if(ret != null && ret.size() > 0) {
//				for(Map m : ret) {
//					//PackageBean bean = new PackageBean();
//					String string = StringUtil.convertNull((String)m.get("F_PRODID"));
//					//bean.setPkgNumBoss(StringUtil.convertNull((String)m.get("F_PKG_NUM_BOSS")));
//					ids.add(string);
//				}
//			}
////			this.getCache().add("T_I8RHTC", ids);
//			this.getCache().add("T_I8RHTC", JSON.toJSONString(ids));
//		}
//
//		return ids;
//	}
//
//	public boolean isT8JTHRH(String tcId) throws DAOException{
//		List<String> ids = getI8TC();
//		if(null != ids && 0 < ids.size())
//		{
//			for(String id : ids)
//			{
//				if(id.equals(tcId))
//				{
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//}
