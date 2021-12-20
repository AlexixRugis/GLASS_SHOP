package com.example.glassesshop.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.glassesshop.ar.ARActivity;
import com.example.glassesshop.databinding.ActivityGlassesDetailBinding;
import com.example.glassesshop.utils.interfaces.IPKTransitor;

public class GlassesDetailActivity extends AppCompatActivity implements IPKTransitor {

    private ActivityGlassesDetailBinding binding;
    private int pk;
    private GlassesDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pk = getIntent().getIntExtra("pk", -1);

        viewModel = new ViewModelProvider(this, new GlassesDetailViewModelFactory(pk, this)).get(GlassesDetailViewModel.class);

        binding = ActivityGlassesDetailBinding.inflate(getLayoutInflater());

        viewModel.getGlassesData().observe(this, (data) -> {
            binding.setGlasses(data);
        });

        binding.setVm(viewModel);

        setContentView(binding.getRoot());
    }

    private void GotoARWear() {
        Intent intent = new Intent(this, ModelLoadingActivity.class);

        intent.putExtra(ModelLoadingActivity.MODEL_URL_PARAM_NAME, viewModel.getGlassesData().getValue().getModelUrl());
        intent.putExtra(ModelLoadingActivity.TEXTURE_URL_PARAM_NAME, viewModel.getGlassesData().getValue().getTextureUrl());

        startActivity(intent);
    }

    @Override
    public void TransitTo(int pk) {
        GotoARWear();
    }
}