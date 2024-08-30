package com.cloudstudio.reading.entities.nertworkBean;


/**
 * @ClassName LoginBean
 * @Author Create By matrix
 * @Date 2024/8/29 14:28
 */
public class LoginResponseBean {
    private boolean handleType;
    private int handleCode;
    private String handleMessage;
    private HandleData handleData;

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

    public String getHandleMessage() {
        return handleMessage;
    }

    public void setHandleMessage(String handleMessage) {
        this.handleMessage = handleMessage;
    }

    public HandleData getHandleData() {
        return handleData;
    }

    public void setHandleData(HandleData handleData) {
        this.handleData = handleData;
    }
}
