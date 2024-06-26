package com.example.yuanann.stray_cat.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class Search extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    Button b1;
    EditText e1;
    String c;
    private boolean firstLanuch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView list=(ListView) findViewById(R.id.list2);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Search.this.finish();
            }
        });

        Intent intent = getIntent();
        final String n = intent.getStringExtra("name");

        DatabaseHelper helper=new DatabaseHelper(Search.this);

        Cursor cursor=helper.querynews_search(n);
//        定义SimpleCursorAdapter
        int p=0;
        final int[] desc = new int[cursor.getCount()];
        while(cursor.moveToNext()) {
            int a2 = cursor.getInt(cursor.getColumnIndex("_id"));
            desc[p] = a2;
            p++;
        }
        SimpleCursorAdapter simpleAdapter=new SimpleCursorAdapter(Search.this,R.layout.layout_info,cursor,new String[]{"c_fm","c_name","c_time"},new int[]{R.id.t1,R.id.t2,R.id.t3});

        //设置适配器
        list.setAdapter(simpleAdapter);
        //给ListView添加事件处理
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cl=desc[i];
                Intent intent=new Intent(Search.this,Mynews.class);
                intent.putExtra("id",cl);
                startActivity(intent);
            }
        });
    }
}
