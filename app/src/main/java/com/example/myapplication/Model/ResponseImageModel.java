package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseImageModel {
//    @SerializedName("kode")
//    String kode;
//    @SerializedName("pesan")
//    String pesan;
//
//    public String getKode() {
//        return kode;
//    }
//
//    public void setKode(String kode) {
//        this.kode = kode;
//    }
//
//    public String getPesan() {
//        return pesan;
//    }
//
//    public void setPesan(String pesan) {
//        this.pesan = pesan;
//    }
    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("response")
    private String response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
