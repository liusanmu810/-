package com.example.yuanann.stray_cat.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class Donate_record  extends Fragment {
    String c;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_donate_record, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);


        SharedPreferences sp =  getActivity().getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);

        ListView list2=(ListView) getActivity().findViewById(R.id.list2);
        LinearLayout l1=(LinearLayout) getActivity().findViewById(R.id.a1);

        DatabaseHelper helper=new DatabaseHelper(getContext());

        Cursor cursor=helper.queryDonate();
//        定义SimpleCursorAdapter
//        if(p==0){
//            l1.setBackgroundResource(R.drawable.z5);
//        }

        int a=0;
        int i=0;

        SimpleCursorAdapter simpleAdapter=new SimpleCursorAdapter(getContext(),R.layout.layout_donate,cursor,new String[]{"d_name","d_time","d_money"},new int[]{R.id.a2,R.id.a3,R.id.a4});
        //设置适配器
        list2.setAdapter(simpleAdapter);
        //给ListView添加事件处理
    }
}
