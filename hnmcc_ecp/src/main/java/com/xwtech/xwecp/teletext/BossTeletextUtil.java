package com.xwtech.xwecp.teletext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.funcs.*;
import com.xwtech.xwecp.util.RandomString;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.lilystudio.util.StringReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BossTeletextUtil implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(BossTeletextUtil.class);

    @Resource(name = "wellFormedDAO")

    private WellFormedDAO wellFormedDAO;

    private String charset = "UTF-8";

    private Map<String, IExternalFunctionExecutor> externalFunctionMap;

    private VelocityEngine engine = null;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public WellFormedDAO getWellFormedDAO() {
        return wellFormedDAO;
    }


    public void setWellFormedDAO(WellFormedDAO wellFormedDAO) {
        this.wellFormedDAO = wellFormedDAO;
    }

    /**
     * 查询发送报文模板
     *
     * @param cmd 命令字
     * @return
     */
    public String getTeletextTemplateFromDatabase(String cmd) {
        String textTemplate = "";
        Map<String, Object> mapResult = null;

        String sql = "select tbs.F_BOSS_IN_TMP  from  T_BOSS_INT_DA_TEST tbs where tbs.F_BOSS_INT_NUM='" + cmd + "'";
        try {
            List<Map<String, Object>> rsList = this.wellFormedDAO.getJdbcTemplate().queryForList(sql);
            mapResult = rsList != null && rsList.size() > 0 ? rsList.get(0) : new HashMap<String, Object>();
            logger.info(JSONObject.toJSON(rsList) + ",sql:" + sql + "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("操作数据库出现错误！！！");

        }
        textTemplate = (String) mapResult.get("f_boss_in_tmp");

        return textTemplate;
    }

    /**
     * 获取发送报文模板
     *
     * @param cmd 命令字
     * @return
     */
    public String getTeletextTemplate(String cmd) {

        String mergeTeletext = "";
        String cacheTemp = "";
        if (cmd != null && !"".equals(cmd)) {
            try {
                // cacheTemp =
                // (String)wellFormedDAO.getCache().get("BOSS_IN_TEMPLATE_CODE_KEY_"
                // + cmd);
                if (null != wellFormedDAO.getCache().get("BOSS_IN_TEMPLATE_CODE_KEY_" + cmd)) {
                    cacheTemp = JSON.parse(wellFormedDAO.getCache().get("BOSS_IN_TEMPLATE_CODE_KEY_" + cmd)).toString();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                logger.error("读取缓存出错！！！");
                return null;
            }
            if (cacheTemp != null && !"".equals(cacheTemp)) {
                mergeTeletext = cacheTemp;
            } else {
                // 查询发送报文模板
                mergeTeletext = this.getTeletextTemplateFromDatabase(cmd);
                // wellFormedDAO.getCache().add("BOSS_IN_TEMPLATE_CODE_KEY_" +
                // cmd, mergeTeletext);
                wellFormedDAO.getCache().add("BOSS_IN_TEMPLATE_CODE_KEY_" + cmd, JSON.toJSONString(mergeTeletext));
            }
        }
        return mergeTeletext;
    }

    /**
     * to_boss_code2
     * 组装发送至BOSS的报文
     *
     * @param cmd   命令字
     * @param param 参数
     * @return
     */
    public String mergeTeletext(String cmd, List<RequestParameter> param) {
        String mergeTeletext = "";
        if (cmd != null && !"".equals(cmd)) {
            String text = getTeletextTemplate(cmd); // 获取模板内容
            VelocityContext context = new VelocityContext(); // 生成数据容器对象
            // 这里需要设置数据的值
            if (param != null) {
                // 开始加载报文头
                context.put("request_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                // context.put("request_seq",
                // new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                // RandomStr.getRandomNum(2));
                String randomString = RandomString.createRandom();
                // seqLogger.info(randomString);
                context.put("request_seq", randomString);
                context.put("t", "\t");
                // 结束加载报文头
                for (int i = 0; i < param.size(); i++) {
                    context.put(param.get(i).getParameterName(), param.get(i).getParameterValue());
                }

                // DEL010001办理接口定制产品ID转换
                IExternalFunctionExecutor functionExecutor = getExternalFunctionMap().get("to_boss_code2");
                if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("id"))) {
                    context.put("bossCode", functionExecutor.execute((String) context.get("id")));
                }

                //DEL930049      InstanceId
                if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("InstanceId"))) {
                    context.put("InstanceId", functionExecutor.execute((String) context.get("InstanceId")));
                }

                //DEL980011
                if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("AddProdPrcId"))) {
                    context.put("AddProdPrcId", functionExecutor.execute((String) context.get("AddProdPrcId")));
                }

                // 梦网产品设置sp
                functionExecutor = getExternalFunctionMap().get("to_boss_code7");
                if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("id"))) {
                    // 916205|-DXBGX|04
                    // 如果是梦网退订不再操作数据库
                    String[] res = new String[5];
                    if (cmd.equals("hnmcc_cancel_mwcp_01")) {
                        res = ((String) context.get("id")).split("\\|");
                    } else {
                        res = functionExecutor.execute((String) context.get("id")).split("\\|");
                    }
                    // String[] res =
                    // functionExecutor.execute((String)context.get("id")).split("\\|");
                    if (res != null && res.length == 3) {
                        context.put("spId", res[0]);
                        context.put("spBizCode", res[1]);
                        context.put("bizType", res[2]);
                    } else if (res != null && res.length == 2) {
                        context.put("spId", res[0]);
                        context.put("spBizCode", res[1]);
                    }

                }

                StringWriter writer = new StringWriter(); // 设置接收模板数据的输出流
                try {
                    // 匹配替换参数
                    boolean flag = engine.evaluate(context, writer, "", new StringReader(text));
                    if (flag) {
                        mergeTeletext = writer.toString();
                    }
                    String lineLength = String.format("% 5d",
                            getWordCountCode(mergeTeletext.substring(mergeTeletext.indexOf("FFFFFFFF") + 8), "GBK")
                                    + 120); // 设置请求报文长度
                    mergeTeletext = mergeTeletext.replaceAll("-----", lineLength);
                    // mergeTeletext = this.evaluateFunctions(mergeTeletext);
                } catch (Exception e) {
                    logger.error("替换摸板信息出错！！！", e);
                    return null;
                }
            }
        }
        return mergeTeletext.trim();
    }

    /* 组装 以jbossremoting参数 发送至BOSS的报文 2016年11月17日 16:54:04 start */

    /**
     * 查询发送报文模板
     * @param cmd 命令字
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getRemoteTeletextTemplateFromDatabase(String cmd) {
        Map<String, Object> textTemplate = new HashMap<String, Object>();
        Map mapResult = null;

        String sql = "select tbs.F_BOSS_IN_TMP, tbs.F_BOSS_IN_TMP_JSON_SYS, tbs.F_BOSS_IN_TMP_JSON_BUSI, tbs.F_BOSS_IN_TMP_TYPE  from  T_BOSS_INT_DA_TEST tbs where tbs.F_BOSS_INT_NUM='"
                + cmd + "'";
        try {
            mapResult = (Map) this.wellFormedDAO.getJdbcTemplate().queryForObject(sql, new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Map<String, String> mapRs = new HashMap<String, String>();
                    ResultSetMetaData metaData = rs.getMetaData();

                    for (int num = 1; num <= metaData.getColumnCount(); num++) {
                        // if (metaData.getColumnType(num) == Types.CLOB)
                        // {

                        mapRs.put(metaData.getColumnName(num).toLowerCase(), rs.getString(num));
                        // BossTeletextUtil.this.wellFormedDAO.getLobHandler().getClobAsString(rs,
                        // num));
                        // }
                    }
                    return mapRs;
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("操作数据库出现错误！！！");

        }
        textTemplate.put("teletext", (String) mapResult.get("f_boss_in_tmp"));
        textTemplate.put("sysParam", (String) mapResult.get("f_boss_in_tmp_json_sys"));
        textTemplate.put("busiParam", (String) mapResult.get("f_boss_in_tmp_json_busi"));
        textTemplate.put("type", (String) mapResult.get("f_boss_in_tmp_type"));

        return textTemplate;
    }

    /**
     * 获取发送报文模板
     *
     * @param cmd 命令字
     * @return
     */
    public Map<String, Object> getRemoteTeletextTemplate(String cmd) {

        Map<String, Object> mergeTeletextMap = new HashMap<String, Object>();
        if (cmd != null && !"".equals(cmd)) {
            Map<String, Object> cacheTemp = new HashMap<String, Object>();
            try {
                if (null != wellFormedDAO.getCache().get("BOSS_IN_TEMPLATE_CODE_KEY_REMOTE_" + cmd)) {
                    cacheTemp = (Map<String, Object>) JSON
                            .parse(wellFormedDAO.getCache().get("BOSS_IN_TEMPLATE_CODE_KEY_REMOTE_" + cmd));
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                logger.error("读取缓存出错！！！");
                return null;
            }
            if (cacheTemp != null && cacheTemp.get("type") != null) {
                mergeTeletextMap = cacheTemp;
            } else {
                // 查询发送报文模板
                mergeTeletextMap = this.getRemoteTeletextTemplateFromDatabase(cmd);
                wellFormedDAO.getCache().add("BOSS_IN_TEMPLATE_CODE_KEY_REMOTE_" + cmd,
                        JSON.toJSONString(mergeTeletextMap));
            }
        }
        return mergeTeletextMap;
    }

    /**
     * 组装 以jbossremoting参数 发送至BOSS的报文
     *
     * @param cmd   命令字
     * @param param 参数
     * @return Map key:teletext,key:busiParam,key:sysParam,key:type
     */
    public Map<String, Object> mergeRemoteTeletext(String cmd, List<RequestParameter> param) {
        Map<String, Object> remoteParam = new HashMap<String, Object>();
        String mergeTeletext = "";
        String mergesysParam = "";
        String mergebusiParam = "";
        String type = "0";
        if (cmd != null && !"".equals(cmd)) {
            Map<String, Object> textMap = getRemoteTeletextTemplate(cmd); // 获取模板内容
            type = textMap.get("type") == null ? "0" : (String) textMap.get("type");
            if (textMap.get("type") != null && StringTeletext.DEFAULT_REQ_TYPE.equals((String) textMap.get("type"))) {

                String text = (String) textMap.get("teletext"); // 获取默认模板内容
                VelocityContext context = new VelocityContext(); // 生成数据容器对象

                // 这里需要设置数据的值
                if (param != null) {
                    // 开始加载报文头
                    context.put("request_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                    // context.put("request_seq",
                    // new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                    // + RandomStr.getRandomNum(2));
                    String randomString = RandomString.createRandom();
                    // seqLogger.info(randomString);
                    context.put("request_seq", randomString);
                    context.put("t", "\t");
                    // 结束加载报文头
                    for (int i = 0; i < param.size(); i++) {
                        context.put(param.get(i).getParameterName(), param.get(i).getParameterValue());
                    }

                    // DEL010001办理接口定制产品ID转换
                    IExternalFunctionExecutor functionExecutor = getExternalFunctionMap().get("to_boss_code2");
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("id"))) {
                        context.put("bossCode", functionExecutor.execute((String) context.get("id")));
                    }

                    //DEL930049      InstanceId
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("InstanceId"))) {
                        context.put("InstanceId", functionExecutor.execute((String) context.get("InstanceId")));
                    }

                    //DEL980011
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("AddProdPrcId"))) {
                        context.put("AddProdPrcId", functionExecutor.execute((String) context.get("AddProdPrcId")));
                    }


                    // 梦网产品设置sp
                    functionExecutor = getExternalFunctionMap().get("to_boss_code7");
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("id"))) {
                        // 916205|-DXBGX|04
                        // 如果是梦网退订不再操作数据库
                        String[] res = new String[5];
                        if (cmd.equals("hnmcc_cancel_mwcp_01")) {
                            res = ((String) context.get("id")).split("\\|");
                        } else {
                            res = functionExecutor.execute((String) context.get("id")).split("\\|");
                        }
                        // String[] res =
                        // functionExecutor.execute((String)context.get("id")).split("\\|");
                        if (res != null && res.length == 3) {
                            context.put("spId", res[0]);
                            context.put("spBizCode", res[1]);
                            context.put("bizType", res[2]);
                        } else if (res != null && res.length == 2) {
                            context.put("spId", res[0]);
                            context.put("spBizCode", res[1]);
                        }

                    }

                    StringWriter writer = new StringWriter(); // 设置接收模板数据的输出流
                    try {
                        // 匹配替换参数
                        boolean flag = engine.evaluate(context, writer, "", new StringReader(text));
                        if (flag) {
                            mergeTeletext = writer.toString();
                        }

                        String lineLength = String.format("% 5d",
                                getWordCountCode(mergeTeletext.substring(mergeTeletext.indexOf("FFFFFFFF") + 8), "GBK")
                                        + 120); // 设置请求报文长度
                        mergeTeletext = mergeTeletext.replaceAll("-----", lineLength);
                        // mergeTeletext =
                        // this.evaluateFunctions(mergeTeletext);
                    } catch (Exception e) {
                        logger.error("替换摸板信息出错！！！", e);
                        return null;
                    }
                }
            }
            // 设置jbossremoting 组装json数据
            if (textMap.get("type") != null && StringTeletext.REMOTING_REQ_TYPE.equals((String) textMap.get("type"))) {

                /* 业务参数处理 start */
                String busitext = (String) textMap.get("busiParam");// 获取参数模板内容
                VelocityContext context = new VelocityContext(); // 生成数据容器对象

                // 这里需要设置数据的值
                if (param != null) {
                    for (int i = 0; i < param.size(); i++) {
                        context.put(param.get(i).getParameterName(), param.get(i).getParameterValue());
                    }

                    // DEL010001办理接口定制产品ID转换
                    IExternalFunctionExecutor functionExecutor = getExternalFunctionMap().get("to_boss_code2");
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("id"))) {
                        context.put("bossCode", functionExecutor.execute((String) context.get("id")));
                    }


                    //DEL930049      InstanceId
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("InstanceId"))) {
                        context.put("InstanceId", functionExecutor.execute((String) context.get("InstanceId")));
                    }

                    //DEL980011
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("AddProdPrcId"))) {
                        context.put("AddProdPrcId", functionExecutor.execute((String) context.get("AddProdPrcId")));
                    }
                    //DEL930049
                    functionExecutor = getExternalFunctionMap().get("to_boss_code2");
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("OptrId"))) {
                        context.put("OptrId", functionExecutor.execute((String) context.get("OptrId")));
                    }
                    // 梦网产品设置sp
                    functionExecutor = getExternalFunctionMap().get("to_boss_code7");
                    if (null != functionExecutor && StringUtils.isNotEmpty((String) context.get("id"))) {
                        // 916205|-DXBGX|04
                        // 如果是梦网退订不再操作数据库
                        String[] res = new String[5];
                        if (cmd.equals("hnmcc_cancel_mwcp_01")) {
                            res = ((String) context.get("id")).split("\\|");
                        } else {
                            res = functionExecutor.execute((String) context.get("id")).split("\\|");
                        }
                        // String[] res =
                        // functionExecutor.execute((String)context.get("id")).split("\\|");
                        if (res != null && res.length == 3) {
                            context.put("spId", res[0]);
                            context.put("spBizCode", res[1]);
                            context.put("bizType", res[2]);
                        } else if (res != null && res.length == 2) {
                            context.put("spId", res[0]);
                            context.put("spBizCode", res[1]);
                        }

                    }

                    StringWriter writer = new StringWriter(); // 设置接收模板数据的输出流
                    try {
                        // 匹配替换参数
                        boolean flag = engine.evaluate(context, writer, "", new StringReader(busitext));
                        if (flag) {
                            mergebusiParam = writer.toString();
                        }

                    } catch (Exception e) {
                        logger.error("替换摸板信息出错！！！", e);
                        return null;
                    }
                }
                /* 业务参数处理 end */
                /* 系统参数处理 start */
                String systext = (String) textMap.get("sysParam"); // 获取系统参数模板内容
                context = new VelocityContext();
                // 系统参数请求时间
                context.put("request_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

                StringWriter writer = new StringWriter(); // 设置接收模板数据的输出流
                try {
                    // 匹配替换参数
                    boolean flag = engine.evaluate(context, writer, "", new StringReader(systext));
                    if (flag) {
                        mergesysParam = writer.toString();
                    }

                } catch (Exception e) {
                    logger.error("替换摸板信息出错！！！", e);
                    return null;
                }
                /* 系统参数处理 end */
            }
        }
        remoteParam.put("busiParam", mergebusiParam);
        remoteParam.put("sysParam", mergesysParam);
        remoteParam.put("teletext", mergeTeletext);
        remoteParam.put("type", type);
        return remoteParam;
    }


    /* 组装 以jbossremoting参数 发送至BOSS的报文 2016年11月17日 16:54:04 end */

    private int getWordCountCode(String s, String code) {
        try {
            return s.getBytes(code).length;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    private String evaluateFunctions(String in) throws Exception {
        String pStr = "\\%.{2,200}[\\(.*\\)]++";
        String groupStr = "\\%(.*?\\()(.*)?\\)";
        Pattern groupPattern = Pattern.compile(groupStr);
        Pattern p = Pattern.compile(pStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(in);
        StringBuffer buf = new StringBuffer();
        while (matcher.find()) {
            String tmp = matcher.group();
            Matcher m = groupPattern.matcher(tmp);
            if (m.find()) {
                if (m.groupCount() == 2) {
                    String functionName = m.group(1);
                    functionName = functionName.substring(0, functionName.length() - 1); // 去掉结尾的"("
                    String parameter = m.group(2);
                    String value = "";
                    if (parameter.startsWith("\"") && parameter.endsWith("\"")) // 如果直接是字串
                    {
                        parameter = parameter.substring(1, parameter.length() - 1); // 去掉两端的引号
                        // 执行函数
                        value = this.executeExternalFunction(functionName, parameter);
                    } else if (parameter.matches(pStr)) // 如果参数本身又是一个函数
                    {
                        value = this.recursiveEvaluateFunction(functionName, parameter);
                    }
                    matcher.appendReplacement(buf, value);
                }
            }
        }
        matcher.appendTail(buf);
        String ret = buf.toString();
        // return ret.replaceAll("\\\\$", "$");
        return ret;
    }

    private String recursiveEvaluateFunction(String functionName, String parameter) {
        if (parameter.startsWith("\"") && parameter.endsWith("\"")) {
            parameter = parameter.substring(1, parameter.length() - 1); // 去掉两端的引号
            return this.executeExternalFunction(functionName, parameter);
        } else {
            String groupStr = "\\%(.*?\\()(.*)?\\)";
            Pattern groupPattern = Pattern.compile(groupStr);
            Matcher m = groupPattern.matcher(parameter);
            if (m.find()) {
                if (m.groupCount() == 2) {
                    String subName = m.group(1);
                    subName = subName.substring(0, subName.length() - 1); // 去掉结尾的"("
                    String subParam = m.group(2);
                    return this.executeExternalFunction(functionName, recursiveEvaluateFunction(subName, subParam));
                }
            }
            return functionName + "(" + parameter + ")";
        }
    }

    private String executeExternalFunction(String functionName, String parameter) {
        IExternalFunctionExecutor functionExecutor = this.getExternalFunctionMap().get(functionName);
        if (functionExecutor != null) {
            return functionExecutor.execute(parameter);
        }
        logger.warn("未配置函数[" + functionName + "]!");
        return functionName + "(" + parameter + ")";
    }


    public Map<String, IExternalFunctionExecutor> getExternalFunctionMap() {
        if (externalFunctionMap != null) {
            return externalFunctionMap;
        } else {
            externalFunctionMap = new HashMap<>();
            externalFunctionMap.put("to_boss_code", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BossCodeFunctionExecutor.class));
            externalFunctionMap.put("to_boss_code2", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BossCode2FunctionExecutor.class));
            externalFunctionMap.put("to_boss_code3", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BossCode3FunctionExecutor.class));
            externalFunctionMap.put("to_boss_code4", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BossCode4FunctionExecutor.class));
            externalFunctionMap.put("to_boss_code6", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BossCode6FunctionExecutor.class));
            externalFunctionMap.put("to_boss_code7", XWECPApp.SPRING_CONTEXT.getBean(BusinessCode2BossCode7FunctionExecutor.class));
            externalFunctionMap.put("to_opr_type", XWECPApp.SPRING_CONTEXT.getBean(ToOprTypeFunctionExecutor.class));
            externalFunctionMap.put("to_opr_type3", XWECPApp.SPRING_CONTEXT.getBean(ToOprTypeFunction2Executor.class));
            externalFunctionMap.put("change_oprType", XWECPApp.SPRING_CONTEXT.getBean(ChangeOprTypeFunctionExecutor.class));
            externalFunctionMap.put("change_oprCode", XWECPApp.SPRING_CONTEXT.getBean(ChangeOprCodeFunctionExecutor.class));
            externalFunctionMap.put("choose_flag", XWECPApp.SPRING_CONTEXT.getBean(ChooseFlagFunctionExecutor.class));
            externalFunctionMap.put("to_member_lev", XWECPApp.SPRING_CONTEXT.getBean(ToMemberLevelExecutor.class));
            externalFunctionMap.put("to_order_code", XWECPApp.SPRING_CONTEXT.getBean(ToOrderCode.class));
            externalFunctionMap.put("value_map", XWECPApp.SPRING_CONTEXT.getBean(ValueMap.class));
            externalFunctionMap.put("to_member_level", XWECPApp.SPRING_CONTEXT.getBean(ToMemberLevelExecutor.class));
