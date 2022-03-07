package com.test.rem_word;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.bumptech.glide.Glide;

import java.io.File;

public class LoginActivity extends BaseActivity {
    private EditText username;
    private Button login;

    //记住密码参数
    //sharedpreferences
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox playornot;

    //昵称
    String name=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT>=21){
//            View decorView=getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            getWindow().setStatusBarColor(Color.parseColor("#8000"));
//        }
        setContentView(R.layout.activity_login);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        username=(EditText)findViewById(R.id.usernamex);
        login=(Button)findViewById(R.id.login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        playornot=(CheckBox)findViewById(R.id.playornot);
        editor=pref.edit();//获取editor实例

        //判断是否需要输入昵称
        if(pref.getString("name",null)!=null){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();//销毁登录页面
        }

        ImageView headp=findViewById(R.id.headp);
        TextView usernamet=findViewById(R.id.usernamet);
        pref=PreferenceManager.getDefaultSharedPreferences(this);
        String urlpic=pref.getString("picture",null);
        String usernametget=pref.getString("name",null);
        File pic=null;
        if(urlpic==null){
            pic=new File("");
        }
        else{
            pic=new File(urlpic);
        }
        if(!pic.exists()){

        }
        if(urlpic!=null&&pic.exists()){
            Glide.with(LoginActivity.this).load(urlpic).into(headp);
        }
        if(usernametget!=null){
            usernamet.setText(usernametget);
        }
        else{
            //ToastUtil.showToast(LoginActivity.this,"加载头像失败");
        }
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username1=null;
                if(username.getText().toString().equals("")){
                    username1="词汇菌";
                }
                else{
                    username1=username.getText().toString();
                }
                editor.putString("name",username1);
                editor.apply();
                if(playornot.isChecked()){
                    Intent intent=new Intent(LoginActivity.this,videoplay.class);
                    startActivity(intent);
                    finish();//销毁登录页面
                }
                else{
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();//销毁登录页面
                }
            }
        });

        //为登录界面的按钮添加点击事件
        TextView loginway=(TextView)findViewById(R.id.loginway);
        loginway.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ToastUtil.showToast(LoginActivity.this,"暂未写入该模块");
            }
        });
        TextView forgetpass=(TextView)findViewById(R.id.forgetpass);
        forgetpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ToastUtil.showToast(LoginActivity.this,"暂未写入该模块");
            }
        });
        TextView newuser=(TextView)findViewById(R.id.newuser);
        newuser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ToastUtil.showToast(LoginActivity.this,"暂未写入该模块");
            }
        });

        View v=findViewById(R.id.panel);
        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                HideSoftInput(v.getWindowToken());
            }
        });
    }
    /*
    隐藏软键盘
     */
    private void HideSoftInput(IBinder token){
        if(token!=null){
            InputMethodManager manager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //如果中途直接从登陆界面退出
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}