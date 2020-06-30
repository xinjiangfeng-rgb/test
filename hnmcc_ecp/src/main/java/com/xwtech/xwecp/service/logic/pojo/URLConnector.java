//package com.xwtech.xwecp.service.logic.pojo;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class URLConnector {
//
//	public String getContentFromURL(String url, String param) {
//		if (url == null || url.trim().length() == 0) {
//			return "";
//		}
//
//		InputStream is = sendRequest(url, param);
//		if (is == null) {
//			return "";
//		}
//		return readStream(is);
//	}
//
//	private InputStream sendRequest(String remote_url, String xml) {
//		try {
//			System.out.println("经分请求：" + remote_url + "\n参数：\n" + xml);
//			URL url = new URL(remote_url);
//			URLConnection httpUrlConn = url.openConnection();
//			// 发送POST请求必须设置如下两行
//			httpUrlConn.setConnectTimeout(5000);
//			httpUrlConn.setReadTimeout(5000);
//			httpUrlConn.setDoOutput(true);
//			httpUrlConn.setDoInput(true);
//			PrintWriter out = new PrintWriter(httpUrlConn.getOutputStream());
//			// 发送请求参数
//			out.print(xml);
//			out.flush();
//
//			// 获取返回输入流
//			InputStream is = httpUrlConn.getInputStream();
//			if (is != null) {
//				out.close();
//				httpUrlConn = null;
//				out = null;
//				return is;
//			}
//			return null;
//		} catch (Exception e) {
//			System.out.println("error:" + e.getMessage());
//		}
//		return null;
//	}
//
//	public String readStream(InputStream is) {
//		try {
//			if (is == null) {
//				return "";
//			}
//			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//			StringBuilder sb = new StringBuilder();
//			String line = "";
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//			br.close();
//			return sb.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//}
//
//class Invoker {
//	public void invoke() {
//
//	}
//}