package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL218919Result;

/**
 * 转套餐并参加活动
 */
public interface ITurnPackageParticipateActivitieService {

    /**
     *
     * @param billId        手机号码
     * @param basicofferid  套餐id
     * @param ployid        活动id或者code
     * @return
     * @throws LIException
     */
    DEL218919Result delTurnPackageParticipateActivitie(String billId, String basicofferid, String ployid)throws LIException;

}
