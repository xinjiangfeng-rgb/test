package com.xwtech.xwecp.logs;

public interface ILogsDAO {

    public boolean insertLInterfaceAccessLog(EcpLiLog lb);

    public boolean insertBossRequestAccessLog(EcpBossLog lb);


}
