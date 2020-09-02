package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Faq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Company> companies = new ArrayList<>();

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Kantinian.id merupakan bentuk baru pembayaran dalam genggaman anda. Dengan menggunakan Kantinian.id" +
                ", anda bisa melakukan berbagai macam transaksi elektronik melalui berbagai layanan yang tersedia, seperti Saldo" +
                "Kantinian, Transfer Bank, Kartu Kredit, dan juga Setor Tunai ke minimarket"));

        Company kantin = new Company("Apa Itu Kantinian.id ?",products);
        companies.add(kantin);

        ArrayList<Product> keuntungan = new ArrayList<>();
        keuntungan.add(new Product("Keuntungannya adalah"));

        Company untung = new Company("Apa Keuntungan dari akun Kantinian ?",keuntungan);
        companies.add(untung);

        ArrayList<Product> topup = new ArrayList<>();
        topup.add(new Product("Dengan cara"));

        Company top = new Company("Bagaimana Top Up saldo Kantinian ?",topup);
        companies.add(top);

        ArrayList<Product> ganti = new ArrayList<>();
        ganti.add(new Product("Dengan cara"));

        Company gan = new Company("Bagaimana cara mengganti password dan PIN ?",ganti);
        companies.add(gan);

        ArrayList<Product> limit = new ArrayList<>();
        limit.add(new Product("Dengan cara"));

        Company lim = new Company("Bagaimana cara limit transaksi siswa ?",limit);
        companies.add(gan);

        ProductAdapter adapter = new ProductAdapter(companies);
        recyclerView.setAdapter(adapter);
    }
}
