package com.example.glassesshop.api.glasses;

import android.util.Log;

import com.example.glassesshop.api.HttpUtils;
import com.example.glassesshop.ui.home.GlassesDetailModel;
import com.example.glassesshop.ui.home.GlassesModel;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GlassesApiUtils {
    public static void getGlasses(int id, IGlasses glassesHandler) {
        HttpUtils.get("glasses/" + String.valueOf(id), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    GlassesDetailModel detailModel = new GlassesDetailModel();
                    detailModel.setName(response.getString("name"));
                    detailModel.setDescription(response.getString("description"));
                    detailModel.setCost(response.getString("cost"));

                    if (!response.isNull("frame")) {
                        detailModel.setFrameName(
                                response.getJSONObject("frame").getString("name")
                        );
                    }

                    if (!response.isNull("form")) {
                        detailModel.setFrameName(
                                response.getJSONObject("form").getString("name")
                        );
                    }

                } catch (JSONException ignored) {
                }
            }
        });
    }

    public static void getAllGlasses(IGlasseslist glassesListHanler) {
        HttpUtils.get("glasses/all/", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = (JSONArray) response.get("results");
                    ArrayList<GlassesModel> models = new ArrayList<>();

                    for (int i=0; i < jsonArray.length(); i++) {
                        JSONObject jsonModel = jsonArray.getJSONObject(i);

                        GlassesModel model = new GlassesModel(
                                jsonModel.getInt("pk"),
                                jsonModel.getString("name"),
                                jsonModel.getString("current_cost"),
                                jsonModel.getString("avatar")
                        );

                        models.add(model);
                    }
                    glassesListHanler.OnGetGlasses(models);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void getGlassesDetail(int pk, IGlassesDetail glassesDetailHandler) {
        HttpUtils.get(String.format("glasses/%d/", pk), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    GlassesDetailModel detailModel = new GlassesDetailModel();
                    detailModel.setName(response.getString("name"));
                    detailModel.setDescription(response.getString("description"));
                    detailModel.setCost(response.getString("current_cost"));
                    detailModel.setFormName(((JSONObject)response.get("form")).getString("name"));
                    detailModel.setFrameName(((JSONObject)response.get("frame")).getString("name"));

                    if (!response.isNull("avatar")) detailModel.setAvatar(response.getString("avatar"));
                    if (!response.isNull("model_file")) detailModel.setModelUrl(response.getString("model_file"));
                    if (!response.isNull("texture_file")) detailModel.setTextureUrl(response.getString("texture_file"));

                    glassesDetailHandler.onGetGlassesDetail(detailModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
