package com.example.yuanann.stray_cat.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.yuanann.stray_cat.fragment.Mine;
import com.example.yuanann.stray_cat.fragment.Mine_welfare;
import com.example.yuanann.stray_cat.fragment.Movement;
import com.example.yuanann.stray_cat.R;
import com.example.yuanann.stray_cat.fragment.Contribute;
import com.example.yuanann.stray_cat.fragment.Donate_record;
import com.example.yuanann.stray_cat.fragment.Home;

public class MainActivity extends AppCompatActivity {
    Button home, recommend, course, circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        home = (Button) findViewById(R.id.home);
        recommend = (Button) findViewById(R.id.recommend);
        course = (Button) findViewById(R.id.course);
        circle = (Button) findViewById(R.id.circle);

        SharedPreferences sp = MainActivity.this.getSharedPreferences("User", MODE_PRIVATE);
        final String name =sp.getString("name", "Tom");
        final String type =sp.getString("type", "公益组织");

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);

        if (id == 1) {
            Fragment one = new Home();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.add(R.id.fag1, one);
            //提交事务
            ft.commit();
            Drawable drawable = getResources().getDrawable(R.drawable.e1);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            home.setCompoundDrawables(null, drawable, null, null);

            Drawable fi = getResources().getDrawable(R.drawable.e4);
            fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            recommend.setCompoundDrawables(null, fi, null, null);

            Drawable co = getResources().getDrawable(R.drawable.e6);
            co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            course.setCompoundDrawables(null, co, null, null);

            Drawable ci = getResources().getDrawable(R.drawable.e8);
            ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            circle.setCompoundDrawables(null, ci, null, null);

            home.setTextColor(Color.parseColor("#ff92a6"));
            recommend.setTextColor(Color.parseColor("#8a8a8a"));
            course.setTextColor(Color.parseColor("#8a8a8a"));
            circle.setTextColor(Color.parseColor("#8a8a8a"));

        } else if (id == 2) {
            Fragment two = new Movement();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.replace(R.id.fag1, two);
            //提交事务
            ft.commit();
            Drawable drawable = getResources().getDrawable(R.drawable.e2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            home.setCompoundDrawables(null, drawable, null, null);

            Drawable fi = getResources().getDrawable(R.drawable.e3);
            fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            recommend.setCompoundDrawables(null, fi, null, null);

            Drawable co = getResources().getDrawable(R.drawable.e6);
            co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            course.setCompoundDrawables(null, co, null, null);

            Drawable ci = getResources().getDrawable(R.drawable.e8);
            ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            circle.setCompoundDrawables(null, ci, null, null);

            home.setTextColor(Color.parseColor("#8a8a8a"));
            recommend.setTextColor(Color.parseColor("#ff92a6"));
            course.setTextColor(Color.parseColor("#8a8a8a"));
            circle.setTextColor(Color.parseColor("#8a8a8a"));
        } else if (id == 3) {
            Fragment two = new Contribute();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.replace(R.id.fag1, two);
            //提交事务
            ft.commit();
            Drawable drawable = getResources().getDrawable(R.drawable.e2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            home.setCompoundDrawables(null, drawable, null, null);

            Drawable fi = getResources().getDrawable(R.drawable.e4);
            fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            recommend.setCompoundDrawables(null, fi, null, null);

            Drawable co = getResources().getDrawable(R.drawable.e5);
            co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            course.setCompoundDrawables(null, co, null, null);

            Drawable ci = getResources().getDrawable(R.drawable.e8);
            ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            circle.setCompoundDrawables(null, ci, null, null);

            home.setTextColor(Color.parseColor("#8a8a8a"));
            recommend.setTextColor(Color.parseColor("#8a8a8a"));
            course.setTextColor(Color.parseColor("#ff92a6"));
            circle.setTextColor(Color.parseColor("#8a8a8a"));
        } else if (id == 4) {
            if (type.equals("公益组织")) {
                Fragment two = new Mine_welfare();
                //实例化管理器
                FragmentManager fm = getSupportFragmentManager();
                //定义事务
                FragmentTransaction ft = fm.beginTransaction();
                //将当前的fragment替换为第二个
                ft.replace(R.id.fag1, two);
                //提交事务
                ft.commit();
            } else {
                Fragment two = new Mine();
                //实例化管理器
                FragmentManager fm = getSupportFragmentManager();
                //定义事务
                FragmentTransaction ft = fm.beginTransaction();
                //将当前的fragment替换为第二个
                ft.replace(R.id.fag1, two);
                //提交事务
                ft.commit();
            }
            Drawable drawable = getResources().getDrawable(R.drawable.e2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            home.setCompoundDrawables(null, drawable, null, null);

            Drawable fi = getResources().getDrawable(R.drawable.e4);
            fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            recommend.setCompoundDrawables(null, fi, null, null);

            Drawable co = getResources().getDrawable(R.drawable.e6);
            co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            course.setCompoundDrawables(null, co, null, null);

            Drawable ci = getResources().getDrawable(R.drawable.e7);
            ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            circle.setCompoundDrawables(null, ci, null, null);

            home.setTextColor(Color.parseColor("#8a8a8a"));
            recommend.setTextColor(Color.parseColor("#8a8a8a"));
            course.setTextColor(Color.parseColor("#8a8a8a"));
            circle.setTextColor(Color.parseColor("#ff92a6"));
        }

        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment two = new Movement();
                //实例化管理器
                FragmentManager fm = getSupportFragmentManager();
                //定义事务
                FragmentTransaction ft = fm.beginTransaction();
                //将当前的fragment替换为第二个
                ft.replace(R.id.fag1, two);
                //提交事务
                ft.commit();
                Drawable drawable = getResources().getDrawable(R.drawable.e2);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                home.setCompoundDrawables(null, drawable, null, null);

                Drawable fi = getResources().getDrawable(R.drawable.e3);
                fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                recommend.setCompoundDrawables(null, fi, null, null);

                Drawable co = getResources().getDrawable(R.drawable.e6);
                co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                course.setCompoundDrawables(null, co, null, null);

                Drawable ci = getResources().getDrawable(R.drawable.e8);
                ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                circle.setCompoundDrawables(null, ci, null, null);

                home.setTextColor(Color.parseColor("#8a8a8a"));
                recommend.setTextColor(Color.parseColor("#ff92a6"));
                course.setTextColor(Color.parseColor("#8a8a8a"));
                circle.setTextColor(Color.parseColor("#8a8a8a"));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment one = new Home();
                //实例化管理器
                FragmentManager fm = getSupportFragmentManager();
                //定义事务
                FragmentTransaction ft = fm.beginTransaction();
                //将当前的fragment替换为第二个
                ft.replace(R.id.fag1, one);
                //提交事务
                ft.commit();
                Drawable drawable = getResources().getDrawable(R.drawable.e1);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                home.setCompoundDrawables(null, drawable, null, null);

                Drawable fi = getResources().getDrawable(R.drawable.e4);
                fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                recommend.setCompoundDrawables(null, fi, null, null);

                Drawable co = getResources().getDrawable(R.drawable.e6);
                co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                course.setCompoundDrawables(null, co, null, null);

                Drawable ci = getResources().getDrawable(R.drawable.e8);
                ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                circle.setCompoundDrawables(null, ci, null, null);

                home.setTextColor(Color.parseColor("#ff92a6"));
                recommend.setTextColor(Color.parseColor("#8a8a8a"));
                course.setTextColor(Color.parseColor("#8a8a8a"));
                circle.setTextColor(Color.parseColor("#8a8a8a"));

            }
        });
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("公益组织")) {
                    Fragment two = new Donate_record();
                    //实例化管理器
                    FragmentManager fm = getSupportFragmentManager();
                    //定义事务
                    FragmentTransaction ft = fm.beginTransaction();
                    //将当前的fragment替换为第二个
                    ft.replace(R.id.fag1, two);
                    //提交事务
                    ft.commit();
                } else {
                    Fragment two = new Contribute();
                    //实例化管理器
                    FragmentManager fm = getSupportFragmentManager();
                    //定义事务
                    FragmentTransaction ft = fm.beginTransaction();
                    //将当前的fragment替换为第二个
                    ft.replace(R.id.fag1, two);
                    //提交事务
                    ft.commit();
                }


                Drawable drawable = getResources().getDrawable(R.drawable.e2);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                home.setCompoundDrawables(null, drawable, null, null);

                Drawable fi = getResources().getDrawable(R.drawable.e4);
                fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                recommend.setCompoundDrawables(null, fi, null, null);

                Drawable co = getResources().getDrawable(R.drawable.e5);
                co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                course.setCompoundDrawables(null, co, null, null);

                Drawable ci = getResources().getDrawable(R.drawable.e8);
                ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                circle.setCompoundDrawables(null, ci, null, null);


                home.setTextColor(Color.parseColor("#8a8a8a"));
                recommend.setTextColor(Color.parseColor("#8a8a8a"));
                course.setTextColor(Color.parseColor("#ff92a6"));
                circle.setTextColor(Color.parseColor("#8a8a8a"));

            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (type.equals("公益组织")) {
//                    Fragment two = new Mine_welfare();
//                    //实例化管理器
//                    FragmentManager fm = getSupportFragmentManager();
//                    //定义事务
//                    FragmentTransaction ft = fm.beginTransaction();
//                    //将当前的fragment替换为第二个
//                    ft.replace(R.id.fag1, two);
//                    //提交事务
//                    ft.commit();
//                } else {
                    Fragment two = new Mine();
                    //实例化管理器
                    FragmentManager fm = getSupportFragmentManager();
                    //定义事务
                    FragmentTransaction ft = fm.beginTransaction();
                    //将当前的fragment替换为第二个
                    ft.replace(R.id.fag1, two);
                    //提交事务
                    ft.commit();
//                }

                Drawable drawable = getResources().getDrawable(R.drawable.e2);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                home.setCompoundDrawables(null, drawable, null, null);

                Drawable fi = getResources().getDrawable(R.drawable.e4);
                fi.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                recommend.setCompoundDrawables(null, fi, null, null);

                Drawable co = getResources().getDrawable(R.drawable.e6);
                co.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                course.setCompoundDrawables(null, co, null, null);

                Drawable ci = getResources().getDrawable(R.drawable.e7);
                ci.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                circle.setCompoundDrawables(null, ci, null, null);

                home.setTextColor(Color.parseColor("#8a8a8a"));
                recommend.setTextColor(Color.parseColor("#8a8a8a"));
                course.setTextColor(Color.parseColor("#8a8a8a"));
                circle.setTextColor(Color.parseColor("#ff92a6"));
            }
        });
    }
}