package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistOrtu extends AppCompatActivity {
    EditText no, email, password, c_pass;
    Button lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_ortu);
        no = (EditText) findViewById(R.id.nohp);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.passwordortu);
        c_pass = (EditText) findViewById(R.id.passwordortu2);
        lanjut = (Button) findViewById(R.id.lanjutt);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sc_pass = c_pass.getText().toString();
                String spass = password.getText().toString();
                if (!sc_pass.equals(spass)){
                    Toast.makeText(RegistOrtu.this,"password anda tidak cocok", Toast.LENGTH_LONG).show();
                }
                else {
                    String nomor = no.getText().toString();
                    String semail = email.getText().toString();
                    String spassword = password.getText().toString();
                    Intent intent = new Intent(RegistOrtu.this, RegistOrtudua.class);
                    intent.putExtra("nomor",nomor);
                    intent.putExtra("email",semail);
                    intent.putExtra("password",spass);
                    startActivity(intent);

                }
            }
        });

    }
}
