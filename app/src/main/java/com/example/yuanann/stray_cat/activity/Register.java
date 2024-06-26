package com.example.yuanann.stray_cat.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends AppCompatActivity {
    Button b1;
    EditText ed1, ed2, ed3;
    String name, psw, pswagain;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置当前页面的布局初始化组件
        setContentView(R.layout.activity_register);
        b1 = (Button) findViewById(R.id.b1);
        ed1 = (EditText) findViewById(R.id.edit1);
        ed2 = (EditText) findViewById(R.id.edit2);
        ed3 = (EditText) findViewById(R.id.edit3);
        login = (TextView) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Register.this, Login.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = ed1.getText().toString().trim();
                psw = ed2.getText().toString().trim();
                pswagain = ed3.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Register.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(Register.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pswagain)) {
                    Toast.makeText(Register.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if (!psw.equals(pswagain)) {
                    Toast.makeText(Register.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                } else if (isExistN(name)) {
                    Toast.makeText(Register.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Register.this.finish();
                    DatabaseHelper dbHelper = new DatabaseHelper(Register.this);
                    ContentValues values = new ContentValues();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    values.put("u_name", name);
                    values.put("u_psd", psw);
                    values.put("u_state","y");
                    values.put("u_time", str);
                    dbHelper.insert(values);

                    Intent intent = new Intent();
                    intent.setClass(Register.this,Login.class);
                    startActivity(intent);
                }
            }

        });
    }

    private boolean isExistN(String name) {
        boolean a = true;
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor mycursor = dbHelper.queryUser(name);
        if (mycursor.moveToFirst() == false) {
            a = false;
        }
        return a;
    }
}

