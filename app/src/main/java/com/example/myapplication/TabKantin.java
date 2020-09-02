package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

public class TabKantin extends TabActivity {
    ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_kantin);
        kembali = (ImageButton) findViewById(R.id.pengaturanrtrfd);
        TabHost th = getTabHost();
        TabHost.TabSpec ts;
        Intent i;

        i = new Intent().setClass(this, RiwayatTransaksi.class);
        ts = th.newTabSpec("Pending").setIndicator("Pending",null).setContent(i);
        th.addTab(ts);

        i = new Intent().setClass(this, RiwayatTransaksiSelesai.class);
        ts = th.newTabSpec("Selesai").setIndicator("Selesai",null).setContent(i);
        th.addTab(ts);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabKantin.this, Sliding.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }
    public void onBackPressed(){
        Intent intent = new Intent(TabKantin.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
