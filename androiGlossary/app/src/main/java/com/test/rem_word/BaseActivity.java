package com.test.rem_word;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.test.fragmentbestpractice.FORCE_OFFLINE");
        receiver=new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;//置空
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("通知");
            builder.setMessage("确定要重启程序吗？");
            builder.setCancelable(true);
            builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
//                    ToastUtil.showToast(context,"点击返回键可取消");
                }
            });
            builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    ActivityCollector.finishAll();//销毁所有活动
                    Intent i=new Intent(context,MainActivity.class);
                    context.startActivity(i);    //其中的context是所有的上下文环境
                }
            });
            builder.show();
        }
    }
}
