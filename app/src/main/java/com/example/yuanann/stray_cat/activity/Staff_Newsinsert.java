package com.example.yuanann.stray_cat.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Staff_Newsinsert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__newsinsert);

        Intent intent = getIntent();
        final int head = intent.getIntExtra("head",0);
        final String t1 = intent.getStringExtra("t");
        final String c1 = intent.getStringExtra("c");

        final EditText ed1=(EditText)findViewById(R.id.ed1);
        final EditText ed2=(EditText)findViewById(R.id.ed2);
        ImageView image=(ImageView)findViewById(R.id.image);
        Button b1 = (Button) findViewById(R.id.bu1);
        ImageView b2 = (ImageView) findViewById(R.id.ziliao);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Staff_Newsinsert.this.finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Staff_Newsinsert.this.finish();
                String t = ed1.getText().toString();
                String c = ed2.getText().toString();
                finish();
                Intent intent = new Intent();
                intent.setClass(Staff_Newsinsert.this, Fm.class);
                intent.putExtra("t",t);
                intent.putExtra("c",c);
                intent.putExtra("trans","内容添加");
                startActivity(intent);
            }
        });

        if (TextUtils.isEmpty(t1)&&TextUtils.isEmpty(c1)) {

        }else if(TextUtils.isEmpty(t1)){
            ed2.setText(c1);
        }else if(TextUtils.isEmpty(c1)){
            ed1.setText(t1);
        }else{
            ed1.setText(t1);
            ed2.setText(c1);
        }

        if (head==0) {

        }else{
            image.setImageResource(head);
            b2.setVisibility(View.VISIBLE );
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                String c1 = ed1.getText().toString().trim();
                String c2=ed2.getText().toString().trim();
                int img=head;
                SharedPreferences sp = Staff_Newsinsert.this.getSharedPreferences("User", MODE_PRIVATE);
                final String name =sp.getString("name", null);
                if (TextUtils.isEmpty(c1)) {
                    Toast.makeText(Staff_Newsinsert.this, "请输入活动标题", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(c2)) {
                    Toast.makeText(Staff_Newsinsert.this, "请输入活动内容", Toast.LENGTH_SHORT).show();
                }else if(img==0){
                    Toast.makeText(Staff_Newsinsert.this, "请选择一张活动封面", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Staff_Newsinsert.this, "志愿者活动发布成功", Toast.LENGTH_SHORT).show();
                    DatabaseHelper dbHelper = new DatabaseHelper(Staff_Newsinsert.this);
                    ContentValues values = new ContentValues();
                    Cursor cursor5=dbHelper.queryUser(name);;

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);

                    Random random = new Random();
                    for (int i = 0; i < 10; i++) {
                        // 生成 0-9 随机整数
                        number = random.nextInt(3000);
                    }

                    values.put("c_fm", img);
                    values.put("c_name", c1);
                    values.put("c_content",c2);
                    values.put("c_time",str);
                    dbHelper.insertArticle(values);

                    Intent intent = new Intent();
                    finish();
                    intent.setClass(Staff_Newsinsert.this,News.class);
                    startActivity(intent);

                }
            }
        });

    }
}

