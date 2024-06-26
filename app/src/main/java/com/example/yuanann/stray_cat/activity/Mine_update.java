package com.example.yuanann.stray_cat.activity;

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

public class Mine_update extends AppCompatActivity {
    Button b1;
    EditText ed1,ed2,ed3,ed4;
    String p,pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_update);

        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mine_update.this.finish();
            }
        });
        b1 = (Button) findViewById(R.id.bu1);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);

        SharedPreferences sp = Mine_update.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper. queryUser(name);
//            if(cursor.moveToFirst()) {//判断数据表里有数据
//            }
//                cursor.close();
        String psd=null;
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            psd = cursor.getString(cursor.getColumnIndex("u_psd"));
        }

        ed1.setText(psd);
        ed2.setText(psd);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p= ed1.getText().toString().trim();
                pa= ed2.getText().toString().trim();
                int l1=p.length();
                int l2=pa.length();
                SharedPreferences sp = Mine_update.this.getSharedPreferences("User", MODE_PRIVATE);
                final String name =sp.getString("name", null);
                Intent intent = getIntent();
                final int num = intent.getIntExtra("mid", 0);
                if (TextUtils.isEmpty(p)) {
                    Toast.makeText(Mine_update.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pa)) {
                    Toast.makeText(Mine_update.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if (!p.equals(pa)) {
                    Toast.makeText(Mine_update.this, "两次输入的密码不一样", Toast.LENGTH_SHORT).show();
                } else if (l1>20) {
                    Toast.makeText(Mine_update.this, "密码设置过长", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Mine_update.this, "密码修改成功", Toast.LENGTH_SHORT).show();

                    DatabaseHelper dbHelper = new DatabaseHelper(Mine_update.this);
                    dbHelper. updatuserP(name,p);

//                    Task_Insert.this.finish();
                    intent.putExtra("id",4);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                    intent.setClass(Mine_update.this, MainActivity.class);
                    startActivity(intent);

                }
            }

        });
    }
}

