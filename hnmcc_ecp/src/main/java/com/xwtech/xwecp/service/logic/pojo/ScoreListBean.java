package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

public class ScoreListBean {

    private List<ScoreList> scoreList;
    private  SumsCorelist sumsCorelist;

    public List<ScoreList> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<ScoreList> scoreList) {
        this.scoreList = scoreList;
    }

    public SumsCorelist getSumsCorelist() {
        return sumsCorelist;
    }

    public void setSumsCorelist(SumsCorelist sumsCorelist) {
        this.sumsCorelist = sumsCorelist;
    }
}
