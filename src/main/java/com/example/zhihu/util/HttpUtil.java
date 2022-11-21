package com.example.zhihu.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpUtil {
    //  构件必要的header
    public Map<String,String> buildHeader(String referer, String host, String cookie){
        Map<String,String> header = new HashMap<>();
        if (!referer.isEmpty()){
            header.put("refer",referer);
        }
        if (!host.isEmpty()){
            header.put("host",host);
        }
        if (!cookie.isEmpty()){
            header.put("cookie",cookie);
        }
        return header;
    }

    //  根据输入的url,读取页面并返回
    public String getContent(String url, Map<String,String> headers){
        //  创建okhttp实例
        OkHttpClient okHttpClient = new OkHttpClient();
        //  定义一个request
        Request request =new Request.Builder()
                .url(url)
                .addHeader("Referer",headers.get("referer"))
                .addHeader("host",headers.get("host"))
                .addHeader("cookie",headers.get("cookie"))
                .build();
        //  结果字符串
        String result = null;
        try {
            //执行请求
            Response response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
