package com.example.appdoctruyenandroid.Update;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appdoctruyenandroid.R;

public class UpdateFragment extends Fragment {

   private UpdateViewModel updateViewModel;

   public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
       updateViewModel = ViewModelProviders.of(this).get(UpdateViewModel.class);

       View root = inflater.inflate(R.layout.update_fragment2, container, false);
       final TextView textView = root.findViewById(R.id.text_Update);
       updateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
           @Override
           public void onChanged(@Nullable String s) {
               textView.setText(s);
           }
       });
       return root;
   }
}