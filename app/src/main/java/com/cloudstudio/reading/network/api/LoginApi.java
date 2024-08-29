package com.cloudstudio.reading.network.api;

import com.cloudstudio.reading.network.api.base.WebApi;
import com.cloudstudio.reading.network.service.LoginService;

import retrofit2.Retrofit;

/**
 * @ClassName LoginApi
 * @Author Create By matrix
 * @Date 2024/8/29 14:27
 */
public class LoginApi extends WebApi {
    String url="https://42f9f82b.r7.cpolar.cn/api/";
    Retrofit retrofit=getApi(url);

    @Override
    public <T> T getService() {
        return (T) retrofit.create(LoginService.class);
    }
}
