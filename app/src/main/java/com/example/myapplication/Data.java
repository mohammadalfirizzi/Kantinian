package com.example.myapplication;

public class Data {
    private String id_sekolah, nama_sekolah;

    public Data() {
    }

    public Data(String id_sekolah, String pendidikan) {
        this.id_sekolah = id_sekolah;
        this.nama_sekolah = nama_sekolah;
    }

    public String getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(String id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }
}
