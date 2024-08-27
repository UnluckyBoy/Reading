package com.cloudstudio.reading.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.cloudstudio.reading.R;

/**
 * @ClassName ElementUtil
 * @Author Create By matrix
 * @Date 2024/8/27 14:56
 */
public class ElementUtil {

    public static void textStatusClick(Context context, TextView textView){
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }
    public static void textStatusNormal(Context context,TextView textView){
        textView.setTypeface(null, Typeface.NORMAL);
        textView.setTextColor(ContextCompat.getColor(context, R.color.gray));
    }
}
