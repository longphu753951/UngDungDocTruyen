package com.example.appdoctruyenandroid.Models;

public class TaiKhoanDto {
    private String TenTaiKhoan ;
    private String MatKhau ;
    private String TenHienThi ;
    private String Email;

    public TaiKhoanDto() {
    }

    public TaiKhoanDto(String tenTaiKhoan, String matKhau, String tenHienThi, String email) {
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
        TenHienThi = tenHienThi;
        Email = email;
    }

    public TaiKhoanDto(String tenTaiKhoan, String matKhau) {
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        TenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getTenHienThi() {
        return TenHienThi;
    }

    public void setTenHienThi(String tenHienThi) {
        TenHienThi = tenHienThi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
