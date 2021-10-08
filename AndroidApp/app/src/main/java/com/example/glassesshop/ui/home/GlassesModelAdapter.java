package com.example.glassesshop.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glassesshop.R;
import com.example.glassesshop.api.DownloadImagesTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GlassesModelAdapter extends ArrayAdapter<GlassesModel> {

    public GlassesModelAdapter(@NonNull Context context, @NonNull List<GlassesModel> objects) {
        super(context, R.layout.catalog_glasses_model, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GlassesModel glasses = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.catalog_glasses_model, null);
        }

        ((TextView) convertView.findViewById(R.id.glasses_name))
                .setText(glasses.getName());
        ((TextView) convertView.findViewById(R.id.glasses_cost))
                .setText(glasses.getCost());

        if (!glasses.getPreview_image().isEmpty()) {
            ImageView previewImage = (ImageView)convertView.findViewById(R.id.glasses_preview);
            previewImage.setTag(glasses.getPreview_image());

            new DownloadImagesTask().execute(previewImage);
        }

        return convertView;
    }
}

