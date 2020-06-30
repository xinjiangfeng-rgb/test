package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class QRY040300Result extends BaseServiceInvocationResult{
    List<QRY040300ResultBean> list = new ArrayList<QRY040300ResultBean>();

    public List<QRY040300ResultBean> getList() {
        return list;
    }

    public void setList(List<QRY040300ResultBean> list) {
        this.list = list;
    }
}
