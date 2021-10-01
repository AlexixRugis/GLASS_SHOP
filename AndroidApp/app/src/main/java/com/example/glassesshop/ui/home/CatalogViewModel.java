package com.example.glassesshop.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.glassesshop.api.HttpUtils;
import com.example.glassesshop.api.glasses.GlassesApiUtils;
import com.example.glassesshop.api.glasses.IGlasseslist;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CatalogViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<GlassesModel>> glassesData;

    public CatalogViewModel() {
        glassesData = new MutableLiveData<>();

        GlassesApiUtils.getAllGlasses(new IGlasseslist() {
            @Override
            public void OnGetGlasses(ArrayList<GlassesModel> glasses) {
                glassesData.setValue(glasses);
            }
        });
    }

    public LiveData<ArrayList<GlassesModel>> getData() {
        return glassesData;
    }
}