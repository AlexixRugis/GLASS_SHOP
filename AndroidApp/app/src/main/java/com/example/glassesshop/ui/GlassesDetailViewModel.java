package com.example.glassesshop.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.glassesshop.ui.home.GlassesDetailModel;

public class GlassesDetailViewModel extends ViewModel {
    private int pk;
    private final MutableLiveData<GlassesDetailModel> glassesData;

    public GlassesDetailViewModel() {
        glassesData = new MutableLiveData<>();
    }

    public LiveData<GlassesDetailModel> getGlassesData() { return glassesData; }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
}
