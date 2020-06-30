package com.xwtech.xwecp.util;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.communication.CommunicateException;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wunan on 2019/3/29.
 */
public class HttpRequestUtil {
    public static  String send2Power(String url, JSONObject params)
            throws CommunicateException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().
                connectTimeout(100000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS);
        OkHttpClient okHttpClient = builder.build();
        String s = String.valueOf(params);
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"),s))
                .build();
        String result = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("服务器端错误: " + response);
            }
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
