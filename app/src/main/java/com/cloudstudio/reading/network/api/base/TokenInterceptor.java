package com.cloudstudio.reading.network.api.base;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName TokenInterceptor
 * @Author Create By matrix
 * @Date 2024/8/29 14:50
 */
public class TokenInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        // 假设我们只对特定请求的响应感兴趣，这里我们简单地从所有响应中获取token
        String token = response.header("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            // 这里可以存储token到全局变量、事件总线等
            // 例如：GlobalState.setToken(token.substring("Bearer ".length()));
            System.out.println("Received token: " + token);
        }
        return response;
    }
}
