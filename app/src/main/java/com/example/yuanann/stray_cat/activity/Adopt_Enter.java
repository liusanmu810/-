package com.example.yuanann.stray_cat.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adopt_Enter extends AppCompatActivity {
    AlertDialog.Builder dialog = null;
    String acont,aname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt__enter);
        TextView h2 = findViewById(R.id.a2);
        TextView h3 = findViewById(R.id.a3);
        TextView h4 = findViewById(R.id.a4);
        ImageView h5 = findViewById(R.id.a5);
        Button b1 = (Button) findViewById(R.id.bu1);
        Intent intent = getIntent();
        final int num = intent.getIntExtra("num", -1);
        final int id = intent.getIntExtra("id", num + 1);

        SharedPreferences sp = Adopt_Enter.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name = sp.getString("name", null);
        final String type = sp.getString("type", "null");

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adopt_Enter.this.finish();
            }
        });
        DatabaseHelper helper = new DatabaseHelper(Adopt_Enter.this);

        if(id>2){
//        Cursor cursor=helper.queryCircle_select();
            Cursor cursor=helper.queryAdopt(id);
            String[] descA = new String[cursor.getCount()];
            String[] descB = new String[cursor.getCount()];
            String[] descC = new String[cursor.getCount()];
            Bitmap[] descD = new Bitmap[cursor.getCount()];
            final int[] descE= new int[cursor.getCount()];
            final String[] descF = new String[cursor.getCount()];
            String[] descG = new String[cursor.getCount()];
            String[] descH = new String[cursor.getCount()];
//        final int all=cursor.getCount()+4;
            int i = 0;

            while(cursor.moveToNext()){
                String a2 = cursor.getString(cursor.getColumnIndex("f_sendname"));
                descA[i] = a2;
                String a3 = cursor.getString(cursor.getColumnIndex("f_time"));
                descB[i] = a3;
                String a4 = cursor.getString(cursor.getColumnIndex("f_content"));
                descC[i] = a4;
                String a5 = cursor.getString(cursor.getColumnIndex("f_pic"));
                Bitmap bm = BitmapFactory.decodeFile("data//data//"+Adopt_Enter.this.getPackageName()+ "//files//"+a5);
                descD[i] = bm;
                int a6 = cursor.getInt(cursor.getColumnIndex("_id"));
                descE[i] = a6;
                String a7 = cursor.getString(cursor.getColumnIndex("f_state"));
                descF[i] = a7;
                i++;
            }
            List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
            for(int z=0;z<descA.length;z++){
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("a1",descA[z]);
                item.put("a2",descB[z]);
                item.put("a3",descC[z]);
                item.put("a4",descD[z]);
                item.put("a5",descE[z]);
                item.put("a6",descF[z]);
                listItems.add(item);
            }
            h2.setText(descA[0]);
            h3.setText(descB[0]);
            h4.setText(descC[0]);
            h5.setImageBitmap(descD[0]);
            aname=descA[0];
            acont=descC[0];
        }else{
            Cursor cursor=helper.queryAdopt(id);
            String[] descA = new String[cursor.getCount()];
            String[] descB = new String[cursor.getCount()];
            final String[] descC = new String[cursor.getCount()];
            int[] descD = new int[cursor.getCount()];
            final int[] descE= new int[cursor.getCount()];
            final String[] descF = new String[cursor.getCount()];
            String[] descG = new String[cursor.getCount()];
            String[] descH = new String[cursor.getCount()];
//        final int all=cursor.getCount()+4;
            int i = 0;

            while(cursor.moveToNext()){
                String a2 = cursor.getString(cursor.getColumnIndex("f_sendname"));
                descA[i] = a2;
                String a3 = cursor.getString(cursor.getColumnIndex("f_time"));
                descB[i] = a3;
                String a4 = cursor.getString(cursor.getColumnIndex("f_content"));
                descC[i] = a4;
                int a5 = cursor.getInt(cursor.getColumnIndex("f_pic"));
                descD[i] = a5;
                int a6 = cursor.getInt(cursor.getColumnIndex("_id"));
                descE[i] = a6;
                String a7 = cursor.getString(cursor.getColumnIndex("f_state"));
                descF[i] = a7;
                i++;
            }
            List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
            for(int z=0;z<descA.length;z++){
                Map<String,Object> item=new HashMap<String, Object>();
                item.put("a1",descA[z]);
                item.put("a2",descB[z]);
                item.put("a3",descC[z]);
                item.put("a4",descD[z]);
                item.put("a5",descE[z]);
                item.put("a6",descF[z]);
                listItems.add(item);
            }
            h2.setText(descA[0]);
            h3.setText(descB[0]);
            h4.setText(descC[0]);
            h5.setImageResource(descD[0]);
            aname=descA[0];
            acont=descC[0];
        }

        final long cursor3=helper.allmyapply(name,id);
        if (cursor3!=0){
//            Toast.makeText(Mynews.this, "您已经关注，请不要重复关注", Toast.LENGTH_SHORT).show();
            b1.setText("已申请领养");
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursor3==0){
                    dialog = new AlertDialog.Builder(Adopt_Enter.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("你确定申请收养吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String len=""+acont.length();
                            int a=Integer.parseInt(len);
                            if(a>=8){
                                acont=acont.substring(0,8);
                            }
                            DatabaseHelper dbHelper = new DatabaseHelper(Adopt_Enter.this);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
                            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                            String str = formatter.format(curDate);
                            ContentValues values = new ContentValues();
                            values.put("m_vid",id);
                            values.put("m_vname", aname);
                            values.put("m_uname",name);
                            values.put("m_content", acont);
                            values.put("m_state","申请中");
                            values.put("m_time",str);
                            dbHelper.insertMyapply(values);
                            Adopt_Enter.this.finish();
                            Intent intent = new Intent();
                            intent.setClass(Adopt_Enter.this, Adopt_Enter.class);
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
                else {

                }
            }
        });
    }
}
