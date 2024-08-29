package com.cloudstudio.reading.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName UserManager
 * @Author Create By matrix
 * @Date 2024/8/29 15:08
 */
public class UserManager {
    private static UserManager instance;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USER_TOKEN = "token";

    private UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager(context);
        }
        return instance;
    }

    // 保存用户Token
    public void saveUserToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_TOKEN, token);
        editor.apply();
    }

    // 获取用户Token
    public String getUserToken() {
        return sharedPreferences.getString(KEY_USER_TOKEN, null);
    }

    // 检查用户是否已登录
    public boolean isUserLogin() {
        return getUserToken() != null;
    }

    // 登出用户（清除Token）
    public void logout() {
        saveUserToken(null);
    }
}
