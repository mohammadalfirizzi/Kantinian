package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ubahpinpass extends AppCompatActivity {
    ImageButton ubahpin, ubahpass, kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpinpass);
        ubahpin = (ImageButton) findViewById(R.id.ubahpin);
        ubahpass = (ImageButton) findViewById(R.id.ubahpass);
        kembali = (ImageButton) findViewById(R.id.pengaturan);
        ubahpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ubahpinpass.this, ubahpindua.class);
                startActivity(intent);
            }
        });
        ubahpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ubahpinpass.this, ubahpin.class);
                startActivity(intent);
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ubahpinpass.this, Sliding.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(ubahpinpass.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
