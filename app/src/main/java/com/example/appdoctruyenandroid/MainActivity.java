package com.example.appdoctruyenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyenandroid.Models.TaiKhoanDto;
import com.example.appdoctruyenandroid.Remote.IMyAPI;
import com.example.appdoctruyenandroid.Remote.RetrofitClient;

import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    /* EditText edtId, edtMK;
     Button buttonLogin ;
     IMyAPI iMyAPI;
     CompositeDisposable compositeDisposable = new CompositeDisposable();*/
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = (EditText) findViewById(R.id.edtID);
        edtMK = (EditText) findViewById(R.id.editTextTextPassword);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        buttonLogin= (Button)findViewById(R.id.btnDangNhap);
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
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.equals("0")){
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        }
                        if(s.equals("1")){
                            Toast.makeText(MainActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                        if(s.equals("2")){
                            Toast.makeText(MainActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"Kết nối mạng không thành công, vui lòng kiểm tra lại",Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });

    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_update, R.id.navigation_store, R.id.navigation_bookmark, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}

   /* @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }
}*/