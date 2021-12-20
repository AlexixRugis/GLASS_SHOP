package com.example.glassesshop.ui.home;

public class GlassesDetailModel {
    private String name;
    private String description;
    private String formName;
    private String frameName;
    private String cost;
    private String avatar;
    private String modelUrl;
    private String textureUrl;

    public GlassesDetailModel() {
        name = "";
        description = "";
        formName = "";
        frameName = "";
        cost = "";
        avatar = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public String getTextureUrl() {
        return textureUrl;
    }

    public void setTextureUrl(String textureUrl) {
        this.textureUrl = textureUrl;
    }

    public boolean isVisible() {
        return this.modelUrl != null && this.textureUrl != null;
    }
}
