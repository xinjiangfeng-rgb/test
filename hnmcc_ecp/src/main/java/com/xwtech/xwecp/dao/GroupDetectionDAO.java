package com.xwtech.xwecp.dao;

import com.xwtech.xwecp.Jedis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository("groupDetectionDAO")
public class GroupDetectionDAO  extends  BaseDAO {
    private static final Logger logger = LoggerFactory.getLogger(GroupDetectionDAO.class);

    @Resource
    private RedisService redisService;

    private static final String group_detection = "group_detection_";


    /**
     * 查询所有集团探测业务接口模板
     *
     * @return
     */
    public List<Map<String, Object>> getAllTem() {
        StringBuffer str = null;
        List<Map<String, Object>> list = null;
        try {
            str = new StringBuffer();
            str.append(" select * from T_ECP_DATA_TEMPLEMENT ");

            logger.info(" ====== 查询所有集团探测业务接口模板 sql ====== " + str.toString());
            list = (List<Map<String, Object>>) this.jdbcTemplate.queryForList(str.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    /**
     * 查询所有集团探测号码
     *
     * @return
     */
    public boolean getAllTelNum(String phoneNum) {
        String key = group_detection + phoneNum;
        String keyStr = redisService.get(key);
        if (StringUtils.isNotBlank(keyStr)) {
            if ("Y".equals(keyStr)) {
                return true;
            } else if ("N".equals(keyStr)) {
                return false;
            }
        }
        StringBuffer str = null;
        List<Map<String, Object>> list = null;
        try {
            str = new StringBuffer();
            str.append(" select * from t_group_detection_customers where phone_num=? ");
            List<Object> args = new ArrayList<Object>();
            args.add(phoneNum);

            list = (List<Map<String, Object>>) this.jdbcTemplate.queryForList(str.toString(), args.toArray());
            if (list == null || list.size() < 1) {
                redisService.set(key, "N", 2, TimeUnit.HOURS);
            } else {
                redisService.set(key, "Y", 2, TimeUnit.HOURS);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list == null ? false : (list.size() > 0 ? true : false);
    }



    /**
     *  查询所有集团探测号码
     * @return
     */
    public List<Map<String,Object>> getAllTelNum() {
        StringBuffer str = null;
        List<Map<String,Object>> list = null;
        try {
            str = new StringBuffer();
            str.append("select * from t_group_detection_customers");

            logger.info(" ====== 查询所有集团探测号码 sql ====== " + str.toString());
            list = (List<Map<String,Object>>)this.getJdbcTemplate().queryForList(str.toString());
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }


}