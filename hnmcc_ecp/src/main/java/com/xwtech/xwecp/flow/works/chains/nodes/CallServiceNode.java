package com.xwtech.xwecp.flow.works.chains.nodes;

import com.xwtech.xwecp.flow.works.chains.AbstractFlowControl;
import com.xwtech.xwecp.log.PerformanceTracer;
import com.xwtech.xwecp.msg.MessageHelper;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.pojo.ChannelInfo;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ServiceExecutor;

/**
 * 最后请求节点
 *
 * @author maofw
 */
public class CallServiceNode extends AbstractFlowControl {

    @Override
    public boolean execute(ServiceExecutor serviceExecutor, ServiceMessage inputMessage, String clientIp,
                           ChannelInfo channelInfo, Object o) {
        // 直接返回true 执行
        return true;
    }

    @Override
    public ServiceMessage failed(MessageHelper messageHelper, ServiceMessage inputMessage, Object o) {
        // execute 返回true 所以该方法不会被执行
        return null;
    }

    // 成功执行之后 执行的方法
    public ServiceMessage executeSuccess(ServiceExecutor serviceExecutor, MessageHelper messageHelper,
                                         ServiceMessage inputMessage, Object o)
            throws Exception {
        // System.out.println(this.getClass() + " executeSuccess!");
        // 如果 判断成功 可以继续
        PerformanceTracer pt = PerformanceTracer.getInstance();
        long n = pt.addBreakPoint();
        BaseServiceInvocationResult ret;

        ret = serviceExecutor.callService(inputMessage);

        pt.trace("处理业务逻辑结束...", n);
        String accessId = inputMessage.getHead().getSequence();
        if (ret != null) {
            ret.setAccessId(accessId);
        }
        ServiceMessage responseMsg = messageHelper.createResponseMessage(inputMessage);
        ResponseData responseData = new ResponseData();
        responseData.setServiceResult(ret);
        responseMsg.setData(responseData);

        /**********************************************************************/
        /* 测试返回结果 生产环境需要删除 -start */
        /**********************************************************************/

        // TODO 改造方案
        // 1.项目启动时，建立与CRM的连接对象
        // 2.创建socket对象，放在抽象类中，每个指令继承，共用socket对象
        // Interface inter = Spring.getBean("QRY040010");
        // QRY040010Result result1 = inter.method();

//        ServiceMessage responseMsg = messageHelper.createResponseMessage(inputMessage);
//        ResponseData responseData = new ResponseData();
//        // QRY020001Result result = new QRY020001Result();
//        String cmd =inputMessage.getHead().getCmd();
//    	String accessId = inputMessage.getHead().getSequence();
//        BaseServiceInvocationResult result=SimulateResult.getSimulateResult(cmd, accessId);

        // List<GommonBusiness> gommList = new ArrayList<GommonBusiness>();
        // GommonBusiness gb = new GommonBusiness();
        // gb.setBeginDate("2015-01-27");
        // gb.setEndDate("2015-01-27");
        // gb.setId("19001");
        // gb.setJtvwId("DGFCJQ");
        // gb.setName("订购");
        // gb.setState(1);
        // gb.setReserve1("苦工工");
        // gb.setReserve2("大保见");
        // gommList.add(gb);
        //
        // List<VIPUser> userList = new ArrayList<VIPUser>();
        //
        // VIPUser user = new VIPUser();
        // user.setCardId("626662666262626262626");
        // user.setCreateDate("2015-01-01");
        // user.setUserId("28282828282828");
        // user.setType("1");
        // user.setMsisdn("13838383838");
        // user.setGrade("1");
        // userList.add(user);
        //
        // result.setVipUser(userList);
        // result.setGommonBusiness(gommList);

        // result.setErrorCode("");
        // result.setErrorMessage("");

        // result.setUserId("100001");
        // result.setUserName("张三");
        // result.setState("025");
        // result.setBrand("全球通");
        // result.setCity("HONGKONG");
        // result.setGrade("1");
        // result.setIcNo("9000000001");
        // result.setIcType("1");
        // result.setIsorder4gprod("1");
        // result.setPayMode("1");
        // result.setContactName("张三");

//        responseData.setServiceResult(result);
//        responseMsg.setData(responseData);

        /**********************************************************************/
        /* 测试返回结果 生产环境需要删除 -end */
        /**********************************************************************/

        return responseMsg;
    }

    // 成功执行之后是否执行
    public boolean isExecuteSuccess() {
        return true;
    }
}
