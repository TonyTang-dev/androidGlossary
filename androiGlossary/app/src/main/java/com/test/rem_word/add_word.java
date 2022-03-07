package com.test.rem_word;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class add_word extends AppCompatActivity {

    public String text=null;
    //读写
    private SharedPreferences pref=null;
    private SharedPreferences.Editor editor=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        editor=pref.edit();

        //获取实例
        EditText mytext=findViewById(R.id.words);
        TextView toast=findViewById(R.id.toast);
        Button save=findViewById(R.id.save);
        String show=pref.getString("note","");
        mytext.setText(show);
        mytext.setSelection(mytext.length());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text=mytext.getText().toString();
                if(!text.equals("")&&save.getText().equals("保存")){
                    editor.putString("note",text);
                    editor.apply();
                    ToastUtil.showToast(add_word.this,"内容已保存");
                }
                if(mytext.getLineCount()>20&&save.getText().equals("保存")){
                    ToastUtil.showToast(add_word.this,"文本框内容已达最大限制");
                    save.setText("清空");
                    toast.setVisibility(View.INVISIBLE);
                }
                else if(save.getText().equals("清空")){
                    mytext.setText("");
                    toast.setVisibility(View.VISIBLE);
                    save.setText("保存");
                    editor.putString("note","");
                    editor.apply();
                }
                else if(text.equals("")&&save.getText().equals("保存")){
                    ToastUtil.showToast(add_word.this,"请输入内容再保存");
                }
            }
        });
    }
}