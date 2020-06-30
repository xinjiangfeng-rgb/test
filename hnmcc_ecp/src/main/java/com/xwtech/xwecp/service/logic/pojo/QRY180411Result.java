package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

public class QRY180411Result extends BaseServiceInvocationResult {
    private List<LineOrgNum> result = new ArrayList<>();


    public List<LineOrgNum> getLine() {
        return result;
    }

    public void setResult(List<LineOrgNum> result) {
        this.result = result;
    }
}
