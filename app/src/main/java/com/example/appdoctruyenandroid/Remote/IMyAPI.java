package com.example.appdoctruyenandroid.Remote;

import com.example.appdoctruyenandroid.Models.Banner;
import com.example.appdoctruyenandroid.Models.Category;
import com.example.appdoctruyenandroid.Models.Chapter;
import com.example.appdoctruyenandroid.Models.Comic;
import com.example.appdoctruyenandroid.Models.Page;
import com.example.appdoctruyenandroid.Models.TaiKhoanDto;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableToList;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMyAPI {
     @POST("api/login")
     Observable<TaiKhoanDto> login(@Body TaiKhoanDto taiKhoanDto);
     @POST("api/register")
     Observable<String> register(@Body TaiKhoanDto taiKhoanDto);
     @GET("api/Truyen/like")
     Observable<List<Banner>> getLikeTruyen();
     @GET("api/Truyen/new")
     Observable<List<Comic>> getNewTruyen();
     @GET("api/Chapter/{comicid}")
     Observable<List<Chapter>> getChapterList(@Path("comicid")int comicid);
     @GET("api/page/{chapterid}")
     Observable<List<Page>> getPageList(@Path("chapterid")int chapterid);
     @GET("api/login")
     Observable<TaiKhoanDto> getTaiKhoan(@Path("tenTK")String tenTK);
     @POST("api/TruyenYeuThich/Them/{maTaiKhoan}/{maTruyen}")
     Observable<String> themTruyenYeuThich(@Path("maTaiKhoan")int maTaiKhoan,@Path("maTruyen")int maTruyen);
     @GET("api/TruyenYeuThich/KiemTra/{maTaiKhoan}/{maTruyen}")
     Observable<Boolean> kiemTraTruyenYeuThich(@Path("maTaiKhoan")int maTaiKhoan,@Path("maTruyen")int maTruyen);
     @POST("api/TruyenYeuThich/Xoa/{maTaiKhoan}/{maTruyen}")
     Observable<String> xoaTruyenYeuThich(@Path("maTaiKhoan")int maTaiKhoan,@Path("maTruyen")int maTruyen);
     @GET("api/TruyenYeuThich/{maTaiKhoan}")
     Observable<List<Comic>> getLikeTruyen(@Path("maTaiKhoan")int maTaiKhoan);
     @POST("api/Truyen/Search")
     Observable<List<Comic>> getSearchTruyen(@Body Comic comic);
     @GET("api/Theloai")
     Observable<List<Category>> getCategory();
     @POST("api/TheLoaiTruyen/Filter")
     Observable<List<Comic>> getFilterComic(@Body List<Integer> theLoaiIds);
     @POST("api/login/doithongtin")
     Observable<String> doithongtin (@Body TaiKhoanDto taiKhoanDto);
     @POST("api/login/doimatkhau")
     Observable<String> doimatkhau (@Body TaiKhoanDto taiKhoanDto);
}
