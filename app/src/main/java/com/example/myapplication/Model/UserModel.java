package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id_customer")
    String id_customer;
    @SerializedName("email_login")
    String email_login;
    @SerializedName("telp_ortu")
    String telp_ortu;
    @SerializedName("nama_customer")
    String nama_customer;
    @SerializedName("nama_ortu")
    String nama_ortu;
    @SerializedName("nama_sekolah")
    String nama_sekolah;
    @SerializedName("password_login")
    String password_login;
    @SerializedName("id_login")
    String id_login;
    @SerializedName("id_keluarga")
    String id_keluarga;
    @SerializedName("telp_alternatif")
    String telp_alternatif;
    @SerializedName("kode_template")
    String kode_template;


    public UserModel() {};
    public String getId_customer() {
        return id_customer;
    }
    public void setId_customer(String id_customer){
        this.id_customer = id_customer;
    }

    public String getEmail_login() {
        return email_login;
    }
    public void setEmail_login(String email_login){
        this.email_login = email_login;
    }
    public String getTelp_ortu() {
        return telp_ortu;
    }
    public void setTelp_ortu(String telp_ortu){
        this.telp_ortu = telp_ortu;
    }
    public String getNama_customer() {
        return nama_customer;
    }
    public void setNama_customer(String nama_customer){
        this.nama_customer = nama_customer;
    }
    public String getNama_ortu() {
        return nama_ortu;
    }

    public void setNama_ortu(String nama_ortu) {
        this.nama_ortu = nama_ortu;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getPassword_login() {
        return password_login;
    }

    public void setPassword_login(String password_login) {
        this.password_login = password_login;
    }

    public String getId_login() {
        return id_login;
    }

    public void setId_login(String id_login) {
        this.id_login = id_login;
    }

    public String getId_keluarga() {
        return id_keluarga;
    }

    public void setId_keluarga(String id_keluarga) {
        this.id_keluarga = id_keluarga;
    }
    public String getTelp_alternatif() {
        return telp_alternatif;
    }

    public void setTelp_alternatif(String telp_alternatif) {
        this.telp_alternatif = telp_alternatif;
    }
    public String getKode_template() {
        return kode_template;
    }

    public void setKode_template(String kode_template) {
        this.kode_template = kode_template;
    }
}
