package com.xwtech.xwecp.dao;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.pojo.NumberSegment;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import com.xwtech.xwecp.service.logic.pojo.ErrorMapping;
import com.xwtech.xwecp.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.xwtech.xwecp.pojo.CityArchives;


@Repository("wellFormedDAO")
public class WellFormedDAO extends  BaseDAO
{
    private static final Logger logger = LoggerFactory.getLogger(WellFormedDAO.class);



    public WellFormedDAO(){
        System.out.println("WellFormedDAOinit");
    }



//    public Object executeDatabaseAccess(IDatabaseAccessCallback callback)
//        throws DAOException
//    {
//        Connection conn = null;
//        try
//        {
//            conn = this.getDataSource().getConnection();
//            Object obj = callback.execute(conn);
//            return obj;
//        }
//        catch (Exception e)
//        {
//            throw new DAOException(e);
//        }
//        finally
//        {
//            try
//            {
//                if (conn != null)
//                {
//                    conn.close();
//                }
//            }
//            catch (SQLException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Object executeDatabaseAccessWithTransaction(IDatabaseAccessCallback callback)
//        throws DAOException
//    {
//        Connection conn = null;
//        try
//        {
//            conn = this.getDataSource().getConnection();
//            conn.setAutoCommit(false);
//            Object obj = callback.execute(conn);
//            conn.commit();
//            return obj;
//        }
//        catch (Exception e)
//        {
//            try
//            {
//                conn.rollback();
//            }
//            catch (SQLException e1)
//            {
//                logger.error(e1, e1);
//            }
//            throw new DAOException(e);
//        }
//        finally
//        {
//            try
//            {
//                if (conn != null)
//                {
//                    conn.close();
//                }
//            }
//            catch (SQLException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
    
