package com.example.myapplication;

public class DataModel {
    String id_customer;
    String nama_customer;
    String mati_kartu;
    String saldo_kartu;

    String id_keluarga;

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getMati_kartu() {
        return mati_kartu;
    }

    public void setMati_kartu(String mati_kartu) {
        this.mati_kartu = mati_kartu;
    }
    public String getId_keluarga() {
        return id_keluarga;
    }

    public void setId_keluarga(String id_keluarga) {
        this.id_keluarga = id_keluarga;
    }

    public String getSaldo_kartu() {
        return saldo_kartu;
    }

    public void setSaldo_kartu(String saldo_kartu) {
        this.saldo_kartu = saldo_kartu;
    }
}
