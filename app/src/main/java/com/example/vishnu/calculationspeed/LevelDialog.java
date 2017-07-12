package com.example.vishnu.calculationspeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelDialog extends AppCompatActivity {
    String diff_level,finalType_cat,main_cat;
    TextView textView_right,textView_wrong,textView_amt;
    Button btn_simple,btn_tough,btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_dialog);

        Bundle bundle = getIntent().getExtras();
        main_cat=bundle.getString("main_cat");

        //diff_level=bundle.getString("level");
        //finalType_cat = bundle.getString("cat_type");

        btn_simple = (Button) findViewById(R.id.button_simple);
        btn_tough = (Button) findViewById(R.id.button_tough);
        btn_cancel = (Button) findViewById(R.id.button_cancle);
        textView_amt = (TextView) findViewById(R.id.textView_amt);

        if(main_cat.equals("0")) {
            textView_amt.setText("You get 10 sec to answer");
            btn_simple.setText("Simple Quiz");
            btn_tough.setText("Tough Quiz");

            /*if (finalType_cat.equals("simple")) {

                //textView_right.setText("Continuous 20 right ans clears the level");
                //textView_wrong.setText("One Wrong answers  and you fails");

            }
            if (finalType_cat.equals("tough")) {

                //textView_right.setText("Continuous 20 right ans clears the level");
                //textView_wrong.setText("One Wrong answer and you fails ");
            }*/
        }

        if(main_cat.equals("1")) {
            textView_amt.setText("You get 30 sec to answer ");
            btn_simple.setText("Simple Test");
            btn_tough.setText("Tough Test");
            /*if (finalType_cat.equals("simple")) {
                textView_amt.setText("You get 30 sec to type 4 answers ");
                //textView_right.setText("Continuous 20 right ans clears the level");
                //textView_wrong.setText("One Wrong answer and you fails");
            }
            if (finalType_cat.equals("tough")) {
                textView_amt.setText("You get 10 sec to answer");
                //textView_right.setText("Continuous 20 right ans clears the level");
                //textView_wrong.setText("One Wrong answer and you fails ");
            }*/

        }

        btn_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(main_cat.equals("0")){

                    Intent intent_quiz=new Intent(LevelDialog.this,Quiz.class);
                    // for simple level quiz
                    intent_quiz.putExtra("main_cat",main_cat);

                    // for simple level
                    String level = "simple";
                    intent_quiz.putExtra("level",level);
                    startActivity(intent_quiz);

                }
                if(main_cat.equals("1")){
                    // for simple level test
                    Intent intent_test= new Intent(LevelDialog.this,Test.class);
                    intent_test.putExtra("main_cat",main_cat);
                    // for simple level
                    String level = "simple";
                    intent_test.putExtra("level",level);
                    startActivity(intent_test);
                }

                /*if(main_cat.equals("0")) {
                    Intent quiz_page = new Intent(LevelDialog.this, Quiz.class);
                    if (finalType_cat.equals("simple")) {
                        Intent quiz_page = new Intent(LevelDialog.this, Quiz.class);
                        String cat_dis = "simple";
                        quiz_page.putExtra("level", diff_level);
                        String main_cat_1 = main_cat;
                        quiz_page.putExtra("main_cat", main_cat_1);
                        quiz_page.putExtra("cat_type", cat_dis);
                        startActivity(quiz_page);

                    }
                    if (finalType_cat.equals("tough")) {
                        Intent quiz_page = new Intent(LevelDialog.this, Quiz.class);
                        String cat_dis = "tough";
                        quiz_page.putExtra("level", diff_level);
                        String main_cat_1 = main_cat;
                        quiz_page.putExtra("main_cat", main_cat_1);
                        quiz_page.putExtra("cat_type", cat_dis);
                        startActivity(quiz_page);
                    }
                }
                if(main_cat.equals("1")) {
                    if (finalType_cat.equals("simple")) {
                        Intent quiz_page = new Intent(LevelDialog.this, Test.class);
                        String cat_dis = "simple";
                        quiz_page.putExtra("level", diff_level);
                        String main_cat_1 = main_cat;
                        quiz_page.putExtra("main_cat", main_cat_1);
                        quiz_page.putExtra("cat_type", cat_dis);
                        startActivity(quiz_page);

                    }
                    if (finalType_cat.equals("tough")) {
                        Intent quiz_page = new Intent(LevelDialog.this, Test.class);
                        String cat_dis = "tough";
                        quiz_page.putExtra("level", diff_level);
                        String main_cat_1 = main_cat;
                        quiz_page.putExtra("main_cat", main_cat_1);
                        quiz_page.putExtra("cat_type", cat_dis);
                        startActivity(quiz_page);
                    }

                }*/
            }
        });

        btn_tough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(main_cat.equals("0")){

                    Intent intent_quiz=new Intent(LevelDialog.this,Quiz.class);
                    // for quiz tough level
                    intent_quiz.putExtra("main_cai",main_cat);

                    // for though level
                    String level = "tough";
                    intent_quiz.putExtra("level",level);

                    startActivity(intent_quiz);

                }
                if(main_cat.equals("1")){
                    // for tough level test
                    Intent intent_test= new Intent(LevelDialog.this,Test.class);
                    intent_test.putExtra("main_cat",main_cat);
                    // for though level test
                    String level = "tough";
                    intent_test.putExtra("level",level);
                    startActivity(intent_test);
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }
        });
    }//on create


    public  void closeDialog(){
        finish();
    }
}
