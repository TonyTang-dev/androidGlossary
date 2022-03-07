package com.test.rem_word;

import android.content.Context;
import android.widget.Toast;
/*
自定义提示消息类---静态方法
 */
public class ToastUtil {
    private static Context context=null;
    private static Toast toast=null;

    public static void showToast(Context context,String text){
        if(toast==null){
            toast=Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }
        else{
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
