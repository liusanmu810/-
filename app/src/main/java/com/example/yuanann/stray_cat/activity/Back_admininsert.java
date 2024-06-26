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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Back_admininsert extends AppCompatActivity {

    Button b1;
    EditText ed1,ed2,ed4_1,ed4_2,ed4_3,ed5,ed6,ed7;
    RadioGroup ed3,ed8;
    String state="可登录",sex="男",s="y";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_admininsert);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back_admininsert.this.finish();
            }
        });
        b1 = (Button) findViewById(R.id.bu1);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u= ed1.getText().toString();
                String p = ed2.getText().toString();

                if (TextUtils.isEmpty(u)) {
                    Toast.makeText(getApplicationContext(), "请输公益组织名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                }
                else if (isExistN(u)) {
                    Toast.makeText(getApplicationContext(), "此名称已经存在", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    ContentValues values3 = new ContentValues();
                    DatabaseHelper dbHelper = new DatabaseHelper(Back_admininsert.this);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    values3.put("a_name", u);
                    values3.put("a_psd", p);
                    values3.put("a_time", str);
                    dbHelper.insertStaff(values3);

                    Intent intent = new Intent();
                    intent.setClass(Back_admininsert.this, MainActivity2.class);
                    intent.putExtra("id",2);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                    startActivity(intent);
                }
            }
        });
    }
    private boolean isExistN(String name) {
        boolean a=true;
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor mycursor=dbHelper.queryStaff(name);
        if (mycursor.moveToFirst() == false)
        {
            a=false;
        }
        return a;
    }
}
