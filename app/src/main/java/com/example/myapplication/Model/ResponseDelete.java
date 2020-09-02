package com.example.myapplication.Model;

import java.util.List;

public class ResponseDelete {
    String  kode, pesan;
    List<DeleteTopupModel> result;

    public List<DeleteTopupModel> getResult() {
        return result;
    }

    public void setResult(List<DeleteTopupModel> result) {
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
