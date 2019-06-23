package com.androiddeft.loginandregistration;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class OpenScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=1000;
    private ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);
        icon=findViewById(R.id.icon);

        Animation myanim = AnimationUtils.loadAnimation(OpenScreen.this, R.anim.fadein);
        icon.startAnimation(myanim);
        final Intent aftersplash = new Intent(OpenScreen.this,LoginActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(aftersplash);
                    finish();
                }
            }
        };
        timer.start();
    }
}


