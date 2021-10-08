package com.example.glassesshop.api.glasses;

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
                                jsonModel.getString("name"),
                                jsonModel.getString("cost"),
                                ""
                        );

                        JSONArray variants = jsonModel.getJSONArray("variants");
                        System.out.println(variants.length());
                        if (variants.length() > 0) {
                            if (!variants.getJSONObject(0).isNull("avatar")) {
                                model.setPreview_image(variants.getJSONObject(0).getString("avatar"));
                                System.out.println(model.getPreview_image());
                            }
                        }

                        models.add(model);
                    }

                    glassesListHanler.OnGetGlasses(models);
                } catch (JSONException ignored) {
                }
            }
        });
    }
}
