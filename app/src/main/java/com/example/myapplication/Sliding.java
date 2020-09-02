package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Util.SessionManager;
import com.example.myapplication.adapter.AdapterData;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sliding extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private ArrayList<DataModel> arrayList;
    ImageButton profil,topup,logout,riwayattrf,limit,trf,cicilan,sedekah,tbanak;
    Button textprofilsaya,textRiwayatTransaksi,textpengaturan,textbantuan,texttentang,texthubungi;
    TextView nama,sekolah, namaanak, nisnanak, validthru;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);
        arrayList = new ArrayList<>();
        nama = (TextView) findViewById(R.id.textprofil);
        sekolah = (TextView) findViewById(R.id.textsekolah);
        namaanak = (TextView) findViewById(R.id.namaanak);
        nisnanak = (TextView) findViewById(R.id.nisnanak);
        validthru = (TextView) findViewById(R.id.validanak);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        sm = new SessionManager(Sliding.this);

        sm.checkLogin();
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        final RelativeLayout content = (RelativeLayout) findViewById(R.id.content);
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        HashMap<String, String> map = sm.getDetailLogin();

        nama.setText(map.get(sm.KEY_ORTU));
        sekolah.setText(map.get(sm.KEY_SEKOLAH));
//        nisnanak.setText(map.get(sm.KEY_ID));
//        namaanak.setText(map.get(sm.KEY_CUSTOMER));
        String id_keluarga = map.get(sm.KEY_IDKELUARGA);
        sm.checkLogin();

        profil = (ImageButton) findViewById(R.id.profil);
        topup = (ImageButton) findViewById(R.id.topup);
        logout = (ImageButton) findViewById(R.id.btnlogout);
        riwayattrf = (ImageButton) findViewById(R.id.riwayattrf);
        limit = (ImageButton) findViewById(R.id.limit);
        trf = (ImageButton) findViewById(R.id.trf);
        cicilan = (ImageButton) findViewById(R.id.cicilan);
        sedekah = (ImageButton) findViewById(R.id.sedekah);
        tbanak = (ImageButton) findViewById(R.id.tbanak);
        textprofilsaya = (Button) findViewById(R.id.textprofilsaya);
        textRiwayatTransaksi = (Button) findViewById(R.id.textRiwayatTransaksi);
        textpengaturan = (Button) findViewById(R.id.textpengaturan);
        textbantuan = (Button) findViewById(R.id.textbantuan);
        texttentang = (Button) findViewById(R.id.texttentang);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Sliding.this);
        recyclerView.setLayoutManager(layoutManager);
        ApiRequest apiRequest = RetroClient.getClient().create(ApiRequest.class);


//        if (savedInstanceState == null) {
//            Bundle bundle = getIntent().getExtras();
//            if (bundle == null) {
//                id_keluarga = null;
//            }
//            else {
//                id_keluarga = bundle.getString("id_keluarga");
//            }
//        }
//        else {
//            id_keluarga = (String) savedInstanceState.getSerializable("id_keluarga");
//        }
//        Toast.makeText(Sliding.this, id_keluarga, Toast.LENGTH_SHORT).show();

        Call<ResponseeModel> getData = apiRequest.getBaca(id_keluarga);
        getData.enqueue(new Callback<ResponseeModel>() {
            @Override
            public void onResponse(Call<ResponseeModel> call, Response<ResponseeModel> response) {
                Log.d("RETRO","RESPONSE : " +response.body().getKode());
                arrayList = (ArrayList<DataModel>) response.body().getResult();
                cardAdapter = new CardAdapter(Sliding.this, arrayList);
                recyclerView.setAdapter(cardAdapter);
            }

            @Override
            public void onFailure(Call<ResponseeModel> call, Throwable t) {

            }
        });
        texthubungi = (Button) findViewById(R.id.texthubungi);profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, ProfilSaya.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Sliding.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);
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
                        sm.logout();
                        sm.checkLogin();
                    }
                });
                alertDialog.show();
            }
        });
        textprofilsaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, ProfilSaya.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, Topup.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        riwayattrf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, TabKantin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//                Toast.makeText(Sliding.this, "Menu Masih Belum Tersedia ! Silakan bisa Menunggu ", Toast.LENGTH_SHORT).show();
            }
        });
        textRiwayatTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, TabKantin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        textpengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, ubahpinpass.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        textbantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, Faq.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        texttentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, Faq.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        texthubungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, HubungiKami.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sliding.this, LimitTransaksi.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        trf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Sliding.this, transfer.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                Toast.makeText(Sliding.this, "Menu Masih Belum Tersedia ! Silakan bisa Menunggu ", Toast.LENGTH_SHORT).show();
            }
        });
        sedekah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sliding.this, "Menu Masih Belum Tersedia ! Silakan bisa Menunggu ", Toast.LENGTH_SHORT).show();
            }
        });
        cicilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sliding.this, "Menu Masih Belum Tersedia ! Silakan bisa Menunggu ", Toast.LENGTH_SHORT).show();
            }
        });
        tbanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sliding.this, TambahAnak.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}
