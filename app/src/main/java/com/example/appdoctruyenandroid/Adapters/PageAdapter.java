package com.example.appdoctruyenandroid.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyenandroid.Models.Comic;
import com.example.appdoctruyenandroid.Models.Page;
import com.example.appdoctruyenandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {

    Context context;
    List<Page> pageList;

    public PageAdapter(Context context, List<Page> pageList) {
        this.context = context;
        this.pageList = pageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pageView = LayoutInflater.from(context).inflate(R.layout.page_item,parent,false);
        return new MyViewHolder(pageView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(pageList.get(position).getThumbnail()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.page_view);
        }
    }
}
