package com.example.vishnu.calculationspeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class SplashScreen extends AppCompatActivity {

    RelativeLayout relativeLayout;
    int splashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        relativeLayout= (RelativeLayout) findViewById(R.id.splash_layout);
        if(splashImg==0){
            relativeLayout.setBackgroundResource(R.drawable.read_back);
        }
        else{
            relativeLayout.setBackgroundResource(splashImg);
        }

        Thread displaySplash=new Thread(){
            public void run(){

                try {
                    sleep(2*1000);
                    Intent intent=new Intent(SplashScreen.this,MainMenu.class);
                    startActivity(intent);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        displaySplash.start();
    }
}
