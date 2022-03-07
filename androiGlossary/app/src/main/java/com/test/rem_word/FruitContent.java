package com.test.rem_word;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FruitContent extends AppCompatActivity {
    public static final String FRUIT_NAME="fruit_name";//只有静态方法才能被其他类直接句点表示法访问
    public static final String FRUIT_IMAGE_ID="fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_content);

        Intent intent=getIntent();
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        int fruitImageId=intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView=(ImageView)findViewById(R.id.fruit_iamge_view);
        TextView fruitContenttext=(TextView)findViewById(R.id.fruit_content_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent=generateFruitContent(fruitName);
        fruitContenttext.setText(fruitContent);

        //悬浮按钮
        FloatingActionButton fab_commment=(FloatingActionButton)findViewById(R.id.comment);
        fab_commment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Snackbar.make(v,"点击评论按钮",Snackbar.LENGTH_SHORT)
                        .setAction("取消",new View.OnClickListener(){//设置动作
                            @Override
                            public void onClick(View view){
                                ToastUtil.showToast(FruitContent.this,"执行取消");
                            }
                        }).show();
            }
        });
    }

    private String generateFruitContent(String fruitName){
        StringBuilder fruitContent=new StringBuilder();
        for(int i=0;i<300;i++){
            fruitContent.append(fruitName);
            fruitContent.append(" ");
        }
        return fruitContent.toString();
    }

    //作用是屏幕旋转时不重新加载活动
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                ToastUtil.showToast(FruitContent.this,"返回标题页");
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}