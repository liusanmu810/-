package com.example.yuanann.stray_cat.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class News extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ListView list2=(ListView)findViewById(R.id.list2);

        ImageView back=(ImageView)findViewById(R.id.back);
        Button bu=(Button)findViewById(R.id.sendtask);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News.this.finish();
            }
        });

        SharedPreferences sp = News.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);
        final String type =sp.getString("type", "null");

        DatabaseHelper helper=new DatabaseHelper(News.this);

        Cursor cursor=helper.querynews();
//        定义SimpleCursorAdapter
        int p=0;
        final int[] desc = new int[cursor.getCount()];
        while(cursor.moveToNext()) {
            int a2 = cursor.getInt(cursor.getColumnIndex("_id"));
            desc[p] = a2;
            p++;
        }
//        if(p==0){
//            list.setBackgroundResource(R.drawable.f5);
//        }
        SimpleCursorAdapter simpleAdapter=new SimpleCursorAdapter(News.this,R.layout.layout_info,cursor,new String[]{"c_fm","c_name","c_time"},new int[]{R.id.t1,R.id.t2,R.id.t3});

        list2.setAdapter(simpleAdapter);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cl=desc[i];
                Intent intent=new Intent(News.this,Mynews.class);
                intent.putExtra("id",cl);
                startActivity(intent);
            }
        });

        if(type.equals("用户")){
            bu.setVisibility(View.GONE);
        }

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(News.this,Staff_Newsinsert.class);
                startActivity(intent);
            }
        });
    }
}
