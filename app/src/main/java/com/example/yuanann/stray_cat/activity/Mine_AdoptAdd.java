package com.example.yuanann.stray_cat.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mine_AdoptAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine__adopt_add);
        ListView list=(ListView)findViewById(R.id.list2);
        ImageView back=(ImageView)findViewById(R.id.back);

        SharedPreferences sp = Mine_AdoptAdd.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);
        DatabaseHelper helper = new DatabaseHelper(Mine_AdoptAdd.this);
        Cursor cursor=helper.queryAdoptAdd_user(name);

        String[] descA = new String[cursor.getCount()];
        String[] descB = new String[cursor.getCount()];
        String[] descC = new String[cursor.getCount()];
        Bitmap[] descD = new Bitmap[cursor.getCount()];
        final int[] descE= new int[cursor.getCount()];
        final String[] descF = new String[cursor.getCount()];
        String[] descG = new String[cursor.getCount()];
        String[] descH = new String[cursor.getCount()];
        int i = 0;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id",4);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                intent.setClass(Mine_AdoptAdd.this, MainActivity.class);
                startActivity(intent);
            }
        });

        while(cursor.moveToNext()){
            String a2 = cursor.getString(cursor.getColumnIndex("f_sendname"));
            descA[i] = a2;
            String a3 = cursor.getString(cursor.getColumnIndex("f_time"));
            descB[i] = a3;
            String a4 = cursor.getString(cursor.getColumnIndex("f_content"));
            descC[i] = a4;
            String a5 = cursor.getString(cursor.getColumnIndex("f_pic"));
            Bitmap bm = BitmapFactory.decodeFile("data//data//"+Mine_AdoptAdd.this.getPackageName()+ "//files//"+a5);
            descD[i] = bm;
            int a6 = cursor.getInt(cursor.getColumnIndex("_id"));
            descE[i] = a6;
            String a7 = cursor.getString(cursor.getColumnIndex("f_state"));
            descF[i] = a7;
            i++;
        }
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int z=0;z<descA.length;z++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("a1",descA[z]);
            item.put("a2",descB[z]);
            item.put("a3",descC[z]);
            item.put("a4",descD[z]);
            item.put("a5",descE[z]);
            item.put("a6",descF[z]);
            listItems.add(item);
        }
//        定义SimpleCursorAdapter
        SimpleAdapter simpleAdapter=new SimpleAdapter(Mine_AdoptAdd.this,listItems,R.layout.layout_movement,new String[]{"a1","a2","a3","a4"},new int[]{R.id.a2,R.id.a3,R.id.a4,R.id.a5});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {

            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if( (view instanceof ImageView) & (data instanceof Bitmap) ) {
                    ImageView iv = (ImageView) view;
                    Bitmap bm = (Bitmap) data;
                    iv.setImageBitmap(bm);
                    return true;
                }
                return false;
            }
        });
        //设置适配器
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cl=descE[i];
                Intent intent=new Intent(Mine_AdoptAdd.this, Adopt_Enter.class);
                intent.putExtra("id",cl);
                startActivity(intent);
            }
        });

    }
}
