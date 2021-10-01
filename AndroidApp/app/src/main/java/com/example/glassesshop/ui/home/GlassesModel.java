package com.example.glassesshop.ui.home;

public class GlassesModel {

    private String name;
    private String cost;
    private String preview_image;

    public GlassesModel(String name, String cost, String preview_image) {
        this.name = name;
        this.cost = cost;
        this.preview_image = preview_image;
    }

    public GlassesModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPreview_image() {
        return preview_image;
    }

    public void setPreview_image(String preview_image) {
        this.preview_image = preview_image;
    }
}
