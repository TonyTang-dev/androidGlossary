package com.test.rem_word;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Mywordbase extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywordbase);
        TextView mydata=findViewById(R.id.myenglish);
        mydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(Mywordbase.this,"您正在使用此词库，可返回首页查看");
            }
        });
    }
}