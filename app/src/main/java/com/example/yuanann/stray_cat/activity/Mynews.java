package com.example.yuanann.stray_cat.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class Mynews extends AppCompatActivity {
    AlertDialog.Builder dialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynews);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mynews.this.finish();
            }
        });

        ImageView h1 = findViewById(R.id.t1);
        TextView h2 = findViewById(R.id.t2);
        TextView h3 = findViewById(R.id.t3);
        final Button g = findViewById(R.id.g);

        Intent intent = getIntent();
        final int num = intent.getIntExtra("num", -1);
        final int id = intent.getIntExtra("id", num + 1);

        SharedPreferences sp = Mynews.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name = sp.getString("name", null);
        final String type = sp.getString("type", "null");

        //获得历史数据
        DatabaseHelper helper = new DatabaseHelper(Mynews.this);
        final long cursor3=helper.allmyarticle(name,id);
            Cursor cursor = helper.queryMynews(id+"");

        if (cursor3!=0){
//            Toast.makeText(Mynews.this, "您已经关注，请不要重复关注", Toast.LENGTH_SHORT).show();
            g.setBackgroundResource(R.drawable.a_back2);
            g.setTextColor(Color.parseColor("#ffffff"));
            g.setText("已关注");
        }

            int[] descA = new int[cursor.getCount()];
            final String[] descB = new String[cursor.getCount()];
            String[] descC = new String[cursor.getCount()];

            int i = 0;
            while (cursor.moveToNext()) {
                int a1 = cursor.getInt(cursor.getColumnIndex("c_fm"));
                descA[i] = a1;
                String a2 = cursor.getString(cursor.getColumnIndex("c_name"));
                descB[i] = a2;
                String a3 = cursor.getString(cursor.getColumnIndex("c_content"));
                descC[i] = a3;
                i++;
            }
            h1.setBackgroundResource(descA[0]);
            h2.setText(descB[0]);
            h3.setText(descC[0]);

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                if (cursor3!=0){
                    dialog = new AlertDialog.Builder(Mynews.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("你确定要取消关注吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseHelper dbHelper = new DatabaseHelper(Mynews.this);
                            dbHelper.delart(name,id);
                            dialog.dismiss();
                            Mynews.this.finish();
                            Intent intent = new Intent();
                            intent.setClass(Mynews.this, Mynews.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    });
                    dialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(MainActivity2.this, "You clicked No",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                    }
                    else{
                    DatabaseHelper dbHelper = new DatabaseHelper(Mynews.this);
                    ContentValues values = new ContentValues();
                        Toast.makeText(Mynews.this, "关注成功", Toast.LENGTH_SHORT).show();
                        values.put("m_vid", id);
                        values.put("m_uname",name);
                        dbHelper.insertMyarticle(values);
                    Mynews.this.finish();
                    Intent intent = new Intent();
                    intent.setClass(Mynews.this, Mynews.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });
    }
}
