package com.cloudstudio.reading.util;

import android.content.Context;

/**
 * @ClassName DipPx
 * @Author Create By matrix
 * @Date 2024/8/24 23:52
 */
public class DipPx {

    /**
     * 将dp值转换为px值，保证尺寸在不同屏幕密度上的一致性
     *
     * @param context 上下文，用于获取屏幕密度
     * @param dp 要转换的dp值
     * @return 转换后的px值
     */
    public static int dip2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f); // 加0.5f是为了四舍五入
    }

    // 如果需要，还可以添加px转dp的方法
    public static float px2dip(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return px / scale;
    }
}
