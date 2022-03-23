package com.example.moviestreaming.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.moviestreaming.R;

import java.util.List;

public class ViewPager_Adapter extends PagerAdapter {
    Context  context;
    List<Image> items;

    public ViewPager_Adapter(Context context, List<Image> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v= LayoutInflater.from(context).inflate(R.layout.viewpager_layout,null);
        ImageView i=v.findViewById(R.id.pager_image);
        Glide.with(context).load(items.get(position).getImage_s()).into(i);
        ViewPager viewPager=(ViewPager) container;
        viewPager.addView(v);


        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager) container;
        View vv=(View) object;
        viewPager.removeView(vv);
    }
}
