package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class transfer extends AppCompatActivity {
    TextView angka;
    Button sepuluh,duapuluh,dualima,limapuluh,seratus,duaratus, lanjutkan;
    ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        angka = (TextView) findViewById(R.id.nominal);
        kembali = (ImageButton) findViewById(R.id.pengaturantrf);
        lanjutkan = (Button) findViewById(R.id.lanjutkan);
        sepuluh = (Button) findViewById(R.id.sepuluh);
        duapuluh = (Button) findViewById(R.id.duapuluh);
        dualima = (Button) findViewById(R.id.dualima);
        limapuluh = (Button) findViewById(R.id.limapuluh);
        seratus = (Button) findViewById(R.id.seratus);
        duaratus = (Button) findViewById(R.id.duaratus);
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
                seratus.setBackgroundResource(R.drawable.custom_button);
                seratus.setTextColor(Color.parseColor("#000000"));
                duaratus.setBackgroundResource(R.drawable.custom_button);
                duaratus.setTextColor(Color.parseColor("#000000"));
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
                seratus.setBackgroundResource(R.drawable.custom_button);
                seratus.setTextColor(Color.parseColor("#000000"));
                duaratus.setBackgroundResource(R.drawable.custom_button);
                duaratus.setTextColor(Color.parseColor("#000000"));
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
                seratus.setBackgroundResource(R.drawable.custom_button);
                seratus.setTextColor(Color.parseColor("#000000"));
                duaratus.setBackgroundResource(R.drawable.custom_button);
                duaratus.setTextColor(Color.parseColor("#000000"));
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
                seratus.setBackgroundResource(R.drawable.custom_button);
                seratus.setTextColor(Color.parseColor("#000000"));
                duaratus.setBackgroundResource(R.drawable.custom_button);
                duaratus.setTextColor(Color.parseColor("#000000"));
            }
        });
        seratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sepuluh.setBackgroundResource(R.drawable.custom_button);
                sepuluh.setTextColor(Color.parseColor("#000000"));
                angka.setText("100000");
                duapuluh.setBackgroundResource(R.drawable.custom_button);
                duapuluh.setTextColor(Color.parseColor("#000000"));
                dualima.setBackgroundResource(R.drawable.custom_button);
                dualima.setTextColor(Color.parseColor("#000000"));
                limapuluh.setBackgroundResource(R.drawable.custom_button);
                limapuluh.setTextColor(Color.parseColor("#000000"));
                seratus.setBackgroundResource(R.drawable.custom_buttonmerah);
                seratus.setTextColor(Color.parseColor("#FFFFFF"));
                duaratus.setBackgroundResource(R.drawable.custom_button);
                duaratus.setTextColor(Color.parseColor("#000000"));
            }
        });
        duaratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sepuluh.setBackgroundResource(R.drawable.custom_button);
                sepuluh.setTextColor(Color.parseColor("#000000"));
                angka.setText("200000");
                duapuluh.setBackgroundResource(R.drawable.custom_button);
                duapuluh.setTextColor(Color.parseColor("#000000"));
                dualima.setBackgroundResource(R.drawable.custom_button);
                dualima.setTextColor(Color.parseColor("#000000"));
                limapuluh.setBackgroundResource(R.drawable.custom_button);
                limapuluh.setTextColor(Color.parseColor("#000000"));
                seratus.setBackgroundResource(R.drawable.custom_button);
                seratus.setTextColor(Color.parseColor("#000000"));
                duaratus.setBackgroundResource(R.drawable.custom_buttonmerah);
                duaratus.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(transfer.this, Topup.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        lanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(transfer.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog_batal,null);
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
                        String sangka = angka.getText().toString();
                        Intent intent = new Intent(transfer.this, Transferdua.class);
                        intent.putExtra("angka", sangka);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                });
                alertDialog.show();

            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(transfer.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
