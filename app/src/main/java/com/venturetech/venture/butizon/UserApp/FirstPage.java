package com.venturetech.venture.butizon.UserApp;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.StartActivity.ImageModel;
import com.venturetech.venture.butizon.StartActivity.Sliding_Image_Adapter;
import com.venturetech.venture.butizon.UserLogin.UserLogin;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.paraffinn, R.drawable.acneee,

            R.drawable.golden,R.drawable.fruit,R.drawable.aromatherapy,R.drawable.galvanic,R.drawable.collagen};
    ViewFlipper viewFlipper;//viewFlipperr;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        imageView=findViewById(R.id.click);
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstPage.this,UserLogin.class);
                startActivity(intent);
            }
        });

        int images[]= {R.drawable.spa,R.drawable.butis,R.drawable.butit,R.drawable.spaa,R.drawable.hairr,R.drawable.make,R.drawable.ba,R.drawable.ad};
        viewFlipper=findViewById(R.id.v_flip);


        for (int image:images){
            Flipper(image);
        }
    }
    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(new Sliding_Image_Adapter(FirstPage.this, imageModelArrayList));


        final float density = getResources().getDisplayMetrics().density;


        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
    }
    public void Flipper(int images){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);//2 sec
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }

    }

