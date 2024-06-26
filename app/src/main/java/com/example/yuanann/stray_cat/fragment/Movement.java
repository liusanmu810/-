package com.example.yuanann.stray_cat.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yuanann.stray_cat.R;
import com.example.yuanann.stray_cat.activity.Adopt_insert;
import com.example.yuanann.stray_cat.activity.Circle_Insert;

public class Movement extends Fragment {
    String info="1";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_movement, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);


        final TextView source, travel, more;
        source = (TextView) getActivity().findViewById(R.id.source);
        travel = (TextView) getActivity().findViewById(R.id.travel);
        Button bu=(Button)getActivity().findViewById(R.id.sendtask);

        Fragment one = new Mymovement();
        //实例化管理器
        FragmentManager fm = getFragmentManager();
        //定义事务
        FragmentTransaction ft = fm.beginTransaction();
        //添加fragment
        ft.add(R.id.fag2, one);
        //提交事务`
        ft.commit();
        source.setBackgroundResource(R.drawable.line_bottom1);
        travel.setBackgroundColor(Color.parseColor("#FFFFFF"));
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info="1";
                source.setBackgroundResource(R.drawable.line_bottom1);
                travel.setBackgroundColor(Color.parseColor("#FFFFFF"));
                //实例化fragment
                Fragment one = new Mymovement();
                //实例化管理器
                FragmentManager fm = getFragmentManager();
                //定义事务
                FragmentTransaction ft = fm.beginTransaction();
                //添加fragment
                ft.replace(R.id.fag2, one);
                //提交事务`
                ft.commit();

            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info="2";
                travel.setBackgroundResource(R.drawable.line_bottom1);
                source.setBackgroundColor(Color.parseColor("#FFFFFF"));
                //实例化fragment
                Fragment two = new Mymovement2();
                //实例化管理器
                FragmentManager fm = getFragmentManager();
                //定义事务
                FragmentTransaction ft = fm.beginTransaction();
                //添加fragment
                ft.replace(R.id.fag2, two);
                //提交事务`
                ft.commit();
            }
        });

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(info.equals("1")){
                    Intent intent = new Intent();
                    intent.setClass(getContext(), Circle_Insert.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.setClass(getContext(), Adopt_insert.class);
                    startActivity(intent);
                }


            }
        });



    }
}