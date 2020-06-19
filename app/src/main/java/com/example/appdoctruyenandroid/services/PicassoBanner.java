package com.example.appdoctruyenandroid.services;

import android.view.Gravity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ss.com.bannerslider.ImageLoadingService;

public class PicassoBanner implements ImageLoadingService {
    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.get().load(url).resize(600,300).centerCrop(Gravity.getAbsoluteGravity(Gravity.TOP,50)).into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.get().load(resource).resize(600,300).centerCrop(Gravity.getAbsoluteGravity(Gravity.TOP,50)).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Picasso.get().load(url).resize(600,300).centerCrop(Gravity.getAbsoluteGravity(Gravity.TOP,50)).placeholder(placeHolder).error(errorDrawable).into(imageView);
    }
}
