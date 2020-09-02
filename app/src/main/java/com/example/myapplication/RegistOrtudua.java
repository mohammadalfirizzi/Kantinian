package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ResponseApiModel;
import com.example.myapplication.Model.ResponseOrtuModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistOrtudua extends AppCompatActivity {
    EditText nama, alamat, ktp;
    Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_ortudua);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        ktp = (EditText) findViewById(R.id.ktp);
        daftar = (Button) findViewById(R.id.btn_daftar);
        final String nomor, email, password;
        if (savedInstanceState == null) {

            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                nomor = null;
                email = null;
                password = null;
            }
            else {
                nomor = bundle.getString("nomor");
                email = bundle.getString("email");
                password = bundle.getString("password");
            }
        }
        else {
            nomor = (String) savedInstanceState.getSerializable("nomor");
            email = (String) savedInstanceState.getSerializable("email");
            password = (String) savedInstanceState.getSerializable("password");
        }
//        Toast.makeText(RegistOrtudua.this, nomor, Toast.LENGTH_SHORT).show();
//        Toast.makeText(RegistOrtudua.this, email, Toast.LENGTH_SHORT).show();
//        Toast.makeText(RegistOrtudua.this, password, Toast.LENGTH_SHORT).show();
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequest Api = RetroClient.getRequestService();
                Call<ResponseOrtuModel> daftar = Api.sendOrtu(nama.getText().toString(), nomor, email, alamat.getText().toString(), ktp.getText().toString(), password);
                daftar.enqueue(new Callback<ResponseOrtuModel>() {
                @Override
                public void onResponse(Call<ResponseOrtuModel> call, Response<ResponseOrtuModel> response) {
                    Log.d("RETRO", "response : " + response.body().toString());
                    String kode = response.body().getKode();

                    if(kode.equals("1"))
                    {
                        Toast.makeText(RegistOrtudua.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistOrtudua.this, "Data Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseOrtuModel> call, Throwable t) {
                    Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                }
            });

            }
        });
    }
}
