package com.example.yuanann.stray_cat.activity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class Mine_Donate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine__donate);

        SharedPreferences sp = Mine_Donate.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);

        ListView list2=(ListView) findViewById(R.id.list2);
        ImageView back=(ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mine_Donate.this.finish();
            }
        });

        DatabaseHelper helper=new DatabaseHelper(Mine_Donate.this);

        Cursor cursor=helper.queryDonatet_user(name);
//        定义SimpleCursorAdapter
//        if(p==0){
//            l1.setBackgroundResource(R.drawable.z5);
//        }

        int a=0;
        int i=0;

        SimpleCursorAdapter simpleAdapter=new SimpleCursorAdapter(Mine_Donate.this,R.layout.layout_donate,cursor,new String[]{"d_name","d_time","d_money"},new int[]{R.id.a2,R.id.a3,R.id.a4});
        //设置适配器
        list2.setAdapter(simpleAdapter);
        //给ListView添加事件处理
    }
    }
