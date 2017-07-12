package com.example.vishnu.calculationspeed;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] mainMenu_name_data,mainMenu_hint;
    RoundedBitmapDrawable[] mainMenu_image_data;
    GridView myGrid;
    RoundedBitmapDrawable[] roundedBitmapDrawable;
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        myGrid= (GridView) findViewById(R.id.gridView_main);
        MobileAds.initialize(this,"ca-app-pub-3253643817495351~4110127224");
        Point size =new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        int columnsWidth= width/2-6;
        myGrid.setColumnWidth(columnsWidth);

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

        Resources res = getResources();
        mainMenu_name_data=res.getStringArray(R.array.mainMenu_name_res);
        mainMenu_hint=new String[]{"","","","","",""};


        int[] imgs=new int[]{R.drawable.quiz,R.drawable.test,R.drawable.dashboard,R.drawable.memories,
                R.drawable.notes,R.drawable.rate_app};

        ArrayList<Bitmap> bitmaps=new ArrayList<>();
        ArrayList<RoundedBitmapDrawable>roundedBitmapDrawables=new ArrayList<>();

        for(int i=0;i<imgs.length;i++){
            bitmaps.add(BitmapFactory.decodeResource(getResources(),imgs[i]));
            roundedBitmapDrawables.add(RoundedBitmapDrawableFactory.create(getResources(),bitmaps.get(i)));
            roundedBitmapDrawables.get(i).setCircular(true);
        }

        roundedBitmapDrawables.toArray();
        roundedBitmapDrawable= new RoundedBitmapDrawable[roundedBitmapDrawables.size()];
        roundedBitmapDrawable=roundedBitmapDrawables.toArray(roundedBitmapDrawable);

        myGridAdapter adopter=new myGridAdapter(this,mainMenu_name_data,mainMenu_hint,roundedBitmapDrawable);
        myGrid.setAdapter(adopter);
        myGrid.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        if(i==0){

            Intent cat_page = new Intent(MainMenu.this,LevelDialog.class);
            String row_position = Integer.toString(i);
            cat_page.putExtra("main_cat",row_position);
            startActivity(cat_page);
/*
            // Prepare the Interstitial Ad
            interstitial = new InterstitialAd(MainMenu.this);
// Insert the Ad Unit ID
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    // Call displayInterstitial() function
                    displayInterstitial();
                }
            });*/



        }
        if(i==1){

            Intent cat_page = new Intent(MainMenu.this,LevelDialog.class);
            String row_position = Integer.toString(i);
            cat_page.putExtra("main_cat",row_position);
            startActivity(cat_page);
        }
        if(i==2){

            Intent cat_page = new Intent(MainMenu.this,Dashboard.class);
            String row_position = Integer.toString(i);
            cat_page.putExtra("main_cat",row_position);
            startActivity(cat_page);
        }

        if(i==3){
            Intent memo_page = new Intent(MainMenu.this,Memories.class);
            String row_position = Integer.toString(i);
            memo_page.putExtra("main_cat",row_position);
            startActivity(memo_page);

        }
        if(i==4){
            Intent notes_page = new Intent(MainMenu.this,Memories.class);
            String row_position = Integer.toString(i);
            notes_page.putExtra("main_cat",row_position);
            startActivity(notes_page);
        }
        if(i==5){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.vishnu.calculationspeed"));
            startActivity(intent);
        }
    }

   /* public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details?id=com.vishnu.calculationspeed");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details?id=com.vishnu.calculationspeed");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }*/

}
