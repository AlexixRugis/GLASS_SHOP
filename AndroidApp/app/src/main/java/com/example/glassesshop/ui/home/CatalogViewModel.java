package com.example.glassesshop.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.glassesshop.api.glasses.GlassesApiUtils;
import com.example.glassesshop.api.glasses.IGlasseslist;
import com.example.glassesshop.utils.interfaces.IPKTransitor;

import java.util.ArrayList;

public class CatalogViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<GlassesModel>> glassesData;
    private IPKTransitor detailTransitor;

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

    public void ShowDetails(int index) {
        GlassesModel model = glassesData.getValue().get(index);
        detailTransitor.TransitTo(model.getPk());
    }

    public void setDetailTransitor(IPKTransitor detailTransitor) {
        this.detailTransitor = detailTransitor;
    }
}