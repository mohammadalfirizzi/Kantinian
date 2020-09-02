package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ResponseReadModel;
import com.example.myapplication.Util.SessionManager;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transferdua extends AppCompatActivity {
    ImageButton kembali;
    CheckBox checkBox;
    TextView hasilnominal,hasilkodeunik,hasiltotal,hasilid,id_customer;
    Button bayarsekarang;
    final int random = new Random().nextInt(100) + 400;
    private static final String TAG = Transferdua.class.getSimpleName();
    SessionManager sm;
    int total = 0;
    int hasilnominala = 0;
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferdua);
        hasilnominal = (TextView) findViewById(R.id.hasilnominaltopup);
        hasilkodeunik = (TextView) findViewById(R.id.hasilkodeunik);
        hasiltotal = (TextView) findViewById(R.id.hasiltotal);
        hasilid = (TextView) findViewById(R.id.hasilid);
        id_customer = (TextView) findViewById(R.id.id_cuss);
        bayarsekarang = (Button) findViewById(R.id.bayarsekarang);
        kembali = (ImageButton) findViewById(R.id.pengaturantrfd);
        checkBox = (CheckBox) findViewById(R.id.checks);
        sm = new SessionManager(Transferdua.this);
        sm.checkLogin();

        HashMap<String, String> map = sm.getDetailLogin();
        id_customer.setText(map.get(sm.KEY_ID));
        id_customer.setVisibility(View.INVISIBLE);
        sm.checkLogin();
        String shasil = hasilnominal.getText().toString();
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                shasil = null;
                hasilnominala = Integer.parseInt(shasil);
                hasilnominal.setText(formatRupiah.format((double)hasilnominala));
            }
            else {
                shasil = bundle.getString("angka");
                hasilnominala = Integer.parseInt(shasil);
                hasilnominal.setText(formatRupiah.format((double)hasilnominala));
            }
        }
        else {
            shasil = (String) savedInstanceState.getSerializable("angka");
            hasilnominala = Integer.parseInt(shasil);
            hasilnominal.setText(formatRupiah.format((double)hasilnominala));
        }
        String srandom = String.valueOf(random);
        Random random1 = new Random();
        String generateid = String.format("%08d", random1.nextInt(100000000));
        hasilid.setText(generateid);
        final String shasilid = hasilid.getText().toString();
        hasilkodeunik.setText(formatRupiah.format((double)random));

        total = hasilnominala + random + 1000;
        hasiltotal.setText(formatRupiah.format((double)total));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    bayarsekarang.setEnabled(true);
                    bayarsekarang.setClickable(true);
                }
                else if (!buttonView.isChecked()){
                    bayarsekarang.setEnabled(false);
                    bayarsekarang.setClickable(false);
                    Toast.makeText(Transferdua.this, "Anda Harus Mencetang Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });


        final String finalShasil = shasil;
        bayarsekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()==false){
                    Toast.makeText(Transferdua.this,"Mohon Dichecklist terlebih dahulu", Toast.LENGTH_LONG).show();
                }
                else {
                    ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                    Call<ResponseReadModel> getData = api.sendTopup(hasilid.getText().toString(), id_customer.getText().toString(), String.valueOf(hasilnominala), String.valueOf(random));
                    getData.enqueue(new Callback<ResponseReadModel>() {
                        @Override
                        public void onResponse(Call<ResponseReadModel> call, Response<ResponseReadModel> response) {
                            Log.d(TAG, "response : " + response.toString());
                            String kode = response.body().getKode();
                            if (kode.equals("1")) {
                                Toast.makeText(Transferdua.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Transferdua.this, UploadBukti.class);
                                intent.putExtra("hasilid", shasilid);
                                intent.putExtra("angkaz", finalShasil);
                                String shasiltotal = hasiltotal.getText().toString();
                                intent.putExtra("angka", shasiltotal);
                                startActivity(intent);
                            } else if (kode.equals("2")) {
                                Toast.makeText(Transferdua.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseReadModel> call, Throwable t) {
                            Log.e(TAG, "error" + t.getMessage());
                        }
                    });
                }
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transferdua.this, transfer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(Transferdua.this, transfer.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
