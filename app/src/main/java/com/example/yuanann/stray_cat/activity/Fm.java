package com.example.yuanann.stray_cat.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.yuanann.stray_cat.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fm extends AppCompatActivity {
    AlertDialog.Builder dialog = null;
    int n2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);

        GridView list2=(GridView)findViewById(R.id.grid);
        ImageView back=(ImageView)findViewById(R.id.back);

        Intent intent = getIntent();
        final String trans = intent.getStringExtra("trans");
        final String t = intent.getStringExtra("t");
        final String c = intent.getStringExtra("c");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fm.this.finish();
            }
        });

        final int[] imageA={R.drawable.n1,R.drawable.n2,R.drawable.n3,R.drawable.n4, R.drawable.n5,R.drawable.n6,R.drawable.n7,R.drawable.n8,R.drawable.n9, R.drawable.n10,
                R.drawable.n11,R.drawable.n12,R.drawable.n13,R.drawable.n14, R.drawable.n15,R.drawable.n16,R.drawable.n17,R.drawable.n18,R.drawable.n19, R.drawable.n20,
                R.drawable.n21,R.drawable.n22,R.drawable.n23,R.drawable.n24};

        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<imageA.length;i++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("a1",imageA[i]);
            listItems.add(item);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(Fm.this,listItems,R.layout.layout_fm,new String[]{"a1"},new int[]{R.id.t1});
        //设置适配器
        list2.setAdapter(simpleAdapter);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int mynum=imageA[i];
                dialog = new AlertDialog.Builder(Fm.this);
                dialog.setTitle("提示");
                dialog.setMessage("你确定要选择这张图片吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(trans.equals("志愿者添加")){
                            Fm.this.finish();
                            Intent intent=new Intent(Fm.this, Staff_Recruitinsert.class);
                            intent.putExtra("t",t);
                            intent.putExtra("c",c);
                            intent.putExtra("head",mynum);
                            startActivity(intent);
                        }else{
                            Fm.this.finish();
                            Intent intent=new Intent(Fm.this, Staff_Newsinsert.class);
                            intent.putExtra("t",t);
                            intent.putExtra("c",c);
                            intent.putExtra("head",mynum);
                            startActivity(intent);
                        }
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
        });

    }
}
