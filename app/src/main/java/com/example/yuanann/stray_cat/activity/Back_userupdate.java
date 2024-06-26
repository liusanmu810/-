package com.example.yuanann.stray_cat.activity;

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

public class Back_userupdate extends AppCompatActivity {

    Button b1,b2;
    EditText ed1,ed2;
    RadioGroup ed3;
    String state="可登录";
    String sex="男";
    String s="y";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_userupdate);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back_userupdate.this.finish();
            }
        });
        b1 = (Button) findViewById(R.id.bu1);
        b2 = (Button) findViewById(R.id.bu2);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (RadioGroup) findViewById(R.id.ed3);

        Intent intent = getIntent();
        //得到对应点击的num的值
        final int num = intent.getIntExtra("num", 0);
        //得到所点开的话题的id值
        final int id= intent.getIntExtra("id", 0);
        final String myid=id+"";
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper. queryuser_myid(myid);
//            if(cursor.moveToFirst()) {//判断数据表里有数据
//            }
//                cursor.close();
        String name=null;
        String psd=null;

        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            name = cursor.getString(cursor.getColumnIndex("u_name"));
            psd = cursor.getString(cursor.getColumnIndex("u_psd"));
            state = cursor.getString(cursor.getColumnIndex("u_state"));
        }
        ed1.setText(name);
        ed2.setText(psd);
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
                } else if (TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplicationContext(), "请输入用户密码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();

                    DatabaseHelper dbHelper = new DatabaseHelper(Back_userupdate.this);
                    dbHelper. updatuserP(myid,p,s);
                    Intent intent = new Intent();
                    intent.setClass(Back_userupdate.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });

        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Back_userupdate.this, "用户名不可以修改", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(Back_userupdate.this);
                    Toast.makeText(Back_userupdate.this, "删除成功", Toast.LENGTH_SHORT).show();
                    dbHelper.deluser(myid);
                Intent intent = new Intent();
                intent.setClass(Back_userupdate.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
