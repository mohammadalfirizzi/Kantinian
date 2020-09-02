package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ListModel;
import com.example.myapplication.Model.ResponseReadModel;
import com.example.myapplication.Util.SessionManager;
import com.example.myapplication.adapter.AdapterData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatTransaksi extends AppCompatActivity {
    private TextView idcus;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<ListModel> mItems = new ArrayList<>();
    SessionManager sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_transaksi);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewtrf);
        mRecyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this)    ;
        mRecyclerView.setLayoutManager(mManager);
        sm = new SessionManager(RiwayatTransaksi.this);
        idcus = (TextView) findViewById(R.id.idcus);
        sm.checkLogin();

        HashMap<String, String> map = sm.getDetailLogin();
        idcus.setText(map.get(sm.KEY_ID));
        idcus.setVisibility(View.INVISIBLE);
        sm.checkLogin();

        ApiRequest api = RetroClient.getClient().create(ApiRequest.class);

        Call<ResponseReadModel> getData = api.getRiwayat(idcus.getText().toString());
        getData.enqueue(new Callback<ResponseReadModel>() {
            @Override
            public void onResponse(Call<ResponseReadModel> call, Response<ResponseReadModel> response) {
                Log.d("RETRO", "RESPONSE" +response.body().getKode());
                mItems = response.body().getResult();
                mAdapter = new AdapterData(RiwayatTransaksi.this, mItems);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseReadModel> call, Throwable t) {
                Log.d("RETRO", "FAILED : Gagal");
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(RiwayatTransaksi.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
