package com.example.vishnu.calculationspeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class Quiz extends AppCompatActivity implements AdapterView.OnItemClickListener {

    int rdm_opr,rdm_x,rdm_y,rdm_z,rdm_opr2,ans,rdm_ans,rdm_ans_add,level_redm,rdm_rag;
    int  rightAns_simple=0,wrongAns_simple=0,amtAns_simple=0,rightAns_tough=0,wrongAns_tough=0,amtAns_tough=0,highScore_simple=0,highScore_tough=0;
    String list_ans_a,list_ans_b, list_ans_c,list_ans_d,diff_level,finalType_cat,main_cat,level;
    String[] ansOpt;
    TextView text_opr,text_x,text_y,text_z,text_opr2,tme,textView_score,textView_highScore;
    ListView listView_opt;
    String[] imgs={"A","B","C","D"};
    Button btn_end,btn_next;
    String[] ansCheck ={"","","",""};
    boolean stopTimer=false,end_check=true;
    long timeMax = 11000;     //10 sec = 10*1000 milli sec
    long decTime = 1000;        // 1 sec decrease
    long remTime=10;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        main_cat=bundle.getString("main_cat");
        level=bundle.getString("level");
        //finalType_cat = bundle.getString("cat_type");

        //level_redm=Integer.valueOf(diff_level) +1 ;
        //rdm_rag = level_redm*5;

        final SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("rightAns_simple",rightAns_simple);
        editor.putInt("wrongAns_simple",wrongAns_simple);
        editor.putInt("amtAns_simple",amtAns_simple);
        editor.putInt("rightAns_tough",rightAns_tough);
        editor.putInt("wrongAns_tough",wrongAns_tough);
        editor.putInt("amtAns_tough",amtAns_tough);
        editor.commit();

        if( level.equals("simple")){

            setContentView(R.layout.activity_quiz);

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

            text_opr= (TextView) findViewById(R.id.textView_opr);
            text_x= (TextView) findViewById(R.id.plus_x);
            text_y= (TextView) findViewById(R.id.plus_y);
            btn_next= (Button) findViewById(R.id.button_next);
            btn_end= (Button) findViewById(R.id.button_end);
            listView_opt= (ListView) findViewById(R.id.listView_opt);
            textView_score= (TextView) findViewById(R.id.textView_Score);
            textView_highScore= (TextView) findViewById(R.id.textView_highScore);


            tme = (TextView) findViewById(R.id.textView_Timer);

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(end_check==false){
                        Toast.makeText(Quiz.this,"First Answer this Quiz",Toast.LENGTH_LONG).show();

                    }

                    if(end_check==true){
                        end_check=false;
                        //btn_end.setEnabled(true);
                        //btn_end.setBackgroundResource(R.drawable.button_shape);
                        //btn_next.setText("Next Quiz");
                        amtAns_simple=amtAns_simple+1;
                        highScore_simple = sharedPreferences.getInt("highScore_simple", 0);

                        if(rightAns_simple>highScore_simple){
                            editor.putInt("highScore_simple",rightAns_simple);
                            editor.commit();
                            textView_highScore.setText("Highest Score:"+rightAns_simple);
                            Toast.makeText(Quiz.this,"New Highest Score",Toast.LENGTH_LONG).show();
                        }
                        else{
                            textView_highScore.setText("Highest Score:"+highScore_simple);
                        }

                        textView_score.setText("Score:"+rightAns_simple+"/"+amtAns_simple);

                        setValue_single();

                    }

                }
            });

            btn_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        countDownTimer.cancel();
                        editor.putInt("amtAns_simple",amtAns_simple);
                        editor.commit();
                        Intent intent = new Intent(Quiz.this, MyDialog.class);
                        intent.putExtra("level", level);
                        intent.putExtra("main_cat", main_cat);
                        startActivity(intent);

                }
            });
            btn_next.callOnClick();
        }
        if(level.equals("tough")){

            setContentView(R.layout.quiz_double);

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

            text_opr= (TextView) findViewById(R.id.textView_opr);
            text_x= (TextView) findViewById(R.id.plus_x);
            text_y= (TextView) findViewById(R.id.plus_y);
            text_z=(TextView)findViewById(R.id.plus_z);
            text_opr2=(TextView)findViewById(R.id.textView_opr2);
            tme = (TextView) findViewById(R.id.textView_Timer);
            btn_next= (Button) findViewById(R.id.button_next);
            btn_end= (Button) findViewById(R.id.button_end);
            listView_opt= (ListView) findViewById(R.id.listView_opt);
            textView_score= (TextView) findViewById(R.id.textView_Score);
            textView_highScore= (TextView) findViewById(R.id.textView_highScore);

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(end_check==false){
                        Toast.makeText(Quiz.this,"First Answer this Quiz",Toast.LENGTH_LONG).show();

                    }

                    if(end_check==true){
                        end_check=false;
                        //btn_end.setEnabled(true);
                        //btn_end.setBackgroundResource(R.drawable.button_shape);
                        //btn_next.setText("Next Quiz");
                        amtAns_tough=amtAns_tough+1;
                        highScore_tough = sharedPreferences.getInt("highScore_tough", 0);

                        if(rightAns_tough>highScore_tough){
                            editor.putInt("highScore_tough",rightAns_tough);
                            editor.commit();
                            textView_highScore.setText("Highest Score:"+rightAns_tough);
                            Toast.makeText(Quiz.this,"New Highest Score",Toast.LENGTH_LONG).show();
                        }
                        else{
                            textView_highScore.setText("Highest Score:"+highScore_tough);
                        }

                        textView_score.setText("Score:"+rightAns_tough+"/"+amtAns_tough);

                        setValue_double();
                    }
                }
            });

            btn_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    countDownTimer.cancel();
                    editor.putInt("amtAns_tough",amtAns_tough);
                    editor.commit();
                    Intent intent = new Intent(Quiz.this, MyDialog.class);
                    String diff_level="tough";
                    intent.putExtra("level", diff_level);
                    String mainCat= "0";
                    intent.putExtra("main_cat", mainCat);
                    startActivity(intent);
                }
            });
            btn_next.callOnClick();
        }

    }

    @Override
    public void onBackPressed(){
        btn_end.callOnClick();
    }

    public void setValue_single(){
        ansCheck =new String[]{"","","",""};
        rdm_opr = new Random().nextInt(4);
        rdm_ans = new Random().nextInt(3);

        rdm_y=new Random().nextInt(5)+(int)(amtAns_simple*0.25)+2;
        rdm_x = (1+new Random().nextInt(rdm_y)) * rdm_y;

        text_x.setText(Integer.toString(rdm_x));
        text_y.setText(Integer.toString(rdm_y));
        if (rdm_opr==0 ){
            text_opr.setText("+");
            ans=rdm_x+rdm_y;
        }
        if (rdm_opr==1){
            text_opr.setText("-");
            ans=rdm_x-rdm_y;
        }
        if (rdm_opr==2){
            text_opr.setText("x");
            ans=rdm_x*rdm_y;
        }
        else {
            text_opr.setText("÷");
            ans=rdm_x/rdm_y;
        }
        rdm_ans_add= new Random().nextInt(3)+1;
        list_ans_a=Integer.toString(ans);
        list_ans_b=Integer.toString(ans+rdm_ans_add*5);
        list_ans_c=Integer.toString(ans-rdm_ans_add);
        list_ans_d=Integer.toString(ans+rdm_ans_add+2);

        if(rdm_ans==0) ansOpt = new String[]{list_ans_a, list_ans_b, list_ans_c, list_ans_d};
        if(rdm_ans==1) ansOpt = new String[]{list_ans_b, list_ans_c, list_ans_d, list_ans_a};
        if(rdm_ans==2) ansOpt = new String[]{list_ans_c, list_ans_d, list_ans_a, list_ans_b};
        if(rdm_ans==3) ansOpt = new String[]{list_ans_d, list_ans_a, list_ans_b, list_ans_c};

        optListAdapter adapter=new optListAdapter(this,ansOpt,ansCheck,imgs);
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
                    rightAns_simple=rightAns_simple-1;
                    /*for(i=0;i<4;i++){
                        if(ansOpt[i].equals(list_ans_a)){
                            long rID = listView_opt.getItemIdAtPosition(i);
                            TextView rAns= (TextView) findViewById(R.id.textView_tittle_opt);
                            onItemClick(listView_opt,rAns,i,rID);
                            tme.setText("Times Up!!");
                            ansCheck[i]="Right answer";
                        }
                    }*/
                    btn_end.callOnClick();
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
                    rightAns_simple=rightAns_simple-1;
                    /*for(i=0;i<4;i++){
                        if(ansOpt[i].equals(list_ans_a)){
                            long rID = listView_opt.getItemIdAtPosition(i);
                            TextView rAns= (TextView) findViewById(R.id.textView_tittle_opt);
                            onItemClick(listView_opt,rAns,i,rID);
                            tme.setText("Times Up!!");
                            ansCheck[i]="Right answer";
                        }
                    }*/
                    btn_end.callOnClick();
                }
            }.start();
        }

    }

    public void setValue_double(){
        ansCheck =new String[]{"","","",""};

        rdm_opr2= new Random().nextInt(1);
        rdm_opr = new Random().nextInt(3);
        rdm_ans = new Random().nextInt(3);

        int amtAns_rdm = (int)(amtAns_tough*0.25)+1;

        rdm_y=new Random().nextInt(3)+amtAns_rdm + 1;
        rdm_x = (1+new Random().nextInt(5)+amtAns_rdm) * rdm_y;
        rdm_z= new Random().nextInt(10) + amtAns_rdm;

        text_x.setText(Integer.toString(rdm_x));
        text_y.setText(Integer.toString(rdm_y));
        text_z.setText(Integer.toString(rdm_z));
        if(rdm_opr2==0){
            text_opr2.setText("+");
            if (rdm_opr==0 ){
                text_opr.setText("+");
                ans=rdm_x+rdm_y+rdm_z;
            }
            if (rdm_opr==1){
                text_opr.setText("-");
                ans=rdm_x-rdm_y+rdm_z;
            }
            if (rdm_opr==2){
                text_opr.setText("x");
                ans=rdm_x*rdm_y+rdm_z;
            }
            if(rdm_opr==3){
                text_opr.setText("÷");
                ans=rdm_x/rdm_y+rdm_z;
            }
        }
        if(rdm_opr2==1){
            text_opr2.setText("-");
            if (rdm_opr==0 ){
                text_opr.setText("+");
                ans=rdm_x+rdm_y-rdm_z;
            }
            if (rdm_opr==1){
                text_opr.setText("-");
                ans=rdm_x-rdm_y-rdm_z;
            }
            if (rdm_opr==2){
                text_opr.setText("x");
                ans=rdm_x*rdm_y-rdm_z;
            }
            if(rdm_opr==3){
                text_opr.setText("÷");
                ans=rdm_x/rdm_y-rdm_z;
            }
        }

        rdm_ans_add= new Random().nextInt(3)+1;
        int  rdm_ans_add2= new Random().nextInt(5)+1;
        list_ans_a=Integer.toString(ans);
        list_ans_b=Integer.toString(ans+rdm_ans_add*10);
        list_ans_c=Integer.toString(ans-rdm_ans_add2);
        list_ans_d=Integer.toString(ans+rdm_ans_add+2);

        if(rdm_ans==0) ansOpt = new String[]{list_ans_a, list_ans_b, list_ans_c, list_ans_d};
        if(rdm_ans==1) ansOpt = new String[]{list_ans_b, list_ans_c, list_ans_d, list_ans_a};
        if(rdm_ans==2) ansOpt = new String[]{list_ans_c, list_ans_d, list_ans_a, list_ans_b};
        if(rdm_ans==3) ansOpt = new String[]{list_ans_d, list_ans_a, list_ans_b, list_ans_c};

        final optListAdapter adapter=new optListAdapter(this,ansOpt,ansCheck,imgs);
        listView_opt.setAdapter(adapter);
        listView_opt.setOnItemClickListener(this);

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
                    int i;
                    rightAns_tough=rightAns_tough-1;
                    /*for(i=0;i<4;i++){
                        if(ansOpt[i].equals(list_ans_a)){
                            long rID = listView_opt.getItemIdAtPosition(i);
                            TextView rAns= (TextView) findViewById(R.id.textView_tittle_opt);
                            onItemClick(listView_opt,rAns,i,rID);
                            ansCheck[i]="Right answer";
                        }
                    }*/
                    //tme.setText("Times Up!!");
                    btn_end.callOnClick();
                }

            }.start();
        }
        else {
            countDownTimer.cancel();
            countDownTimer= new CountDownTimer(timeMax, decTime) {

                @Override
                public void onTick(long millisUntilFinished) {
                    tme.setText("Time Left:" + millisUntilFinished / 1000);
                    remTime = millisUntilFinished;
                }

                @Override
                public void onFinish() {
                    int i;
                    rightAns_tough=rightAns_tough-1;
                    /*for(i=0;i<4;i++){
                        if(ansOpt[i].equals(list_ans_a)){
                            long rID = listView_opt.getItemIdAtPosition(i);
                            TextView rAns= (TextView) findViewById(R.id.textView_tittle_opt);
                            onItemClick(listView_opt,rAns,i,rID);
                            ansCheck[i]="Right answer";
                        }
                    }*/
                    //tme.setText("Times Up!!");
                    btn_end.callOnClick();
                }
            }.start();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        end_check=true;

        ansCheck= new String[]{"","","",""};
        countDownTimer.cancel();
        tme.setText("Time Left:" + remTime / 1000);
        final SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if(ansOpt[position].equals(list_ans_a)){

            if(level.equals("simple")){
                rightAns_simple=rightAns_simple+1;
                editor.putInt("rightAns_simple",rightAns_simple);
                editor.commit();
            }
            if(level.equals("tough")) {
                rightAns_tough = rightAns_tough + 1;
                editor.putInt("rightAns_tough", rightAns_tough);
                editor.commit();
            }
            ansCheck[position]="Right answer";
            btn_next.callOnClick();

        }

        else {
                if(level.equals("simple")){
                    wrongAns_simple=wrongAns_simple+1;
                    editor.putInt("wrongAns_simple",wrongAns_simple);
                    editor.commit();
                }
                if(level.equals("tough")){
                    wrongAns_tough=wrongAns_tough+1;
                    editor.putInt("wrongAns_tough",wrongAns_tough);
                    editor.commit();
                }

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

            btn_end.callOnClick();
        }
        //optListAdapter adapter=new optListAdapter(this,ansOpt,ansCheck,imgs);
        ///listView_opt.setAdapter(adapter);
        //listView_opt.setOnItemClickListener(null);

    }
}

