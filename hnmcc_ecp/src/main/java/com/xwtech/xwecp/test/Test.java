package com.xwtech.xwecp.test;


import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.dao.DAOException;
import com.xwtech.xwecp.dao.GroupDetectionDAO;
import com.xwtech.xwecp.dao.ServiceCallerPrivilegeDAOImpl;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.dao.service.ServiceConfigDAOImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 54344 on 2018/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:cfg/applicationContext-*.xml"})
public class Test  {

    @Autowired
    private WellFormedDAO wellFormedDAO;

    @Autowired
    ServiceCallerPrivilegeDAOImpl serviceCallerPrivilegeDAO;

    @Autowired
    GroupDetectionDAO groupDetectionDAO;


    @Autowired
    ServiceConfigDAOImpl serviceConfigDAO;

    @org.junit.Test
    public void test() throws DAOException {

        System.out.println(serviceConfigDAO.getServiceConfig("QRY010097"));

    }
}
