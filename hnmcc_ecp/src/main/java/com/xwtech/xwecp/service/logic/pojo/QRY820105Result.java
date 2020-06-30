package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询用户所属目标客户群
 */
public class QRY820105Result extends BaseServiceInvocationResult {
    private List<UserTarget> UserTarget = new ArrayList<>();

    public List<UserTarget> getUserTarget() {
        return UserTarget;
    }

    public void setUserTarget(List<UserTarget> userTarget) {
        UserTarget = userTarget;
    }
}
