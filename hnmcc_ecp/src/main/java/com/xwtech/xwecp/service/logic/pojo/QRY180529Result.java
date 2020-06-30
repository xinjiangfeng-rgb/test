package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY180529Result  extends BaseServiceInvocationResult {


    private List<PointsDueNew> pointsDueNewList;

    public List<PointsDueNew> getPointsDueNewList() {
        return pointsDueNewList;
    }

    public void setPointsDueNewList(List<PointsDueNew> pointsDueNewList) {
        this.pointsDueNewList = pointsDueNewList;
    }
}
