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

public class Mine_Circle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine__circle);

        ListView list=(ListView)findViewById(R.id.list2);
        ImageView back=(ImageView)findViewById(R.id.back);

        SharedPreferences sp = Mine_Circle.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);
        DatabaseHelper helper = new DatabaseHelper(Mine_Circle.this);
        Cursor cursor=helper.queryCircle_user(name);

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
                Mine_Circle.this.finish();
            }
        });

        while(cursor.moveToNext()){
            String a2 = cursor.getString(cursor.getColumnIndex("o_sendname"));
            descA[i] = a2;
            String a3 = cursor.getString(cursor.getColumnIndex("o_time"));
            descB[i] = a3;
            String a4 = cursor.getString(cursor.getColumnIndex("o_demand"));
            descC[i] = a4;
            String a5 = cursor.getString(cursor.getColumnIndex("o_pic"));
            Bitmap bm = BitmapFactory.decodeFile("data//data//"+Mine_Circle.this.getPackageName()+ "//files//"+a5);
            descD[i] = bm;
            int a6 = cursor.getInt(cursor.getColumnIndex("_id"));
            descE[i] = a6;
            String a7 = cursor.getString(cursor.getColumnIndex("o_place"));
            descF[i] = a7;
            String a8 = cursor.getString(cursor.getColumnIndex("o_type"));
            descG[i] = a8;
            String a9 = cursor.getString(cursor.getColumnIndex("o_content"));
            descH[i] = a9;
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
            item.put("a7",descG[z]);
            item.put("a8",descH[z]);
            listItems.add(item);
        }
//        定义SimpleCursorAdapter
        SimpleAdapter simpleAdapter=new SimpleAdapter(Mine_Circle.this,listItems,R.layout.layout_movement2,new String[]{"a1","a2","a3","a6","a7","a8","a4"},new int[]{R.id.a2,R.id.a3,R.id.a4,R.id.a5,R.id.a6,R.id.a7,R.id.a8});
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
                Intent intent=new Intent(Mine_Circle.this, Circle_Enter.class);
                intent.putExtra("id",cl);
                startActivity(intent);
            }
        });

    }
}
