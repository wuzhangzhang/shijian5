package com.example.wuzhangzhang.shijian5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;


import android.widget.Button;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qwe);

        final TextView textView = (TextView)findViewById(R.id.jindu);


        final Handler handler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1+"");

            }
        };

        final Runnable myWorker = new Runnable() {
            public void run() {
                int progress = 0;
                while(progress <= 100){
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress +=5;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        Button button = (Button) findViewById(R.id.s);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker, "jobThread");
                workThread.start();
            }
        });



    }


}