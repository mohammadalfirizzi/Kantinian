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
import com.example.myapplication.Model.ResponseUpdateModel;
import com.example.myapplication.Util.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ubahpin extends AppCompatActivity {
    ImageButton kembali,simpan;
    EditText old_pass, new_pass, c_pass;
    TextView id_login, password_login;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpin);
        final TextView username = (TextView) findViewById(R.id.saatini);
        final TextView sekarang = (TextView) findViewById(R.id.sekarang);
        final TextView konfirmasi = (TextView) findViewById(R.id.konfirm);
        kembali = (ImageButton) findViewById(R.id.pengaturanubahpin);
        simpan = (ImageButton) findViewById(R.id.simpan);
        old_pass = (EditText) findViewById(R.id.saatini);
        new_pass = (EditText) findViewById(R.id.sekarang);
        c_pass = (EditText) findViewById(R.id.konfirm);
        id_login = (TextView) findViewById(R.id.textidlogina);
        password_login = (TextView) findViewById(R.id.textpasswordlogina);

        final float density = getResources().getDisplayMetrics().density;
        final Drawable drawable_pass = getResources().getDrawable(R.drawable.ubahpinn);

        final int width=Math.round(52*density);
        final int height=Math.round(48*density);

        drawable_pass.setBounds(0,0,width,height);
        username.setCompoundDrawables(drawable_pass,null,null,null);
        sekarang.setCompoundDrawables(drawable_pass,null,null,null);
        konfirmasi.setCompoundDrawables(drawable_pass,null,null,null);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ubahpin.this, ubahpinpass.class);
                startActivity(intent);
            }
        });
        sm = new SessionManager(ubahpin.this);

        sm.checkLogin();

        HashMap<String, String> map = sm.getDetailLogin();
        password_login.setText(map.get(sm.KEY_PASSWORD));
        id_login.setText(map.get(sm.KEY_IDKELUARGA));
        id_login.setVisibility(View.INVISIBLE);
        password_login.setVisibility(View.INVISIBLE);
        sm.checkLogin();
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid_login = id_login.getText().toString();
                String sold_pass = old_pass.getText().toString();
                String spassword_login = password_login.getText().toString();
                String snew_pass = new_pass.getText().toString();
                String sc_pass = c_pass.getText().toString();
                ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                Call<ResponseUpdateModel> sendUpdate = api.sendUpdate(sid_login,spassword_login,sold_pass,snew_pass,sc_pass);
                sendUpdate.enqueue(new Callback<ResponseUpdateModel>() {
                    @Override
                    public void onResponse(Call<ResponseUpdateModel> call, Response<ResponseUpdateModel> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        if (kode.equals("1"))
                        {
                            Toast.makeText(ubahpin.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        }
                        else if (kode.equals("0"))
                        {
                            Toast.makeText(ubahpin.this, "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                        }
                        else if (kode.equals("101"))
                        {
                            Toast.makeText(ubahpin.this, "Password dahulu salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUpdateModel> call, Throwable t) {
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });

    }
    public void onBackPressed(){
        Intent intent = new Intent(ubahpin.this, ubahpinpass.class);
        startActivity(intent);
    }
}
