package com.example.appdoctruyenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText txtMK, txtMKC;
    Button btnDoiTT,btnHuy;
    IMyAPI iMyAPI;
    AlertDialog dialog;
    String matKhau, matKhauConf;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        txtMK = (EditText) findViewById(R.id.editTextPasswordDoi);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        txtMKC = (EditText) findViewById(R.id.editTextPasswordConfirmDoi);
        btnHuy =(Button)findViewById(R.id.buttonHuyMK);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnDoiTT=(Button)findViewById(R.id.buttonDoiMK);
        btnDoiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matKhau = txtMK.getText().toString().trim();
                matKhauConf = txtMKC.getText().toString().trim();
                if ( !validPassword() | !validPasswordConfirm()){
                    return;
                }
                else{
                    doiMK();
                }
            }
        });
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
    public void doiMK(){
        dialog = new SpotsDialog.Builder()
                .setContext(DoiMatKhauActivity.this)
                .build();
        Common.signin_TaiKhoan.setMatKhau(matKhau);
        compositeDisposable.add(iMyAPI.doimatkhau(Common.signin_TaiKhoan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(DoiMatKhauActivity.this,s,Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                        Toast.makeText(DoiMatKhauActivity.this,"Kết nối mạng không thành công, vui lòng kiểm tra lại",Toast.LENGTH_SHORT).show();
                    }
                }));
    }
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }
}