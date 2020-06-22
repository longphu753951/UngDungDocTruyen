package com.example.appdoctruyenandroid;

import android.app.Dialog;
import android.os.Bundle;

import com.example.appdoctruyenandroid.Adapters.ChapterAdapter;
import com.example.appdoctruyenandroid.Common.Common;
import com.example.appdoctruyenandroid.Models.Chapter;
import com.example.appdoctruyenandroid.Remote.IMyAPI;
import com.example.appdoctruyenandroid.Remote.RetrofitClient;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChapterScrollingActivity extends AppCompatActivity {
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    RecyclerView recycler_chapter1;
    Dialog dialog;
    private long backPressedTime = System.currentTimeMillis();
    private Toast backToast;
    ImageView imageView;
    boolean kiemTra = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_scrolling2);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        //init MyAPI
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView)findViewById(R.id.image_comic_chapter);
        Picasso.get().load(Common.selected_comic.getThumbnail()).resize(1000,600).centerCrop(Gravity.getAbsoluteGravity(Gravity.TOP,50)).into(imageView);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(Common.selected_comic.getTenTruyen());
        //Set nút like
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Kiểm tra truyện đã được like chưa
        compositeDisposable.add(iMyAPI.kiemTraTruyenYeuThich(Common.signin_TaiKhoan.getId(),Common.selected_comic.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                       if(aBoolean == true) {
                           fab.setImageResource(R.drawable.ic_baseline_favorite_24);
                           kiemTra = true;
                       }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getBaseContext(),"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                    }
                }));

        //set sự kiện nút like
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(kiemTra == false){
                    kiemTra = true;
                    compositeDisposable.add(iMyAPI.themTruyenYeuThich(Common.signin_TaiKhoan.getId(),Common.selected_comic.getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    fab.setImageResource(R.drawable.ic_baseline_favorite_24);
                                    Snackbar.make(view, s, Snackbar.LENGTH_LONG).show();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getBaseContext(),"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                                }
                            }));

                }
                else{

                    kiemTra = false;
                    compositeDisposable.add(iMyAPI.xoaTruyenYeuThich(Common.signin_TaiKhoan.getId(),Common.selected_comic.getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    fab.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                                    Snackbar.make(view, s, Snackbar.LENGTH_LONG).show();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getBaseContext(),"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                                }
                            }));

                    Snackbar.make(view, "Đã dislike truyện", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        //
        recycler_chapter1 = (RecyclerView) findViewById(R.id.recycler_chapter_scroll);
        recycler_chapter1.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_chapter1.setLayoutManager(layoutManager);
        recycler_chapter1.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));
        //
        fetchChapter(Common.selected_comic.getId());

    }
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    

    private void fetchChapter(int comicId) {
        dialog= new SpotsDialog.Builder()
                .setContext(ChapterScrollingActivity.this)
                .setMessage("Cứ từ từ rồi cháo cũng nhừ")
                .build();
         compositeDisposable.add(iMyAPI.getChapterList(comicId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Chapter>>() {
                    @Override
                    public void accept(List<Chapter> chapters) throws Exception {
                        Common.chapterList = chapters;
                        recycler_chapter1.setAdapter(new ChapterAdapter(getBaseContext(),chapters));
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(ChapterScrollingActivity.this, "Không lấy được chapter nào", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }));
    }
}