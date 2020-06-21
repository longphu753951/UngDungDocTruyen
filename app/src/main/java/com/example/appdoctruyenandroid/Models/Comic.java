package com.example.appdoctruyenandroid.Models;

import java.time.Instant;

public class Comic {
    private int id ;
    private String tenTruyen ;
    private String thumbnail ;
    private int like;
    public Comic(int id, String tenTruyen, String thumbnail, int like) {
        this.id = id;
        this.tenTruyen = tenTruyen;
        this.thumbnail = thumbnail;
        this.like = like;
    }

    public Comic() {
    }
    public Comic (String tenTruyen){
        this.tenTruyen = tenTruyen;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
