package com.cloudstudio.reading.network.api;

import com.cloudstudio.reading.network.api.base.WebApi;
import com.cloudstudio.reading.network.service.QueryUserInfoService;

import retrofit2.Retrofit;

/**
 * @ClassName queryUserInfoApi
 * @Author Create By matrix
 * @Date 2024/8/30 13:48
 */
public class QueryUserInfoApi extends WebApi {
    String url="https://42f9f82b.r7.cpolar.cn/api/";
    Retrofit retrofit=getApi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(QueryUserInfoService.class);
    }
}
