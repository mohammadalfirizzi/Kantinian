package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ResponseUpdatepinModel;
import com.example.myapplication.Util.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ubahpindua extends AppCompatActivity {
    ImageButton kembali,simpan;
    EditText old_pin, new_pin, c_pin;
    TextView id_customer, kode_template;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpindua);
        final TextView username = (TextView) findViewById(R.id.saatini);
        final TextView sekarang = (TextView) findViewById(R.id.sekarang);
        final TextView konfirmasi = (TextView) findViewById(R.id.konfirm);
        kembali = (ImageButton) findViewById(R.id.pengaturanubahpass);
        simpan = (ImageButton) findViewById(R.id.simpan);
        old_pin = (EditText) findViewById(R.id.saatini);
        new_pin = (EditText) findViewById(R.id.sekarang);
        c_pin = (EditText) findViewById(R.id.konfirm);
        id_customer = (TextView) findViewById(R.id.id_customer);
        kode_template = (TextView) findViewById(R.id.kode_template);
        final float density = getResources().getDisplayMetrics().density;
        final Drawable drawable_pass = getResources().getDrawable(R.drawable.ubahpindua);

        final int width=Math.round(52*density);
        final int height=Math.round(48*density);

        drawable_pass.setBounds(0,0,width,height);
        username.setCompoundDrawables(drawable_pass,null,null,null);
        sekarang.setCompoundDrawables(drawable_pass,null,null,null);
        konfirmasi.setCompoundDrawables(drawable_pass,null,null,null);
        sm = new SessionManager(ubahpindua.this);

        sm.checkLogin();

        HashMap<String, String> map = sm.getDetailLogin();
        kode_template.setText(map.get(sm.KEY_KODETEMP));
        id_customer.setText(map.get(sm.KEY_ID));
        kode_template.setVisibility(View.INVISIBLE);
        id_customer.setVisibility(View.INVISIBLE);
        sm.checkLogin();

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ubahpindua.this, ubahpinpass.class);
                startActivity(intent);
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid_customer = id_customer.getText().toString();
                String skode_template = kode_template.getText().toString();
                String sold_pin = old_pin.getText().toString();
                String snew_pin = new_pin.getText().toString();
                String sc_pin = c_pin.getText().toString();
                ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                Call<ResponseUpdatepinModel> sendUpdatepin = api.sendUpdatepin(sid_customer,skode_template,sold_pin,snew_pin,sc_pin);
                sendUpdatepin.enqueue(new Callback<ResponseUpdatepinModel>() {
                    @Override
                    public void onResponse(Call<ResponseUpdatepinModel> call, Response<ResponseUpdatepinModel> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        if (kode.equals("1"))
                        {
                            Toast.makeText(ubahpindua.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        }
                        else if (kode.equals("0"))
                        {
                            Toast.makeText(ubahpindua.this, "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                        }
                        else if (kode.equals("101"))
                        {
                            Toast.makeText(ubahpindua.this, "Password dahulu salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUpdatepinModel> call, Throwable t) {
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });

    }
    public void onBackPressed(){
        Intent intent = new Intent(ubahpindua.this, ubahpinpass.class);
        startActivity(intent);
    }
}
