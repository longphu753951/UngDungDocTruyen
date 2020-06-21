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
import com.example.appdoctruyenandroid.Models.TaiKhoanDto;
import com.example.appdoctruyenandroid.Remote.IMyAPI;
import com.example.appdoctruyenandroid.Remote.RetrofitClient;

import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
     EditText edtId, edtMK;
     Button buttonLogin,buttonDangKy ;
     IMyAPI iMyAPI;
     CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = (EditText) findViewById(R.id.edtID);
        edtMK = (EditText) findViewById(R.id.editTextTextPassword);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        buttonLogin= (Button)findViewById(R.id.btnDangNhap);
        nhapNut();
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new SpotsDialog.Builder()
                        .setContext(MainActivity.this)
                        .build();
                dialog.show();

                TaiKhoanDto user = new TaiKhoanDto(edtId.getText().toString(),edtMK.getText().toString());
                compositeDisposable.add(iMyAPI.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TaiKhoanDto>() {
                    @Override
                    public void accept(TaiKhoanDto s) throws Exception {
                            Common.signin_TaiKhoan = s;
                            Intent trangChu = new Intent(MainActivity.this, MainActivity2.class);
                            trangChu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(trangChu);
                            dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"Lỗi đăng nhập, vui lòng thử lại",Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });

    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }

    private void nhapNut(){
        buttonDangKy= (Button)findViewById(R.id.btnDangKy);
        buttonDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}