package com.test.rem_word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cardview extends AppCompatActivity {

    private Fruit[] fruits={new Fruit("苹果",R.drawable.apple),new Fruit("梨子",R.drawable.pear),
            new Fruit("草莓",R.drawable.strawberray)};
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter adapter;

    //下拉刷新
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        //toolbar
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //悬浮按钮
        FloatingActionButton fab2=(FloatingActionButton)findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Snackbar.make(v,"点击悬浮按钮",Snackbar.LENGTH_SHORT)
                        .setAction("取消",new View.OnClickListener(){//设置动作
                            @Override
                            public void onClick(View view){
                                ToastUtil.showToast(cardview.this,"执行取消");
                            }
                        }).show();
            }
        });

        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_viewcard);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        //下拉刷新
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                refreshFruits();
            }
        }));
    }

    //作用是屏幕旋转时不重新加载活动
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    private void refreshFruits(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    Thread.sleep(2000);
                }
                catch(InterruptedException e){
                    ToastUtil.showToast(cardview.this,"刷新中断");
                }
                //处理主面板UI
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);//隐藏进度条
                    }
                });
            }
        }).start();//启动线程
    }

    private void initFruits(){
        fruitList.clear();
        for(int i=0;i<80;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    //菜单栏菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_back,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.back_main:
                ToastUtil.showToast(cardview.this,"返回首页");
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}