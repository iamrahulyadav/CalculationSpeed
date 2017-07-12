package com.example.vishnu.calculationspeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class Test extends AppCompatActivity {
    TextView p1,p2,p3,mi1,mi2,mi3,mu1,mu2,mu3,d1,d2,d3,tme,ritAns;
    Button bs,smt,end;
    EditText sumAns,subAns,mutAns,divAns;
    int sum1,sum2,sum3,sub1,sub2,sub3,mut1,mut2,mut3,div1,div2,div3,level_redm;
    int  rightTest_simple=0,wrongTest_simple=0,amtTest_simple=0,rightTest_tough=0,wrongTest_tough=0,amtTest_tough=0;
    long remTime=30;
    boolean stopTimer=false,checkEmpty_sum=false,checkEmpty_sub=false,checkEmpty_mut=false,checkEmpty_div=false,end_check=false;
    long timeMax = 31000;     //30 sec = 30*1000 milli sec
    long decTime = 1000;        // 1 sec decrease
    String diff_level,main_cat,finalType_cat,level;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        main_cat=bundle.getString("main_cat");
        level=bundle.getString("level");
        //finalType_cat = bundle.getString("cat_type");


        final SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("rightTest_simple",rightTest_simple);
        editor.putInt("wrongTest_simple",wrongTest_simple);
        editor.putInt("amtTest_simple",amtTest_simple);
        editor.putInt("rightTest_tough",rightTest_tough);
        editor.putInt("wrongTest_tough",wrongTest_tough);
        editor.putInt("amtTest_tough",amtTest_tough);
        editor.commit();

        diff_level="4";
        level_redm=Integer.valueOf(diff_level) +1 ;
        final int rdm_rag = level_redm*5;

        if( level.equals("simple")){
            setContentView(R.layout.activity_test);

            final AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setVisibility(View.GONE);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    if(mAdView.getVisibility()==View.GONE){
                        mAdView.setVisibility(View.VISIBLE);
                    }
                }
            });

            p1=(TextView)findViewById(R.id.plus_x);
            p2=(TextView)findViewById(R.id.plus_y);
            mi1=(TextView)findViewById(R.id.minus_x);
            mi2=(TextView)findViewById(R.id.minus_y);
            mu1=(TextView)findViewById(R.id.multi_x);
            mu2=(TextView)findViewById(R.id.multi_y);
            d1=(TextView)findViewById(R.id.divide_x);
            d2=(TextView)findViewById(R.id.divide_y);
            ritAns= (TextView) findViewById(R.id.textView_ritAns);
            tme=(TextView)findViewById(R.id.textView_Timer);
            bs=(Button)findViewById(R.id.button_start);
            smt=(Button)findViewById(R.id.button_next);
            end= (Button) findViewById(R.id.button_end);
            sumAns =(EditText)findViewById(R.id.editText_sum);
            subAns =(EditText)findViewById(R.id.editText_sub);
            mutAns =(EditText)findViewById(R.id.editText_mut);
            divAns =(EditText)findViewById(R.id.editText_div);

            smt.setEnabled(false);
            smt.setBackgroundResource(R.drawable.button_false);
            bs.setEnabled(true);
            end.setEnabled(false);

            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        countDownTimer.cancel();
                        Intent intent = new Intent(Test.this, MyDialog.class);
                        String mainCat = main_cat;
                        String level = "simple";
                        intent.putExtra("level", level);
                        intent.putExtra("main_cat", mainCat);
                        startActivity(intent);
                }
            });

            bs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    end_check=true;

                    amtTest_simple=amtTest_simple+4;
                    editor.putInt("amtTest_simple",amtTest_simple);
                    editor.commit();

                    checkEmpty_sum=false;
                    checkEmpty_sub=false;
                    checkEmpty_mut=false;
                    checkEmpty_div=false;

                    // Enable submit button
                    smt.setEnabled(true);
                    bs.setEnabled(false);
                    end.setEnabled(true);
                    end.setBackgroundResource(R.drawable.button_shape);
                    smt.setBackgroundResource(R.drawable.button_shape);
                    bs.setBackgroundResource(R.drawable.button_false);

                    // Enable editText box
                    sumAns.setEnabled(true);
                    subAns.setEnabled(true);
                    mutAns.setEnabled(true);
                    divAns.setEnabled(true);

                    //to generate random numbers

                    sum1 = new Random().nextInt(rdm_rag) + 1;
                    sum2 = new Random().nextInt(rdm_rag) + 1;
                    sub2 = new Random().nextInt(rdm_rag) + 1;
                    sub1 = sub2 + new Random().nextInt(rdm_rag) + 1;
                    mut1 = new Random().nextInt(rdm_rag) + 1;
                    mut2 = new Random().nextInt(rdm_rag) + 1;
                    div2 = new Random().nextInt((Integer.valueOf(level_redm)+2)*2) + 1;
                    div1 = div2 * (new Random().nextInt(rdm_rag)+1);

                    //Set all random number to there respective place
                    p1.setText(Integer.toString(sum1));
                    p2.setText(Integer.toString(sum2));
                    mi1.setText(Integer.toString(sub1));
                    mi2.setText(Integer.toString(sub2));
                    mu1.setText(Integer.toString(mut1));
                    mu2.setText(Integer.toString(mut2));
                    d1.setText(Integer.toString(div1));
                    d2.setText(Integer.toString(div2));


                    //Erase the previous entry in editText box
                    sumAns.getText().clear();
                    subAns.getText().clear();
                    mutAns.getText().clear();
                    divAns.getText().clear();

                    //Change the color of editText box to white(the original one)
                    sumAns.setBackgroundColor(Color.TRANSPARENT);
                    subAns.setBackgroundColor(Color.TRANSPARENT);
                    mutAns.setBackgroundColor(Color.TRANSPARENT);
                    divAns.setBackgroundColor(Color.TRANSPARENT);
                    ritAns.setText("");

                    //To set the focus on particular editText box
                    sumAns.requestFocus();

                    //For Timer

                    if(stopTimer==false){
                       countDownTimer= new CountDownTimer(timeMax, decTime) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                                    tme.setText("Time Left:" + millisUntilFinished / 1000);
                                    remTime = millisUntilFinished;
                                    stopTimer=true;
                            }

                            @Override
                            public void onFinish() {
                                stopTimer=true;
                                smt.callOnClick();
                                tme.setText("Times Up!!");
                            }
                        }.start();
                    }
                    else{
                        countDownTimer.cancel();
                        countDownTimer= new CountDownTimer(timeMax, decTime) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                    tme.setText("Time Left:" + millisUntilFinished / 1000);
                                    remTime = millisUntilFinished;
                            }

                            @Override
                            public void onFinish() {
                                smt.callOnClick();
                                tme.setText("Times Up!!");
                            }
                        }.start();
                    }
                }
            });

            smt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    smt.setEnabled(false);
                    bs.setEnabled(true);
                    smt.setBackgroundResource(R.drawable.button_false);
                    bs.setBackgroundResource(R.drawable.button_shape);
                    bs.setText("Next Test");

                    //Store correct ans in different variables

                    int sum = sum1 + sum2 ;
                    int sub = sub1 - sub2;
                    int mut = mut1 * mut2;
                    int div = div1 / div2;

                    // To check the empty editText box
                    if (sumAns.getText().toString().matches("") ) {
                        sumAns.setText("0");
                        checkEmpty_sum=true;
                    }
                    if (subAns.getText().toString().matches("") ){
                        subAns.setText("0");
                        checkEmpty_sub=true;
                    }
                    if (mutAns.getText().toString().matches("") ){
                        mutAns.setText("0");
                        checkEmpty_mut=true;
                    }
                    if (divAns.getText().toString().matches("") ){
                        divAns.setText("0");
                        checkEmpty_div=true;
                    }

                    // Get integer value from editText box and store in a variable
                    int giv_sum = Integer.parseInt(sumAns.getText().toString());
                    int giv_sub = Integer.parseInt(subAns.getText().toString());
                    int giv_mut = Integer.parseInt(mutAns.getText().toString());
                    int giv_div = Integer.parseInt(divAns.getText().toString());

                    //To check the ans given by user and change the background color of editText box accordingly

                    if (sum==giv_sum){
                        if(checkEmpty_sum==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();

                            sumAns.setBackgroundColor(Color.RED);
                        }
                        else{
                            rightTest_simple=rightTest_simple+1;
                            editor.putInt("rightTest_simple",rightTest_simple);
                            editor.commit();
                            sumAns.setBackgroundColor(Color.GREEN);
                        }
                    }
                    else {
                        if(checkEmpty_sum==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();

                            sumAns.setBackgroundColor(Color.RED);
                        }
                        wrongTest_simple=wrongTest_simple+1;
                        editor.putInt("wrongTest_simple",wrongTest_simple);
                        editor.commit();
                        sumAns.setBackgroundColor(Color.RED);
                    }

                    if (sub==giv_sub) {
                        if(checkEmpty_sub==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();
                        }
                        else{
                            rightTest_simple=rightTest_simple+1;
                            editor.putInt("rightTest_simple",rightTest_simple);
                            editor.commit();
                            subAns.setBackgroundColor(Color.GREEN);
                        }
                    }
                    else {
                        if(checkEmpty_sub==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();
                        }
                        wrongTest_simple=wrongTest_simple+1;
                        editor.putInt("wrongTest_simple",wrongTest_simple);
                        editor.commit();

                        subAns.setBackgroundColor(Color.RED);
                    }

                    if (mut==giv_mut) {
                        if(checkEmpty_mut==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();
                            mutAns.setBackgroundColor(Color.RED);
                        }
                        else{
                            mutAns.setBackgroundColor(Color.GREEN);
                            rightTest_simple=rightTest_simple+1;
                            editor.putInt("rightTest_simple",rightTest_simple);
                            editor.commit();
                        }
                    }
                    else {
                        if(checkEmpty_mut==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();
                        }
                        wrongTest_simple=wrongTest_simple+1;
                        editor.putInt("wrongTest_simple",wrongTest_simple);
                        editor.commit();
                        mutAns.setBackgroundColor(Color.RED);
                    }

                    if (div==giv_div) {
                        if(checkEmpty_div==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();
                            divAns.setBackgroundColor(Color.RED);
                        }
                        else{
                            rightTest_simple=rightTest_simple+1;
                            editor.putInt("rightTest_simple",rightTest_simple);
                            editor.commit();
                            divAns.setBackgroundColor(Color.GREEN);
                        }
                    }
                    else {
                        if(checkEmpty_div==true){
                            wrongTest_simple=wrongTest_simple-1;
                            editor.putInt("wrongTest_simple",wrongTest_simple);
                            editor.commit();
                        }
                        divAns.setBackgroundColor(Color.RED);
                        wrongTest_simple=wrongTest_simple+1;
                        editor.putInt("wrongTest_simple",wrongTest_simple);
                        editor.commit();
                    }

                    // Show the Right answer to the user

                    ritAns.setText("Ans: "+" "+sum+","+" "+sub+","+" "+mut+","+" "+div);

                    //For Timer
                    countDownTimer.cancel();
                    tme.setText("Time Left:" + remTime / 1000);
                }
            });


            bs.callOnClick();
        }
        // For Double Operator Speed Test

        if(level.equals("tough")) {
            setContentView(R.layout.test_double);

            final AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setVisibility(View.GONE);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    if(mAdView.getVisibility()==View.GONE){
                        mAdView.setVisibility(View.VISIBLE);
                    }
                }
            });

            p1=(TextView)findViewById(R.id.plus_x);
            p2=(TextView)findViewById(R.id.plus_y);
            p3=(TextView)findViewById(R.id.plus_Z);
            mi1=(TextView)findViewById(R.id.minus_x);
            mi2=(TextView)findViewById(R.id.minus_y);
            mi3= (TextView) findViewById(R.id.minus_z);
            mu1=(TextView)findViewById(R.id.multi_x);
            mu2=(TextView)findViewById(R.id.multi_y);
            mu3=(TextView)findViewById(R.id.multi_z);
            d1=(TextView)findViewById(R.id.divide_x);
            d2=(TextView)findViewById(R.id.divide_y);
            d3=(TextView)findViewById(R.id.divide_z);
            tme=(TextView)findViewById(R.id.textView_Timer);
            ritAns= (TextView) findViewById(R.id.textView_ritAns);
            bs=(Button)findViewById(R.id.button_start);
            smt=(Button)findViewById(R.id.button_next);
            end= (Button) findViewById(R.id.button_end);
            sumAns =(EditText)findViewById(R.id.editText_sum);
            subAns =(EditText)findViewById(R.id.editText_sub);
            mutAns =(EditText)findViewById(R.id.editText_mut);
            divAns =(EditText)findViewById(R.id.editText_div);

            smt.setEnabled(false);
            end.setEnabled(false);
            bs.setEnabled(true);

            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    countDownTimer.cancel();
                    Intent intent = new Intent(Test.this,MyDialog.class);
                    String mainCat=main_cat;
                    String level ="tough";
                    intent.putExtra("level",level);
                    intent.putExtra("main_cat",mainCat);
                    startActivity(intent);
                }
            });

            bs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    amtTest_tough=amtTest_tough+4;
                    editor.putInt("amtTest_tough",amtTest_tough);
                    editor.commit();

                    checkEmpty_sum=false;
                    checkEmpty_sub=false;
                    checkEmpty_mut=false;
                    checkEmpty_div=false;

                    // Enable submit button
                    smt.setEnabled(true);
                    bs.setEnabled(false);
                    end.setEnabled(true);
                    end.setBackgroundResource(R.drawable.button_shape);
                    smt.setBackgroundResource(R.drawable.button_shape);
                    bs.setBackgroundResource(R.drawable.button_false);

                    // Enable editText box
                    sumAns.setEnabled(true);
                    subAns.setEnabled(true);
                    mutAns.setEnabled(true);
                    divAns.setEnabled(true);

                    //to generate random numbers between 1 to 21
                    sum1 = new Random().nextInt(rdm_rag) + 1;
                    sum2 = new Random().nextInt(rdm_rag) + 1;
                    sum3 = new Random().nextInt(rdm_rag) + 1;

                    sub3 = new Random().nextInt(rdm_rag) + 1;
                    sub2 = sub3 + new Random().nextInt(rdm_rag) + 1;
                    sub1 = sub2 + new Random().nextInt(rdm_rag) + 1;

                    mut1 = new Random().nextInt(rdm_rag) + 1;
                    mut2 = new Random().nextInt(rdm_rag) + 1;
                    mut3 = new Random().nextInt(rdm_rag) + 1;

                    div2 = new Random().nextInt((Integer.valueOf(level_redm)+2)*2) + 1;
                    div1 = div2 * (new Random().nextInt(rdm_rag)+1);
                    div3 = (div1/div2) + new Random().nextInt(rdm_rag) + 1;

                    //Set all random number to there respective place
                    p1.setText(Integer.toString(sum1));
                    p2.setText(Integer.toString(sum2));
                    p3.setText(Integer.toString(sum3));
                    mi1.setText(Integer.toString(sub1));
                    mi2.setText(Integer.toString(sub2));
                    mi3.setText(Integer.toString(sub3));
                    mu1.setText(Integer.toString(mut1));
                    mu2.setText(Integer.toString(mut2));
                    mu3.setText(Integer.toString(mut3));
                    d1.setText(Integer.toString(div1));
                    d2.setText(Integer.toString(div2));
                    d3.setText(Integer.toString(div3));


                    //Erase the previous entry in editText box
                    sumAns.getText().clear();
                    subAns.getText().clear();
                    mutAns.getText().clear();
                    divAns.getText().clear();

                    //Change the color of editText box to white(the original one)
                    sumAns.setBackgroundColor(Color.TRANSPARENT);
                    subAns.setBackgroundColor(Color.TRANSPARENT);
                    mutAns.setBackgroundColor(Color.TRANSPARENT);
                    divAns.setBackgroundColor(Color.TRANSPARENT);
                    ritAns.setText("");

                    //To set the focus on particular editText box
                    sumAns.requestFocus();

                    //For Timer

                    if(stopTimer==false){
                        countDownTimer= new CountDownTimer(timeMax, decTime) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                tme.setText("Time Left:" + millisUntilFinished / 1000);
                                remTime = millisUntilFinished;
                                stopTimer=true;
                            }

                            @Override
                            public void onFinish() {
                                stopTimer=true;
                                smt.callOnClick();
                                tme.setText("Times Up!!");
                            }
                        }.start();
                    }
                    else{
                        countDownTimer.cancel();
                        countDownTimer= new CountDownTimer(timeMax, decTime) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                    tme.setText("Time Left:" + millisUntilFinished / 1000);
                                    remTime = millisUntilFinished;
                            }

                            @Override
                            public void onFinish() {
                                smt.callOnClick();
                                tme.setText("Times Up!!");
                            }
                        }.start();
                    }
                }
            });

            smt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    smt.setEnabled(false);
                    bs.setEnabled(true);
                    bs.setText("Next Test");
                    smt.setBackgroundResource(R.drawable.button_false);
                    bs.setBackgroundResource(R.drawable.button_shape);

                    //Store correct ans in different variables

                    int sum = sum1 + sum2 + sum3 ;
                    int sub = sub1 - sub2 +sub3;
                    int mut = mut1 * mut2 - mut3;
                    int div = (div1 / div2) + div3;

                    // To check the empty editText box
                    if (sumAns.getText().toString().matches("") ) {
                        sumAns.setText("0");
                        checkEmpty_sum=true;
                    }
                    if (subAns.getText().toString().matches("") ){
                        subAns.setText("0");
                        checkEmpty_sub=true;
                    }
                    if (mutAns.getText().toString().matches("") ){
                        mutAns.setText("0");
                        checkEmpty_mut=true;
                    }
                    if (divAns.getText().toString().matches("") ){
                        divAns.setText("0");
                        checkEmpty_div=true;
                    }

                    // Get integer value from editText box and store in a variable
                    int giv_sum = Integer.parseInt(sumAns.getText().toString());
                    int giv_sub = Integer.parseInt(subAns.getText().toString());
                    int giv_mut = Integer.parseInt(mutAns.getText().toString());
                    int giv_div = Integer.parseInt(divAns.getText().toString());

                    //To check the ans given by user and change the background color of editText box accordingly

                    if (sum==giv_sum){
                        if(checkEmpty_sum==true) {
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                            sumAns.setBackgroundColor(Color.RED);
                        }
                        else{
                            rightTest_tough=rightTest_tough+1;
                            editor.putInt("rightTest_tough",rightTest_tough);
                            editor.commit();
                            sumAns.setBackgroundColor(Color.GREEN);
                        }
                    }
                    else {
                        if(checkEmpty_sum==true) {
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                        }

                        wrongTest_tough=wrongTest_tough+1;
                        editor.putInt("wrongTest_tough",wrongTest_tough);
                        editor.commit();
                        sumAns.setBackgroundColor(Color.RED);
                    }

                    if (sub==giv_sub) {
                        if(checkEmpty_sub==true){
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                            subAns.setBackgroundColor(Color.RED);
                        }
                        else{
                            rightTest_tough=rightTest_tough+1;
                            editor.putInt("rightTest_tough",rightTest_tough);
                            editor.commit();
                            subAns.setBackgroundColor(Color.GREEN);
                        }
                    }
                    else {
                        if(checkEmpty_sub==true) {
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                        }
                        wrongTest_tough=wrongTest_tough+1;
                        editor.putInt("wrongTest_tough",wrongTest_tough);
                        editor.commit();
                        subAns.setBackgroundColor(Color.RED);
                    }

                    if (mut==giv_mut) {
                        if(checkEmpty_mut==true){
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                            mutAns.setBackgroundColor(Color.RED);
                        }
                        else{
                            rightTest_tough=rightTest_tough+1;
                            editor.putInt("rightTest_tough",rightTest_tough);
                            editor.commit();
                            mutAns.setBackgroundColor(Color.GREEN);
                        }
                    }
                    else {
                        if(checkEmpty_mut==true) {
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                        }
                        wrongTest_tough=wrongTest_tough+1;
                        editor.putInt("wrongTest_tough",wrongTest_tough);
                        editor.commit();
                        mutAns.setBackgroundColor(Color.RED);
                    }

                    if (div==giv_div) {
                        if(checkEmpty_div==true){
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                            divAns.setBackgroundColor(Color.RED);
                        }
                        else{
                            rightTest_tough=rightTest_tough+1;
                            editor.putInt("rightTest_tough",rightTest_tough);
                            editor.commit();
                            divAns.setBackgroundColor(Color.GREEN);
                        }
                    }
                    else {
                        if(checkEmpty_div==true) {
                            wrongTest_tough=wrongTest_tough-1;
                            editor.putInt("wrongTest_tough",wrongTest_tough);
                            editor.commit();
                        }
                        wrongTest_tough=wrongTest_tough+1;
                        editor.putInt("wrongTest_tough",wrongTest_tough);
                        editor.commit();
                        divAns.setBackgroundColor(Color.RED);
                    }


                    // Show the Right answer to the user
                    ritAns.setText("Ans: "+" "+sum+","+" "+sub+","+" "+mut+","+" "+div);


                    //For Timer
                    countDownTimer.cancel();
                    tme.setText("Time Left:" + remTime / 1000);
                }
            });
            bs.callOnClick();

        }
    }

    @Override
    public void onBackPressed(){
        end.callOnClick();
    }
}
