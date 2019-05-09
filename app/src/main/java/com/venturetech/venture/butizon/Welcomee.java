package com.venturetech.venture.butizon;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.venturetech.venture.butizon.StartActivity.PreferenceManage;
import com.venturetech.venture.butizon.StartActivity.Sliding;

public class Welcomee extends AppCompatActivity {
    private ViewPager mpager;
    private int[] layouts={R.layout.choose,R.layout.choosing};
    private MpAdapter mpAdapter;


    private ViewPager viewPager;
    private Sliding.MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button  btnNext;
    private PreferenceManage prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomee);
        android.support.v7.app.ActionBar a = getSupportActionBar();
        a.hide();
        try {
            if (Build.VERSION.SDK_INT>=21){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            mpager=findViewById(R.id.view_pager);
            mpAdapter=new MpAdapter(layouts,this);
            mpager.setAdapter(mpAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
