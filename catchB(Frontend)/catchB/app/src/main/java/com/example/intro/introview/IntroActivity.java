package com.example.intro.introview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intro.R;
import com.example.intro.login.Login;

public class IntroActivity extends AppCompatActivity { //Intro 첫 화면 시작.
    TextView tv1, tv2;
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= findViewById(R.id.tv1);
        iv1= findViewById(R.id.iv1);
        tv2= findViewById(R.id.tv2);

        tv1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.appear));
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //startActivity(intent);
                startActivity(new Intent(IntroActivity.this, Login.class));
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000); //3초후 화면전환
    }
}