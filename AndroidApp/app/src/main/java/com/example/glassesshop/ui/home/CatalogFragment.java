package com.example.glassesshop.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.glassesshop.R;
import com.example.glassesshop.databinding.CatalogGlassesModelBinding;
import com.example.glassesshop.databinding.FragmentCatalogBinding;
import com.example.glassesshop.ui.DataBindingListAdapter;
import com.example.glassesshop.utils.interfaces.IPKTransitor;

public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CatalogViewModel catalogViewModel = new ViewModelProvider(this).get(CatalogViewModel.class);
        catalogViewModel.setDetailTransitor((IPKTransitor)getActivity());

        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.catalogList;

        DataBindingListAdapter<GlassesModel, CatalogGlassesModelBinding> adapter =
                new DataBindingListAdapter<>(getContext(), R.layout.catalog_glasses_model, BR.glasses, null);


        catalogViewModel.getData().observe(getViewLifecycleOwner(), (data) -> {
            Log.d("d", "data");
            adapter.setDataList(data);
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                catalogViewModel.ShowDetails(i);
            }
        });

        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}