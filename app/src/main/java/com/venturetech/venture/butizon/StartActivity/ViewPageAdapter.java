package com.venturetech.venture.butizon.StartActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.venturetech.venture.butizon.R;

public class ViewPageAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images={R.drawable.pa,R.drawable.pb,R.drawable.pc,R.drawable.pd,R.drawable.pe};

    public ViewPageAdapter(Context context) {
        this.context = context;
    }

    @Override

    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.costom,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView);

        imageView.setImageResource(images[position]);
        ViewPager viewPager=(ViewPager)container;
        viewPager.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp=(ViewPager)container;
        View view=(View)object;
        vp.removeView(view);

    }

}
