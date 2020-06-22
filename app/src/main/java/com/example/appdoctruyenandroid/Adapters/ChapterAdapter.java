package com.example.appdoctruyenandroid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appdoctruyenandroid.Common.Common;
import com.example.appdoctruyenandroid.Interface.IRecyclerOnClick;
import com.example.appdoctruyenandroid.Models.Chapter;
import com.example.appdoctruyenandroid.PageActivity;
import com.example.appdoctruyenandroid.R;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {

    Context context;
    List<Chapter> chapterList;

    public ChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chapter_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_chapter_number.setText(new StringBuilder(chapterList.get(position).getTenChuong()));

        Common.selected_chapter = chapterList.get(position);

        holder.setiRecyclerOnClick(new IRecyclerOnClick() {
            @Override
            public void onClick(View view, int position) {
                Intent pageIntent = new Intent(context, PageActivity.class);
                pageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(pageIntent);
                Common.chapter_index= position;
                Common.selected_chapter = chapterList.get(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_chapter_number ;
        IRecyclerOnClick iRecyclerOnClick;

        public void setiRecyclerOnClick(IRecyclerOnClick iRecyclerOnClick) {
            this.iRecyclerOnClick = iRecyclerOnClick;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_chapter_number = (TextView)itemView.findViewById(R.id.txt_chapter_number);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            iRecyclerOnClick.onClick(v,getAdapterPosition());
        }
    }
}
