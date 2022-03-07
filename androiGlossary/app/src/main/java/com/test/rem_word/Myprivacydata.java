package com.test.rem_word;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

public class Myprivacydata extends AppCompatActivity {

    //读写
    private SharedPreferences pref=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprivacydata);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        ImageView myp=findViewById(R.id.myheadphoto);
        TextView text=findViewById(R.id.mydata);
        String name1=pref.getString("name","单词菌");
        text.setText("我的昵称："+name1);

        String urlpic=pref.getString("picture",null);
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
            Glide.with(Myprivacydata.this).load(urlpic).into(myp);
        }
        else{
            ToastUtil.showToast(Myprivacydata.this,"加载头像失败");
        }
    }
}