package com.example.yuanann.stray_cat.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.activity.MainActivity2;
import com.example.yuanann.stray_cat.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment {
    AlertDialog.Builder dialog = null;

    public BlankFragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        ListView list = (ListView) getActivity().findViewById(R.id.list);
        DatabaseHelper helper = new DatabaseHelper(getContext());
        Cursor cursor2=helper.queryApply();
        final int[] descA = new int[cursor2.getCount()];
        final int[] descB = new int[cursor2.getCount()];
        final int all=cursor2.getCount();
        int i = 0;
        while(cursor2.moveToNext()) {
            int a1= cursor2.getInt(cursor2.getColumnIndex("_id"));
            descA[i] = a1;
            int a2= cursor2.getInt(cursor2.getColumnIndex("m_vid"));
            descB[i] = a2;
            i++;
        }
//        定义SimpleCursorAdapter
        SimpleCursorAdapter simpleAdapter1=new SimpleCursorAdapter(getContext(),R.layout.back_circle,cursor2,new String[]{"_id","m_uname","m_content","m_state","m_time"},new int[]{R.id.u1,R.id.u2,R.id.u3,R.id.u4,R.id.u5});

        //设置适配器
        list.setAdapter(simpleAdapter1);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int a=descA[i];
                final int b=descB[i];
                final String x=a+"";
                dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("您确定要通过用户申请吗");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "您已通过申请",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                        dbHelper. updatuserApply(x);
                        dbHelper. delAdopt(b);
                        Intent intent = new Intent();
                        intent.putExtra("id",3);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                        intent.setClass(getContext(), MainActivity2.class);
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
        });

    }
}
