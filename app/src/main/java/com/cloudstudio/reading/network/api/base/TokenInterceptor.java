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
    private final String token;
    public TokenInterceptor(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        // 添加token到Header中
        Request request = original.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(request);
    }
}
