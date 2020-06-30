package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

public class ReturnInfoBeans {

    private List<RightList> list;

    public List<RightList> getList() {
        return list;
    }

    public void setList(List<RightList> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ReturnInfoBeans{" +
                "list=" + list +
                '}';
    }
}
