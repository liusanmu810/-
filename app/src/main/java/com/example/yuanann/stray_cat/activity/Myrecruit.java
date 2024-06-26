package com.example.yuanann.stray_cat.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Myrecruit extends AppCompatActivity {
    AlertDialog.Builder dialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecruit);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Myrecruit.this.finish();
            }
        });

        ImageView h1 = findViewById(R.id.h1);
        TextView h2 = findViewById(R.id.h2);
        TextView h3 = findViewById(R.id.h3);
        TextView h4 = findViewById(R.id.h4);
        TextView h5 = findViewById(R.id.h5);
        final Button cou = findViewById(R.id.cou);

        Intent intent = getIntent();
        final int num = intent.getIntExtra("num", -1);
        final int id = intent.getIntExtra("id", num + 1);

        SharedPreferences sp = Myrecruit.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name = sp.getString("name", null);
        final String type = sp.getString("type", "null");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Myrecruit.this.finish();
            }
        });

        //获得历史数据
        DatabaseHelper helper = new DatabaseHelper(Myrecruit.this);
        final long cursor3=helper.allmyvol(name,id);
        Cursor cursor = helper.queryRe(id+"");

        if (cursor3!=0){
//            Toast.makeText(Mynews.this, "您已经关注，请不要重复关注", Toast.LENGTH_SHORT).show();
            cou.setText("已加入志愿者活动");
        }

        int[] descA = new int[cursor.getCount()];
        final String[] descB = new String[cursor.getCount()];
        String[] descC = new String[cursor.getCount()];
        String[] descD = new String[cursor.getCount()];
        String[] descE = new String[cursor.getCount()];

        int i = 0;
        while (cursor.moveToNext()) {
            int a1 = cursor.getInt(cursor.getColumnIndex("c_fm"));
            descA[i] = a1;
            String a2 = cursor.getString(cursor.getColumnIndex("c_name"));
            descB[i] = a2;
            String a3 = cursor.getString(cursor.getColumnIndex("c_time"));
            descC[i] = a3;
            String a4 = cursor.getString(cursor.getColumnIndex("c_man"));
            descD[i] = a4;
            String a5 = cursor.getString(cursor.getColumnIndex("c_content"));
            descE[i] = a5;
            i++;
        }
        h1.setBackgroundResource(descA[0]);
        h2.setText(descB[0]);
        h3.setText(descC[0]);
        h4.setText(descD[0]);
        h5.setText(descE[0]);

        cou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                if (cursor3!=0){
                    Toast.makeText(Myrecruit.this, "您已经加入志愿者活动，请不要重复加入", Toast.LENGTH_SHORT).show();
                }else if (type.equals("公益组织")) {
                    Toast.makeText(Myrecruit.this, "公益组织人员不用参加志愿者活动哦", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseHelper dbHelper = new DatabaseHelper(Myrecruit.this);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    ContentValues values = new ContentValues();
                    values.put("v_rid", id);
                    values.put("v_uname",name);
                    values.put("v_rname",descB[0]);
                    values.put("v_time",str);
                    dbHelper.insertVol(values);
                    cou.setText("已加入志愿者活动");
                }
            }
        });
    }
}
