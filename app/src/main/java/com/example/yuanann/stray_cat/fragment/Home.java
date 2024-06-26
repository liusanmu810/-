package com.example.yuanann.stray_cat.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.activity.MainActivity;
import com.example.yuanann.stray_cat.activity.Mine_Adopt;
import com.example.yuanann.stray_cat.activity.Mine_Donate;
import com.example.yuanann.stray_cat.activity.Mine_Vol;
import com.example.yuanann.stray_cat.activity.Mynews;
import com.example.yuanann.stray_cat.activity.Myrecruit;
import com.example.yuanann.stray_cat.activity.News;
import com.example.yuanann.stray_cat.R;
import com.example.yuanann.stray_cat.activity.Recruit;
import com.example.yuanann.stray_cat.activity.Search;

public class Home extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        ListView list2=(ListView)getActivity().findViewById(R.id.list2);
        TextView more1= (TextView)getActivity().findViewById(R.id.more1);
        TextView more2= (TextView)getActivity().findViewById(R.id.more2);
        LinearLayout t1= (LinearLayout)getActivity().findViewById(R.id.t1);
        TextView ta1= (TextView)getActivity().findViewById(R.id.ta1);
        LinearLayout t2= (LinearLayout)getActivity().findViewById(R.id.t2);
        TextView ta2= (TextView)getActivity().findViewById(R.id.ta2);
        LinearLayout t3= (LinearLayout)getActivity().findViewById(R.id.t3);
        TextView ta3= (TextView)getActivity().findViewById(R.id.ta3);
        TextView x1= (TextView)getActivity().findViewById(R.id.x1);
        TextView x2= (TextView)getActivity().findViewById(R.id.x2);
        TextView x3= (TextView)getActivity().findViewById(R.id.x3);
        TextView x4= (TextView)getActivity().findViewById(R.id.x4);

        final EditText ed= getActivity().findViewById(R.id.searchedit);
        Button bu= getActivity().findViewById(R.id.searchbutton);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = ed.getText().toString();
                Intent intent = new Intent();
                intent.setClass(getContext(), Search.class);
                intent.putExtra("name",p);
                startActivity(intent);
            }
        });

        Fragment banner = new A1();
        //实例化管理器
        FragmentManager fragmentManager1 = getFragmentManager();
        //定义事务
        FragmentTransaction fta1 = fragmentManager1.beginTransaction();
        //添加fragment
        fta1.add(R.id.fag4, banner);
        //提交事务`
        fta1.commit();

        DatabaseHelper helper=new DatabaseHelper(getContext());
        //获得历史数据

        Cursor cursor2 = helper.querynews();

        int[] descA = new int[cursor2.getCount()];
        final String[] descB = new String[cursor2.getCount()];
        final int[] descC = new int[cursor2.getCount()];

        int i = 0;
        while (cursor2.moveToNext()) {
            int a1 = cursor2.getInt(cursor2.getColumnIndex("c_fm"));
            descA[i] = a1;
            String a2 = cursor2.getString(cursor2.getColumnIndex("c_name"));
            descB[i] = a2;
            int a3 = cursor2.getInt(cursor2.getColumnIndex("_id"));
            descC[i] = a3;
            i++;
        }
        t1.setBackgroundResource(descA[0]);
        ta1.setText(descB[0]);
        t2.setBackgroundResource(descA[1]);
        ta2.setText(descB[1]);
        t3.setBackgroundResource(descA[2]);
        ta3.setText(descB[2]);

        Cursor cursor=helper.queryRe();

        SimpleCursorAdapter simpleAdapter=new SimpleCursorAdapter(getContext(),R.layout.layout_requir1,cursor,new String[]{"c_fm","c_name"},new int[]{R.id.t1,R.id.t2});
        list2.setAdapter(simpleAdapter);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(), Myrecruit.class);
                intent.putExtra("num",i);
                startActivity(intent);
            }
        });

        x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Mine_Adopt.class);
                startActivity(intent);
            }
        });

        x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id",2);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                intent.setClass(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Mine_Vol.class);
                startActivity(intent);
            }
        });

        x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Mine_Donate.class);
                startActivity(intent);
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Mynews.class);
                intent.putExtra("id",descC[0]);
                startActivity(intent);
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(),Mynews.class);
                intent.putExtra("id",descC[1]);
                startActivity(intent);
            }
        });


        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(),Mynews.class);
                intent.putExtra("id",descC[2]);
                startActivity(intent);
            }
        });


        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), News.class);
                startActivity(intent);
            }
        });

        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Recruit.class);
                startActivity(intent);
            }
        });

    }
}