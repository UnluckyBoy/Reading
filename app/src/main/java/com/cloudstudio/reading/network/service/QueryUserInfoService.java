package com.cloudstudio.reading.network.service;

import com.cloudstudio.reading.entities.nertworkBean.QueryInfoResponseBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ClassName queryUserInfoService
 * @Author Create By matrix
 * @Date 2024/8/30 13:49
 */
public interface QueryUserInfoService {
    @GET("query_user")
    Call<QueryInfoResponseBean> getState(
            @Query("account") String account
    );
}
