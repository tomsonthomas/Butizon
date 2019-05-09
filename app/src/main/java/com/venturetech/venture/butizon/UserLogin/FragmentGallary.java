package com.venturetech.venture.butizon.UserLogin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.venturetech.venture.butizon.R;

public class FragmentGallary extends Fragment {
    ViewFlipper viewFlipper,viewFlipperr;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_secod_page,container,false);
        int images[]= {R.drawable.tc,R.drawable.ta,R.drawable.spa,R.drawable.butis,R.drawable.butit,R.drawable.spaa,R.drawable.hairr,R.drawable.make,R.drawable.ba,R.drawable.ad};
        viewFlipper=view.findViewById(R.id.v_flip);
        int imagees[]= {R.drawable.td,R.drawable.tb,R.drawable.ga,R.drawable.gb,R.drawable.gc,R.drawable.gd,R.drawable.ge,R.drawable.gf,R.drawable.bb,R.drawable.aa,R.drawable.ab};

        viewFlipperr=view.findViewById(R.id.v_flipp);


        for (int image:images){
            Flipper(image);
        }
        for (int imagee:imagees){
            Flipperr(imagee);
        }
        return view;
    }
    public void Flipper(int images){
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);//2 sec
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }
    public void Flipperr(int imagees){
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(imagees);

        viewFlipperr.addView(imageView);
        viewFlipperr.setFlipInterval(2000);//2 sec
        viewFlipperr.setAutoStart(true);
        viewFlipperr.setInAnimation(getContext(),android.R.anim.slide_in_left);
        viewFlipperr.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }
}
