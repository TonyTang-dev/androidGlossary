package com.test.rem_word;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class videoplay extends AppCompatActivity {
    private VideoView videoPlayer=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT>=21){
//            View decorView=getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.parseColor("#8000"));
//        }
        setContentView(R.layout.activity_videoplay);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        if(ContextCompat.checkSelfPermission(videoplay.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(videoplay.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else{
            initvideoPath();
        }
    }
    private void initvideoPath(){
        String uri="android.resource://"+getPackageName()+"/"+R.raw.movie;
        videoPlayer=(VideoView)findViewById(R.id.video_view);
        videoPlayer.setVideoURI(Uri.parse(uri));
        videoPlayer.start();
        videoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent=new Intent(videoplay.this,MainActivity.class);
                startActivity(intent);
                finish();//销毁登录页面
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initvideoPath();
                }
                else{
                    ToastUtil.showToast(videoplay.this,"您拒绝了该权限");
                    finish();
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(videoPlayer!=null){
            videoPlayer.suspend();//释放资源
        }
    }
    //作用是屏幕旋转时不重新加载活动
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
}