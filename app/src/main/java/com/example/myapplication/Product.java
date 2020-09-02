package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    public final String nama;

    public Product(String nama) {
        this.nama = nama;
    }

    protected Product(Parcel in) {
        nama = in.readString();
    }

    public static final Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
    }
}
