package com.example.yuanann.stray_cat.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class Back_login extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    String n,p;
    TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置当前页面的布局初始化组件
        setContentView(R.layout.activity_back_login);
        b1 = (Button) findViewById(R.id.b1);
        e1 = (EditText) findViewById(R.id.edit1);
        e2 = (EditText) findViewById(R.id.edit2);
        user = (TextView) findViewById(R.id.user);


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Back_login.this, Login.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = e1.getText().toString();
                String p = e2.getText().toString();
                String adminid=isAdmin_id(n);
                if (TextUtils.isEmpty(n)) {
                    Toast.makeText(getApplicationContext(), "请输入管理员名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (p.equals(isExistP(n))) {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(Back_login.this, MainActivity2.class);
                    startActivity(intent);
                        SharedPreferences sp = getSharedPreferences("Admin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", n);
                        editor.putString("adminid", adminid);
                        editor.commit();
                    e1.setText("");
                    e2.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "登录失败,密码错误", Toast.LENGTH_SHORT).show();
                    e1.setText("");
                    e2.setText("");
                }
            }


        });
    }

    private String isExistP(String name) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper.  queryAdmin(name);
        String psd=null;
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            psd = cursor.getString(cursor.getColumnIndex("a_psd"));

        }
        return psd;
    }

    private String isAdmin_id(String name) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper.  queryAdmin(name);
        String id=null;
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            id = cursor.getString(cursor.getColumnIndex("_id"));

        }
        return id;
    }

}
