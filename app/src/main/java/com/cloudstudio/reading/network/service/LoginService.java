package com.cloudstudio.reading.network.service;

import com.cloudstudio.reading.entities.nertworkBean.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ClassName LoginService
 * @Author Create By matrix
 * @Date 2024/8/29 14:27
 */
public interface LoginService {
    @GET("login")
    Call<LoginBean> getState(
            @Query("authorization") String authorization,
            @Query("account") String account,
            @Query("password") String password
    );
}
