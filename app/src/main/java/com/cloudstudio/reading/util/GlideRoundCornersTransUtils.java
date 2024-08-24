package com.cloudstudio.reading.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * @ClassName GlideRoundCornersTransUtils
 * @Author Create By matrix
 * @Date 2024/8/24 22:47
 */
public class GlideRoundCornersTransUtils implements Transformation<Bitmap> {

    private final BitmapPool mBitmapPool;
    private float radius;
    private final boolean mLeftTop, mLeftBottom,mRightTop, mRightBottom;


    public GlideRoundCornersTransUtils(Context context, float radius,boolean leftTop,boolean leftBottom,boolean rightTop,boolean rightBottom) {
        this.mBitmapPool = Glide.get(context).getBitmapPool();
        this.radius = radius;
        this.mLeftTop=leftTop;
        this.mLeftBottom=leftBottom;
        this.mRightTop=rightTop;
        this.mRightBottom=rightBottom;
    }

    @NonNull
    @Override
    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();
        int finalWidth, finalHeight;
        //输出目标的宽高或高宽比例
        float scale;
        if (outWidth > outHeight) {
            //如果 输出宽度 > 输出高度 求高宽比
            scale = (float) outHeight / (float) outWidth;
            finalWidth = source.getWidth();
            //固定原图宽度,求最终高度
            finalHeight = (int) ((float) source.getWidth() * scale);
            if (finalHeight > source.getHeight()) {
                //如果 求出的最终高度 > 原图高度 求宽高比
                scale = (float) outWidth / (float) outHeight;
                finalHeight = source.getHeight();
                //固定原图高度,求最终宽度
                finalWidth = (int) ((float) source.getHeight() * scale);
            }
        } else if (outWidth < outHeight) {
            //如果 输出宽度 < 输出高度 求宽高比
            scale = (float) outWidth / (float) outHeight;
            finalHeight = source.getHeight();
            //固定原图高度,求最终宽度
            finalWidth = (int) ((float) source.getHeight() * scale);
            if (finalWidth > source.getWidth()) {
                //如果 求出的最终宽度 > 原图宽度 求高宽比
                scale = (float) outHeight / (float) outWidth;
                finalWidth = source.getWidth();
                finalHeight = (int) ((float) source.getWidth() * scale);
            }
        } else {
            //如果 输出宽度=输出高度
            finalHeight = source.getHeight();
            finalWidth = finalHeight;
        }
        //修正圆角
        this.radius *= (float) finalHeight / (float) outHeight;
        Bitmap outBitmap = this.mBitmapPool.get(finalWidth, finalHeight, Bitmap.Config.ARGB_8888);
        if (outBitmap == null) {
            outBitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        //关联画笔绘制的原图bitmap
        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //计算中心位置,进行偏移
        int width = (source.getWidth() - finalWidth) / 2;
        int height = (source.getHeight() - finalHeight) / 2;
        if (width != 0 || height != 0) {
            Matrix matrix = new Matrix();
            matrix.setTranslate((float) (-width), (float) (-height));
            shader.setLocalMatrix(matrix);
        }

        paint.setShader(shader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0F, 0.0F, (float) canvas.getWidth(), (float) canvas.getHeight());
        //先绘制圆角矩形
        canvas.drawRoundRect(rectF, this.radius, this.radius, paint);
        //左上角圆角
        if (!mLeftTop) {
            canvas.drawRect(0, 0, radius, radius, paint);
            //drawLeftTopCorner(canvas, paint);
        }
        //右上角圆角
        if (!mRightTop) {
            canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
            //drawRightTopCorner(canvas, paint);
        }
        //左下角圆角
        if (!mLeftBottom) {
            canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
            //drawLeftBottomCorner(canvas, paint);
        }
        //右下角圆角
        if (!mRightBottom) {
            canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
            //drawRightBottomCorner(canvas, paint);
        }

        return BitmapResource.obtain(outBitmap, this.mBitmapPool);
    }

    /** 画左上角 */
    private void drawLeftTopCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(0, 0, radius, radius, paint);
    }

    /** 画左下角 */
    private void drawLeftBottomCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
    }

    /** 画右上角 */
    private void drawRightTopCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
    }

    /** 画右下角 */
    private void drawRightBottomCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
    }

    /** 画左侧角 */
    private void drawLeftCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(0, 0, radius, radius, paint);
        canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
    }

    /** 画右侧角 */
    private void drawRightCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
        canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
    }

    /** 画上角 */
    private void drawTopCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(0, 0, radius, radius, paint);
        canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
    }

    /** 画下角 */
    private void drawBottomCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
        canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
    }

    private void drawAllCorner(Canvas canvas, Paint paint) {
        canvas.drawRect(0, 0, radius, radius, paint);
        canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
        canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
        canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
