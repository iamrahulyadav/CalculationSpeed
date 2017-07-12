package com.example.vishnu.calculationspeed;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoTheory extends Fragment {

    ListView listView_memo;
    String[] memo_num,memo_power,memo_equal,memo_result;
    View view;
    Resources res;
    CountDownTimer countDownTimer;
    public MemoTheory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_memo_theory, container, false);
        listView_memo= (ListView) view.findViewById(R.id.listView_memoTheory);
        Bundle bundle = getActivity().getIntent().getExtras();
        String memo_cat = bundle.getString("memo_cat");
        String main_cat = bundle.getString("main_cat");

        final AdView mAdView = (AdView) view.findViewById(R.id.adView);
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

        if(main_cat.equals("3")){
            if(memo_cat.equals("0")){
                res=getResources();
                memo_num= res.getStringArray(R.array.num);
                memo_power= res.getStringArray(R.array.two);
                memo_equal= res.getStringArray(R.array.memo_equal);
                memo_result= res.getStringArray(R.array.num_square);
            }
            if(memo_cat.equals("1")){
                res=getResources();
                memo_num= res.getStringArray(R.array.num);
                memo_power= res.getStringArray(R.array.three);
                memo_equal= res.getStringArray(R.array.memo_equal);
                memo_result= res.getStringArray(R.array.num_quebec);
            }
            if(memo_cat.equals("2")){
                res=getResources();
                memo_num= res.getStringArray(R.array.two);
                memo_power= res.getStringArray(R.array.num);
                memo_equal= res.getStringArray(R.array.memo_equal);
                memo_result= res.getStringArray(R.array.two_power);
            }
            if(memo_cat.equals("3")){
                res=getResources();
                memo_num= res.getStringArray(R.array.three);
                memo_power= res.getStringArray(R.array.num);
                memo_equal= res.getStringArray(R.array.memo_equal);
                memo_result= res.getStringArray(R.array.three_power);
            }
            if(memo_cat.equals("4")){
                res=getResources();
                memo_num= res.getStringArray(R.array.divisibility_num);
                memo_power= res.getStringArray(R.array.empty);
                memo_equal= res.getStringArray(R.array.Divisibility);
                memo_result= res.getStringArray(R.array.empty);
            }
            if(memo_cat.equals("5")){
                res=getResources();
                memo_num=res.getStringArray(R.array.fr_num);
                memo_power=res.getStringArray(R.array.fr_empty);
                memo_equal=res.getStringArray(R.array.frequency);
                memo_result=res.getStringArray(R.array.fr_empty);
            }
        }
        if(main_cat.equals("4")){

            if(memo_cat.equals("0")){
                res=getResources();
                memo_num= res.getStringArray(R.array.integers);
                memo_power= res.getStringArray(R.array.integers_empty);
                memo_equal= res.getStringArray(R.array.integers_description);
                memo_result= res.getStringArray(R.array.integers_empty);
            }
            if(memo_cat.equals("1")){
                res=getResources();
                memo_num= res.getStringArray(R.array.addition);
                memo_power= res.getStringArray(R.array.addition_empty);
                memo_equal= res.getStringArray(R.array.addition_description);
                memo_result= res.getStringArray(R.array.addition_empty);
            }
            if(memo_cat.equals("2")){
                res=getResources();
                memo_num= res.getStringArray(R.array.subtraction);
                memo_power= res.getStringArray(R.array.subtraction_empty);
                memo_equal= res.getStringArray(R.array.subtraction_description);
                memo_result= res.getStringArray(R.array.subtraction_empty);
            }
            if(memo_cat.equals("3")){
                res=getResources();
                memo_num= res.getStringArray(R.array.multi);
                memo_power= res.getStringArray(R.array.multi_empty);
                memo_equal= res.getStringArray(R.array.multi_description);
                memo_result= res.getStringArray(R.array.multi_empty);
            }
            if(memo_cat.equals("4")){
                res=getResources();
                memo_num= res.getStringArray(R.array.division);
                memo_power= res.getStringArray(R.array.division_empty);
                memo_equal= res.getStringArray(R.array.division_description);
                memo_result= res.getStringArray(R.array.division_empty);
            }
            if(memo_cat.equals("5")){
                res=getResources();
                memo_num= res.getStringArray(R.array.fraction);
                memo_power= res.getStringArray(R.array.fraction_empty);
                memo_equal= res.getStringArray(R.array.fraction_description);
                memo_result= res.getStringArray(R.array.fraction_empty);
            }
        }

        memoListAdapter adapter = new memoListAdapter(this.getActivity(),memo_num,memo_power,memo_equal,memo_result);
        listView_memo.setAdapter(adapter);


        return view;
    }

}

class memoListViewHolder {
    TextView memo_num_holder,memo_power_holder,memo_equal_holder,memo_result_holder;
    memoListViewHolder(View v){
        memo_num_holder= (TextView) v.findViewById(R.id.textView_num);
        memo_power_holder= (TextView) v.findViewById(R.id.textView_power);
        memo_equal_holder= (TextView) v.findViewById(R.id.textView_equal);
        memo_result_holder= (TextView) v.findViewById(R.id.textView_result);
    }
}

class memoListAdapter extends ArrayAdapter<String> {
    Context context;
    String[] memo_num_Array;
    String[] memo_power_Array;
    String[] memo_equal_Array;
    String[] memo_result_Array;

    public memoListAdapter(Context c,String[] memo_num_View,String[]memo_power_View,String[] memo_equal_View,String[]memo_result_View) {
        super(c, R.layout.single_memo_theory,R.id.textView_num,memo_num_View);
        this.memo_num_Array=memo_num_View;
        this.memo_power_Array=memo_power_View;
        this.memo_equal_Array=memo_equal_View;
        this.memo_result_Array=memo_result_View;
        this.context=c;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row = convertView;
        memoListViewHolder holder = null;
        if (row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_memo_theory,parent,false);
            holder=new memoListViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder= (memoListViewHolder) row.getTag();
        }

        holder.memo_num_holder.setText(memo_num_Array[i]);
        holder.memo_power_holder.setText(memo_power_Array[i]);
        holder.memo_equal_holder.setText(memo_equal_Array[i]);
        holder.memo_result_holder.setText(memo_result_Array[i]);
        if (memo_result_Array[0].equals("")){
            holder.memo_num_holder.setTextSize(20);
            holder.memo_equal_holder.setTextSize(15);
        }


        return row;
    }
}


