package com.example.yuanann.stray_cat.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mine_Vol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine__vol);

        GridView grid=(GridView)findViewById(R.id.grid);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Mine_Vol.this.finish();
            }
        });

        SharedPreferences sp =Mine_Vol.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);

        DatabaseHelper helper=new DatabaseHelper(Mine_Vol.this);

        Cursor cursor=helper.queryVol(name);
//        定义SimpleCursorAdapter
        int p = 0;
        final int[] desc = new int[cursor.getCount()];
        while(cursor.moveToNext()) {
            int a2 = cursor.getInt(cursor.getColumnIndex("v_rid"));
            desc[p] = a2;
            p++;
        }

        int a=0;
        int i=0;

        String[] descA = new String[desc.length];
        String[] descB = new String[desc.length];
        final int[] descC= new int[desc.length];
        for(a=0;a<cursor.getCount();a++) {
            Cursor cursor2 = helper.queryVol_id(desc[a]);
            int n = cursor2.getCount();
            while (cursor2.moveToNext()) {
                String a1 = cursor2.getString(cursor2.getColumnIndex("c_fm"));
                descA[i] = a1;
                String a2 = cursor2.getString(cursor2.getColumnIndex("c_name"));
                descB[i] = a2;
                int a3 = cursor2.getInt(cursor2.getColumnIndex("_id"));
                descC[i] = a3;
                i++;
            }
        }

        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int z=0;z<descA.length;z++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("a1",descA[z]);
            item.put("a2",descB[z]);
            item.put("a3",descC[z]);
            listItems.add(item);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(Mine_Vol.this,listItems,R.layout.layout_foucs,new String[]{"a1","a2"},new int[]{R.id.tv_fm,R.id.tv_title});

        //设置适配器
        grid.setAdapter(simpleAdapter);
        //给ListView添加事件处理
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cl=descC[i];
                Intent intent=new Intent(Mine_Vol.this, Myrecruit.class);
                intent.putExtra("id",cl);
                startActivity(intent);
            }
        });
    }
}
