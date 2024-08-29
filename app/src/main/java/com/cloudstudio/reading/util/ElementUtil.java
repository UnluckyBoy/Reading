package com.cloudstudio.reading.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.EditText;
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

    /**
     * 检查TextView（或其子类，如EditText）的值是否为空或仅包含空白字符。
     *
     * @param textView 要检查的TextView或其子类实例。
     * @return 如果值为空或仅包含空白字符，则返回true；否则返回false。
     */
    public static boolean checkElementValue(TextView textView) {
        if (textView == null) {
            return true; // 如果TextView本身为null，则认为是空的
        }
        String text = textView.getText().toString();
        return text.trim().isEmpty();
    }

    /**
     * 检查EditText的值是否为空或仅包含空白字符。
     * 提供此方法是为了代码的可读性和明确性。
     *
     * @param editText 要检查的EditText实例。
     * @return 如果值为空或仅包含空白字符，则返回true；否则返回false。
     */
    public static boolean checkElementValue(EditText editText) {
        return checkElementValue((TextView) editText);
    }
}
