package com.xwtech.xwecp.service.logic.client_impl.common;

import java.util.List;
import java.util.Map;

import com.xwtech.xwecp.service.logic.pojo.MCSResult;

/**
 * 4. 对外开放能力API<br/>
 * 4.1. 营销传播推荐服务接口<br/>
 * 4.1.1. 接口描述<br/>
 * 营销传播推荐服务。<br/>
 * 4.1.2. 能力编码<br/>
 * ESB_MARKETING_COMMUNICATION<br/>
 * 4.1.3. 请求方式<br/>
 * 采用http方式。<br/>
 * 4.1.4. 接口类型<br/>
 * 同步接口<br/>
 * 4.1.5. 业务参数<br/>
 * 参数 说明 大小 是否必选 参数类型 备注<br/>
 * Version 接口服务版本号，默认0100 10 Y C <br/>
 * Platform 请求的平台标识码 100 Y C <br/>
 * Event 请求的事件类型 100 Y C <br/>
 * OperateTime 操作时间 100 Y C 格式：yyyy-MM-dd hh24:mi:ss<br/>
 * Sign 数据签名 100 Y C md5（渠道标识+事件标识+事件惟一序列号+服务号码+请求时间+密钥）<br/>
 * userId 第三方平台操作员用户ID 100 Y C <br/>
 * userAreaCode 第三方平台操作员用户归属地市（371） 100 Y C <br/>
 * SequenceNo 请求序列号 100 Y C 需唯一<br/>
 * ServNumber 服务号码 20 Y C 长度11位<br/>
 * AreaCode 服务号码所属地市编号 10 Y C 长度3位，如郑州为371<br/>
 * EventProperty 事件扩展属性 100 Y C <br/>
 * PropertyName 扩展属性名 100 N C <br/>
 * PropertyValue 扩展属性值 100 N C <br/>
 * 范例：<br/>
 * { " SequenceNo ": "", …… " PropertyValue ": "" }<br/>
 * 4.1.6. 响应参数<br/>
 * 参数 说明 是否必选 参数类型 备注<br/>
 * respCode 结果代码 必选 String 接口调用的返回码<br/>
 * 00000：成功，其他：失败<br/>
 * respDesc 结果说明 必选 String 接口调用的返回消息描述。<br/>
 * result 返回结果 必选 MAP <br/>
 * 
 * Result 对象属性<br/>
 * 参数 说明 是否必选 参数类型 备注<br/>
 * SO_MEMBER_DEAL 结果集 是 jsonString 返回的结果JSON对象字符串<br/>
 * 
 * SO_MEMBER_DEAL对象属性<br/>
 * 参数 说明 参数类型 是否是必选项 备注 <br/>
 * Message 营销活动 C <br/>
 * MessageSn 推荐序列号 C 惟一<br/>
 * PromptId 营销用语ID C Title 推荐标题 C 字符串，不得为空<br/>
 * CatalogId 传播类别 C <br/>
 * CatalogName 传播类别名称 C <br/>
 * ImageUrl 图片文件名 C 字符串，可能为空，相对路径<br/>
 * TargetUrl 目标URL C 字符串，可能为空<br/>
 * Introduction 内容介绍 C 字符串，可能为空<br/>
 * OfferId 内容关联的产品OfferID C 字符串，多个产品ID用半角逗号分隔<br/>
 * SmsPrompt 短信营销语 C <br/>
 * OrderNo 排序号 C 数字<br/>
 * EventProperty 事件扩展属性 C <br/>
 * PropertyName 扩展属性名 C <br/>
 * PropertyValue 扩展属性值 C <br/>
 * HasSendSms 是否发送过短信 C 1 是，0 否<br/>
 * <br/>
 * 返回结果<br/>
 * 正常应答报文如下：<br/>
 * {<br/>
 * "respCode":"00000",<br/>
 * "respDesc":"调用成功!",<br/>
 * "result":{<br/>
 * "SO_MEMBER_DEAL":[<br/>
 * {<br/>
 * " Message ":"",<br/>
 * ……<br/>
 * " HasSendSms ":""<br/>
 * },<br/>
 * {<br/>
 * " Message ":"",<br/>
 * ……<br/>
 * " HasSendSms ":"" <br/>
 * },<br/>
 * ]<br/>
 * }<br/>
 * }<br/>
 *
 * 
 */
public interface ImarketingCommunicationService {

	public List<MCSResult> marketingCommunication(String mobile, String cityCode,
			Map<String, Object> eventPropertyParam);

}
