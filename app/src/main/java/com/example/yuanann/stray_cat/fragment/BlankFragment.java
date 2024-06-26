package com.example.yuanann.stray_cat.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.yuanann.stray_cat.activity.Back_userupdate;
import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;
import com.example.yuanann.stray_cat.activity.Back_userinsert;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);

    }
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        ListView list = (ListView) getActivity().findViewById(R.id.list);
        Button bu = (Button) getActivity().findViewById(R.id.bu1);
        DatabaseHelper helper = new DatabaseHelper(getContext());
        Cursor cursor2=helper.query();
        final int[] descA = new int[cursor2.getCount()];
        String[] descB = new String[cursor2.getCount()];
        final int all=cursor2.getCount();
        int i = 0;
        while(cursor2.moveToNext()) {
            int a1= cursor2.getInt(cursor2.getColumnIndex("_id"));
            descA[i] = a1;
            i++;
        }
//        定义SimpleCursorAdapter
        SimpleCursorAdapter simpleAdapter1=new SimpleCursorAdapter(getContext(),R.layout.back_user,cursor2,new String[]{"_id","u_name","u_psd","u_state","u_time"},new int[]{R.id.u1,R.id.u2,R.id.u3,R.id.u4,R.id.u5});

        //设置适配器
        list.setAdapter(simpleAdapter1);

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Back_userinsert.class);
                startActivity(intent);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int a=descA[i];
                Intent intent=new Intent(getContext(), Back_userupdate.class);
                intent.putExtra("num",i);
                intent.putExtra("id",a);
                startActivity(intent);
            }
        });
    }

    }
