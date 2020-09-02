package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBiodataModel {
    @SerializedName("kode")
    String kode;
    @SerializedName("pesan")
    String pesan;
    @SerializedName("result")
    List<BiodataModel> result;

    public ResponseBiodataModel(){}
    public List<BiodataModel> getResult() {
        return result;
    }
    public void setResult(List<BiodataModel> result) {
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
