package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUpdateBiodataModel {
    @SerializedName("kode")
    String kode;
    @SerializedName("pesan")
    String pesan;
    @SerializedName("result")
    List<UpdateBiodataModel> result;

    public ResponseUpdateBiodataModel(){}
    public List<UpdateBiodataModel> getResult() {
        return result;
    }
    public void setResult(List<UpdateBiodataModel> result) {
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
