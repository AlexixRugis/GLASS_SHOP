package com.example.glassesshop.ui;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.glassesshop.api.glasses.GlassesApiUtils;
import com.example.glassesshop.api.glasses.IGlassesDetail;
import com.example.glassesshop.ui.home.GlassesDetailModel;

public class GlassesDetailViewModel extends ViewModel {
    private int pk;
    private final MutableLiveData<GlassesDetailModel> glassesData;

    public GlassesDetailViewModel(int pk) {
        Log.d("", String.valueOf(pk));
        this.pk = pk;
        glassesData = new MutableLiveData<>();

        if (pk != -1) {
            GlassesApiUtils.getGlassesDetail(pk, new IGlassesDetail() {
                @Override
                public void onGetGlassesDetail(GlassesDetailModel model) {
                    glassesData.setValue(model);
                }
            });
        }
    }

    public LiveData<GlassesDetailModel> getGlassesData() { return glassesData; }

    public int getPk() {
        return pk;
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Log.d("", String.valueOf(imageUrl));
        if (imageUrl == null || imageUrl.isEmpty()) return;

        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().centerCrop())
                .into(view);
    }
}
