package com.example.test05.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.test05.MyApplication;


public class CommonUtil {
	/**
	 * 在主线程执行任务
	 * @param runnable
	 */
	public static void runOnUIThread(Runnable runnable){
		MyApplication.getHandler().post(runnable);
	}
	
	/**
	 * 将child从它的父View中移除
	 * @param child
	 */
	public static void removeSelfFromParent(View child){
		if(child!=null){
			ViewParent parent = child.getParent();
			if(parent!=null && parent instanceof ViewGroup){
				ViewGroup group = (ViewGroup) parent;
				group.removeView(child);//将child从父View中移除
			}
		}
	}
	
	/**
	 * 获取字符串资源
	 * @param resId
	 * @return
	 */
	public static String getString(int resId){
		return MyApplication.getApplication().getResources().getString(resId);
	}
	public static int getColor(int resId){
		return MyApplication.getApplication().getResources().getColor(resId);
	}
	
	public static String[] getStringArray(int resId){
		return MyApplication.getApplication().getResources().getStringArray(resId);
	}
	
	public static Drawable getDrawable(int resId){
		return MyApplication.getApplication().getResources().getDrawable(resId);
	}
	/**
	 * 获取dp资源，并且获取后的值是已经将dp转化为px了
	 * @param resId
	 * @return
	 */
	public static float getDimesion(int resId){
		return MyApplication.getApplication().getResources().getDimensionPixelSize(resId);
	}
}
