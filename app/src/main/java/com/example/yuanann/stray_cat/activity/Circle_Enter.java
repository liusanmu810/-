package com.example.yuanann.stray_cat.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Circle_Enter extends AppCompatActivity {
    String c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle__enter);
        TextView h1 = findViewById(R.id.a1);
        TextView h2 = findViewById(R.id.a2);
        TextView h3 = findViewById(R.id.a3);
        TextView h4 = findViewById(R.id.a4);
        TextView h5 = findViewById(R.id.a5);
        ImageView h6 = findViewById(R.id.a6);
        TextView h7 = findViewById(R.id.a7);
        Button b1 = (Button) findViewById(R.id.bu1);
        final EditText e1 = (EditText) findViewById(R.id.ed1);
        ListView list2=(ListView)findViewById(R.id.list);
        Intent intent = getIntent();
        final int num = intent.getIntExtra("num", -1);
        final int id = intent.getIntExtra("id", num + 1);

        SharedPreferences sp = Circle_Enter.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name = sp.getString("name", null);
        final String type = sp.getString("type", "null");

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Circle_Enter.this.finish();
            }
        });
        DatabaseHelper helper = new DatabaseHelper(Circle_Enter.this);

        if(id>2){
//        Cursor cursor=helper.queryCircle_select();
            Cursor cursor=helper.queryCicle(id);
            String[] descA = new String[cursor.getCount()];
            String[] descB = new String[cursor.getCount()];
            String[] descC = new String[cursor.getCount()];
            Bitmap[] descD = new Bitmap[cursor.getCount()];
            final int[] descE= new int[cursor.getCount()];
            final String[] descF = new String[cursor.getCount()];
            String[] descG = new String[cursor.getCount()];
            String[] descH = new String[cursor.getCount()];
//        final int all=cursor.getCount()+4;
            int i = 0;

            while(cursor.moveToNext()){
                String a2 = cursor.getString(cursor.getColumnIndex("o_sendname"));
                descA[i] = a2;
                String a3 = cursor.getString(cursor.getColumnIndex("o_time"));
                descB[i] = a3;
                String a4 = cursor.getString(cursor.getColumnIndex("o_demand"));
                descC[i] = a4;
                String a5 = cursor.getString(cursor.getColumnIndex("o_pic"));
                Bitmap bm = BitmapFactory.decodeFile("data//data//"+Circle_Enter.this.getPackageName()+ "//files//"+a5);
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
            h1.setText(descA[0]);
            h2.setText(descB[0]);
            h3.setText(descF[0]);
            h4.setText(descG[0]);
            h5.setText(descH[0]);
            h6.setImageBitmap(descD[0]);
            h7.setText(descC[0]);

        }else{
            Cursor cursor=helper.queryCicle(id);
            String[] descA = new String[cursor.getCount()];
            String[] descB = new String[cursor.getCount()];
            String[] descC = new String[cursor.getCount()];
            int[] descD = new int[cursor.getCount()];
            final int[] descE= new int[cursor.getCount()];
            final String[] descF = new String[cursor.getCount()];
            String[] descG = new String[cursor.getCount()];
            String[] descH = new String[cursor.getCount()];
//        final int all=cursor.getCount()+4;
            int i = 0;

            while(cursor.moveToNext()){
                String a2 = cursor.getString(cursor.getColumnIndex("o_sendname"));
                descA[i] = a2;
                String a3 = cursor.getString(cursor.getColumnIndex("o_time"));
                descB[i] = a3;
                String a4 = cursor.getString(cursor.getColumnIndex("o_demand"));
                descC[i] = a4;
                int a5 = cursor.getInt(cursor.getColumnIndex("o_pic"));
                descD[i] = a5;
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
            h1.setText(descA[0]);
            h2.setText(descB[0]);
            h3.setText(descF[0]);
            h4.setText(descG[0]);
            h5.setText(descH[0]);
            h6.setImageResource(descD[0]);
            h7.setText(descC[0]);
        }

            Cursor cursor2=helper.querycircle_comment(id);
//        定义SimpleCursorAdapter
            SimpleCursorAdapter simpleAdapter1=new SimpleCursorAdapter(Circle_Enter.this,R.layout.layout_mycircle_comment,cursor2,new String[]{"oc_uname","oc_time","oc_content"},new int[]{R.id.a2,R.id.a3,R.id.a4});

            int [] a1={R.drawable.a1,R.drawable.a1,R.drawable.a1,R.drawable.a1};
            //设置适配器
            list2.setAdapter(simpleAdapter1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id",2);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                intent.setClass(Circle_Enter.this, MainActivity.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = e1.getText().toString().trim();
                int length=e1.length();
                if (TextUtils.isEmpty(c)) {
                    Toast.makeText(Circle_Enter.this, "请输入评论", Toast.LENGTH_SHORT).show();
                } else if (length>2000) {
                    Toast.makeText(Circle_Enter.this, "评论文字过长", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Circle_Enter.this, "评论发布成功", Toast.LENGTH_SHORT).show();
                    DatabaseHelper dbHelper = new DatabaseHelper(Circle_Enter.this);
                    ContentValues values = new ContentValues();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    values.put("oc_oid",id);
                    values.put("oc_uname", name);
                    values.put("oc_content",c);
                    values.put("oc_time", str);
                    dbHelper.insertCircle_comment(values);
                    finish();
                    Intent intent = new Intent();
                    intent.setClass(Circle_Enter.this,Circle_Enter.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }

        });
    }
    public static void restartActivity(Circle_Enter t){
        Intent intent = new Intent();
        intent.setClass(t, t.getClass());
        t.startActivity(intent);
        t.overridePendingTransition(0,0);
        t.finish();
    }

    }
