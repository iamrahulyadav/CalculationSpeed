package com.example.vishnu.calculationspeed;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.math.BigInteger;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoQuiz extends Fragment implements AdapterView.OnItemClickListener {

    long rdm_ans,rdm_ans_add;
    int  rightAns_quiz=0,wrongAns_quiz=0,amtAns_quiz=0;
    BigInteger list_ans_a_int,list_ans_b_int, list_ans_c_int,list_ans_d_int;
    String list_ans_a,list_ans_b, list_ans_c,list_ans_d,main_cat;
    String[] ansOpt;
    TextView text_opr,text_x,tme;
    ListView listView_opt;
    String[] imgs={"A","B","C","D"};
    Button btn_end,btn_next;
    String[] ansCheck ={"","","",""};
    boolean stopTimer=false,cancelTimer=false,end_check=false;
    long timeMax = 10000;     //10 sec = 10*1000 milli sec
    long decTime = 1000;        // 1 sec decrease
    long remTime=10;
    CountDownTimer countDownTimer;
    View rootView;
    String memo_cat;
    long quesNum,quesPower,rdmNum,ans,rdmAns_1,rdmAns_2,rdmAns_3;

    public MemoQuiz() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView =inflater.inflate(R.layout.fragment_memo_quiz, container, false);
        Bundle bundle = getActivity().getIntent().getExtras();
        memo_cat = bundle.getString("memo_cat");
        main_cat = bundle.getString("main_cat");

        final AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
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

        text_opr= (TextView) rootView.findViewById(R.id.textView_power);
        text_x= (TextView) rootView.findViewById(R.id.plus_x);
        btn_next= (Button) rootView.findViewById(R.id.button_next);
        btn_end= (Button) rootView.findViewById(R.id.button_end);
        listView_opt= (ListView) rootView.findViewById(R.id.listView_opt);
        tme = (TextView) rootView.findViewById(R.id.textView_Timer);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer=true;
                end_check=true;
                btn_next.setText("Next Quiz");
                amtAns_quiz=amtAns_quiz+1;

                setValue_single();

            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (end_check==false){
                    Toast.makeText(getActivity(),"First start the Quiz",Toast.LENGTH_LONG).show();
                }
                else {

                    cancelTimer = true;
                    countDownTimer.cancel();

                    Intent intent = new Intent(MemoQuiz.this.getActivity(), MyDialog.class);
                    String level = "simple";
                    String mainCat = main_cat;
                    intent.putExtra("level", level);
                    intent.putExtra("main_cat", mainCat);
                    intent.putExtra("amtAns_quiz", Integer.toString(amtAns_quiz));
                    intent.putExtra("rightAns_quiz", Integer.toString(rightAns_quiz));
                    intent.putExtra("wrongAns_quiz", Integer.toString(wrongAns_quiz));
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }

    public void setValue_single(){
        
        ansCheck =new String[]{"","","",""};
        imgs=new String[]{"A","B","C","D"};
        rdmNum=new Random().nextInt(24)+1;
        rdm_ans =new Random().nextInt(3);
        if(memo_cat.equals("0")){
            quesNum=rdmNum;
            quesPower=2;
            ans= (long) Math.pow(quesNum,quesPower);

            rdmAns_1=quesNum+1;
            rdmAns_2=quesNum+2;
            rdmAns_3=quesNum-1;
            list_ans_a_int=BigInteger.valueOf(ans);
            list_ans_b_int=BigInteger.valueOf((long)Math.pow(rdmAns_1,quesPower));
            list_ans_c_int=BigInteger.valueOf((long)Math.pow(rdmAns_2,quesPower));
            list_ans_d_int=BigInteger.valueOf((long)Math.pow(rdmAns_3,quesPower));

        }
        if(memo_cat.equals("1")) {
            quesNum = rdmNum;
            quesPower = 3;
            ans = (long) Math.pow(quesNum, quesPower);
            rdmAns_1 = quesNum + 1;
            rdmAns_2 = quesNum + 2;
            rdmAns_3 = quesNum - 1;
            list_ans_a_int = BigInteger.valueOf(ans);
            list_ans_b_int = BigInteger.valueOf((long) Math.pow(rdmAns_1, quesPower));
            list_ans_c_int = BigInteger.valueOf((long) Math.pow(rdmAns_2, quesPower));
            list_ans_d_int = BigInteger.valueOf((long) Math.pow(rdmAns_3, quesPower));
        }
        if(memo_cat.equals("2")){
            quesNum=2;
            quesPower=rdmNum;
            ans= (long) Math.pow(quesNum,quesPower);
            rdmAns_1=quesPower+1;
            rdmAns_2=quesPower+2;
            rdmAns_3=quesPower-1;
            list_ans_a_int=BigInteger.valueOf(ans);
            list_ans_b_int=BigInteger.valueOf((long)Math.pow(quesNum,rdmAns_1));
            list_ans_c_int=BigInteger.valueOf((long)Math.pow(quesNum,rdmAns_2));
            list_ans_d_int=BigInteger.valueOf((long)Math.pow(quesNum, rdmAns_3));
        }
        if(memo_cat.equals("3")){
            rdmNum=new Random().nextInt(16)+1;
            quesNum=3;
            quesPower=rdmNum;
            ans= (long) Math.pow(quesNum,quesPower);
            rdmAns_1=quesPower+1;
            rdmAns_2=quesPower+2;
            rdmAns_3=quesPower-1;
            list_ans_a_int=BigInteger.valueOf(ans);
            list_ans_b_int=BigInteger.valueOf((long)Math.pow(quesNum,rdmAns_1));
            list_ans_c_int=BigInteger.valueOf((long)Math.pow(quesNum,rdmAns_2));
            list_ans_d_int=BigInteger.valueOf((long)Math.pow(quesNum, rdmAns_3));
        }
        if(memo_cat.equals("4")){
            quesNum=rdmNum;
            quesPower=2;
            ans= (long) Math.pow(quesNum,quesPower);
            rdmAns_1=quesNum+1;
            rdmAns_2=quesNum+2;
            rdmAns_3=quesNum-1;
            list_ans_a_int=BigInteger.valueOf(ans);
            list_ans_b_int=BigInteger.valueOf((long)Math.pow(rdmAns_1,quesPower));
            list_ans_c_int=BigInteger.valueOf((long)Math.pow(rdmAns_2,quesPower));
            list_ans_d_int=BigInteger.valueOf((long)Math.pow(rdmAns_3,quesPower));

        }
        if(memo_cat.equals("5")){
            quesNum=new Random().nextInt(10)+1;
            long rdmNum2= new Random().nextInt(15)+1;
            quesPower=rdmNum2;
            ans= (long) Math.pow(quesNum,quesPower);
            rdmAns_1=quesPower+1;
            rdmAns_2=quesPower+2;
            rdmAns_3=quesPower-01;
                list_ans_a_int=BigInteger.valueOf(ans);
                list_ans_b_int=BigInteger.valueOf((long)Math.pow(rdmNum,rdmAns_1));
                list_ans_c_int=BigInteger.valueOf((long)Math.pow(rdmNum,rdmAns_2));
                list_ans_d_int=BigInteger.valueOf((long)Math.pow(rdmNum, rdmAns_3));
        }

        int quesNum_int=(int) quesNum;
        int quesPower_int=(int) quesPower;
        text_x.setText(Integer.toString(quesNum_int));
        text_opr.setText(Integer.toString(quesPower_int));

        rdm_ans_add=  new Random().nextInt(3)+1;

        list_ans_a=list_ans_a_int.toString();
        list_ans_b=list_ans_b_int.toString();
        list_ans_c=list_ans_c_int.toString();
        list_ans_d=list_ans_d_int.toString();

        if(rdm_ans==0) ansOpt = new String[]{list_ans_a, list_ans_b, list_ans_c, list_ans_d};
        if(rdm_ans==1) ansOpt = new String[]{list_ans_b, list_ans_c, list_ans_d, list_ans_a};
        if(rdm_ans==2) ansOpt = new String[]{list_ans_c, list_ans_d, list_ans_a, list_ans_b};
        if(rdm_ans==3) ansOpt = new String[]{list_ans_d, list_ans_a, list_ans_b, list_ans_c};

        optListAdapter adapter=new optListAdapter(this.getActivity(),ansOpt,ansCheck,imgs);
        listView_opt.setAdapter(adapter);
        listView_opt.setOnItemClickListener(this);

        if(stopTimer==false){
            countDownTimer = new CountDownTimer(timeMax, decTime) {

                @Override
                public void onTick(long millisUntilFinished) {

                    tme.setText("Time Left:" + millisUntilFinished / 1000);
                    remTime = millisUntilFinished;
                    stopTimer=true;
                }

                @Override
                public void onFinish() {

                    int i;
                    rightAns_quiz=rightAns_quiz-1;
                    for(i=0;i<4;i++){
                        if(ansOpt[i].equals(list_ans_a)){
                            long rID = listView_opt.getItemIdAtPosition(i);
                            TextView rAns= (TextView) rootView.findViewById(R.id.textView_tittle_opt);
                            onItemClick(listView_opt,rAns,i,rID);
                            tme.setText("Times Up!!");
                            ansCheck[i]="Right answer";
                        }
                    }
                }
            }.start();
        }
        else{
            countDownTimer.cancel();
            countDownTimer = new CountDownTimer(timeMax, decTime) {
                @Override
                public void onTick(long millisUntilFinished) {

                    tme.setText("Time Left:" + millisUntilFinished / 1000);
                    remTime = millisUntilFinished;
                }

                @Override
                public void onFinish() {

                    int i;
                    rightAns_quiz=rightAns_quiz-1;
                    for(i=0;i<4;i++){
                        if(ansOpt[i].equals(list_ans_a)){
                            long rID = listView_opt.getItemIdAtPosition(i);
                            TextView rAns= (TextView) rootView.findViewById(R.id.textView_tittle_opt);
                            onItemClick(listView_opt,rAns,i,rID);
                            tme.setText("Times Up!!");
                            ansCheck[i]="Right answer";
                        }
                    }
                }
            }.start();
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ansCheck= new String[]{"","","",""};
        countDownTimer.cancel();
        tme.setText("Time Left:" + remTime / 1000);
        final SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if(ansOpt[position].equals(list_ans_a)){
            rightAns_quiz=rightAns_quiz+1;

            ansCheck[position]="Right answer";
        }
        else {

            wrongAns_quiz=wrongAns_quiz+1;

            if(ansOpt[0].equals(list_ans_a)){
                ansCheck[position]="Your answer";
                ansCheck[0]="Right answer";
            }
            if(ansOpt[1].equals(list_ans_a)){
                ansCheck[position]="Your answer";
                ansCheck[1]="Right answer";
            }
            if(ansOpt[2].equals(list_ans_a)){
                ansCheck[position]="Your answer";
                ansCheck[2]="Right answer";
            }
            if(ansOpt[3].equals(list_ans_a)){
                ansCheck[position]="Your answer";
                ansCheck[3]="Right answer";
            }
        }
        optListAdapter adapter=new optListAdapter(this.getActivity(),ansOpt,ansCheck,imgs);
        listView_opt.setAdapter(adapter);
        listView_opt.setOnItemClickListener(null);
    }

    @Override
    public void onDestroyView() {
        if(cancelTimer==true){
            countDownTimer.cancel();
        }
        super.onDestroyView();
    }
}
