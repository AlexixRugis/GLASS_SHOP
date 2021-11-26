package com.example.glassesshop.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.glassesshop.utils.interfaces.IPKTransitor;

public class GlassesDetailViewModelFactory implements ViewModelProvider.Factory {
    private final int pk;
    private final IPKTransitor arTransitor;

    public GlassesDetailViewModelFactory(int pk, @NonNull IPKTransitor arTransitor) {
        this.pk = pk;
        this.arTransitor = arTransitor;
    }


    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GlassesDetailViewModel(pk, arTransitor);
    }
}
