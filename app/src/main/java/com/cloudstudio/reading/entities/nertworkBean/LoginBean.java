package com.cloudstudio.reading.entities.nertworkBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName LoginBean
 * @Author Create By matrix
 * @Date 2024/8/29 14:28
 */
@Data
@Getter
@Setter
public class LoginBean {
    private boolean handleType;
    private int handleCode;
    private String handleMessage;
    private UserInfoBean userInfoBean;
}
