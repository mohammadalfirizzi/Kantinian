package com.example.myapplication.Model;

import java.util.List;

public class ResponseOrtuModel {
    String  kode, pesan;
    List<DataOrtuModel> result;

    public List<DataOrtuModel> getResult() {
        return result;
    }

    public void setResult(List<DataOrtuModel> result) {
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
