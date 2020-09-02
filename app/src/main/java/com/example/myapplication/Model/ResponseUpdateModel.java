package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUpdateModel {
    @SerializedName("kode")
    String kode;
    @SerializedName("pesan")
    String pesan;
    @SerializedName("result")
    List<UpdateModel> result;

    public ResponseUpdateModel(){}
    public List<UpdateModel> getResult() {
        return result;
    }
    public void setResult(List<UpdateModel> result) {
        this.result = result;
    }
    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public String getPesan(){
        return pesan;
    }
    public void setPesan(String pesan){
        this.pesan = pesan;
    }
}
