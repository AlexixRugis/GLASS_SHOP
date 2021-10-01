package com.example.glassesshop.api.glasses;

import com.example.glassesshop.api.HttpUtils;
import com.example.glassesshop.ui.home.GlassesModel;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GlassesApiUtils {
    public static void getAllGlasses(IGlasseslist glassesListHanler) {
        HttpUtils.get("glasses/all/", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = (JSONArray) response.get("results");
                    ArrayList<GlassesModel> models = new ArrayList<>();

                    for (int i=0; i < jsonArray.length(); i++) {
                        JSONObject jsonModel = jsonArray.getJSONObject(i);

                        models.add(new GlassesModel(
                           jsonModel.getString("name"),
                           jsonModel.getString("cost"),
                           ""
                        ));
                    }

                    glassesListHanler.OnGetGlasses(models);
                } catch (JSONException ignored) {
                }
            }
        });
    }
}
