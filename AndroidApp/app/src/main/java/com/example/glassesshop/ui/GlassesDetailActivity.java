package com.example.glassesshop.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.glassesshop.databinding.ActivityGlassesDetailBinding;

public class GlassesDetailActivity extends AppCompatActivity {

    private ActivityGlassesDetailBinding binding;
    private int pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pk = getIntent().getIntExtra("pk", -1);

        GlassesDetailViewModel viewModel = new ViewModelProvider(this).get(GlassesDetailViewModel.class);
        viewModel.setPk(pk);

        binding = ActivityGlassesDetailBinding.inflate(getLayoutInflater());

        viewModel.getGlassesData().observe(this, (data) -> {
            binding.setGlasses(data);
        });

        setContentView(binding.getRoot());
    }
}