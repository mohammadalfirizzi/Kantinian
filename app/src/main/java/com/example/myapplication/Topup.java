package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ListModel;
import com.example.myapplication.Model.ResponseReadModel;
import com.example.myapplication.Util.SessionManager;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Topup extends AppCompatActivity implements View.OnClickListener {
    private CardView klik;
    TextView sekolah;
    int saldo = 0;
    ProgressDialog pd;
    TextView idcus, total, notif;
    ImageButton kembali,topup;
    private static final String TAG = Topup.class.getSimpleName();
    SessionManager sm;
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        kembali = (ImageButton) findViewById(R.id.pengaturantopup);
        topup = (ImageButton) findViewById(R.id.tfbank);
        sm = new SessionManager(Topup.this);
        idcus = (TextView) findViewById(R.id.id_customer);
        total = (TextView) findViewById(R.id.harga);
        notif = (TextView) findViewById(R.id.notif);
        klik = (CardView) findViewById(R.id.topvia3);
        sekolah = (TextView) findViewById(R.id.sekolah);
        pd = new ProgressDialog(this);
        pd.setMessage("Sedang memuat ... ");
        pd.setCancelable(false);
        pd.show();
        sm.checkLogin();
        pd.hide();
        HashMap<String, String> map = sm.getDetailLogin();
//        Toast.makeText(Topup.this, map.get(sm.KEY_POSISI), Toast.LENGTH_SHORT).show();
//        idcus.setText(map.get(sm.KEY_ID));
        idcus.setText(map.get(sm.KEY_IDKELUARGA));

        idcus.setVisibility(View.INVISIBLE);
        sm.checkLogin();
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Topup.this, Sliding.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Topup.this, transfer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        klik.setOnClickListener(this);
        ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
        String sid_customer = idcus.getText().toString();
//        Toast.makeText(Topup.this, sid_customer, Toast.LENGTH_SHORT).show();
        Call<ResponseReadModel> call = api.getRead(sid_customer);
        call.enqueue(new Callback<ResponseReadModel>() {
            @Override
            public void onResponse(Call<ResponseReadModel> call, Response<ResponseReadModel> response) {
                pd.hide();
                Log.d(TAG, "response : " + response.toString());
                ResponseReadModel res = response.body();
                List<ListModel> user = res.getResult();
                saldo = Integer.parseInt(user.get(0).getTotal());
                total.setText(formatRupiah.format((double)saldo));
                sekolah.setText(user.get(0).getNama_sekolah());
//                String pemberitahuan;
                notif.setText(user.get(0).getFlag_saldokurang());
                String pemberitahuan;
                pemberitahuan = user.get(0).getFlag_saldokurang();
                if (pemberitahuan.equals("N")){
                    notif.setText("");
                }
                else {
                    notif.setText("Saldo Anda Akan Habis buat besok");
                }
                Toast.makeText(Topup.this,""+user.get(0).getFlag_saldokurang(),Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<ResponseReadModel> call, Throwable t) {
                Log.e(TAG,"error" + t.getMessage());
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(Topup.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.topvia3 : intent = new Intent(this, transfer.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            default:
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
