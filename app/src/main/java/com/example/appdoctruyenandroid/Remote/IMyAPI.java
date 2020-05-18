package com.example.appdoctruyenandroid.Remote;

import com.example.appdoctruyenandroid.Models.TaiKhoanDto;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMyAPI {
     @POST("api/login")
     Observable<String> login(@Body TaiKhoanDto taiKhoanDto);
}
