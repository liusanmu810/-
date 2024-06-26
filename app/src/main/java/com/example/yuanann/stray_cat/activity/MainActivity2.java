package com.example.yuanann.stray_cat.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yuanann.stray_cat.R;
import com.example.yuanann.stray_cat.fragment.BlankFragment;
import com.example.yuanann.stray_cat.fragment.BlankFragment2;
import com.example.yuanann.stray_cat.fragment.BlankFragment3;
import com.example.yuanann.stray_cat.fragment.BlankFragment4;
import com.example.yuanann.stray_cat.fragment.BlankFragment5;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AlertDialog.Builder dialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int myid = intent.getIntExtra("id", 1);

        if (myid == 1) {
            Fragment one = new BlankFragment();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.add(R.id.fag1, one);
            //提交事务
            ft.commit();
        }else if(myid == 2)
        {
            Fragment one = new BlankFragment2();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.add(R.id.fag1, one);
            //提交事务
            ft.commit();
        }else if(myid == 3)
        {
            Fragment one = new BlankFragment3();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.add(R.id.fag1, one);
            //提交事务
            ft.commit();
        }else {
            Fragment one = new BlankFragment4();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.add(R.id.fag1, one);
            //提交事务
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Fragment two = new BlankFragment();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.replace(R.id.fag1, two);
            //提交事务
            ft.commit();
        } else if (id == R.id.nav_gallery) {
            Fragment two = new BlankFragment2();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.replace(R.id.fag1, two);
            //提交事务
            ft.commit();

        } else if (id == R.id.nav_slideshow) {
            Fragment two = new BlankFragment3();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.replace(R.id.fag1, two);
            //提交事务
            ft.commit();

        } else if (id == R.id.nav_manage) {
            Fragment two = new BlankFragment4();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.replace(R.id.fag1, two);
            //提交事务
            ft.commit();

        } else if (id == R.id.nav_share) {
            Fragment two = new BlankFragment5();
            //实例化管理器
            FragmentManager fm = getSupportFragmentManager();
            //定义事务
            FragmentTransaction ft = fm.beginTransaction();
            //将当前的fragment替换为第二个
            ft.replace(R.id.fag1, two);
            //提交事务
            ft.commit();

        } else if (id == R.id.nav_send) {
            dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setTitle("提示");
            dialog.setMessage("您确定要注销吗");
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity2.this, "您已退出管理员端",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity2.this, Login.class);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
