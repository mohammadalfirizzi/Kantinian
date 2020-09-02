package com.example.myapplication.Model;

public class UpdatePinModel {
    String id_customer, kode_template, old_pin, new_pin, c_pin;

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getKode_template() {
        return kode_template;
    }

    public void setKode_template(String kode_template) {
        this.kode_template = kode_template;
    }

    public String getOld_pin() {
        return old_pin;
    }

    public void setOld_pin(String old_pin) {
        this.old_pin = old_pin;
    }

    public String getNew_pin() {
        return new_pin;
    }

    public void setNew_pin(String new_pin) {
        this.new_pin = new_pin;
    }

    public String getC_pin() {
        return c_pin;
    }

    public void setC_pin(String c_pin) {
        this.c_pin = c_pin;
    }
}
