package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.BiodataModel;
import com.example.myapplication.Model.ResponseBiodataModel;
import com.example.myapplication.Util.SessionManager;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilSaya extends AppCompatActivity {
    ImageButton editprofil,kembali;
    private static final String TAG = ProfilSaya.class.getSimpleName();
    ProgressDialog pd;
    TextView email, nomor1, nomor2,nama,sma, id_customer;
    public SwipeRefreshLayout swipeRefreshLayout;
    final Handler handler = new Handler();
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_saya);
        pd = new ProgressDialog(this);
        pd.setMessage("Sedang memuat ... ");
        pd.setCancelable(false);
        pd.show();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        editprofil = (ImageButton) findViewById(R.id.editprofil);
        kembali = (ImageButton) findViewById(R.id.pengaturanprofilsaya);
        nama = (TextView) findViewById(R.id.nama);
        sma = (TextView) findViewById(R.id.sma);
        email = (TextView) findViewById(R.id.txtemail);
        nomor1 = (TextView) findViewById(R.id.txtnomor1);
        nomor2 = (TextView) findViewById(R.id.txtnomor2);
        id_customer = (TextView) findViewById(R.id.idcustomer);

        sm = new SessionManager(ProfilSaya.this);
        sm.checkLogin();
        pd.hide();
        HashMap<String , String > map = sm.getDetailLogin();
        id_customer.setText(map.get(sm.KEY_IDKELUARGA ));
        id_customer.setVisibility(View.INVISIBLE);
        sm.checkLogin();
        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilSaya.this, Editprofil.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilSaya.this, Sliding.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        ApiRequest api = RetroClient.getClient().create(ApiRequest.class);

        Call<ResponseBiodataModel> getData = api.readBiodata(id_customer.getText().toString());
        getData.enqueue(new Callback<ResponseBiodataModel>() {
            @Override
            public void onResponse(Call<ResponseBiodataModel> call, Response<ResponseBiodataModel> response) {
                pd.hide();
                Log.d(TAG, "response : " + response.toString());
                ResponseBiodataModel res = response.body();
                List<BiodataModel> user = res.getResult();
                email.setText(user.get(0).getEmail_ortu());
                nama.setText(user.get(0).getNama_ortu());
                sma.setText(user.get(0).getNama_sekolah());
                nomor1.setText(user.get(0).getTelp_ortu());
                nomor2.setText(user.get(0).getKtp());
            }

            @Override
            public void onFailure(Call<ResponseBiodataModel> call, Throwable t) {
                Log.e(TAG,"error" + t.getMessage());
            }
        });swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(ProfilSaya.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
