package com.example.yuanann.stray_cat.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

public class Contribute extends Fragment {
    String c;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contribute, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        final EditText e1 = (EditText) getActivity().findViewById(R.id.ed1);
        Button b1 = (Button) getActivity(). findViewById(R.id.bu1);

        SharedPreferences sp =  getActivity().getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", null);
        final String type = sp.getString("type", "null");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = e1.getText().toString().trim();
                int length=e1.length();
                if (TextUtils.isEmpty(c)) {
                    Toast.makeText(getContext(), "请输入捐款金额", Toast.LENGTH_SHORT).show();
                } else if (!isNumeric(c)) {
                    Toast.makeText(getContext(), "请输入有效数字", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "捐款成功，感谢你对流浪猫的爱心", Toast.LENGTH_SHORT).show();
                    DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                    ContentValues values = new ContentValues();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    values.put("d_name", name);
                    values.put("d_money",c);
                    values.put("d_time", str);
                    dbHelper.insertDonate(values);
                    e1.setText("");
                }
            }

        });
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}