//            externalFunctionMap.put("to_boss_brand", XWECPApp.SPRING_CONTEXT.getBean(ToBossBrandExecutor.class));
//            externalFunctionMap.put("to_boss_city", XWECPApp.SPRING_CONTEXT.getBean(ToBossCityExecutor.class));
//            externalFunctionMap.put("to_boss_orgid", XWECPApp.SPRING_CONTEXT.getBean(ToOrgIdExecutor.class));
            externalFunctionMap.put("toOprType", XWECPApp.SPRING_CONTEXT.getBean(ToOprTypeExecutor.class));
            externalFunctionMap.put("to_opr_type2", XWECPApp.SPRING_CONTEXT.getBean(ToOprType2Executor.class));
            externalFunctionMap.put("changeOprType", XWECPApp.SPRING_CONTEXT.getBean(ChangeOprTypeExecutor.class));
            externalFunctionMap.put("change_oprTypeTSB", XWECPApp.SPRING_CONTEXT.getBean(ChangeOprTypeFunctionTSBExecutor.class));
        }

        return externalFunctionMap;
    }

    public void setExternalFunctionMap(Map<String, IExternalFunctionExecutor> externalFunctionMap) {
        this.externalFunctionMap = externalFunctionMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化velocity engine!");
        if (engine == null) {
            engine = new VelocityEngine(); // 初始化并取得Velocity引擎

            try {
                engine.init();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                logger.error("初始化并取得Velocity引擎错误！！！");
            }
        }
    }
}
