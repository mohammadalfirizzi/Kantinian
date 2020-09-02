package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class ImageModel {
    @SerializedName("id_topup")
    String id_topup;

    @SerializedName("image")
    String image;

    public String getId_topup() {
        return id_topup;
    }

    public void setId_topup(String id_topup) {
        this.id_topup = id_topup;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
