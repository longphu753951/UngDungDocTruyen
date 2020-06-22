package com.example.appdoctruyenandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appdoctruyenandroid.Adapters.ComicAdapter;
import com.example.appdoctruyenandroid.Adapters.PageAdapter;
import com.example.appdoctruyenandroid.Common.Common;
import com.example.appdoctruyenandroid.Models.Page;
import com.example.appdoctruyenandroid.Remote.IMyAPI;
import com.example.appdoctruyenandroid.Remote.RetrofitClient;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    View back, next;
    AppBarLayout appBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        back = findViewById(R.id.chapter_back);
        next = findViewById(R.id.chapter_next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Common.chapter_index == Common.chapterList.size()-1){
                    Toast.makeText(PageActivity.this, "Bạn đang đọc chương đầu ", Toast.LENGTH_SHORT).show();

                }
                else{
                    Common.chapter_index++;
                    fetchPage(Common.chapterList.get(Common.chapter_index).getId());
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.chapter_index==0){
                    Toast.makeText(PageActivity.this, "Hiện tại chưa có chương mới, vui lòng đợi", Toast.LENGTH_SHORT).show();

                }
                else{
                    Common.chapter_index--;
                    fetchPage(Common.chapterList.get(Common.chapter_index).getId());
                }
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_pages);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        fetchPage(Common.selected_chapter.getId());
    }

    private void fetchPage(int id) {
        compositeDisposable.add(iMyAPI.getPageList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Page>>() {
                    @Override
                    public void accept(List<Page> pages) throws Exception {
                        recyclerView.setAdapter(new PageAdapter(getBaseContext(), pages));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(PageActivity.this, "Lỗi khi load trang", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}