package com.example.appdoctruyenandroid.Models;

public class TaiKhoanDto {


    private int id;
    private String tenTaiKhoan ;
    private String matKhau ;
    private String tenHienThi ;
    private String email;

    public TaiKhoanDto() {
    }

    public TaiKhoanDto(int id,String tenTaiKhoan, String matKhau, String tenHienThi, String email) {
        this.id = id;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenHienThi = tenHienThi;
        this.email = email;
    }
    public TaiKhoanDto(String tenTaiKhoan, String matKhau, String tenHienThi, String email) {

        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenHienThi = tenHienThi;
        this.email = email;
    }
    public TaiKhoanDto(String tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenHienThi() {
        return tenHienThi;
    }

    public void setTenHienThi(String tenHienThi) {
        this.tenHienThi = tenHienThi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
