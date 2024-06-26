package com.example.yuanann.stray_cat.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment5 extends Fragment {


    public BlankFragment5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment5, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        ListView list = (ListView) getActivity().findViewById(R.id.list);
        DatabaseHelper helper = new DatabaseHelper(getContext());
        Cursor cursor2 = helper.queryDonate();
        final int[] descA = new int[cursor2.getCount()];
        String[] descB = new String[cursor2.getCount()];
        final int all = cursor2.getCount();
        int i = 0;
        while (cursor2.moveToNext()) {
            int a1 = cursor2.getInt(cursor2.getColumnIndex("_id"));
            descA[i] = a1;
            i++;
        }
//        定义SimpleCursorAdapter
        SimpleCursorAdapter simpleAdapter1 = new SimpleCursorAdapter(getContext(), R.layout.back_course, cursor2, new String[]{"_id","d_name","d_money","d_time"}, new int[]{R.id.u1, R.id.u2, R.id.u3, R.id.u4});

        //设置适配器
        list.setAdapter(simpleAdapter1);

    }
}
