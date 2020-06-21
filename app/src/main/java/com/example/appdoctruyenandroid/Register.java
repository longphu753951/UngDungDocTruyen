package com.example.appdoctruyenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyenandroid.Common.Common;
import com.example.appdoctruyenandroid.Models.TaiKhoanDto;
import com.example.appdoctruyenandroid.Remote.IMyAPI;
import com.example.appdoctruyenandroid.Remote.RetrofitClient;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Register extends AppCompatActivity {
    EditText txtTK,txtMK,txtTenHienThi,txtEmail,txtMKC;

    Button btnDangKy;
    IMyAPI iMyAPI;
    AlertDialog dialog;
    String taiKhoan, matKhau, email, tenHienThi,matKhauConf;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnDangKy= (Button)findViewById(R.id.buttonDangKy);
        txtTK =(EditText)findViewById(R.id.txt_tenTK);
        txtMK =(EditText)findViewById(R.id.txtMK);
        txtMKC = (EditText)findViewById(R.id.txtMKC);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        txtEmail =(EditText)findViewById(R.id.txtEmail);
        txtTenHienThi =(EditText)findViewById(R.id.txtTenHienThi);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taiKhoan = txtTK.getText().toString().trim();
                matKhau = txtMK.getText().toString().trim();
                tenHienThi = txtTenHienThi.getText().toString();
                email = txtEmail.getText().toString().trim();
                matKhauConf = txtMKC.getText().toString().trim();
                if (!validateEmail() | !validPassword() | !validPasswordConfirm() | !validateTenHienThi() || !validateTenTK()){
                    return;
                }
                else{
                    taoTK();
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
    private boolean validPassword(){
        if(matKhau.length()==0){
            txtMK.setError("Không được bỏ trống");
            return false;
        }
        else if(!Common.MATKHAU.matcher(matKhau).matches()){
            txtMK.setError("Mật khẩu quá yếu");
            return false;
        }
        else{
            txtMK.setError(null);
            return true;
        }
    }
    private boolean validateTenTK(){
        if(tenHienThi.length()==0){
            txtTK.setError("Không được bỏ trống");
            return false;
        }
        else{
            txtTK.setError(null);
            return true;
        }
    }
    private boolean validPasswordConfirm(){
        if(matKhauConf.length()==0){
            txtMKC.setError("Không được bỏ trống");
            return false;
        }
        else if(!matKhau.contains(matKhauConf) ){
            txtMKC.setError("Mật khẩu xác nhận phải trùng với mật khẩu vừa nhập");
            return false;
        }
        else{
            txtMKC.setError(null);
            return true;
        }
    }
    private void taoTK(){
        dialog = new SpotsDialog.Builder()
                .setContext(Register.this)
                .build();
        TaiKhoanDto user = new TaiKhoanDto(taiKhoan,matKhau,tenHienThi,email);
        compositeDisposable.add(iMyAPI.register(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(Register.this,s,Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                        Toast.makeText(Register.this,"Kết nối mạng không thành công, vui lòng kiểm tra lại",Toast.LENGTH_SHORT).show();
                    }
                }));
    }
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }
}