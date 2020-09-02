package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUpdatepinModel {
    @SerializedName("kode")
    String kode;
    @SerializedName("pesan")
    String pesan;
    @SerializedName("result")
    List<UpdatePinModel> result;

    public ResponseUpdatepinModel(){}
    public List<UpdatePinModel> getResult() {
        return result;
    }
    public void setResult(List<UpdatePinModel> result) {
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
