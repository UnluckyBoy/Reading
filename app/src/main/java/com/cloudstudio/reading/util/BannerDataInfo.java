package com.cloudstudio.reading.util;

/**
 * @ClassName BannerDataInfo
 * @Author Create By matrix
 * @Date 2024-08-13 13:40
 * 轮播数据信息实体
 */
public class BannerDataInfo {
    private final int imageRes;
    private final String url;


    public BannerDataInfo(int mImageRes,String mUrl){
        imageRes=mImageRes;
        url=mUrl;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getUrl() {
        return url;
    }
}
