package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY202000Result;

public interface IPrintEInvoiceLastMouthService {


    public QRY202000Result printEInvoiceLastMouth(String paymentId,
                                                  String acctId,
                                                  String acctName,
                                                  String printNum,
                                                  String billingCycleId,
                                                  String invoiceContent,
                                                  String ghfNsrmc,
                                                  String ghfNsrsbh,
                                                  String ghfDz,
                                                  String ghfDhhm,
                                                  String ghfYhzh,
                                                  String ghfQylx,
                                                  String pushBillId,
                                                  String pushEmail,
                                                  String remark,
                                                  String optCode) throws LIException;

}
