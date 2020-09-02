package com.example.myapplication;

public class Madel {
    String nama, nisn,valid;

    public Madel(String nama, String nisn, String valid) {
        this.nama = nama;
        this.nisn = nisn;
        this.valid = valid;

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
