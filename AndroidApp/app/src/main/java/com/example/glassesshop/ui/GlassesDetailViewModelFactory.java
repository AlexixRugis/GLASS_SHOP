package com.example.glassesshop.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GlassesDetailViewModelFactory implements ViewModelProvider.Factory {
    private final int pk;

    public GlassesDetailViewModelFactory(int pk) {
        this.pk = pk;
    }


    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GlassesDetailViewModel(pk);
    }
}
