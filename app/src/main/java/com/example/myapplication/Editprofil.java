package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ResponseUpdateBiodataModel;
import com.example.myapplication.Util.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editprofil extends AppCompatActivity {
    EditText email, telp_ortu, telp_alternatif;
    ImageButton kembali;
    Button simpan;
    TextView password_login, id_keluarga;

    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);
        final TextView username = (TextView) findViewById(R.id.email);
        final TextView sekarang = (TextView) findViewById(R.id.telepon);
        final TextView konfirmasi = (TextView) findViewById(R.id.teleponrumah);
        password_login = (TextView) findViewById(R.id.textpasswordlogin);
        id_keluarga = (TextView) findViewById(R.id.textidlogin);
        email = (EditText) findViewById(R.id.email);
        telp_ortu = (EditText) findViewById(R.id.telepon);
        telp_alternatif = (EditText) findViewById(R.id.teleponrumah);

        kembali = (ImageButton) findViewById(R.id.pengaturaneditprofile);
        simpan = (Button) findViewById(R.id.simpana);
        final float density = getResources().getDisplayMetrics().density;
        final Drawable drawable_email = getResources().getDrawable(R.drawable.pesan);
        final Drawable drawable_telepon = getResources().getDrawable(R.drawable.telepon);

        final int width=Math.round(52*density);
        final int height=Math.round(48*density);

        drawable_email.setBounds(0,0,width,height);
        drawable_telepon.setBounds(0,0,width,height);
        username.setCompoundDrawables(drawable_email,null,null,null);
        sekarang.setCompoundDrawables(drawable_telepon,null,null,null);
        konfirmasi.setCompoundDrawables(drawable_telepon,null,null,null);
        sm = new SessionManager(Editprofil.this);

        sm.checkLogin();

        HashMap<String, String> map = sm.getDetailLogin();
        password_login.setText(map.get(sm.KEY_PASSWORD));
        id_keluarga.setText(map.get(sm.KEY_IDKELUARGA));
        id_keluarga.setVisibility(View.INVISIBLE);
        password_login.setVisibility(View.INVISIBLE);
        sm.checkLogin();



        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Editprofil.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog_two,null);
                Button btn_cancel = (Button) mView.findViewById(R.id.cancelbtn);
                Button btn_ok = (Button) mView.findViewById(R.id.okbtn);
                builder.setView(mView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sid_keluarga = id_keluarga.getText().toString();
                        String semail = email.getText().toString();
                        String stelp_ortu = telp_ortu.getText().toString();
                        String stelp_alternatif = telp_alternatif.getText().toString();
                        ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                        retrofit2.Call<ResponseUpdateBiodataModel> sendUpdateBiodata = api.sendUpdateBiodata(sid_keluarga,stelp_ortu,stelp_alternatif,semail);
                        sendUpdateBiodata.enqueue(new Callback<ResponseUpdateBiodataModel>() {
                            @Override
                            public void onResponse(Call<ResponseUpdateBiodataModel> call, Response<ResponseUpdateBiodataModel> response) {
                                Log.d("RETRO", "response : " + response.body().toString());
                                String kode = response.body().getKode();
                                if(kode.equals("1"))
                                {
                                    Toast.makeText(Editprofil.this, "Data Anda Kosong", Toast.LENGTH_SHORT).show();
//                                    finish();
                                }
                                else if (kode.equals("2")){
                                    Toast.makeText(Editprofil.this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
//                                    finish();
                                    Intent intent = new Intent(Editprofil.this, ProfilSaya.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                                else {
                                    Toast.makeText(Editprofil.this, "Data Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseUpdateBiodataModel> call, Throwable t) {
                                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                            }
                        });
                    }
                });
                alertDialog.show();
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Editprofil.this, ProfilSaya.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(Editprofil.this, ProfilSaya.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
