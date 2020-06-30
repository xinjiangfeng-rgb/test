package com.xwtech.xwecp.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 54344 on 2018/3/30.
 */
@Repository
public class PaiDuiDao extends BaseDAO   {

    public List<Map<String, Object>> queryBossId(){
        String sql = "SELECT * FROM BOSSID  ";
        List<Map<String, Object>> ret = this.getJdbcTemplate().queryForList(sql);
        return ret;
     }

}
