package com.example.vishnu.calculationspeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends AppCompatActivity {
    Button btn_ok , btn_cancel;
    String cat,level,main_cat,rightAns_quiz,wrongAns_quiz,amtAns_quiz;
    TextView textView_right,textView_wrong,textView_amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_my_dialog);

        Bundle bundle = getIntent().getExtras();
        main_cat = bundle.getString("main_cat");

        //cat = bundle.getString("cat");

        level = bundle.getString("level");

        btn_ok = (Button) findViewById(R.id.button_ok);
        //btn_cancel = (Button) findViewById(R.id.button_cancle);
        textView_amt = (TextView) findViewById(R.id.textView_amt);
        textView_right = (TextView) findViewById(R.id.textView_right);
        textView_wrong = (TextView) findViewById(R.id.textView_wrong);

        final SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if (main_cat.equals("3")){
            rightAns_quiz=bundle.getString("rightAns_quiz");
            wrongAns_quiz=bundle.getString("wrongAns_quiz");
            amtAns_quiz=bundle.getString("amtAns_quiz");

            textView_amt.setText("Ques attempted: " + amtAns_quiz);
            textView_right.setText("Right answers:: " + rightAns_quiz);
            textView_wrong.setText("Wrong answers: " + wrongAns_quiz);

        }

        if (main_cat.equals("0")) {

        if (level.equals("simple")) {
            int getRight_simple,getWrong_simple,getAmt_simple;
            int totalRight_simple,totalWrong_simple,totalAmt_simple,getHighScore_simple;

            getRight_simple = sharedPreferences.getInt("rightAns_simple", 0);
            getWrong_simple = sharedPreferences.getInt("wrongAns_simple", 0);
            getAmt_simple = sharedPreferences.getInt("amtAns_simple", 0);

            totalAmt_simple = sharedPreferences.getInt("totalAmt_simple", 0);
            totalAmt_simple = totalAmt_simple + getAmt_simple;
            editor.putInt("totalAmt_simple", totalAmt_simple);
            editor.commit();

            totalRight_simple = sharedPreferences.getInt("totalRight_simple", 0);
            totalRight_simple = totalRight_simple + getRight_simple;
            editor.putInt("totalRight_simple", totalRight_simple);
            editor.commit();

            totalWrong_simple = sharedPreferences.getInt("totalWrong_simple", 0);
            totalWrong_simple = totalWrong_simple + getWrong_simple;
            editor.putInt("totalWrong_simple", totalWrong_simple);
            editor.commit();

            getHighScore_simple=sharedPreferences.getInt("highScore_simple", 0);
            if(getRight_simple>getHighScore_simple){
                editor.putInt("highScore_simple",getRight_simple);
                textView_right.setText("New Highest:"+getRight_simple);
            }
            else {
                textView_right.setText("Highest: " + getHighScore_simple);
            }
            textView_wrong.setText("Your Score:" + getRight_simple);
            if(getWrong_simple==0){
                textView_amt.setText("Ohh Times Up!!");
            }
            else{

                textView_amt.setText("Oops!! Got Wrong Answer");
            }
        }

       if (level.equals("tough")) {

            int getRight_tough,getwrong_tough,getAmt_tough;
            int totalRight_tough,totalWrong_tough,totalAmt_tough,getHighScore_tough;

            getRight_tough = sharedPreferences.getInt("rightAns_tough", 0);
            getwrong_tough = sharedPreferences.getInt("wrongAns_tough", 0);
            getAmt_tough = sharedPreferences.getInt("amtAns_tough", 0);

            totalAmt_tough = sharedPreferences.getInt("totalAmt_tough", 0);
            totalRight_tough = sharedPreferences.getInt("totalRight_tough", 0);
            totalWrong_tough = sharedPreferences.getInt("totalWrong_tough", 0);

            totalAmt_tough = totalAmt_tough + getAmt_tough;
            totalRight_tough = totalRight_tough + getRight_tough;
            totalWrong_tough = totalWrong_tough + getwrong_tough;

            editor.putInt("totalAmt_tough", totalAmt_tough);
            editor.putInt("totalRight_tough", totalRight_tough);
            editor.putInt("totalWrong_tough", totalWrong_tough);
            editor.commit();

            getHighScore_tough=sharedPreferences.getInt("highScore_tough", 0);

            if(getRight_tough>getHighScore_tough){
                editor.putInt("highScore_tough",getRight_tough);
                textView_right.setText("New Highest:"+getRight_tough);
            }
            else {
                textView_right.setText("Highest: " + getHighScore_tough);
            }

            //textView_amt.setText("Ques attempted: " + getAmt_tough);
            textView_wrong.setText("Your Score: " + getRight_tough);
            if(getwrong_tough==0){

                textView_amt.setText("Ohh Times Up!!");
            }
            else{
                textView_amt.setText("Oops!! Got Wrong Answer");
            }
        }
    }

        if (main_cat.equals("1")) {

            if (level.equals("simple")) {

                int getRightTest_simple,getWrongTest_simple,getAmtTest_simple;
                int totalRightTest_simple,totalWrongTest_simple,totalAmtTest_simple;

                getRightTest_simple = sharedPreferences.getInt("rightTest_simple", 0);
                getWrongTest_simple = sharedPreferences.getInt("wrongTest_simple", 0);
                getAmtTest_simple = sharedPreferences.getInt("amtTest_simple", 0);

                totalAmtTest_simple = sharedPreferences.getInt("totalAmtTest_simple", 0);
                totalRightTest_simple = sharedPreferences.getInt("totalRightTest_simple", 0);
                totalWrongTest_simple = sharedPreferences.getInt("totalWrongTest_simple", 0);

                totalAmtTest_simple = totalAmtTest_simple + getAmtTest_simple;
                totalRightTest_simple = totalRightTest_simple + getRightTest_simple;
                totalWrongTest_simple = totalWrongTest_simple + getWrongTest_simple;

                editor.putInt("totalAmtTest_simple", totalAmtTest_simple);
                editor.putInt("totalRightTest_simple", totalRightTest_simple);
                editor.putInt("totalWrongTest_simple", totalWrongTest_simple);
                editor.commit();


                textView_amt.setText("Ques attempted: " + getAmtTest_simple);
                textView_right.setText("Right answers: " + getRightTest_simple);
                textView_wrong.setText("Wrong answers: " + getWrongTest_simple);
            }
            if (level.equals("tough")) {
                int getRightTest_tough,getwrongTest_tough,getAmtTest_tough;
                int totalRightTest_tough,totalWrongTest_tough,totalAmtTest_tough;

                getAmtTest_tough = sharedPreferences.getInt("amtTest_tough", 0);
                getRightTest_tough = sharedPreferences.getInt("rightTest_tough", 0);
                getwrongTest_tough = sharedPreferences.getInt("wrongTest_tough", 0);


                totalAmtTest_tough = sharedPreferences.getInt("totalAmtTest_tough", 0);
                totalRightTest_tough = sharedPreferences.getInt("totalRightTest_tough", 0);
                totalWrongTest_tough = sharedPreferences.getInt("totalWrongTest_tough", 0);

                totalAmtTest_tough = totalAmtTest_tough + getAmtTest_tough;
                totalRightTest_tough = totalRightTest_tough + getRightTest_tough;
                totalWrongTest_tough = totalWrongTest_tough + getwrongTest_tough;

                editor.putInt("totalAmtTest_tough", totalAmtTest_tough);
                editor.putInt("totalRightTest_tough", totalRightTest_tough);
                editor.putInt("totalWrongTest_tough", totalWrongTest_tough);
                editor.commit();

                textView_amt.setText("Ques attempted: " + getAmtTest_tough);
                textView_right.setText("Right answers:: " + getRightTest_tough);
                textView_wrong.setText("Wrong answers: " + getwrongTest_tough);
            }
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_ok = new Intent(MyDialog.this,MainMenu.class);
                intent_ok.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent_ok.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_ok);
            }
        });

    }
    @Override
    public void onBackPressed(){
        btn_ok.callOnClick();
    }
    public  void closeDialog(){
        finish();
    }
}
