package com.cloudstudio.reading.entities.nertworkBean;


/**
 * @ClassName LoginBean
 * @Author Create By matrix
 * @Date 2024/8/29 14:28
 */
public class LoginBean {
    private boolean handleType;
    private int handleCode;
    private String Authorization;
    private String handleMessage;
    private UserInfoBean handleData;

    public boolean isHandleType() {
        return handleType;
    }

    public void setHandleType(boolean handleType) {
        this.handleType = handleType;
    }

    public int getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(int handleCode) {
        this.handleCode = handleCode;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getHandleMessage() {
        return handleMessage;
    }

    public void setHandleMessage(String handleMessage) {
        this.handleMessage = handleMessage;
    }

    public UserInfoBean getHandleData() {
        return handleData;
    }

    public void setHandleData(UserInfoBean handleData) {
        this.handleData = handleData;
    }
}
