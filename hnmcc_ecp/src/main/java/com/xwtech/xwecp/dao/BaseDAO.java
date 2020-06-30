package com.xwtech.xwecp.dao;


import com.xwtech.xwecp.Jedis.RedisClientNew;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.annotation.Resource;
import java.sql.Types;


public class BaseDAO
{
	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    @Resource(name = "jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;


    @Resource(name = "jdbcTemplateLog")
    protected JdbcTemplate jdbcTemplateLog;

    @Resource(name ="jdbcTemplatewhite" )
    protected JdbcTemplate jdbcTemplateWhite;

    @Autowired
    protected RedisClientNew cache;

    @Autowired
    protected LobHandler lobHandler;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RedisClientNew getCache() {
		return cache;
	}

	public void setCache(RedisClientNew cache) {
		this.cache = cache;
	}

    public JdbcTemplate getJdbcTemplateLog() {
        return jdbcTemplateLog;
    }

    public void setJdbcTemplateLog(JdbcTemplate jdbcTemplateLog) {
        this.jdbcTemplateLog = jdbcTemplateLog;
    }

    public JdbcTemplate getJdbcTemplateWhite() {
        return jdbcTemplateWhite;
    }

    public void setJdbcTemplateWhite(JdbcTemplate jdbcTemplateWhite) {
        this.jdbcTemplateWhite = jdbcTemplateWhite;
    }

    /**
     * Clob字段更新
     * 
     * @param sql
     * @param args
     * @return intResult
     * @throws DataAccessException
     */
    protected void updateForClob(String sql, Object[] args)
            throws DataAccessException {
        String className = "";
        int[] argTypes = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            className = args[i].getClass().getName();
            if (className.indexOf("String") > 0) {
                argTypes[i] = Types.VARCHAR;
            } else if (className.indexOf("Long") > 0
                    || className.indexOf("Integer") > 0
                    || className.indexOf("Double") > 0
                    || className.indexOf("Float") > 0) {
                argTypes[i] = Types.NUMERIC;
            } else if (className.indexOf("SqlLobValue") > 0) {
                argTypes[i] = Types.CLOB;
            } else {
                argTypes[i] = Types.VARCHAR;
            }
        }
        this.getJdbcTemplate().update(sql, args, argTypes);
    }
    
    /**
     * 修改数据，加Stringj类型字段NULL值校验
     * @param sql
     * @param args
     * @throws DAOException
     */
    protected void update(String sql , Object[] args) throws DataAccessException{
    	if(null != args){
    		for(int i = 0 ;i < args.length;i++)
    		{
    			if( args[i] == null ){
    				args[i] = "";
    			}
    		}
    	}
    	int iResult = getJdbcTemplate().update(sql, args);
    }
}
