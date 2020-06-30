package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

public class QRY180410Result extends BaseServiceInvocationResult {

    private List<PackageRemain> packageRemainList = new ArrayList<>();


    public List<PackageRemain> getPackageRemain() {
        return packageRemainList;
    }

    public void setPackageRemain(List<PackageRemain> packageRemainList) {
        this.packageRemainList = packageRemainList;
    }
}
