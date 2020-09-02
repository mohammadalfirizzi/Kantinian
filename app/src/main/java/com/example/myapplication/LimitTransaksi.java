package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.myapplication.Model.ResponseLimitModel;
import com.example.myapplication.Util.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LimitTransaksi extends AppCompatActivity {
    Button sepuluh,duapuluh,dualima,limapuluh,simpan;
    EditText angka;
    TextView id_customer;
    ImageButton kembali;
    private static final String TAG = LimitTransaksi.class.getSimpleName();
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_transaksi);

        sepuluh = (Button) findViewById(R.id.button2);
        duapuluh = (Button) findViewById(R.id.button3);
        dualima = (Button) findViewById(R.id.button4);
        limapuluh = (Button) findViewById(R.id.button5);
        simpan = (Button) findViewById(R.id.bntsimpan);
        angka = (EditText) findViewById(R.id.angka);
        id_customer = (TextView) findViewById(R.id.idcuz);
        kembali = (ImageButton) findViewById(R.id.pengaturanlimit);
        sm = new SessionManager(LimitTransaksi.this);

        sm.checkLogin();

        HashMap<String, String> map = sm.getDetailLogin();
        id_customer.setText(map.get(sm.KEY_ID));
        id_customer.setVisibility(View.INVISIBLE);
        sm.checkLogin();
        sepuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sepuluh.setBackgroundResource(R.drawable.custom_buttonmerah);
                sepuluh.setTextColor(Color.parseColor("#FFFFFF"));
                angka.setText("10000");
                duapuluh.setBackgroundResource(R.drawable.custom_button);
                duapuluh.setTextColor(Color.parseColor("#000000"));
                dualima.setBackgroundResource(R.drawable.custom_button);
                dualima.setTextColor(Color.parseColor("#000000"));
                limapuluh.setBackgroundResource(R.drawable.custom_button);
                limapuluh.setTextColor(Color.parseColor("#000000"));
            }
        });

        duapuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sepuluh.setBackgroundResource(R.drawable.custom_button);
                sepuluh.setTextColor(Color.parseColor("#000000"));
                angka.setText("20000");
                duapuluh.setBackgroundResource(R.drawable.custom_buttonmerah);
                duapuluh.setTextColor(Color.parseColor("#FFFFFF"));
                dualima.setBackgroundResource(R.drawable.custom_button);
                dualima.setTextColor(Color.parseColor("#000000"));
                limapuluh.setBackgroundResource(R.drawable.custom_button);
                limapuluh.setTextColor(Color.parseColor("#000000"));
            }
        });

        dualima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sepuluh.setBackgroundResource(R.drawable.custom_button);
                sepuluh.setTextColor(Color.parseColor("#000000"));
                angka.setText("25000");
                duapuluh.setBackgroundResource(R.drawable.custom_button);
                duapuluh.setTextColor(Color.parseColor("#000000"));
                dualima.setBackgroundResource(R.drawable.custom_buttonmerah);
                dualima.setTextColor(Color.parseColor("#FFFFFF"));
                limapuluh.setBackgroundResource(R.drawable.custom_button);
                limapuluh.setTextColor(Color.parseColor("#000000"));
            }
        });

        limapuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sepuluh.setBackgroundResource(R.drawable.custom_button);
                sepuluh.setTextColor(Color.parseColor("#000000"));
                angka.setText("50000");
                duapuluh.setBackgroundResource(R.drawable.custom_button);
                duapuluh.setTextColor(Color.parseColor("#000000"));
                dualima.setBackgroundResource(R.drawable.custom_button);
                dualima.setTextColor(Color.parseColor("#000000"));
                limapuluh.setBackgroundResource(R.drawable.custom_buttonmerah);
                limapuluh.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LimitTransaksi.this, Sliding.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LimitTransaksi.this);
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
                        ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                        Call<ResponseLimitModel> call = api.updateLimit(id_customer.getText().toString(), angka.getText().toString());
                        call.enqueue(new Callback<ResponseLimitModel>() {
                            @Override
                            public void onResponse(Call<ResponseLimitModel> call, Response<ResponseLimitModel> response) {
                                Log.d(TAG, "response : " + response.toString());
                                String kode = response.body().getKode();
                                if (kode.equals("1")) {
                                    Toast.makeText(LimitTransaksi.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(getIntent());
                                }
                                else if (kode.equals("2")){
                                    Toast.makeText(LimitTransaksi.this, "Data gagal diupdate", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLimitModel> call, Throwable t) {
                                Log.e(TAG,"error" + t.getMessage());
                            }
                        });
                    }
                });
                alertDialog.show();

            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(LimitTransaksi.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
