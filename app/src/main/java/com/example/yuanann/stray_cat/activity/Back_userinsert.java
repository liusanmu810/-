package com.example.yuanann.stray_cat.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Back_userinsert extends AppCompatActivity {
    Button b1;
    EditText ed1,ed2;
    RadioGroup ed3;
    String state="可登录",s="y";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_userinsert);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back_userinsert.this.finish();
            }
        });
        b1 = (Button) findViewById(R.id.bu1);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (RadioGroup) findViewById(R.id.ed3);
        //第一种获得单选按钮值的方法
        //为radioGroup设置一个监听器:setOnCheckedChanged()
        ed3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                state="" + radbtn.getText();
                Toast.makeText(getApplicationContext(), state, Toast.LENGTH_SHORT).show();
                int a=state.length();
                if(a>=3){
                    s="y" ;
                }else {
                    s="n";
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u= ed1.getText().toString();
                String p = ed2.getText().toString();
                if (TextUtils.isEmpty(u)) {
                    Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplicationContext(), "请输入用户密码", Toast.LENGTH_SHORT).show();
                } else if (isExistN(u)) {
                    Toast.makeText(getApplicationContext(), "此用户名已经存在", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    ContentValues values3 = new ContentValues();
                    DatabaseHelper dbHelper = new DatabaseHelper(Back_userinsert.this);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    values3.put("u_name", u);
                    values3.put("u_psd", p);
                    values3.put("u_state",s);
                    values3.put("u_time", str);
                    dbHelper.insert(values3);

                    Intent intent = new Intent();
                    intent.setClass(Back_userinsert.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean isExistN(String name) {
        boolean a=true;
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor mycursor=dbHelper.queryUser(name);
        if (mycursor.moveToFirst() == false)
        {
            a=false;
        }
        return a;
    }
}
