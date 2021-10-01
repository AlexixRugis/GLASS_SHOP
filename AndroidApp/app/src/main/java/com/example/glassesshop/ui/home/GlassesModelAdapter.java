package com.example.glassesshop.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glassesshop.R;
import androidx.annotation.NonNull;

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
                    .inflate(android.R.layout.simple_list_item_2, null);
        }

        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(glasses.getName());
        ((TextView) convertView.findViewById(android.R.id.text2))
                .setText(glasses.getCost());

        return convertView;
    }
}
