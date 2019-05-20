package com.example.test05.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;

public class DrawableUtil {
    /**
     * 用代码的方式生成shape图形
     *
     * @param argb
     * @param radius
     * @return
     */
    public static GradientDrawable generateDrawable(int argb, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);//设置为矩形，默认就是矩形
        drawable.setColor(argb);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    /**
     * 用代码的方式生成状态选择器
     *
     * @param pressed
     * @param normal
     * @return
     */
    public static StateListDrawable generateSelector(Drawable pressed, Drawable normal) {
        StateListDrawable drawable = new StateListDrawable();
        if (VERSION.SDK_INT > 10) {
            drawable.setEnterFadeDuration(500);
            drawable.setExitFadeDuration(500);
        }
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);//设置按下的图片
        drawable.addState(new int[]{}, normal);//设置默认的图片
        return drawable;
    }
}
