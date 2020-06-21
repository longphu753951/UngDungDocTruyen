package com.example.appdoctruyenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyenandroid.Common.Common;
import com.example.appdoctruyenandroid.Remote.IMyAPI;
import com.example.appdoctruyenandroid.Remote.RetrofitClient;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DoiThongTinActivity extends AppCompatActivity {
    EditText txtTK,txtMK,txtTenHienThi,txtEmail,txtMKC;

    Button btnDoiTT,btnHuy;
    IMyAPI iMyAPI;
    AlertDialog dialog;
    String taiKhoan,  email, tenHienThi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_thong_tin);
        btnDoiTT= (Button)findViewById(R.id.buttonDoiTT);
        txtTK =(EditText)findViewById(R.id.txt_tenTKDoi);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        txtEmail =(EditText)findViewById(R.id.txtEmailDoi);
        txtTenHienThi =(EditText)findViewById(R.id.txtTenHienThiDoi);
        txtTK.setText(Common.signin_TaiKhoan.getTenTaiKhoan());
        txtEmail.setText(Common.signin_TaiKhoan.getEmail());
        txtTenHienThi.setText(Common.signin_TaiKhoan.getTenHienThi());
        btnHuy =(Button)findViewById(R.id.buttonHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnDoiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taiKhoan = txtTK.getText().toString().trim();

                tenHienThi = txtTenHienThi.getText().toString();
                email = txtEmail.getText().toString().trim();
                if (!validateEmail()  | !validateTenHienThi() || !validateTenTK()){
                    return;
                }
                else{
                    doiTT();
                }
            }
        });
    }
    private boolean validateTenHienThi(){
        if(tenHienThi.length()==0){
            txtTenHienThi.setError("Không được bỏ trống");
            return false;
        }
        else{
            txtTenHienThi.setError(null);
            return true;
        }
    }
    private boolean validateTenTK(){
        if(taiKhoan.length()==0){
            txtTK.setError("Không được bỏ trống");
            return false;
        }
        else if(taiKhoan.contains(" ")){
            txtTK.setError("Không được co khoang cach");
            return false;
        }
        else{
            txtTK.setError(null);
            return true;
        }
    }
    private boolean validateEmail(){
        if(email.length()==0){
            txtEmail.setError("Không được bỏ trống");
            return false;
        }
        else if(!Common.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmail.setError("Mời nhập đúng email");
            return false;
        }
        else{
            txtEmail.setError(null);
            return true;
        }
    }
    private void doiTT(){
        dialog = new SpotsDialog.Builder()
                .setContext(DoiThongTinActivity.this)
                .build();
        Common.signin_TaiKhoan.setEmail(email);
        Common.signin_TaiKhoan.setTenHienThi(tenHienThi);
        Common.signin_TaiKhoan.setTenTaiKhoan(taiKhoan);
        compositeDisposable.add(iMyAPI.doithongtin(Common.signin_TaiKhoan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(DoiThongTinActivity.this,s,Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                        Toast.makeText(DoiThongTinActivity.this,"Kết nối mạng không thành công, vui lòng kiểm tra lại",Toast.LENGTH_SHORT).show();
                    }
                }));
    }
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }

}