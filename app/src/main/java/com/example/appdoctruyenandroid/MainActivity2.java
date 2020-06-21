package com.example.appdoctruyenandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.appdoctruyenandroid.Adapters.BottomNavigationBehaviorAdapter;
import com.example.appdoctruyenandroid.Fragments.AccountFragment;
import com.example.appdoctruyenandroid.Fragments.FavoriteFragment;
import com.example.appdoctruyenandroid.Fragments.HomeFragment;
import com.example.appdoctruyenandroid.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navButton);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviorAdapter());
        loadFragment(new HomeFragment());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;

                    switch (item.getItemId()){
                        case R.id.home:
                            fragment = new HomeFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.search:
                            fragment = new SearchFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.like:
                            fragment = new FavoriteFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.account:
                            fragment = new AccountFragment();
                            loadFragment(fragment);
                            return true;
                    }
                    return false;
                }
            };
    private void loadFragment(Fragment fragment) {
        /*Tạo fragment mới mỗi lần bấm nút*/

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}