package com.cloudstudio.reading.network.api.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName WebApi
 * @Author Create By matrix
 * @Date 2024/8/29 13:27
 *
 * Retrofit Api基类
 */
public abstract class WebApi {
//    OkHttpClient client = new OkHttpClient.Builder()
//            .addInterceptor(new TokenInterceptor())
//            .build();

    protected Retrofit getApi(String url) {
        /*使用拦截器获取token*/
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new TokenInterceptor())
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public abstract <T> T getService();
}
