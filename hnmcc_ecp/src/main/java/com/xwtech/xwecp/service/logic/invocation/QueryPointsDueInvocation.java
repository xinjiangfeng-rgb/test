package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.*;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryPointsDueInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_180527";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY180527Result result = new QRY180527Result();
        String rspXml = "";
        try {
            BaseJsonQueryUtils util = new BaseJsonQueryUtils();
            util.setConfig(REQUEST_BOSSTELETEXT);
            rspXml = util.getResponseXML(accessId, config, params);
            util.distroy();
            logger.info("******** Boss返回数据为*****　" + rspXml);
            if (StringUtils.isEmpty(rspXml)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
            String bossCode = bossJson.getBossCode();
            String bossDesc = bossJson.getBossDesc();
            logger.info("bossCode" + bossCode);
            logger.info("bossDesc" + bossDesc);
            JSONObject resultObj= JSONObject.parseObject(rspXml).getJSONObject("result");
            JSONArray deal = resultObj.getJSONArray("SO_MEMBER_DEAL");
            List<NewScoreBean> resultList = new ArrayList<>();
            for (int i = 0; i < deal.size(); i++) {
                JSONObject scoreObj = (JSONObject) deal.get(i);
                NewScoreBean scoreBean = new NewScoreBean();
                ScoreListBean scoreListBean=new ScoreListBean();
                JSONObject scoreListObj = scoreObj.getJSONObject("SCORELIST");
                JSONObject sumListobj= scoreListObj.getJSONObject("SUMSCORELIST");
                SumsCorelist sumsCorelist=new SumsCorelist();
                String sumScore=sumListobj.getString("SUMSCORE");
                sumsCorelist.setSumsCore(sumScore);
                scoreListBean.setSumsCorelist(sumsCorelist);
                List<ScoreList> scoreList = new ArrayList<>();
                JSONArray sumListArray=scoreListObj.getJSONArray("SCORELIST");
                for (int j = 0; j < sumListArray.size(); j++) {
                    JSONObject resultScoreObj = (JSONObject) sumListArray.get(j);
                    ScoreList score = new ScoreList();
                    String Score = resultScoreObj.getString("SCORE");
                    String effDate = resultScoreObj.getString("EFF_DATE");
                    String expDate = resultScoreObj.getString("EXP_DATE");
                    score.setScore(Score);
                    score.setEff_date(effDate);
                    score.setExp_date(expDate);
                    scoreList.add(score);
                }
                scoreListBean.setScoreList(scoreList);
                scoreBean.setScoreListBean(scoreListBean);
                resultList.add(scoreBean);
            }
            result.setResult(resultList);
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("新积分到期查询接口", e);
        }
        return result;
    }
}
