package com.example.vishnu.calculationspeed;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashSimple extends Fragment {

    int[] percent,percentTest;
    String[] name;
    String main_cat_1;
    PieChart pieChart,pieChart2;
    View rootView;
    int totalAmt_simple, totalRight_simple, totalWrong_simple,totalRightTest_simple,totalWrongTest_simple,totalAmtTest_simple;


    public DashSimple() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dash_simple, container, false);

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

        Bundle bundle = getActivity().getIntent().getExtras();
        main_cat_1 = bundle.getString("main_cat");
        final SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);

        totalAmt_simple=sharedPreferences.getInt("totalAmt_simple",0);
        totalRight_simple=sharedPreferences.getInt("totalRight_simple",0);
        totalWrong_simple=sharedPreferences.getInt("totalWrong_simple",0);
        totalAmtTest_simple=sharedPreferences.getInt("totalAmtTest_simple",0);
        totalRightTest_simple=sharedPreferences.getInt("totalRightTest_simple",0);
        totalWrongTest_simple=sharedPreferences.getInt("totalWrongTest_simple",0);

        int right=totalRight_simple;
        int wrong=totalWrong_simple;
        int notAtm=totalAmt_simple-( totalRight_simple+totalWrong_simple);

        int rightTest=totalRightTest_simple;
        int wrongTest=totalWrongTest_simple;
        int notAtmTest=totalAmtTest_simple-( totalRightTest_simple+totalWrongTest_simple);

        percent=new int[]{right,notAtm,wrong};
        name=new String[]{"Right","leaved","Wrong" };

        percentTest=new int[]{rightTest,notAtmTest,wrongTest};

        pieChart = (PieChart)rootView.findViewById(R.id.chart);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Quiz Performance");
        pieChart.setEntryLabelTextSize(10f);
        pieChart.animateY(1000);
        pieChart.setRotationEnabled(false);

        pieChart2 = (PieChart)rootView.findViewById(R.id.chart2);
        pieChart2.setEntryLabelColor(Color.BLACK);
        pieChart2.setCenterText("Test Performance");
        pieChart2.setEntryLabelTextSize(10f);
        pieChart2.animateY(1000);
        pieChart2.setRotationEnabled(false);

        setupPieChart();

        return rootView;
    }

    private void setupPieChart() {
        List<PieEntry> pieEntry=new ArrayList<>();
        List<PieEntry> pieEntry2=new ArrayList<>();
        for(int i=0;i<percent.length;i++){
            pieEntry.add(new PieEntry(percent[i],name[i]));
        }
        for(int i=0;i<percentTest.length;i++){
            pieEntry2.add(new PieEntry(percentTest[i],name[i]));
        }
        PieDataSet pieDataSet= new PieDataSet(pieEntry,"Performance");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextSize(10f);
        PieData pieData = new PieData(pieDataSet);

        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieDataSet pieDataSet2= new PieDataSet(pieEntry2,"Performance");
        pieDataSet2.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet2.setValueTextSize(10f);
        PieData pieData2 = new PieData(pieDataSet2);
        pieDataSet2.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        pieChart.setData(pieData);
        pieChart.invalidate();

        pieChart2.setData(pieData2);
        pieChart2.invalidate();
    }


    }
