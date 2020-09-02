package com.example.myapplication.Model;

import java.util.List;

public class ResponseAnakModel {
    String  kode, pesan;
    List<DataAnakModel> result;

    public List<DataAnakModel> getResult() {
        return result;
    }

    public void setResult(List<DataAnakModel> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
