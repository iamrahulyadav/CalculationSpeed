package com.example.vishnu.calculationspeed;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vishnu on 07-Apr-17.
 */
class myGridAdapter extends ArrayAdapter<String> {

    Context context;
    String [] titleArray;
    String [] descriptionArray;
    RoundedBitmapDrawable[] imgArray;
    myGridAdapter(Context c,String[] titles,String[] dsc,RoundedBitmapDrawable[] img){
        super(c,R.layout.single_grid,R.id.textView_title_grid,titles);
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
            myImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        myViewHolder holder = null;
        if (row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_grid,parent,false);
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

