package com.example.appdoctruyenandroid.Models;

import java.time.Instant;

public class Chapter {
    private int id;
    private String tenChuong;

    private int maTruyen;

    public Chapter(int id, String tenChuong, int maTruyen) {
        this.id = id;
        this.tenChuong = tenChuong;

        this.maTruyen = maTruyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

  
    public int getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }
}
