package com.example.appdoctruyenandroid.Adapters;

import com.example.appdoctruyenandroid.Models.Banner;

import java.util.List;

import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class SliderAdapter extends ss.com.bannerslider.adapters.SliderAdapter {
    private List<Banner> bannerList;
    public SliderAdapter(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        imageSlideViewHolder.bindImageSlide(bannerList.get(position).getThumbnail());

    }
}
