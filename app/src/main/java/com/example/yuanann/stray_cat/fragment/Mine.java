package com.example.yuanann.stray_cat.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;
import com.example.yuanann.stray_cat.activity.Login;
import com.example.yuanann.stray_cat.activity.Mine_Adopt;
import com.example.yuanann.stray_cat.activity.Mine_AdoptAdd;
import com.example.yuanann.stray_cat.activity.Mine_Circle;
import com.example.yuanann.stray_cat.activity.Mine_Donate;
import com.example.yuanann.stray_cat.activity.Mine_Foucs;
import com.example.yuanann.stray_cat.activity.Mine_Vol;
import com.example.yuanann.stray_cat.activity.Mine_update;

public class Mine extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mine, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        SharedPreferences sp =  getActivity().getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);

        ImageView t1= (ImageView)getActivity().findViewById(R.id.a1);
        ImageView s= (ImageView)getActivity().findViewById(R.id.setting);
        TextView n= (TextView)getActivity().findViewById(R.id.n);
        TextView t4= (TextView)getActivity().findViewById(R.id.zx);
        TextView h1= (TextView)getActivity().findViewById(R.id.h1);
        TextView h2= (TextView)getActivity().findViewById(R.id.h2);
        TextView h3= (TextView)getActivity().findViewById(R.id.h3);
        TextView z1= (TextView)getActivity().findViewById(R.id.z1);
        TextView z2= (TextView)getActivity().findViewById(R.id.z2);
        TextView z3= (TextView)getActivity().findViewById(R.id.z3);
        TextView a1= (TextView)getActivity().findViewById(R.id.c1);
        TextView a2= (TextView)getActivity().findViewById(R.id.c2);
        TextView a3= (TextView)getActivity().findViewById(R.id.c3);

        n.setText(name);
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        long stunum=dbHelper.allmycircle(name);
        long stunum2=dbHelper.allmyarticle(name);
        long stunum3=dbHelper.allmyapply(name);
        z1.setText(stunum+"");
        z2.setText(stunum2+"");
        z3.setText(stunum3+"");

        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Mine_AdoptAdd.class);
                startActivity(intent);
            }

        });
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Mine_Vol.class);
                startActivity(intent);
            }

        });
        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Mine_Donate.class);
                startActivity(intent);
            }

        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Mine_update.class);
                startActivity(intent);
            }

        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Login.class);
                startActivity(intent);
            }

        });
        z1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Mine_Circle.class);
                startActivity(intent);
            }

        });
        z2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Mine_Foucs.class);
                startActivity(intent);
            }

        });
        z3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Mine_Adopt.class);
                startActivity(intent);
            }

        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Mine_Circle.class);
                startActivity(intent);
            }

        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Mine_Foucs.class);
                startActivity(intent);
            }

        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Mine_Adopt.class);
                startActivity(intent);
            }

        });

}
}
