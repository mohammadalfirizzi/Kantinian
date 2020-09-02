package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.CompoundButtonCompat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ResponseApiModel;
import com.example.myapplication.Model.UserModel;
import com.example.myapplication.Util.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Color.WHITE;

public class login extends AppCompatActivity {
    CheckBox remember;
    private EditText edtusername, edtpassword;
    private Button login, regist;
    Button lupaid,lupapass;
    ProgressDialog pd;
    private static final String TAG = login.class.getSimpleName();
    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pd = new ProgressDialog(this);
        edtusername = (EditText) findViewById(R.id.usernamee);
            edtpassword = (EditText) findViewById(R.id.passwordd);
        remember = (CheckBox) findViewById(R.id.checkBox);
        login = (Button) findViewById(R.id.btnlogin);
        regist = (Button) findViewById(R.id.btnregist);
        lupaid = (Button) findViewById(R.id.lupaid);
        lupapass = (Button) findViewById(R.id.lupapas);
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")){
            Intent intent = new Intent(login.this, Sliding.class);
            startActivity(intent);
        }
        else if (checkbox.equals("false")){
            Toast.makeText(this, "Silakan Masuk Terlebih Dahulu", Toast.LENGTH_LONG).show();
        }
        sm = new SessionManager(login.this);
        if (Build.VERSION.SDK_INT < 21){
            CompoundButtonCompat.setButtonTintList(remember, ColorStateList.valueOf(WHITE));
        }
        lupaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(com.example.myapplication.login.this, "Menu Masih Belum Tersedia ! Silakan bisa Menunggu ", Toast.LENGTH_SHORT).show();
            }
        });
        lupapass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.myapplication.login.this, ubahpinpass.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, RegistOrtu.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                pd.setMessage("Sedang memuat masuk ... ");
                pd.setCancelable(false);
                pd.show();
                ApiRequest Api = RetroClient.getRequestService();
                Call<ResponseApiModel> login = Api.login(edtusername.getText().toString(), edtpassword.getText().toString());
                login.enqueue(new Callback<ResponseApiModel>() {
                    @Override
                    public void onResponse(Call<ResponseApiModel> call, Response<ResponseApiModel> response) {
                        pd.hide();
                        Log.d(TAG, "response : " + response.toString());
                        ResponseApiModel res = response.body();
                        List<UserModel> user = res.getResult();
                        if (res.getKode().equals("1")){
                            sm.storeLogin(user.get(0).getEmail_login(), user.get(0).getTelp_ortu(),user.get(0).getNama_customer(), user.get(0).getId_customer(), user.get(0).getNama_ortu(), user.get(0).getNama_sekolah(), user.get(0).getPassword_login(),
                                    user.get(0).getId_login(), user.get(0).getId_keluarga(), user.get(0).getTelp_alternatif(), user.get(0).getKode_template());
                            Toast.makeText(login.this, "Username / Password Ditemukan login Sukses ! ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, Sliding.class);
                            intent.putExtra("id_customer",user.get(0).getId_customer());
                            intent.putExtra("email_login",user.get(0).getEmail_login());
                            intent.putExtra("telp_ortu",user.get(0).getTelp_ortu());
                            intent.putExtra("nama_customer",user.get(0).getNama_customer());
                            intent.putExtra("nama_ortu",user.get(0).getNama_ortu());
                            intent.putExtra("nama_sekolah",user.get(0).getNama_sekolah());
                            intent.putExtra("password_login",user.get(0).getPassword_login());
                            intent.putExtra("id_login",user.get(0).getId_login());
                            intent.putExtra("id_keluarga",user.get(0).getId_keluarga());
                            intent.putExtra("telp_alternatif",user.get(0).getTelp_alternatif());
                            intent.putExtra("kode_template",user.get(0).getKode_template());
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                        else {
                            pd.hide();
                            Toast.makeText(login.this, "Username / Password tidak cocok", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApiModel> call, Throwable t) {
                            Log.e(TAG,"error" + t.getMessage());
                    }
                });
            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                }
                else if (!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });
    }
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                login.super.onBackPressed();
                finish();
                moveTaskToBack(true);
            }
        });
        alertDialog.show();

    }
}
