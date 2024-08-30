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

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLogin";
    private static final String KEY_USER_ACCOUNT = "ACCOUNT";
    private static final String KEY_USER_HEAD = "HEAD";
    private static final String KEY_USER_NAME = "NAME";
    private static final String KEY_USER_SEX = "SEX";
    private static final String KEY_USER_PHONE = "PHONE";
    private static final String KEY_USER_EMAIL = "EMAIL";
    private static final String KEY_USER_LEVEL = "LEVEL";
    private static final String KEY_USER_STATUS = "STATUS";
    private static final String KEY_USER_IP = "IP";
    private static final String KEY_USER_TOKEN = "Token";
    private SharedPreferences sharedPreferences;

    // 私有构造函数，防止外部直接实例化
    private UserManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // 提供全局访问点
    public static synchronized UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager(context.getApplicationContext());
        }
        return instance;
    }

    // 登录并保存用户信息
//    public void login(String account,String head,String name,String sex,String phone,String email,
//                      int level,int status,String ip,String userToken) {
    public void login(String userToken,String account) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USER_TOKEN, userToken.substring(7));
        editor.putString(KEY_USER_ACCOUNT, account);
//        editor.putString(KEY_USER_HEAD, head);
//        editor.putString(KEY_USER_NAME, name);
//        editor.putString(KEY_USER_SEX, sex);
//        editor.putString(KEY_USER_PHONE, phone);
//        editor.putString(KEY_USER_EMAIL, email);
//        editor.putString(KEY_USER_LEVEL, String.valueOf(level));
//        editor.putString(KEY_USER_STATUS, String.valueOf(status));
//        editor.putString(KEY_USER_IP, ip);
        editor.apply();
    }

    // 登出并清除用户信息
    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.remove(KEY_USER_ACCOUNT);
//        editor.remove(KEY_USER_HEAD);
//        editor.remove(KEY_USER_NAME);
//        editor.remove(KEY_USER_SEX);
//        editor.remove(KEY_USER_PHONE);
//        editor.remove(KEY_USER_EMAIL);
//        editor.remove(KEY_USER_LEVEL);
//        editor.remove(KEY_USER_STATUS);
//        editor.remove(KEY_USER_IP);
        editor.remove(KEY_USER_TOKEN);
        editor.apply();
    }

    // 检查用户是否已登录
    public boolean isLogin() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // 获取用户
    public String getUserAccount() {
        return sharedPreferences.getString(KEY_USER_ACCOUNT, null);
    }
    public String getUserHead() {
        return sharedPreferences.getString(KEY_USER_HEAD, null);
    }
    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, null);
    }
    public String getUserLevel() {
        return sharedPreferences.getString(KEY_USER_LEVEL, null);
    }

    // 获取用户Token
    public String getUserToken() {
        return sharedPreferences.getString(KEY_USER_TOKEN, null);
    }
}
