package com.example.glassesshop.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.glassesshop.api.DownloadFileFromURL;
import com.example.glassesshop.ar.ARActivity;
import com.example.glassesshop.databinding.ActivityModelLoadingBinding;

public class ModelLoadingActivity extends AppCompatActivity {

    public static final String MODEL_URL_PARAM_NAME = "model_url";
    public static final String TEXTURE_URL_PARAM_NAME = "texture_url";

    private ActivityModelLoadingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String modelUrl = intent.getStringExtra(MODEL_URL_PARAM_NAME);
        String textureUrl = intent.getStringExtra(TEXTURE_URL_PARAM_NAME);
        if (modelUrl == null || textureUrl == null) finish();

        binding = ActivityModelLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Context context = this;
        new DownloadFileFromURL(new DownloadFileFromURL.ISaveCallback() {
            @Override
            public void onSave(Boolean success) {
                if (!success) finish();

                new DownloadFileFromURL(new DownloadFileFromURL.ISaveCallback() {
                    @Override
                    public void onSave(Boolean success) {
                        if (success) {
                            Intent arIntent = new Intent(context, ARActivity.class);
                            startActivity(arIntent);
                        }
                        finish();
                    }

                    @Override
                    public void onProgress(int percents) {
                        binding.loadingProgress.setText(String.format("%d/100", 50 + percents/50));
                    }
                }).execute(textureUrl, "/storage/emulated/0/GLASSES_SHOP/cache/glasses.pngblob");
            }

            @Override
            public void onProgress(int percents) {
                binding.loadingProgress.setText(String.format("%d/100", percents/50));
            }
        }).execute(modelUrl, "/storage/emulated/0/GLASSES_SHOP/cache/glasses.binarypb");
    }
}