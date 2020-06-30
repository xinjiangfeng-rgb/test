package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY170716Result;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface IActivityCancelService {

    public QRY170716Result activityCancel(String billid,String offerid);
}
