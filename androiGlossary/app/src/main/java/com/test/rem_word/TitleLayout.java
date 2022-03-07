package com.test.rem_word;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitleLayout extends LinearLayout{
    public TitleLayout(final Context context,AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);

        Button buttonback=(Button)findViewById(R.id.title_back);
        buttonback.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                ((Activity)getContext()).finish();
            }
        });
        Button buttonexit=(Button)findViewById(R.id.title_exit);
        buttonexit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent("com.test.fragmentbestpractice.FORCE_OFFLINE");
                context.sendBroadcast(intent);  //发送这个广播
            }
        });
    }
}
