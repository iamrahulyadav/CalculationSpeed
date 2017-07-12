package com.example.vishnu.calculationspeed;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class Memories extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] memo_name_data,memo_hint,memo_image_data;
    GridView myGrid;
    String main_cat;
    TextView textView_memoTitle;
    int[] imgs;
    RoundedBitmapDrawable[] roundedBitmapDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
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




        Bundle bundle = getIntent().getExtras();
        main_cat = bundle.getString("main_cat");
        textView_memoTitle= (TextView) findViewById(R.id.textView_memoTitle);

        myGrid= (GridView) findViewById(R.id.gridView);
        Resources res = getResources();
        if(main_cat.equals("3")){
            imgs=new int[]{R.drawable.num_square,R.drawable.num_qube,R.drawable.two_power,R.drawable.three_power,
                    R.drawable.divisibility,R.drawable.frequancy};

            memo_name_data=res.getStringArray(R.array.memo_tittle_res);
            memo_hint=res.getStringArray(R.array.memo_hint_res);
            textView_memoTitle.setText("Memories");
        }
        if(main_cat.equals("4")){

            imgs=new int[]{R.drawable.integer,R.drawable.addition,R.drawable.subtraction,R.drawable.multipliction,
                    R.drawable.division,R.drawable.fraction};

            memo_name_data=res.getStringArray(R.array.notes_tittle_res);
            memo_hint=res.getStringArray(R.array.memo_hint_res);
            textView_memoTitle.setText("Notes");
        }

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

        Point size =new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        int columnsWidth= width/2-6;
        myGrid.setColumnWidth(columnsWidth);

        myMemoGridAdapter adopter=new myMemoGridAdapter(this,memo_name_data,memo_hint,roundedBitmapDrawable);
        myGrid.setAdapter(adopter);
        myGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(Memories.this,MemoriesMaterial.class);
        String memo_cat=Integer.toString(position);
        intent.putExtra("memo_cat",memo_cat);
        intent.putExtra("main_cat",main_cat);
        startActivity(intent);
    }
}
class myMemoGridAdapter extends ArrayAdapter<String> {

    Context context;
    String [] titleArray;
    String [] descriptionArray;
    RoundedBitmapDrawable[] imgArray;
    myMemoGridAdapter(Context c,String[] titles,String[] dsc,RoundedBitmapDrawable[] img){
        super(c,R.layout.single_grid_memo,R.id.textView_title_grid,titles);
        this.context=c;
        this.titleArray=titles;
        this.descriptionArray=dsc;
        this.imgArray=img;
    }
    class myViewHolder{
        ImageView myImage;
        TextView myTitle;
        TextView myDescription;
        myViewHolder(View v){
            myImage= (ImageView) v.findViewById(R.id.imageView_grid);
            myTitle= (TextView) v.findViewById(R.id.textView_title_grid);
            myDescription= (TextView) v.findViewById(R.id.textView_hint_grid);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        myViewHolder holder = null;
        if (row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_grid_memo,parent,false);
            holder=new myViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder= (myViewHolder) row.getTag();
        }

        holder.myImage.setImageDrawable(imgArray[position]);
        holder.myTitle.setText(titleArray[position]);
        holder.myDescription.setText(descriptionArray[position]);

        return row;
    }
}

