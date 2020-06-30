package com.xwtech.xwecp.logs;

import com.xwtech.xwecp.dao.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WhiteDaoImpl extends BaseDAO implements IWhiteDao {
    private static final Logger logger = LoggerFactory.getLogger(WhiteDaoImpl.class);
    @Override
    public int getWhitenumber(String param,String status) {
        String sql="SELECT count(1) FROM  ECP27.T_ECP_WHITE where PHONE=? and STATUS=? and EXPIRE_DATE>=SYSDATE";
        int  count = getJdbcTemplateWhite().queryForObject(sql, Integer.class, param,status);
        return count;
    }
}
