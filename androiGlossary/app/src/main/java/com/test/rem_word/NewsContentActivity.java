package com.test.rem_word;

import androidx.appcompat.app.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NewsContentActivity extends BaseActivity {
    public static void actionStart(Context context, String newsTitle, String newsContent){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }//隐藏原标题栏
        String newsTitle=getIntent().getStringExtra("news_title");
        String newsContent=getIntent().getStringExtra("news_content");
        NewsContentFragment newsContentFragment=(NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }
}