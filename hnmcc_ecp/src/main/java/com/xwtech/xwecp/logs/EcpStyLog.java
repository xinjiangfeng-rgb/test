package com.xwtech.xwecp.logs;

/**
 * ecp掉用boss接口的日志
 */
public class EcpStyLog implements Log {
    public EcpStyLog() {
    }

    /**
     * 自增id
     */
    private Long id;

    @Override
    public int getLogState() {
        return 2;
    }

    private String fTraceId;
    private String fAccessId;
    private String fAccessTime;
    private String fResponseTime;//fRESPONSETime
    private String fTotalTime;//fTOTALTime
    private String fResultCode;//fRESULTCode
    private String fErrorMsg;//fERRORMsg
    private String fBossAccId;//fBOSSACCId
    private String fBossId;//fBOSSId
    private String fBisCode;//fBISCode
    private String fSerIp;//fSERIp
    private String fSerProt;//fSERPROT
    private String fBossIp;//fBOSSIP
    private String fBossProt;//fBOSSPROT
    private String fUserMobile;//fUSERMOBILE
    private String fUserBrand;//fUSERBRAND
    private String fUserCity;//fUSERCITY
    private String fRetType;//fRETTYPE
    private String fReqText;//fREQTEXT
    private String fResText;//fRESTEXT
    private String fIsError;//fISERROR
    private String fErrorCode;//fERRORCODE
    private String fPageNum;//fPAGENUM
    private String fRecNum;//fRECNUM
    private String fSerialNum;//fSERIALNUM
    private String fJfserialNum;//fJFSERIALNUM

    public EcpStyLog(String fTraceId, String fAccessId, String fAccessTime, String fResponseTime, String fTotalTime
			, String fResultCode, String fErrorMsg, String fBossAccId, String fBossId, String fBisCode, String fSerIp
			, String fSerProt, String fBossIp, String fBossProt, String fUserMobile, String fUserBrand, String fUserCity
			, String fRetType, String fReqText, String fResText, String fIsError, String fErrorCode, String fPageNum
			, String fRecNum, String fSerialNum, String fJfserialNum){
        this.fTraceId = fTraceId;
        this.fAccessId = fAccessId;
        this.fAccessTime = fAccessTime;
        this.fResponseTime = fResponseTime;
        this.fTotalTime = fTotalTime;
        this.fResultCode = fResultCode;
        this.fErrorMsg = fErrorMsg;
        this.fBossAccId = fBossAccId;
        this.fBossId = fBossId;
        this.fBisCode = fBisCode;
        this.fSerIp = fSerIp;
        this.fSerProt = fSerProt;
        this.fBossIp = fBossIp;
        this.fBossProt = fBossProt;
        this.fUserMobile = fUserMobile;
        this.fUserBrand = fUserBrand;
        this.fUserCity = fUserCity;
        this.fRetType = fRetType;
        this.fReqText = fReqText;
        this.fResText = fResText;
        this.fIsError = fIsError;
        this.fErrorCode = fErrorCode;
        this.fPageNum = fPageNum;
        this.fRecNum = fRecNum;
        this.fSerialNum = fSerialNum;
        this.fJfserialNum = fJfserialNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getfTraceId() {
		return fTraceId;
	}

	public void setfTraceId(String fTraceId) {
		this.fTraceId = fTraceId;
	}

	public String getfAccessId() {
		return fAccessId;
	}

	public void setfAccessId(String fAccessId) {
		this.fAccessId = fAccessId;
	}

	public String getfAccessTime() {
		return fAccessTime;
	}

	public void setfAccessTime(String fAccessTime) {
		this.fAccessTime = fAccessTime;
	}

	public String getfResponseTime() {
		return fResponseTime;
	}

	public void setfResponseTime(String fResponseTime) {
		this.fResponseTime = fResponseTime;
	}

	public String getfTotalTime() {
		return fTotalTime;
	}

	public void setfTotalTime(String fTotalTime) {
		this.fTotalTime = fTotalTime;
	}

	public String getfResultCode() {
		return fResultCode;
	}

	public void setfResultCode(String fResultCode) {
		this.fResultCode = fResultCode;
	}

	public String getfErrorMsg() {
		return fErrorMsg;
	}

	public void setfErrorMsg(String fErrorMsg) {
		this.fErrorMsg = fErrorMsg;
	}

	public String getfBossAccId() {
		return fBossAccId;
	}

	public void setfBossAccId(String fBossAccId) {
		this.fBossAccId = fBossAccId;
	}

	public String getfBossId() {
		return fBossId;
	}

	public void setfBossId(String fBossId) {
		this.fBossId = fBossId;
	}

	public String getfBisCode() {
		return fBisCode;
	}

	public void setfBisCode(String fBisCode) {
		this.fBisCode = fBisCode;
	}

	public String getfSerIp() {
		return fSerIp;
	}

	public void setfSerIp(String fSerIp) {
		this.fSerIp = fSerIp;
	}

	public String getfSerProt() {
		return fSerProt;
	}

	public void setfSerProt(String fSerProt) {
		this.fSerProt = fSerProt;
	}

	public String getfBossIp() {
		return fBossIp;
	}

	public void setfBossIp(String fBossIp) {
		this.fBossIp = fBossIp;
	}

	public String getfBossProt() {
		return fBossProt;
	}

	public void setfBossProt(String fBossProt) {
		this.fBossProt = fBossProt;
	}

	public String getfUserMobile() {
		return fUserMobile;
	}

	public void setfUserMobile(String fUserMobile) {
		this.fUserMobile = fUserMobile;
	}

	public String getfUserBrand() {
		return fUserBrand;
	}

	public void setfUserBrand(String fUserBrand) {
		this.fUserBrand = fUserBrand;
	}

	public String getfUserCity() {
		return fUserCity;
	}

	public void setfUserCity(String fUserCity) {
		this.fUserCity = fUserCity;
	}

	public String getfRetType() {
		return fRetType;
	}

	public void setfRetType(String fRetType) {
		this.fRetType = fRetType;
	}

	public String getfReqText() {
		return fReqText;
	}

	public void setfReqText(String fReqText) {
		this.fReqText = fReqText;
	}

	public String getfResText() {
		return fResText;
	}

	public void setfResText(String fResText) {
		this.fResText = fResText;
	}

	public String getfIsError() {
		return fIsError;
	}

	public void setfIsError(String fIsError) {
		this.fIsError = fIsError;
	}

	public String getfErrorCode() {
		return fErrorCode;
	}

	public void setfErrorCode(String fErrorCode) {
		this.fErrorCode = fErrorCode;
	}

	public String getfPageNum() {
		return fPageNum;
	}

	public void setfPageNum(String fPageNum) {
		this.fPageNum = fPageNum;
	}

	public String getfRecNum() {
		return fRecNum;
	}

	public void setfRecNum(String fRecNum) {
		this.fRecNum = fRecNum;
	}

	public String getfSerialNum() {
		return fSerialNum;
	}

	public void setfSerialNum(String fSerialNum) {
		this.fSerialNum = fSerialNum;
	}

	public String getfJfserialNum() {
		return fJfserialNum;
	}

	public void setfJfserialNum(String fJfserialNum) {
		this.fJfserialNum = fJfserialNum;
	}

    
}
