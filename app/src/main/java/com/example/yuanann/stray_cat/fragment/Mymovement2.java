package com.example.yuanann.stray_cat.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;
import com.example.yuanann.stray_cat.activity.Adopt_Enter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mymovement2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_mymovement2,container,false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        ListView list=(ListView) getActivity().findViewById(R.id.list2);
        ListView listv=(ListView) getActivity().findViewById(R.id.list3);

        DatabaseHelper helper = new DatabaseHelper(getContext());
//        Cursor cursor=helper.queryCircle_select();
        Cursor cursor=helper.queryAdopt_select();
        String[] descA = new String[cursor.getCount()];
        String[] descB = new String[cursor.getCount()];
        String[] descC = new String[cursor.getCount()];
        Bitmap[] descD = new Bitmap[cursor.getCount()];
        final int[] descE= new int[cursor.getCount()];
        final String[] descF = new String[cursor.getCount()];
        String[] descG = new String[cursor.getCount()];
        String[] descH = new String[cursor.getCount()];
//        final int all=cursor.getCount()+4;
        int i = 0;

        while(cursor.moveToNext()){
            String a2 = cursor.getString(cursor.getColumnIndex("f_sendname"));
            descA[i] = a2;
            String a3 = cursor.getString(cursor.getColumnIndex("f_time"));
            descB[i] = a3;
            String a4 = cursor.getString(cursor.getColumnIndex("f_content"));
            descC[i] = a4;
            String a5 = cursor.getString(cursor.getColumnIndex("f_pic"));
            Bitmap bm = BitmapFactory.decodeFile("data//data//"+getContext().getPackageName()+ "//files//"+a5);
            descD[i] = bm;
            int a6 = cursor.getInt(cursor.getColumnIndex("_id"));
            descE[i] = a6;
            String a7 = cursor.getString(cursor.getColumnIndex("f_state"));
            descF[i] = a7;
            i++;
        }
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int z=0;z<descA.length;z++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("a1",descA[z]);
            item.put("a2",descB[z]);
            item.put("a3",descC[z]);
            item.put("a4",descD[z]);
            item.put("a5",descE[z]);
            item.put("a6",descF[z]);
            listItems.add(item);
        }
//        定义SimpleCursorAdapter
        SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),listItems,R.layout.layout_movement,new String[]{"a1","a2","a3","a4"},new int[]{R.id.a2,R.id.a3,R.id.a4,R.id.a5});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {

            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if( (view instanceof ImageView) & (data instanceof Bitmap) ) {
                    ImageView iv = (ImageView) view;
                    Bitmap bm = (Bitmap) data;
                    iv.setImageBitmap(bm);
                    return true;
                }
                return false;
            }
        });
        //设置适配器
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cl=descE[i];
                Intent intent=new Intent(getContext(), Adopt_Enter.class);
                intent.putExtra("id",cl);
                startActivity(intent);
            }
        });


        DatabaseHelper helper2 = new DatabaseHelper(getContext());
//        Cursor cursor=helper.queryCircle_select();
        Cursor cursor1=helper2.queryAdopt_system();
        String[] descA1 = new String[cursor1.getCount()];
        String[] descB1 = new String[cursor1.getCount()];
        String[] descC1 = new String[cursor1.getCount()];
        int[] descD1 = new int[cursor1.getCount()];
        final int[] descE1= new int[cursor1.getCount()];
        final String[] descF1 = new String[cursor1.getCount()];
        String[] descG1 = new String[cursor1.getCount()];
        String[] descH1 = new String[cursor1.getCount()];
//        final int all=cursor.getCount()+4;
        int i1 = 0;

        while(cursor1.moveToNext()){
            String a2 = cursor1.getString(cursor1.getColumnIndex("f_sendname"));
            descA1[i1] = a2;
            String a3 = cursor1.getString(cursor1.getColumnIndex("f_time"));
            descB1[i1] = a3;
            String a4 = cursor1.getString(cursor1.getColumnIndex("f_content"));
            descC1[i1] = a4;
            int a5 = cursor1.getInt(cursor1.getColumnIndex("f_pic"));
            descD1[i1] = a5;
            int a6 = cursor1.getInt(cursor1.getColumnIndex("_id"));
            descE1[i1] = a6;
            String a7 = cursor1.getString(cursor1.getColumnIndex("f_state"));
            descF1[i1] = a7;
            i1++;
        }
        List<Map<String,Object>> listItems1=new ArrayList<Map<String,Object>>();
        for(int z=0;z<descA1.length;z++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("a1",descA1[z]);
            item.put("a2",descB1[z]);
            item.put("a3",descC1[z]);
            item.put("a4",descD1[z]);
            item.put("a5",descE1[z]);
            item.put("a6",descF1[z]);
            listItems1.add(item);
        }
//        定义SimpleCursorAdapter
        SimpleAdapter simpleAdapter1=new SimpleAdapter(getContext(),listItems1,R.layout.layout_movement,new String[]{"a1","a2","a3","a4"},new int[]{R.id.a2,R.id.a3,R.id.a4,R.id.a5});

        //设置适配器
        listv.setAdapter(simpleAdapter1);
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cl=descE1[i];
                Intent intent=new Intent(getContext(),Adopt_Enter.class);
                intent.putExtra("id",cl);
                startActivity(intent);
            }
        });

    }
}
