package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040301Result;
import java.io.Serializable;

/**

 */
public interface IAddRedMemberService extends Serializable{
    public QRY040301Result queryAddMember(String phoneNum,String gbillId,String memSrvpkg,String memeberId) throws LIException;

}
