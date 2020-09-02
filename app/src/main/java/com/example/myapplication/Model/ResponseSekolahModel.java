package com.example.myapplication.Model;

import java.util.List;

public class ResponseSekolahModel {
    String  kode, pesan;
    List<DataSekolahModel> result;

    public List<DataSekolahModel> getResult() {
        return result;
    }

    public void setResult(List<DataSekolahModel> result) {
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
