package com.example.vishnu.calculationspeed;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MemoriesMaterial extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdopter;
    String main_cat;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories_material);

        Bundle bundle = getIntent().getExtras();
        main_cat = bundle.getString("main_cat");

        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        textView= (TextView) findViewById(R.id.textView_tool);
        viewPagerAdopter=new ViewPagerAdapter(getSupportFragmentManager());
        if(main_cat.equals("3")){
            textView.setText("Memories");
            viewPagerAdopter.addFragment(new MemoTheory(),"Theory");
            viewPagerAdopter.addFragment(new MemoQuiz(),"Quiz");
        }
        if(main_cat.equals("4")){
            viewPagerAdopter.addFragment(new MemoTheory(),"Theory");
            textView.setText("Notes");
        }
        viewPager.setAdapter(viewPagerAdopter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
