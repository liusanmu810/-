package com.example.yuanann.stray_cat.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class Back_adminupdate extends AppCompatActivity {

    Button b1, b2;
    EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_adminupdate);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back_adminupdate.this.finish();
            }
        });
        b1 = (Button) findViewById(R.id.bu1);
        b2 = (Button) findViewById(R.id.bu2);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);

        SharedPreferences sp = Back_adminupdate.this.getSharedPreferences("Admin", MODE_PRIVATE);
        final String admin_id =sp.getString("adminid", null);
        final String adminid=admin_id+"";
        Intent intent = getIntent();
        //得到对应点击的num的值
        final int num = intent.getIntExtra("num", 0);
        //得到所点开的话题的id值
        final int id = intent.getIntExtra("id", 0);
        final String myid = id + "";
        Toast.makeText(getApplicationContext(), adminid+"and"+myid, Toast.LENGTH_SHORT).show();
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.queryadin_myid(myid);
//            if(cursor.moveToFirst()) {//判断数据表里有数据
//            }
//                cursor.close();
        String name = null;
        String psd = null;
        String info = null;
        String birth = null;
        String lo = null;
        String weight = null;


        while (cursor.moveToNext()) {
            //moveToNext()移动光标到下一行
            name = cursor.getString(cursor.getColumnIndex("a_name"));
            psd = cursor.getString(cursor.getColumnIndex("a_psd"));
        }
        ed1.setText(name);
        ed2.setText(psd);

        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Back_adminupdate.this, "公益组织名不可以修改", Toast.LENGTH_SHORT).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = ed1.getText().toString();
                String p = ed2.getText().toString();

                if (TextUtils.isEmpty(u)) {
                    Toast.makeText(getApplicationContext(), "请输公益组织名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    ContentValues values3 = new ContentValues();
                    values3.put("a_psd", p);

                    DatabaseHelper dbHelper = new DatabaseHelper(Back_adminupdate.this);
                    dbHelper.updatadminP1(myid, p);

                    Intent intent = new Intent();
                    intent.setClass(Back_adminupdate.this, MainActivity2.class);
                    intent.putExtra("id",2);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                    startActivity(intent);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(Back_adminupdate.this);
                Toast.makeText(Back_adminupdate.this, "删除成功", Toast.LENGTH_SHORT).show();
                dbHelper.deladmin(myid);
                Intent intent = new Intent();
                intent.setClass(Back_adminupdate.this, MainActivity2.class);
                intent.putExtra("id",2);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                startActivity(intent);
            }
        });
    }

    private String isAdmin_id(String name) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper.  queryAdmin(name);
        String psd=null;
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            psd = cursor.getString(cursor.getColumnIndex("_id"));

        }
        return psd;
    }
}
