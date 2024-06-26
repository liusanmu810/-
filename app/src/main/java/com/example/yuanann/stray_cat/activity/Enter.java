package com.example.yuanann.stray_cat.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yuanann.stray_cat.R;

import java.util.Timer;
import java.util.TimerTask;

public class Enter extends AppCompatActivity {

    private Button jumps;
    private int recLen = 3;

    Timer timer = new Timer();
    private final long SPLASH_LENGTH = 3000;
    Handler handler = new Handler();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        jumps=(Button)findViewById(R.id.jumps);
        timer.schedule(task, 1000, 1000);

        jumps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enter.this, Login.class);
                startActivity(intent);
                timer.cancel();
                finish();
            }
        });

    }

    final Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    jumps.setText(recLen + "s跳过");
                    if(recLen == 0){
                        timer.cancel();
                        Intent intent = new Intent(Enter.this, Login.class);
                        startActivity(intent);
                        finish();
                    }

            }
        }
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            recLen--;
            Message message = new Message();
            message.what = 1;
            handler2.sendMessage(message);

        }
    };
}

