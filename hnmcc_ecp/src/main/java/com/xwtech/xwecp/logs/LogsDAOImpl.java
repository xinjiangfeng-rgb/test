package com.xwtech.xwecp.logs;

import com.xwtech.xwecp.dao.BaseDAO;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogsDAOImpl extends BaseDAO implements ILogsDAO {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogsDAOImpl.class);

    @Override
    public boolean insertLInterfaceAccessLog(EcpLiLog lb) {
        try {



            String sql = "INSERT INTO T_ECP_LI_LOG(ID,F_TRACE_ID,F_ACCESS_ID, F_LOGIC_NUMBER, "
                    + "F_ACCESS_TIME, F_RESPONSE_TIME, F_TOTAL_TIME, F_CHANNEL_NUM, "
                    + "F_CHANNEL_USER, F_RESULT_CODE, F_ERROR_MSG, F_USER_MOBILE, "
                    + "F_USER_BRAND, F_USER_CITY, F_CLIENT_IP, "
                    + "F_OP_TYPE, F_BIZ_CODE, F_IS_ERROR, F_ERROR_CODE, F_OPER_ID, F_CLIENT_PORT,F_SER_IP,F_SER_PROT,F_BOSS_IP,F_BOSS_PROT) "
                    + "VALUES (SEQ_t_ecp_li_log.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            int n = this.getJdbcTemplateLog().update(sql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    int index = 1;
                    ps.setString(index++, lb.getFtranceId());
                    ps.setString(index++, lb.getFaccessId());
                    ps.setString(index++, lb.getFlogicNumber());
                    ps.setString(index++, lb.getFaccessTimeStr());
                    ps.setString(index++, lb.getFresponseTimeStr());
                    ps.setString(index++, lb.getFtotalTime().toString());
                    ps.setString(index++, lb.getFchannelNum());
                    ps.setString(index++, lb.getFchannelUser().toString());
                    ps.setString(index++, lb.getFresultCode());
                    ps.setString(index++, lb.getFerrorMsg());
                    ps.setString(index++, lb.getFuserMobile());
                    ps.setString(index++, lb.getFuserBrand());
                    ps.setString(index++, lb.getFuserCity());
                    ps.setString(index++, lb.getFclientIp());
                    ps.setString(index++, lb.getFopType());
                    ps.setString(index++, lb.getFbizCode());
                    ps.setString(index++, lb.getFisError());
                    ps.setString(index++, lb.getFerrorCode());
                    ps.setString(index++, lb.getFoperId());
                    ps.setString(index++, lb.getFclientPort());
                    ps.setString(index++, lb.getFserIP());
                    ps.setString(index++, lb.getFserProt());
                    ps.setString(index++, lb.getFbossIp());
                    ps.setString(index++, lb.getFbossPort());
                }
            });
            return n == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertBossRequestAccessLog(EcpBossLog lb) {
        try {


            String sql = "INSERT INTO T_ECP_BOSS_LOG(ID,F_TRACE_ID,F_ACCESS_ID,F_ACCESS_TIME, F_RESPONSE_TIME, F_TOTAL_TIME, " +
                    "F_RESULT_CODE, F_ERROR_MSG, F_BOSS_ACC_ID, F_BOSS_ID, " +
                    "F_BIS_CODE, F_USER_MOBILE, F_USER_BRAND, F_USER_CITY, " +
                    "F_RET_TYPE,F_PAGE_NUM,F_REC_NUM,F_SERIAL_NUM,F_JFSERIAL_NUM) VALUES (SEQ_t_ecp_boss_log.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            int n = this.getJdbcTemplateLog().update(sql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    int index = 1;
                    ps.setString(index++, lb.getFtraceId());
                    ps.setString(index++, lb.getFaccessId());
                    ps.setString(index++, lb.getFaccessTimeStr());
                    ps.setString(index++, lb.getFresponseTimeStr());
                    ps.setString(index++, lb.getFtotalTime().toString());
                    ps.setString(index++, lb.getFresultCode());
                    ps.setString(index++, lb.getFerrorMsg());
                    ps.setString(index++, lb.getFbossAccId());
                    ps.setString(index++, lb.getFbossId());
                    ps.setString(index++, lb.getFbisCode());
                    ps.setString(index++, lb.getFuserMobile());
                    ps.setString(index++, lb.getFuserBrand());
                    ps.setString(index++, lb.getFuserCity());
                    ps.setString(index++, lb.getFretType());
                    ps.setString(index++, lb.getFpageNum());
                    ps.setString(index++, lb.getFrecNum());
                    ps.setString(index++, lb.getFserialNum());
                    ps.setString(index++, lb.getFjfserialNum());
                }
            });

            return n == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
