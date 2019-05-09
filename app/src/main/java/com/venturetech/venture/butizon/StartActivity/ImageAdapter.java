package com.venturetech.venture.butizon.StartActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.venturetech.venture.butizon.R;

public class ImageAdapter extends PagerAdapter {

    Context mContext;

    ImageAdapter(Context context) {
        this.mContext = context;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((ImageView) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    private int[] sliderImageId = new int[]{ R.drawable.butizon, R.drawable.download};





    @Override
    public int getCount() {
        return sliderImageId.length;
    }


}
