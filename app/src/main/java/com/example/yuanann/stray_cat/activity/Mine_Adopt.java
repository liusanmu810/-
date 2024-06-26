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

public class Mine_Adopt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine__adopt);

        ListView list=(ListView)findViewById(R.id.list2);
        ImageView back=(ImageView)findViewById(R.id.back);

        SharedPreferences sp = Mine_Adopt.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);
        DatabaseHelper helper = new DatabaseHelper(Mine_Adopt.this);
        Cursor cursor=helper.queryAdopt_user(name);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mine_Adopt.this.finish();
            }
        });

//        定义SimpleCursorAdapter
        SimpleCursorAdapter simpleAdapter=new SimpleCursorAdapter(Mine_Adopt.this,R.layout.layout_adopt,cursor,new String[]{"m_content","m_state"},new int[]{R.id.a1,R.id.a2});
        //设置适配器
        list.setAdapter(simpleAdapter);

    }
}



