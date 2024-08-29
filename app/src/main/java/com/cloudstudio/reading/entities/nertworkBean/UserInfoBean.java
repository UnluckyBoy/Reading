package com.cloudstudio.reading.entities.nertworkBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserInfoBean
 * @Author Create By matrix
 * @Date 2024/8/29 14:34
 */
@Data
@Getter
@Setter
public class UserInfoBean {
    private int mId;
    private String mHead;
    private String mName;
    private String mSex;
    private String mAccount;
    private String mPhone;
    private String mEmail;
    private int mLevel;
    private int mStatus;
    private String mAddressIp;
}
