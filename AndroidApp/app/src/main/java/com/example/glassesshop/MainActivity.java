package com.example.glassesshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.glassesshop.ui.GlassesDetailActivity;
import com.example.glassesshop.utils.interfaces.IPKTransitor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.glassesshop.databinding.ActivityMainBinding;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity implements IPKTransitor {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(OpenCVLoader.initDebug()){

            Log.d("Check","OpenCv configured successfully");

        } else{

            Log.d("Check","OpenCv doesn’t configured successfully");

        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_catalog, R.id.navigation_cart, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void GotoDetails(int pk) {
        Intent intent = new Intent(this, GlassesDetailActivity.class);

        intent.putExtra("pk", pk);

        startActivity(intent);
    }

    @Override
    public void TransitTo(int pk) {
        GotoDetails(pk);
    }
}