    /**
     * 查询业务编码
     * 
     * @param parameter
     * @return
     */
    public BossParmDT getBossParm(String parameter)
    {
        StringBuffer str = null;
        BossParmDT dt = null;
        
        try
        {
//            Object obj = this.getCache().get("Parm_" + parameter);
            Object obj = JSON.parseObject(this.getCache().get("Parm_" + parameter),BossParmDT.class);
            if (obj != null && obj instanceof BossParmDT)
            {
                dt = (BossParmDT)obj;
            }
            else
            {
                str = new StringBuffer();
                str.append("SELECT P.* ");
                str.append("FROM t_business_boss_imp P ");
                str.append("WHERE P.F_BUSINESS_NUM = '");
                str.append(parameter);
                str.append("'");
                
                logger.info(" ====== 查询业务的BOSS实现 sql ====== " + str.toString());
                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
                
                for (Map m : list)
                {
                    dt = new BossParmDT();
                    dt.setBusiNum(String.valueOf(StringUtil.convertNull((String)m.get("F_BUSINESS_NUM"))));
                    dt.setParm1(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM1"))));
                    dt.setParm2(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM2"))));
                    dt.setParm3(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM3"))));
                    dt.setParm4(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM4"))));
                    dt.setParm5(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM5"))));
                    dt.setStrBz(String.valueOf(StringUtil.convertNull((String)m.get("F_BZ"))));
                    
//                    this.getCache().add("Parm_" + parameter, dt);
                    this.getCache().add("Parm_" + parameter, JSON.toJSONString(dt));
                }
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return dt;
    }
    /**
     * 特殊业务办理查询
     * 
     * @param parameter
     * @return
     */
    public BossParmDT getBussinessSpecialParm(String parameter)
    {
        StringBuffer str = null;
        BossParmDT dt = null;
        
        try
        {
//            Object obj = this.getCache().get("special_" + parameter);
            Object obj = JSON.parseObject(this.getCache().get("special_" + parameter),BossParmDT.class);
            if (obj != null && obj instanceof BossParmDT)
            {
                dt = (BossParmDT)obj;
            }
            else
            {
                str = new StringBuffer();
                str.append("SELECT  *  from t_bussiness_special_info T where T.ecpcode = '");
                str.append(parameter);
                str.append("'");
                
                logger.info(" ====== 查询特殊业务的BOSS实现 sql ====== " + str.toString());
                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
                
                for (Map m : list)
                {
                    dt = new BossParmDT();
                    dt.setBusiNum(String.valueOf(StringUtil.convertNull((String)m.get("ecpcode"))));
                    dt.setParm1(String.valueOf(StringUtil.convertNull((String)m.get("custom_para_type"))));
                    dt.setParm2(String.valueOf(StringUtil.convertNull((String)m.get("prod_prc_id"))));
                    dt.setParm3(String.valueOf(StringUtil.convertNull((String)m.get("prc_para"))));
                    
//                    this.getCache().add("special_" + parameter, dt);
                    this.getCache().add("special_" + parameter, JSON.toJSONString(dt));
                }
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return dt;
    }
    
    /**
     * 推荐活动中,根据老网厅的bizId查询新网厅的bizNum和bizName
     * 
//     * @param BizId
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public Map<String, Object> getBizNum4BizId(String bizId)
//    {
//        final String CACHE_NAME = "OLD_BIZ_NUM_";
//        List<Map<String,Object>> list = null;
//        try
//        {
////            Object obj = this.getCache().get(CACHE_NAME + bizId);
//            Object obj = JSON.parseArray(this.getCache().get(CACHE_NAME + bizId),Map.class);
//            if (obj != null)
//            {
//                list = (List<Map<String,Object>>)obj;
//            }
//            else
//            {
//                final String sql = "SELECT T.F_NEW_BIZ_NUM,T.F_NEW_BIZ_NAME,T.F_NEW_BIZ_URL FROM T_BIZ_DZ T WHERE T.F_OLD_BIZ_NUM = ?";
//
//                list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql, new Object[] {bizId});
//                if (list.size() > 0)
//                {
////                	this.getCache().add(CACHE_NAME + bizId, list);
//                    this.getCache().add(CACHE_NAME + bizId, JSON.toJSONString(list));
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error("", e);
//        }
//        return list != null && list.size() > 0 ? list.get(0) : null;
//    }
    
    /**
     * 查询业务编码
     * 
     * @param parameter
     * @return
     */
    @SuppressWarnings("unchecked")
//    public BossParmDT getSysBizCodeParm(String parameter)
//    {
//        StringBuffer str = null;
//        BossParmDT dt = null;
//
//        try
//        {
////            Object obj = this.getCache().get("PKG_EXG_ECP_BOSS_DZ_" + parameter);
//            Object obj = JSON.parseArray(this.getCache().get("PKG_EXG_ECP_BOSS_DZ_" + parameter), BossParmDT.class);
//            if (obj != null && obj instanceof BossParmDT)
//            {
//                dt = (BossParmDT)obj;
//            }
//            else
//            {
//                str = new StringBuffer();
//                str.append("SELECT * FROM T_PKG_EXG_ECP_BOSS_DZ T ");
//                str.append("WHERE T.F_BUSI_NUM_BOSS1 = '");
//                str.append(parameter);
//                str.append("'");
//
//                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
//
//                for (Map m : list)
//                {
//                    dt = new BossParmDT();
//                    dt.setBusiNum(String.valueOf(StringUtil.convertNull((String)m.get("F_BUSI_NUM_ECP"))));
////                    this.getCache().add("PKG_EXG_ECP_BOSS_DZ_" + parameter, dt);
//                    this.getCache().add("PKG_EXG_ECP_BOSS_DZ_" + parameter, JSON.toJSONString(dt));
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error(e.getMessage());
//        }
//        return dt;
//    }
    
    /**
     * 查询业务所对应的BOSS编码
     * 
     * @param parameter
     * @return
     */
//    public List getBossParmList(String parameter)
//    {
//        StringBuffer str = null;
//        BossParmDT dt = null;
//        List<BossParmDT> parList = null;
//
//        try
//        {
////            Object obj = this.getCache().get("LIST_" + parameter);
//            Object obj = JSON.parseArray(this.getCache().get("LIST_" + parameter),BossParmDT.class);
//            if (obj != null && obj instanceof List)
//            {
//                parList = (List)obj;
//            }
//            else
//            {
//                str = new StringBuffer();
//                str.append("SELECT P.F_BOSS_IMP_PARM1, P.F_BOSS_IMP_PARM2, ");
//                str.append("P.F_BOSS_IMP_PARM3, P.F_BOSS_IMP_PARM4, P.F_BOSS_IMP_PARM5 ");
//                str.append("FROM T_BUSINESS_BOSS_IMP P ");
//                str.append("WHERE P.F_BUSINESS_NUM = '");
//                str.append(parameter);
//                str.append("'");
//
//                logger.info(" ====== 查询业务的BOSS实现 sql ====== " + str.toString());
//                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
//                parList = new ArrayList();
//
//                for (Map m : list)
//                {
//                    dt = new BossParmDT();
//                    dt.setParm1(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM1"))));
//                    dt.setParm2(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM2"))));
//                    dt.setParm3(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM3"))));
//                    dt.setParm4(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM4"))));
//                    dt.setParm5(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM5"))));
//                    parList.add(dt);
//                }
////                this.getCache().add("LIST_" + parameter, parList);
//                this.getCache().add("LIST_" + parameter, JSON.toJSONString(parList));
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error("", e);
//        }
//        return parList;
//    }
    
    /**
     * 根据业务编码获取其子业务所对应的BOSS编码
     * 
     * @param parameter
     * @return
     */
    public List getSubBossParmList(String parameter)
    {
        StringBuffer str = null;
        BossParmDT dt = null;
        List<BossParmDT> parList = null;
        
        try
        {
//            Object obj = this.getCache().get("SUB_" + parameter);
            Object obj = JSON.parseArray(this.getCache().get("SUB_" + parameter), BossParmDT.class);
            if (obj != null && obj instanceof List)
            {
                parList = (List)obj;
            }
            else
            {
//                str = new StringBuffer();
//                str.append("SELECT P.F_BUSINESS_NUM, P.F_BOSS_IMP_PARM1, ");
//                str.append("P.F_BOSS_IMP_PARM2, P.F_BOSS_IMP_PARM3, ");
//                str.append("P.F_BOSS_IMP_PARM4, P.F_BOSS_IMP_PARM5, P.F_BZ ");
//                str.append("FROM T_BUSINESS_BOSS_IMP P WHERE P.F_BUSINESS_NUM IN ");
//                str.append("(SELECT DISTINCT A.F_BUSINESS_NUM FROM T_BUSINESS_DA A JOIN ");
//                str.append("(SELECT * FROM T_BUSINESS_DA WHERE F_BUSINESS_NUM = '");
//                str.append(parameter);
//                str.append("') B ");
//                str.append("ON SUBSTR(A.F_JB_NUM, 1, 3*B.F_JB) = B.F_JB_NUM ");
//                str.append("WHERE A.F_MJ = 1 )");


                String sql ="SELECT  * " +
                        "FROM\n" +
                        "\t t_business_boss_imp P \n" +
                        "WHERE\n" +
                        "\tP.F_BUSINESS_NUM IN (\n" +
                        "\t\n" +
                        "SELECT DISTINCT\n" +
                        "\tA.F_BUSINESS_NUM \n" +
                        "FROM\n" +
                        "\tt_business_da A JOIN ( SELECT * FROM t_business_da WHERE F_BUSINESS_NUM = '"+parameter+"' ) B ON SUBSTR( A.F_JB_NUM, 1, 3 * B.F_JB ) = B.F_JB_NUM \n" +
                        "WHERE\n" +
                        "\tA.F_MJ = 1 \n" +
                        "\t\n" +
                        "\t)";

                logger.info(" ====== 查询子业务的BOSS实现 sql ====== " + sql);
                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql);
                parList = new ArrayList();
                
                for (Map m : list)
                {
                    dt = new BossParmDT();
                    dt.setBusiNum(String.valueOf(StringUtil.convertNull((String)m.get("F_BUSINESS_NUM"))));
                    dt.setParm1(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM1"))));
                    dt.setParm2(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM2"))));
                    dt.setParm3(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM3"))));
                    dt.setParm4(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM4"))));
                    dt.setParm5(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM5"))));
                    dt.setStrBz(String.valueOf(StringUtil.convertNull((String)m.get("F_BZ"))));
                    parList.add(dt);
                }
//                this.getCache().add("SUB_" + parameter, parList);
                this.getCache().add("SUB_" + parameter, JSON.toJSONString(parList));
            }
        }
        catch (Exception e)
        {
            logger.error("wellFormedDao getSubBossParmList error ：",e);
        }
        return parList;
    }
    /**
     * 根据业务编码获取其业务所对应的BOSS编码
     * 
     * @param parameter
     * @return
     */
    public List getSubBossBaseParmList(String parameter)
    {
        StringBuffer str = null;
        BossParmDT dt = null;
        List<BossParmDT> parList = null;
        
        try
        {
//            Object obj = this.getCache().get("SUB_BASE_" + parameter);
            Object obj = JSON.parseArray(this.getCache().get("SUB_BASE_" + parameter), BossParmDT.class);
            if (obj != null && obj instanceof List)
            {
                parList = (List)obj;
            }
            else
            {
                str = new StringBuffer();
                str.append("SELECT P.F_BUSINESS_NUM, P.F_BOSS_IMP_PARM1, ");
                str.append("P.F_BOSS_IMP_PARM2, P.F_BOSS_IMP_PARM3, ");
                str.append("P.F_BOSS_IMP_PARM4, P.F_BOSS_INT_NUM, P.F_BZ ");
                str.append("FROM t_business_boss_imp P WHERE P.F_BOSS_IMP_PARM1 = '");
                str.append(parameter);
                str.append("'");
                
                logger.info(" ====== 查询业务的BOSS实现 sql ====== " + str.toString());
                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
                parList = new ArrayList();
                
                for (Map m : list)
                {
                    dt = new BossParmDT();
                    dt.setBusiNum(String.valueOf(StringUtil.convertNull((String)m.get("F_BUSINESS_NUM"))));
                    dt.setParm1(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM1"))));
                    dt.setParm2(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM2"))));
                    dt.setParm3(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM3"))));
                    dt.setParm4(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_IMP_PARM4"))));
                    dt.setParm5(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_INT_NUM"))));
                    dt.setStrBz(String.valueOf(StringUtil.convertNull((String)m.get("F_BZ"))));
                    parList.add(dt);
                }
//                this.getCache().add("SUB_BASE_" + parameter, parList);
                this.getCache().add("SUB_BASE_" + parameter, JSON.toJSONString(parList));
            }
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return parList;
    }
    
    /**
     * 查询业务类别
     * 
     * @param f_business_num
     * @param f_li_number
     * @param f_channel_type_num
     * @return
     */
    public String getBusiParm(String f_business_num, String f_li_number, String f_channel_type_num)
    {
        String bossType = "";
        StringBuffer str = null;
        
        try
        {
//            Object obj = this.getCache().get(f_li_number.trim() + "" + f_business_num.trim());
            Object obj = JSON.parse(this.getCache().get(f_li_number.trim() + "" + f_business_num.trim()));
            if (obj != null)
            {
                bossType = obj.toString();
            }
            else
            {
                str = new StringBuffer();
                str.append("SELECT Z.F_BOSS_INT_TYPE ");
                str.append("FROM  t_busi_sort_dz Z ");
                str.append("WHERE Z.F_BUSINESS_NUM = '");
                str.append(f_business_num);
                str.append("' ");
                str.append("AND Z.F_LI_NUMBER = '");
                str.append(f_li_number);
                str.append("' ");
                if (!"".equals(f_channel_type_num))
                {
                    str.append("AND Z.F_CHANNEL_TYPE_NUM = '");
                    str.append(f_channel_type_num);
                    str.append("' ");
                }
                logger.info(" ====== 查询业务归类 sql ====== " + str.toString());
                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
                for (Map m : list)
                {
                    bossType = m.get("F_BOSS_INT_TYPE").toString();
                }
//                this.getCache().add(f_li_number.trim() + "" + f_business_num.trim(), bossType);
                this.getCache().add(f_li_number.trim() + "" + f_business_num.trim(), JSON.toJSONString(bossType));
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        
        return bossType;
    }
    
    /**
     * 查询业务类别
     * 
     * @param f_business_num
     * @param f_li_number
     * @param f_channel_type_num
     * @return
     */
    public String getBusiParm2(String f_business_num, String f_li_number, String f_channel_type_num, String oprType)
    {
        String bossType = "";
        StringBuffer str = null;
        
        try
        {
//            Object obj = this.getCache().get(f_li_number.trim() + "" + f_business_num.trim()+""+oprType.trim());
            Object obj = JSON.parse(this.getCache().get(f_li_number.trim() + "" + f_business_num.trim()+""+oprType.trim()));
            if (obj != null)
            {
                bossType = obj.toString();
            }
            else
            {
                str = new StringBuffer();
                str.append("SELECT Z.F_BOSS_INT_TYPE ");
                str.append("FROM t_busi_sort_dz Z ");
                str.append("WHERE Z.F_BUSINESS_NUM = '");
                str.append(f_business_num);
                str.append("' ");
                str.append("AND Z.F_LI_NUMBER = '");
                str.append(f_li_number);
                str.append("' ");
                str.append("AND Z.OPRTYPE = '");
                str.append(oprType);
                str.append("' ");
                if (!"".equals(f_channel_type_num))
                {
                    str.append("AND Z.F_CHANNEL_TYPE_NUM = '");
                    str.append(f_channel_type_num);
                    str.append("' ");
                }
                logger.info(" ====== 查询业务归类 sql ====== " + str.toString());
                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
                for (Map m : list)
                {
                    bossType = m.get("F_BOSS_INT_TYPE").toString();
                }
//                this.getCache().add(f_li_number.trim() + "" + f_business_num.trim()+""+oprType.trim(), bossType);
                this.getCache().add(f_li_number.trim() + "" + f_business_num.trim()+""+oprType.trim(), JSON.toJSONString(bossType));
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        
        return bossType;
    }
    
//    public String getBossCity(String parameter)
//    {
//        String strRet = "";
//
//        List<CityArchives> list = getCityArchives();
//
//        if (list != null && list.size() > 0)
//        {
//            for (CityArchives bean : list)
//            {
//                if (parameter.equals(bean.getAreaNum()))
//                {
//                    strRet = bean.getBossCode();
//                    break;
//                }
//            }
//        }
//
//        return strRet;
//    }
//
//    /**
//     * 组织机构编码
//     *
//     * @param parameter
//     * @return
//     */
//    public String getBossOrgid(String parameter)
//    {
//        String strRet = "";
//
//        List<CityArchives> list = getCityArchives();
//
//        if (list != null && list.size() > 0)
//        {
//            for (CityArchives bean : list)
//            {
//                if (parameter.equals(bean.getAreaNum()))
//                {
//                    strRet = bean.getOrgId();
//                    break;
//                }
//            }
//        }
//
//        return strRet;
//    }
    
//    /**
//     * 根据Bosscode转换本地保存的名称
//     *
//     * @param parameter
//     * @author Tkk
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public String getCityNameAndNum(String parameter)
//    {
//        String strRet = "";
//
//        List<CityArchives> list = getCityArchives();
//
//        if (list != null && list.size() > 0)
//        {
//            for (CityArchives bean : list)
//            {
//                if (parameter.equals(bean.getBossCode()))
//                {
//                    strRet = bean.getAreaName() + ',' + bean.getAreaNum();
//                    break;
//                }
//            }
//        }
//
//        return strRet;
//    }
    
//    public List getCityArchives()
//    {
//        List retList = null;
//        try
//        {
//            String key = "CITYARCHIVES";
////            Object obj = this.getCache().get(key);
//            Object obj = JSON.parseArray(this.getCache().get(key),CityArchives.class);
//            if (obj != null && obj instanceof List)
//            {
//                retList = (List)obj;
//            }
//            else
//            {
//                String sql = "SELECT T.F_AREA_NUM,T.F_AREA_NAME,T.F_BOSS_CODE,F_BOSS_ORGID FROM T_AREA_DA T";
//
//                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql);
//
//                if (list != null && list.size() > 0)
//                {
//                    retList = new ArrayList<CityArchives>(list.size());
//                    CityArchives bean = null;
//                    for (Map m : list)
//                    {
//                        bean = new CityArchives();
//                        bean.setAreaNum(String.valueOf(StringUtil.convertNull((String)m.get("F_AREA_NUM"))));
//                        bean.setAreaName(String.valueOf(StringUtil.convertNull((String)m.get("F_AREA_NAME"))));
//                        bean.setBossCode(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_CODE"))));
//                        bean.setOrgId(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_ORGID"))));
//
//                        retList.add(bean);
//                    }
////                    this.getCache().add(key, retList);
//                    this.getCache().add(key, JSON.toJSONString(retList));
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error("", e);
//        }
//        return retList;
//    }
    
//    public String getBossBrand(String parameter)
//    {
//        String strRet = "";
//
//        List<BrandArchives> list = getBrandArchives();
//
//        if (list != null && list.size() > 0)
//        {
//            for (BrandArchives bean : list)
//            {
//                if (parameter.equals(bean.getBrandNum()))
//                {
//                    strRet = bean.getBossCode();
//                    break;
//                }
//            }
//        }
//
//        return strRet;
//    }
    
//    public List getBrandArchives()
//    {
//        List retList = null;
//        try
//        {
//            String key = "BRANDARCHIVES";
////            Object obj = this.getCache().get(key);
//            Object obj = JSON.parseArray(this.getCache().get(key),BrandArchives.class);
//            if (obj != null && obj instanceof List)
//            {
//                retList = (List)obj;
//            }
//            else
//            {
//                String sql = "SELECT T.F_BRAND_NUM,T.F_BRAND_NAME,T.F_BOSS_CODE FROM T_BRAND_DA T";
//
//                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql);
//
//                if (list != null && list.size() > 0)
//                {
//                    retList = new ArrayList<BrandArchives>(list.size());
//                    BrandArchives bean = null;
//                    for (Map m : list)
//                    {
//                        bean = new BrandArchives();
//                        bean.setBrandNum(String.valueOf(StringUtil.convertNull((String)m.get("F_BRAND_NUM"))));
//                        bean.setBrandName(String.valueOf(StringUtil.convertNull((String)m.get("F_BRAND_NAME"))));
//                        bean.setBossCode(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_CODE"))));
//
//                        retList.add(bean);
//                    }
////                    this.getCache().add(key, retList);
//                    this.getCache().add(key, JSON.toJSONString(retList));
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error("", e);
//        }
//        return retList;
//    }
    
    /**
     * boss接口转码
     * 
     */
    public ErrorMapping transBossErrCode(String logicId, String bossId, String bossErrCode)
    {
        
        if ("888".equals(bossErrCode))
        {
            ErrorMapping urgencyErrorMap = new ErrorMapping();
            urgencyErrorMap.setLiErrCode("888");
            urgencyErrorMap.setLiErrMsg("目前系统正在进行升级优化，给您带来不便，敬请广大客户谅解!");
            return urgencyErrorMap;
        }
        else
        {
            ErrorMapping errM = null;
            List<Map<String,Object>> list = this.getErrMapping();
            if (list != null && list.size() > 0)
            {
                ErrorMapping em = null;
                for (Map m : list)
                {
                    em = new ErrorMapping();
                    
                    em.setLiNumber(String.valueOf(StringUtil.convertNull((String)m.get("F_LI_NUMBER"))));
                    em.setBossIntNum(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_INT_NUM"))));
                    em.setBossErrCode(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_ERR_CODE"))));
                    em.setBossErrMsg(String.valueOf(StringUtil.convertNull((String)m.get("F_BOSS_ERR_MSG"))));
                    em.setLiErrCode(String.valueOf(StringUtil.convertNull((String)m.get("F_LI_ERR_CODE"))));
                    em.setLiErrMsg(String.valueOf(StringUtil.convertNull((String)m.get("F_LI_ERR_MSG"))));
                    
                    if (logicId.equals(em.getLiNumber()) && bossId.equals(em.getBossIntNum())
                        && bossErrCode.equals(em.getBossErrCode()))
                    {
                        errM = em;
                        break;
                    }
                }
            }
            return errM;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getErrMapping()
    {
        List<Map<String,Object>> retList = null;
        String key = "T_ERR_MAPPING";
        try
        {
//            Object obj = this.getCache().get(key);
            Object obj = JSON.parseArray(this.getCache().get(key),Map.class);
            if (obj != null && obj instanceof List)
            {
                retList = (List<Map<String,Object>>)obj;
            }
            else
            {
                String SQL = "  SELECT *  " +
                        "FROM t_err_mapping ";
                
                retList = this.jdbcTemplate.queryForList(SQL);
                
//                this.getCache().add(key, retList);
                this.getCache().add(key, JSON.toJSONString(retList));
            }
        }
        catch (Exception e)
        {
            logger.error("wellFormedDao getErrMapping error : ", e);
            retList = null;
        }
        return retList;
    }
    
    /**
     * 根据地区查询该地区所开通的业务的列表
     * 
//     *   bossAreaCode Boss发过来的地区Code
//     *   businessParentNum 父业务名称
//     *   businessParentNum 品牌编码
     * @return
     */
    @SuppressWarnings("unchecked")
//    public List<ChildBusinessInfo> getBusinessInfoMapByAreaNum(String bossAreaCode, String businessParentNum,
//        String bossBrandCode)
//    {
//        // 缓存Key
//        String key = MemCachedKey.KEY_GET_BUSINESS_BY_AREA + businessParentNum;
//
//        // 根据Bosscode
//        String areaNum = getAreaNumFormBossCode(bossAreaCode);
//
//        //
//        String brandNum = getBrandNumFormBossCode(bossBrandCode);
//
//        // 组装sql
//        StringBuffer sql = new StringBuffer();
//        sql.append(" SELECT DISTINCT D.F_TYPE TYPE, A.F_BUSINESS_NUM NUM, A.F_BUSINESS_NAME NAME, A.F_JB, A.F_JB_NUM, A.F_MJ   ");
//        sql.append(" FROM T_BUSINESS_DA A JOIN( ");
//        sql.append(" SELECT F_JB_NUM, F_JB FROM T_BUSINESS_DA WHERE F_BUSINESS_NUM = ? ");
//        sql.append(" ) B ON SUBSTR(A.F_JB_NUM, 1, B.F_JB*3) = B.F_JB_NUM ");
//        sql.append(" JOIN ( ");
//        sql.append(" SELECT C1.F_BUSI_NUM, C1.F_AREA_NUM, C2.F_BRAND_NUM ");
//        sql.append(" FROM T_BUSI_AREA_DZ C1, T_BUSI_BRAND_DZ C2 ");
//        sql.append(" WHERE C1.F_BUSI_NUM = C2.F_BUSI_NUM  ");
//        sql.append(" AND C1.F_AREA_NUM IN ( ");
//        sql.append(" SELECT A.F_AREA_NUM FROM T_AREA_DA A JOIN( ");
//        sql.append(" SELECT F_JB_NUM, F_JB FROM T_AREA_DA WHERE F_AREA_NUM = ? ");
//        sql.append(" ) B ON A.F_JB_NUM = SUBSTR(B.F_JB_NUM, 1, 2*A.F_JB)) ");
//        sql.append(" AND C2.F_BRAND_NUM IN ( ");
//        sql.append(" SELECT A.F_BRAND_NUM FROM T_BRAND_DA A JOIN( ");
//        sql.append(" SELECT F_JB_NUM, F_JB FROM T_BRAND_DA WHERE F_BRAND_NUM = ? ");
//        sql.append(" ) B ON A.F_JB_NUM = SUBSTR(B.F_JB_NUM, 1, 2*A.F_JB)) ");
//        sql.append(" ) C ON A.F_BUSINESS_NUM = C.F_BUSI_NUM ");
//        sql.append(" JOIN( ");
//        sql.append(" SELECT DISTINCT B.F_TYPE, A.F_BUSI_NUM FROM T_EFFECT_RULE_BUSI_DZ A ");
//        sql.append("  JOIN(  ");
//        sql.append("    SELECT * FROM T_EFFECT_BUSI_RULE ");
//        sql.append("    WHERE F_EFFECT_TIME_B <= TO_CHAR(SYSDATE, 'YYYYMMDDHH24MMSS') AND ( ");
//        sql.append("      F_EFFECT_TIME_E IS NULL OR F_EFFECT_TIME_E >= TO_CHAR(SYSDATE, 'YYYYMMDDHH24MMSS') ");
//        sql.append("    ) ");
//        sql.append("  ) B ON A.F_RULE_NUM = B.F_RULE_NUM ");
//        sql.append("  JOIN( ");
//        sql.append("    SELECT * FROM T_EFFECT_RULE_AREA_BRAND_DZ ");
//        sql.append("    WHERE  F_AREA_NUM IN ( ");
//        sql.append("      SELECT A.F_AREA_NUM FROM T_AREA_DA A JOIN( ");
//        sql.append("        SELECT F_JB_NUM, F_JB FROM T_AREA_DA WHERE F_AREA_NUM = ? ");
//        sql.append("      ) B ON A.F_JB_NUM = SUBSTR(B.F_JB_NUM, 1, 2*A.F_JB)) ");
//        sql.append("    AND F_BRAND_NUM IN ( ");
//        sql.append("      SELECT A.F_BRAND_NUM FROM T_BRAND_DA A JOIN( ");
//        sql.append("        SELECT F_JB_NUM, F_JB FROM T_BRAND_DA WHERE F_BRAND_NUM = ? ");
//        sql.append("      ) B ON A.F_JB_NUM = SUBSTR(B.F_JB_NUM, 1, 2*A.F_JB)) ");
//        sql.append("  ) C ON A.F_RULE_NUM = C.F_RULE_NUM ");
//        sql.append("  )D ON A.F_BUSINESS_NUM = D.F_BUSI_NUM ");
//        sql.append(" ORDER BY D.F_TYPE, A.F_JB_NUM ");
//
//        return packBusinessMap(sql.toString(), key, new Object[] {businessParentNum, areaNum, brandNum, areaNum,
//            brandNum});
//    }
    
    /**
     * 组装查询出的数据
     * 
     * @param string
     * @param key
     * @param
     * @return
     */
//    private List<ChildBusinessInfo> packBusinessMap(String string, String key, Object[] values)
//    {
//        // 从缓存中获取该Key
////        Object obj = this.getCache().get(key);
//        Object obj = JSON.parseArray(this.getCache().get(key), ChildBusinessInfo.class);
//
//        List<ChildBusinessInfo> childBusiness = null;
//
//        // 如果对象不存在
//        if (obj != null)
//        {
//            childBusiness = (List<ChildBusinessInfo>)obj;
//        }
//        else
//        {
//            childBusiness = new ArrayList<ChildBusinessInfo>();
//            List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(string, values);
//            for (Map<String, Object> param : resultList)
//            {
//                ChildBusinessInfo childBusinessInfo = new ChildBusinessInfo();
//                childBusinessInfo.setType(param.get("TYPE").toString());
//                childBusinessInfo.setBusinessNum(param.get("NUM").toString());
//                childBusinessInfo.setBusinessName(param.get("NAME").toString());
//                childBusiness.add(childBusinessInfo);
//            }
//        }
//        return childBusiness;
//    }
    
//    /**
//     * 根据Boss给的code返回本地数据库保存的值
//     *
//     * @param bossCode
//     * @return
//     */
//    private String getAreaNumFormBossCode(String bossCode)
//    {
//        final String sql = "SELECT T.f_Area_Num FROM T_AREA_DA t WHERE T.F_BOSS_CODE = ?";
//        String cacheKey = MemCachedKey.KEY_GET_AREA_NUM_FORM_BOSS_CODE + bossCode;
//
//        String areaNum;
//        // 查看缓存
//        //Object obj = this.getCache().get(cacheKey);
//        Object obj = JSON.parse(this.getCache().get(cacheKey)).toString();
//        if (obj == null)
//        {
//            List<Map<String,Object>> resultList = this.jdbcTemplate.queryForList(sql, new Object[] {bossCode});
//            if (resultList.isEmpty())
//            {
//                areaNum = null;
//            }
//            else
//            {
//                areaNum = (String)resultList.get(0).get("f_Area_Num");
////                this.getCache().add(cacheKey, areaNum);
//                this.getCache().add(cacheKey, JSON.toJSONString(areaNum));
//            }
//        }
//        else
//        {
//            areaNum = (String)obj;
//        }
//        return areaNum;
//    }
    
//    /**
//     * 根据Boss给的code返回本地数据库保存的值
//     *
//     * @param bossCode
//     * @return
//     */
//    private String getBrandNumFormBossCode(String bossCode)
//    {
//        final String sql = "SELECT T.f_BRAND_Num FROM T_BRAND_DA t WHERE T.F_BOSS_CODE = ? ";
//        String cacheKey = MemCachedKey.KEY_GET_BRAND_NUM_FORM_BOSS_CODE + bossCode;
//
//        String areaNum;
//        // 查看缓存
////        Object obj = this.getCache().get(cacheKey);
//        Object obj = JSON.parse(this.getCache().get(cacheKey)).toString();
//        if (obj == null)
//        {
//            List<Map<String,Object>> resultList = this.jdbcTemplate.queryForList(sql, new Object[] {bossCode});
//            if (resultList.isEmpty())
//            {
//                areaNum = null;
//            }
//            else
//            {
//                areaNum = (String)resultList.get(0).get("f_BRAND_Num");
////                this.getCache().add(cacheKey, areaNum);
//                this.getCache().add(cacheKey, JSON.toJSONString(areaNum));
//            }
//        }
//        else
//        {
//            areaNum = (String)obj;
//        }
//        return areaNum;
//    }
    
//    /**
//     *
//     * @param packageCode
//     * @param packageManagesCode
//     * @param flag 1: 查询时间 0： 查询价格
//     * @return
//     */
//    public int findPackagePriceAndTime(String packageCode, String packageManagesCode, int flag)
//    {
//        // TODO Auto-generated method stub
//        // List<String> groupNameList = (List<String>)(this.getCache().get("CONTY_GROUP_PACKAGE_" + packageCode));
//        //Map<String, String> map = (Map<String, String>)(this.getCache().get("CONTY_GROUP_PACKAGE_" + packageCode));
//    	Map<String, String> map = (Map<String, String>)JSON.parse(this.getCache().get("CONTY_GROUP_PACKAGE_" + packageCode));
//        int price = 0;
//        if (map == null)
//        {
//            try
//            {
//                // String sql =
//                // "select f_money from t_vwang_da_money where f_package_code='"+packageCode+"' and f_brand_code='"+packageManagesCode+"'";
//                String sql = "select f_brand_code,f_money ,pkgtime from t_vwang_da_money where f_package_code='"
//                    + packageCode + "'";
//                // groupNameList = new ArrayList<String>();
//                map = new HashMap<String, String>();
//
//                List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
//                if (list != null)
//                {
//                    for (int i = 0; i < list.size(); i++)
//                    {
//                        Map m = list.get(i);
//                        if (String.valueOf(StringUtil.convertNull((String)m.get("F_BRAND_CODE")))
//                            .equals(packageManagesCode))
//                        {
//                            // groupNameList.add(String.valueOf(StringUtil.convertNull((String)m.get("F_MONEY"))));
//                            map.put("PKGTIME", String.valueOf(StringUtil.convertNull((String)m.get("PKGTIME"))));
//                            map.put("MONEY", String.valueOf(StringUtil.convertNull((String)m.get("F_MONEY"))));
//                            break;
//                        }
//                    }
//                }
////                this.getCache().add("CONTY_GROUP_PACKAGE_" + packageCode, map);
//                this.getCache().add("CONTY_GROUP_PACKAGE_" + packageCode, JSON.toJSONString(map));
//            }
//            catch (Exception e)
//            {
//                // TODO: handle exception
//                e.printStackTrace();
//            }
//        }
//        if (map != null && map.size() > 0)
//        {
//            return flag == 0 ? Integer.parseInt(map.get("MONEY")) : Integer.parseInt(map.get("PKGTIME"));
//        }
//        return price;
//    }
    
//    public List<PackagePrice> findPackagePriceList(String packageCode)
//    {
////        List<PackagePrice> groupNameList = (List<PackagePrice>)(this.getCache().get("CONTY_GROUP_PACKAGE_LIST_"
////            + packageCode));
//
//        List<PackagePrice> groupNameList = JSON.parseArray(this.getCache().get("CONTY_GROUP_PACKAGE_LIST_"+ packageCode), PackagePrice.class);
//        if (groupNameList == null)
//        {
//            groupNameList = new ArrayList<PackagePrice>();
//            String sql = "select f_brand_code,f_money,pkgtime from t_vwang_da_money where f_package_code='"
//                + packageCode + "' and f_brand_code not in ('0000000001','0000000002')";
//            List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
//            if (list != null)
//            {
//                for (int i = 0; i < list.size(); i++)
//                {
//                    Map m = list.get(i);
//                    PackagePrice pp = new PackagePrice();
//                    pp.setPackage_code(String.valueOf(StringUtil.convertNull((String)m.get("F_BRAND_CODE"))));
//                    pp.setPrice(String.valueOf(StringUtil.convertNull((String)m.get("F_MONEY"))));
//                    pp.setPkgTime(String.valueOf(StringUtil.convertNull((String)m.get("PKGTIME"))));
//                    groupNameList.add(pp);
//                }
//            }
////            this.getCache().add("CONTY_GROUP_PACKAGE_LIST_" + packageCode, groupNameList);
//            this.getCache().add("CONTY_GROUP_PACKAGE_LIST_" + packageCode, JSON.toJSONString(groupNameList));
//        }
//        return groupNameList;
//    }
    
//    /**
//     * 查询缴费历史字典
//     *
//     * @param parameter
//     * @return
//     */
//    public Map getChannelPay()
//    {
//        StringBuffer str = new StringBuffer();
//        BossParmDT dt = null;
//        List<BossParmDT> dtList = null;
//
//        Map map = null;
//        try
//        {
////            map = (Map<String, String>)this.getCache().get("CHANNEL_PAY_DA");
//            map = (Map)JSON.parseObject(this.getCache().get("CHANNEL_PAY_DA"));
//            if (map != null)
//            {
//                return map;
//            }
//            else
//            {
//
//                map = new HashMap<String, String>();
//                str.append("SELECT P.F_CHANNEL_PAY_NUM, P.F_CHANNEL_PAY_NAME ");
//                str.append("FROM T_CHANNEL_PAY_DA P ");
//
//                logger.info(" ====== 查询业务的BOSS实现 sql ====== " + str.toString());
//
//                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
//
//                for (Map lp : list)
//                {
//                    // dt = new BossParmDT();
//                    map.put(String.valueOf(StringUtil.convertNull((String)lp.get("F_CHANNEL_PAY_NUM"))),
//                        String.valueOf(StringUtil.convertNull((String)lp.get("F_CHANNEL_PAY_NAME"))));
//                }
////                this.getCache().add("CHANNEL_PAY_DA", map);
//                this.getCache().add("CHANNEL_PAY_DA", JSON.toJSONString(map));
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error(e.getMessage());
//        }
//        return map;
//    }
    
//    /**
//     * 新增加查询缴费历史字典判断
//     *
//     * @param
//     * @return
//     */
//    public Map getChannelPayFlag()
//    {
//        StringBuffer str = new StringBuffer();
//        BossParmDT dt = null;
//        List<BossParmDT> dtList = null;
//
//        Map map = null;
//        try
//        {
////            map = (Map<String, String>)this.getCache().get("T_CHANNEL_PAY_DA_DQ");
//            map = (Map)JSON.parseObject(this.getCache().get("T_CHANNEL_PAY_DA_DQ"));
//            if (map != null)
//            {
//                return map;
//            }
//            else
//            {
//
//                map = new HashMap<String, String>();
//                str.append("SELECT P.F_CHANNEL_PAY_NUM, P.F_CHANNEL_PAY_NAME ");
//                str.append("FROM T_CHANNEL_PAY_DA_DQ P ");
//
//                logger.info(" ====== 查询业务的BOSS实现 sql ====== " + str.toString());
//
//                List<Map<String,Object>> list = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(str.toString());
//
//                for (Map lp : list)
//                {
//                    // dt = new BossParmDT();
//                    map.put(String.valueOf(StringUtil.convertNull((String)lp.get("F_CHANNEL_PAY_NUM"))),
//                        String.valueOf(StringUtil.convertNull((String)lp.get("F_CHANNEL_PAY_NAME"))));
//                }
////                this.getCache().add("T_CHANNEL_PAY_DA_DQ", map);
//                this.getCache().add("T_CHANNEL_PAY_DA_DQ", JSON.toJSONString(map));
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error(e.getMessage());
//        }
//        return map;
//    }
    
    public Map<String, List<NumberSegment>> getNumberSegment()
    {
        Map<String, List<NumberSegment>> nsMap = new HashMap<String, List<NumberSegment>>();
        try
        {
            String SQL_SEG = " SELECT NUMBER_SEG,  AREA_ID FROM t_number_segment ORDER BY number_seg ";
            
            String SQL_NET = " SELECT NETID FROM t_number_segment GROUP BY netid ORDER BY netid ";
            
            List<Map<String,Object>> seglist = this.jdbcTemplate.queryForList(SQL_SEG);
            
            List<Map<String,Object>> netIdlist = this.jdbcTemplate.queryForList(SQL_NET);
            
            NumberSegment ns = null;
            String segNumber = "";
            String netId = "";
            for (int i = 0; i < netIdlist.size(); i++)
            {
                Map net = netIdlist.get(i);
                netId = (String)net.get("NETID");
                List<NumberSegment> nsList = new ArrayList<NumberSegment>();
                for (int j = 0; j < seglist.size(); j++)
                {
                    Map seg = seglist.get(j);
                    segNumber = (String)seg.get("NUMBER_SEG");
                    if (netId.equals(segNumber.substring(0, 3)))
                    {
                        ns = new NumberSegment();
                        ns.setNumSegment(Integer.parseInt((segNumber)));
                        ns.setCity(Integer.parseInt((String)seg.get("AREA_ID")));
                        ns.setNetId(Integer.parseInt(netId));
                        nsList.add(ns);
                    }
                }
                nsMap.put(netId, nsList);
            }
        }
        catch (Exception e)
        {
            logger.error("", e);
            nsMap = null;
        }
        return nsMap;
    }
    
    /**
     * 根据渠道获取已屏蔽的地市编码
     * 
     * @param channel
     * @return
     */
    public List<String> getCRMRegionChannel(String channel)
    {
//        List<String> crmMap = (List<String>)this.getCache().get("t_crm_city_stop" + channel);
        List<String> crmMap = JSON.parseArray(this.getCache().get("t_crm_city_stop" + channel), String.class);
        
        if (null == crmMap)
        {
            String sql = "  select * from t_crm_city_stop t where t.f_channel_num = ? and t.flag = 1 ";
            Object obj[] = new Object[1];
            obj[0] = channel;
            List<Map<String,Object>> ret = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql, obj);
            crmMap = new ArrayList<String>();
            if (ret != null && ret.size() > 0)
            {
                for (Map<String,Object> m : ret)
                {
                    crmMap.add(m.get("region").toString());
                }
            }
//            this.getCache().add("t_crm_city_stop" + channel, crmMap);
            this.getCache().add("t_crm_city_stop" + channel, JSON.toJSONString(crmMap));
        }
        return crmMap;
    }
    
//    /**
//     * 根据渠道获取已屏蔽的地市编码
//     *
//     * @param channel
//     * @return
//     */
//    public List<String> getSystemEmerRegionInfo(String channel)
//    {
////        List<String> crmMap = (List<String>)this.getCache().get("T_URGENT_BOSS_SYS" + channel);
//        List<String> crmMap = JSON.parseArray(this.getCache().get("T_URGENT_BOSS_SYS" + channel), String.class);
//        if (null == crmMap)
//        {
//            String sql = "select * from T_URGENT_BOSS_SYS t where t.f_channel_name = ? AND T.f_flag = 1";
//            Object obj[] = new Object[1];
//            obj[0] = channel;
//            List<Map<String,Object>> ret = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql, obj);
//
//            crmMap = new ArrayList<String>();
//            if (ret != null && ret.size() > 0)
//            {
//                for (Map<String,Object> m : ret)
//                {
//                    crmMap.add(m.get("f_region").toString());
//                }
//            }
////            this.getCache().add("T_URGENT_BOSS_SYS" + channel, crmMap);
//            this.getCache().add("T_URGENT_BOSS_SYS" + channel, JSON.toJSONString(crmMap));
//        }
//        return crmMap;
//    }
    
//    /**
//     * 根据对应的地市获取号码段
//     *
//     * @param cityNum
//     * @return
//     */
//    public List<String> getSystemEmerRegion(String cityNum)
//    {
////        List<String> crmMap = (List<String>)this.getCache().get("t_number_segment" + cityNum);
//        List<String> crmMap = JSON.parseArray(this.getCache().get("t_number_segment" + cityNum), String.class);
//        if (null == crmMap)
//        {
//            String sql = "select * from t_number_segment t where t.area_id = ?";
//            Object obj[] = new Object[1];
//            obj[0] = cityNum;
//            List<Map<String,Object>> ret = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql, obj);
//
//            crmMap = new ArrayList<String>();
//            if (ret != null && ret.size() > 0)
//            {
//                for (Map<String,Object> m : ret)
//                {
//                    crmMap.add(m.get("number_seg").toString());
//                }
//            }
////            this.getCache().add("t_number_segment" + cityNum, crmMap);
//            this.getCache().add("t_number_segment" + cityNum, JSON.toJSONString(crmMap));
//        }
//        return crmMap;
//    }
    
//    /*
//     * type =1： 全包类4G流量 type =2：叠加包类流量 type =3：半包类限时流量
//     */
//    public Set<String> getLTE4GCode(String... type)
//    {
//        String letType = buildString(type);
//        
//        Set<String> codeSets = (Set<String>)this.getCache().get("T_LTEGPRS_CODE" + letType);
//        if (null == codeSets)
//        {
//            String sql = "select * from T_LTEGPRS_CODE t where t.f_let_type in (" + letType + ")";
//            
//            List<Map<String, String>> ret = (List<Map<String, String>>)this.jdbcTemplate.queryForList(sql);
//            
//            codeSets = new HashSet<String>();
//            if (ret != null && ret.size() > 0)
//            {
//                for (Map<String, String> m : ret)
//                {
//                    codeSets.add(m.get("f_let_code").toString().trim());
//                }
//            }
//            this.getCache().add("T_LTEGPRS_CODE" + letType, codeSets);
//        }
//        return codeSets;
//    }
    
//    private String buildString(String... type)
//    {
//        String letType = "";
//        for (int i = 0; i < type.length; i++)
//        {
//            letType += type[i];
//            letType += ",";
//        }
//        letType = letType.substring(0, letType.lastIndexOf(","));
//        return letType;
//    }
    
//    /**
//     * 获取集团V网黑名单中的boss编码
//     *
//     * @return
//     */
//    public List<String> getJTVWCodeSet()
//    {
////        List<String> blackCodeList = (List<String>)this.getCache().get("t_jtvw_black_list");
//        List<String> blackCodeList = JSON.parseArray(this.getCache().get("t_jtvw_black_list"), String.class);
//
//        if (null == blackCodeList)
//        {
//            String sql = "select t.code from T_JTVW_BLACK_LIST t where t.flag = 1";
//
//            List<Map<String,Object>> ret = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql);
//
//            blackCodeList = new ArrayList<String>();
//
//            if (ret != null && ret.size() > 0)
//            {
//                for (Map<String,Object> m : ret)
//                {
//                    blackCodeList.add(m.get("code").toString());
//                }
//            }
////            this.getCache().add("t_jtvw_black_list", blackCodeList);
//            this.getCache().add("t_jtvw_black_list", JSON.toJSONString(blackCodeList));
//        }
//        return blackCodeList;
//    }
    
//    /**
//     * 补录资料（高校迎新）活动的数据库表名
//     *
//     * @param phone
//     * @param endTime
//     * @return
//     */
//    public boolean insertGXYX(String phone, String endTime, String channel, String cityNum)
//    {
//        String sql = "insert into ecos0.T_2014_GXYX_JOB (PHONE_NUMBER,JOB_NAME,JOB_ID,JOB_TIME,LOTTERY_STATE,CHANNEL_NUM,area_num) values (?,?,?,?,?,?,?)";
//        Object obj[] = new Object[7];
//        obj[0] = phone;
//        obj[1] = "补录资料";
//        obj[2] = "1";
//        obj[3] = endTime;
//        obj[4] = "0";
//        obj[5] = channel;
//        obj[6] = cityNum;
//
//        int i = this.jdbcTemplate.update(sql, obj);
//        if (1 == i)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
    
    /**
     * 查询需要缓存数据的接口编号，以及缓存的时间
     * 
     * @return
     */
    public Map<String, String> getRedisData()
    {
//        Map<String, String> redisData = (Map<String, String>)this.getCache().get("t_redis_linterface");
        Map<String, String> redisData = (Map<String, String>)JSON.parse(this.getCache().get("t_redis_linterface"));
        if (null == redisData)
        {
            String sql = " select * from t_redis_linterface t where t.flag = '1' ";
            List<Map<String,Object>> ret = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql);
            
            redisData = new HashMap<String, String>();
            if (ret != null && ret.size() > 0)
            {
                for (Map<String,Object> m : ret)
                {
                    redisData.put((String)m.get("linternum"), (String)m.get("catchtime"));
                }
            }
//            this.getCache().add("t_redis_linterface", redisData);
            this.getCache().add("t_redis_linterface", JSON.toJSONString(redisData));
        }
        return redisData;
        
    }
    
//    /**
//     * 补录资料（高校迎新）活动的数据库表名
//     *
//     * @param phone
//     * @param endTime
//     * @return
//     */
//    public boolean insertUserIcard(String phone, String name, String card, String addr)
//    {
//        String sql = "insert into t_gxyx_user_info values (?,?,?,?)";
//        Object obj[] = new Object[4];
//        obj[0] = phone;
//        obj[1] = name;
//        obj[2] = card;
//        obj[3] = addr;
//        int i = this.jdbcTemplate.update(sql, obj);
//        if (1 == i)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
    
//    /**
//     * 查询不规则GPRS流量产品ID
//     *
//     * @return
//     */
//    public List<String> getAnomalyGprs()
//    {
//        // select * from t_anomaly_gprs
//        //List<String> anomalyGprs = (List<String>)this.getCache().get("t_anomaly_gprs");
//        List<String> anomalyGprs = JSON.parseArray(this.getCache().get("t_anomaly_gprs"), String.class);
//
//        if (null == anomalyGprs)
//        {
//            String sql = "select t.f_product_id from T_ANOMALY_GPRS t WHERE 1=1";
//
//            List<Map<String,Object>> ret = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql);
//
//            anomalyGprs = new ArrayList<String>();
//
//            if (ret != null && ret.size() > 0)
//            {
//                for (Map<String,Object> m : ret)
//                {
//                    anomalyGprs.add(m.get("f_product_id").toString());
//                }
//            }
//            this.getCache().add("t_anomaly_gprs", JSON.toJSONString(anomalyGprs));
//        }
//        return anomalyGprs;
//    }
    
//    /**
//     * 查询支付宝充值地市信息(邮箱、PID、KEY)
//     * 
//     * @author Ou
//     * @return Map
//     */
//    public Map<String, Map<String, String>> getAliPayRechargeInfo()
//    {
//        Map<String, Map<String, String>> aliPayRechargeInfo = (Map<String, Map<String, String>>)this.getCache()
//            .get("t_alipay_recharge_info");
//        
//        if (null == aliPayRechargeInfo)
//        {
//            String sql = "select * from t_alipay_recharge_info t WHERE 1=1";
//            
//            List<Map<String, String>> ret = (List<Map<String, String>>)this.jdbcTemplate.queryForList(sql);
//            
//            aliPayRechargeInfo = new HashMap<String, Map<String, String>>();
//            // 支付宝充值邮箱
//            Map<String, String> dicAccount = null;
//            // 支付宝充值PID
//            Map<String, String> dicPid = null;
//            // 支付宝充值KEY
//            Map<String, String> dicKey = null;
//            
//            if (null != ret && ret.size() > 0)
//            {
//                dicAccount = new HashMap<String, String>();
//                dicPid = new HashMap<String, String>();
//                dicKey = new HashMap<String, String>();
//                
//                for (Map<String, String> m : ret)
//                {
//                    dicAccount.put(m.get("f_city_id").toString(), m.get("f_dic_account").toString()); // 邮箱
//                    dicPid.put(m.get("f_city_id").toString(), m.get("f_dic_pid").toString()); // PID
//                    dicKey.put(m.get("f_city_id").toString(), m.get("f_dic_key").toString()); // KEY
//                }
//                aliPayRechargeInfo.put("dicAccount", dicAccount); // 支付宝充值 邮箱
//                aliPayRechargeInfo.put("dicPid", dicPid); // 支付宝充值 PID
//                aliPayRechargeInfo.put("dicKey", dicKey); // 支付宝充值 KEY
//                
//                this.getCache().add("t_alipay_recharge_info", aliPayRechargeInfo);
//            }
//        }
//        return aliPayRechargeInfo;
//    }


//    /**
//     * 根据用户订购的政策id查询活动编码
//     * @param policyId
//     * @return
//     */
//    public List<String> getProidByActCode(String policyId) {
//        String key = "KEY_ACTCODE_BY_PROID_" + policyId;
//        List<String> crmMap = JSON.parseArray(this.getCache().get(key), String.class);
//        if (null == crmMap) {
//            String sql = "SELECT t.f_act_num FROM tm_act_policy t where t.f_policy_num = ?";
//            Object obj[] = new Object[1];
//            obj[0] = policyId;
//            List<Map<String,Object>> ret = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql, obj);
//            crmMap = new ArrayList<String>();
//            if (ret != null && ret.size() > 0) {
//                for (Map<String,Object> m : ret) {
//                    crmMap.add(m.get("f_act_num").toString());
//                }
//                //缓存失效时间
//                long expreInSeconds=30*60*1000;
//                this.getCache().add(key, JSON.toJSONString(crmMap),expreInSeconds);
//            }
//
//        }
//        return crmMap;
//    }

    /**
     * 查询号段
     *
     * @return
     */
    public String queryPhoneSection(String phone) {
        if (StringUtils.isEmpty(phone) || phone.length() < 11) {
            return "";
        }
        String phoneSec = phone.substring(0, 7);

        String key = "ECP_PHONE_SEC_" + phoneSec;
        String keyStr = this.getCache().get(key);
        if (StringUtils.isNotBlank(keyStr)) {
            return keyStr;
        }

        String sql = "select * from t_phone_section where phoneSec=?";
        Object obj[] = new Object[1];
        obj[0] = phoneSec;
        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.jdbcTemplate.queryForList(sql, obj);
        if (ret != null && ret.size() > 0) {
            String city = ret.get(0).get("city").toString();
            this.getCache().add(key, city);
            return city;
        }

        return "";

    }
}