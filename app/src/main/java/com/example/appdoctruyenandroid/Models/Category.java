package com.example.appdoctruyenandroid.Models;

public class Category {
    public int id ;
    public String tenTheLoai ;
    public String ghiChu ;

    public Category() {
    }

    public Category(int id, String tenTheLoai, String ghiChu) {
        this.id = id;
        this.tenTheLoai = tenTheLoai;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