class optListViewHolder {
    TextView opt_tittle_holder,opt_hint_holder,opt_img_holder;
    optListViewHolder(View v){
        opt_tittle_holder= (TextView) v.findViewById(R.id.textView_tittle_opt);
        opt_hint_holder= (TextView) v.findViewById(R.id.textView_hint_opt);
        opt_img_holder= (TextView) v.findViewById(R.id.imageView_opt);
    }
}

class optListAdapter extends ArrayAdapter<String> {
    Context context;
    String[] opt_tittle_Array;
    String[] opt_hint_Array;
    String[] opt_img_Array;

    public optListAdapter(Context c,String[] opt_tittle_View,String[] opt_hint_View,String[]opt_img_View) {
        super(c, R.layout.single_list_opt,R.id.textView_tittle_opt,opt_tittle_View);
        this.opt_tittle_Array=opt_tittle_View;
        this.opt_hint_Array=opt_hint_View;
        this.opt_img_Array=opt_img_View;
        this.context=c;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row = convertView;
        optListViewHolder holder = null;
        if (row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_list_opt,parent,false);
            holder=new optListViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder= (optListViewHolder) row.getTag();
        }

        holder.opt_img_holder.setText(opt_img_Array[i]);
        holder.opt_tittle_holder.setText(opt_tittle_Array[i]);
        holder.opt_hint_holder.setText(opt_hint_Array[i]);
        if(opt_hint_Array[i].equals("Right answer")){
            holder.opt_hint_holder.setBackground(new ColorDrawable(Color.GREEN));
        }
        if(opt_hint_Array[i].equals("Your answer")){
            holder.opt_hint_holder.setBackground(new ColorDrawable(Color.RED));
        }

        return row;
    }
}
