package com.xwtech.xwecp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MessageJsonUtil {
	public static final String BOSS_CODE = "00000";

	/** 逻辑接口成功编码 */
	public static final String LOGIC_SUCESS = "0";

	/** 逻辑接口失败编码 */
	public static final String LOGIC_ERROR = "1";
	/**
	 * 返回状态jsonkey
	 */
	static String RESP_CODE = "respCode";
	/**
	 * 返回描述jsonkey
	 */
	static String RESP_DESC = "respDesc";
	/**
	 * 返回详细结果jsonkey
	 */
	static String RESULT = "result";
	/**
	 * 返回详细结果集jsonkey
	 */
	static String SO_MEMBER_DEAL = "SO_MEMBER_DEAL";

	static String GPRS_PKG_USE_HIS = "GPRS_PKG_USE_HIS";

	private static JSONObject bossJson = null;

	private static MessageJsonUtil INSTANCE = new MessageJsonUtil();

	private MessageJsonUtil() {
	}

	public static MessageJsonUtil getInstance(String text) {
		if (null == text || text.length() == 0) {
			return null;
		}
		bossJson = JSON.parseObject(text);
		return INSTANCE;
	}

	/**
	 * 解析返回状态
	 * 
	 * @return
	 */
	public String getBossCode() {
		return bossJson.getString(RESP_CODE);
	}

	/**
	 * 解析返回描述
	 * 
	 * @return
	 */
	public String getBossDesc() {
		return bossJson.getString(RESP_DESC);
	}

	/**
	 * 解析result中SO_MEMBER_DEAL为字符串
	 * 
	 * @return
	 */
	public String getStringResult() {
		return getResult().getString(SO_MEMBER_DEAL);
	}

	public String getGprsPkgUseHis() {
		return getResult().getString(GPRS_PKG_USE_HIS);
	}

	/**
	 * 解析出result中SO_MEMBER_DEAL为集合
	 * 
	 * @return
	 */
	public JSONArray getJSONArrayResult() {
		Object o = getResult().get(SO_MEMBER_DEAL);
		if (o instanceof JSONArray) {
			return (JSONArray) o;
		} else if (o instanceof JSONObject) {
			JSONArray json = new JSONArray();
			json.add(o);
			return json;
		}
		return new JSONArray();
	}

	/**
	 * 解析出result中SO_MEMBER_DEAL
	 * 
	 * @return
	 */
	public JSONObject getJSONObjectResult() {
		Object o = getResult().get(SO_MEMBER_DEAL);
		if (o instanceof JSONArray) {
			return (JSONObject) ((JSONArray) o).get(0);
		} else if (o instanceof JSONObject) {
			return (JSONObject) o;
		}
		return new JSONObject();
	}

	/**
	 * 解析result
	 * 
	 * @return
	 */
	public JSONObject getResult() {
		return bossJson.getJSONObject(RESULT);
	}

	/**
	 * 新加方法，added by swt
	 * 
	 * @param key
	 *            json中的键
	 * @param clazz
	 *            将key对应的value转换为给定的class对象
	 * @return
	 */
	public <T> T get(String key, Class<T> clazz) {
		return bossJson.getObject(key, clazz);
	}

	/**
	 * 新加方法，added by swt,将给定的json串转换为对应的class
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> T getBossResult(Class<T> clazz) {
		return bossJson.toJavaObject(clazz);
	}

	public static void main(String[] args) {
		String text = "{\"respCode\":\"00000\",\"respDesc\":\"调用成功!\",\"result\":{\"SO_MEMBER_DEAL\":[{\"FREE_RES\":\"50\",\"FREE_RES_LEFT\":\"50\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"60001294\",\"ITEM_NAME\":\"4G飞享套餐-38A元套餐50分300M2015版新-免费通话时长\",\"RESOURCESCODE\":\"01\",\"RESOURCE_TYPE\":\"语音\",\"SUB_DATE\":\"20161123000000\",\"UNIT_DES\":\"分钟\"},{\"FREE_RES\":\"100\",\"FREE_RES_LEFT\":\"100\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"60001296\",\"ITEM_NAME\":\"4G飞享套餐-48A元套餐100分300M2015版新-免费通话时长\",\"RESOURCESCODE\":\"01\",\"RESOURCE_TYPE\":\"语音\",\"SUB_DATE\":\"20161123000000\",\"UNIT_DES\":\"分钟\"},{\"FREE_RES\":\"150\",\"FREE_RES_LEFT\":\"150\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"60001302\",\"ITEM_NAME\":\"全国套餐关爱计划叠加包-48A元套餐赠送150分150M-免费通话时长\",\"RESOURCESCODE\":\"01\",\"RESOURCE_TYPE\":\"语音\",\"SUB_DATE\":\"20161123000000\",\"UNIT_DES\":\"分钟\"},{\"FREE_RES\":\"307200\",\"FREE_RES_LEFT\":\"307200\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"61001826\",\"ITEM_NAME\":\"4G飞享套餐-38A元套餐50分300M2015版新-免费流量\",\"RESOURCESCODE\":\"04\",\"RESOURCE_TYPE\":\"流量\",\"SUB_DATE\":\"20161123000000\",\"UNIT_DES\":\"KB\"},{\"FREE_RES\":\"307200\",\"FREE_RES_LEFT\":\"307200\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"61001828\",\"ITEM_NAME\":\"4G飞享套餐-48A元套餐100分300M2015版新-免费流量\",\"RESOURCESCODE\":\"04\",\"RESOURCE_TYPE\":\"流量\",\"SUB_DATE\":\"20161123000000\",\"UNIT_DES\":\"KB\"},{\"FREE_RES\":\"153600\",\"FREE_RES_LEFT\":\"153600\",\"FREE_RES_USED\":\"0\",\"ITEM_ID\":\"61001834\",\"ITEM_NAME\":\"全国套餐关爱计划叠加包-48A元套餐赠送150分150M-省内流量\",\"RESOURCESCODE\":\"04\",\"RESOURCE_TYPE\":\"流量\",\"SUB_DATE\":\"20161123000000\",\"UNIT_DES\":\"KB\"}]}}";
		MessageJsonUtil bossjson = MessageJsonUtil.getInstance(text);
		Object o = bossjson.getResult().get(SO_MEMBER_DEAL);
		if (o instanceof JSONArray) {
			bossjson.getJSONArrayResult();
		} else if (o instanceof JSONObject) {
			bossjson.getJSONObjectResult();
		}
		System.out.println(bossjson.getStringResult());

	}

}
