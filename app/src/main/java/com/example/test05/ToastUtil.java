package com.example.test05;

import android.widget.Toast;


public class ToastUtil {
	public static Toast toast;//单例的吐司
	/**
	 * 能够连续弹的吐司，不用等上个吐司消失
	 * @param text
	 */
	public static void showToast(String text){
		if(toast==null){
			//创建toast
			toast = Toast.makeText(MyApplication.getApplication(), text,0);
		}else {
			//改变当前toast的文本
			toast.setText(text);
		}
		toast.show();
	}
}
