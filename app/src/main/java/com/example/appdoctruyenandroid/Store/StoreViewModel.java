package com.example.appdoctruyenandroid.Store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StoreViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public StoreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Danh sách");
    }

    public LiveData<String> getText() {
        return mText;
    }
}