package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.DataSekolahModel;
import com.example.myapplication.Model.ResponseAnakModel;
import com.example.myapplication.Model.ResponseSekolahModel;
import com.example.myapplication.Model.UserModel;
import com.example.myapplication.Util.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahAnak extends AppCompatActivity {
    TextView txt_hasil,namajenis, namajeniskelas;;
    EditText nisn,nama,password,c_pass,namasekolah;
    Button btn_save,cek;
    RadioGroup jk_customer,kelas;
    RadioButton lk, pr, kelas1, kelas2, kelas3;
    ProgressDialog pDialog;
    public static final String url = "http://kantinian.id/androidapi/viewsekolah.php";

    private static final String TAG = TambahAnak.class.getSimpleName();

    public static final String TAG_ID = "id_sekolah";
    public static final String TAG_PENDIDIKAN = "nama_sekolah";
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_anak2);
        namajenis = (TextView) findViewById(R.id.namajenis);
        namajeniskelas = (TextView) findViewById(R.id.namajeniskelas);
        nisn = (EditText) findViewById(R.id.nisn);
        nama = (EditText) findViewById(R.id.nama);
        namasekolah = (EditText) findViewById(R.id.ceksekolah);
        password = (EditText) findViewById(R.id.password);
        c_pass = (EditText) findViewById(R.id.c_password);
        btn_save = (Button) findViewById(R.id.btn_insertdata);
        cek = (Button) findViewById(R.id.cek);
        jk_customer = (RadioGroup) findViewById(R.id.edt_jk);
        kelas = (RadioGroup) findViewById(R.id.kelas);
        lk = (RadioButton) findViewById(R.id.lk);
        pr = (RadioButton) findViewById(R.id.pr);
        kelas1 = (RadioButton) findViewById(R.id.kelas1a);
        kelas2 = (RadioButton) findViewById(R.id.kelas2a);
        kelas3 = (RadioButton) findViewById(R.id.kelas3a);
        txt_hasil = (TextView) findViewById(R.id.txt_hasil);
        sm = new SessionManager(TambahAnak.this);
        sm.checkLogin();
        HashMap<String, String> map = sm.getDetailLogin();
        final String id_keluarga = map.get(sm.KEY_IDKELUARGA);
        sm.checkLogin();
//        Toast.makeText(TambahAnak.this,""+id_keluarga, Toast.LENGTH_LONG).show();
        String sidcustomer = nisn.getText().toString();

        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                retrofit2.Call<ResponseSekolahModel> cek = api.getCek(nisn.getText().toString());
                cek.enqueue(new Callback<ResponseSekolahModel>() {
                    @Override
                    public void onResponse(Call<ResponseSekolahModel> call, Response<ResponseSekolahModel> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        List<DataSekolahModel> data = response.body().getResult();
                        if(kode.equals("1")){
//                            Toast.makeText(TambahAnak.this, "Data Ditemukan "+data.get(0).getNama_sekolah(), Toast.LENGTH_SHORT).show();
                            namasekolah.setText(data.get(0).getNama_sekolah());
                            txt_hasil.setText(data.get(0).getId_sekolah());
                            Toast.makeText(TambahAnak.this, "Data Ditemukan "+data.get(0).getId_sekolah(), Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(TambahAnak.this, ""+nisn.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseSekolahModel> call, Throwable t) {

                    }
                });
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = jk_customer.getCheckedRadioButtonId();
                int selectedId3 = kelas.getCheckedRadioButtonId();
                String sjksiswa = namajenis.getText().toString();
                String skelas = namajeniskelas.getText().toString();
                if (selectedId == lk.getId()){
                    sjksiswa = lk.getText().toString();
                } else if (selectedId == pr.getId()) {
                    sjksiswa = pr.getText().toString();
                }
                else {
                    Toast.makeText(TambahAnak.this, "Belum memilih", Toast.LENGTH_SHORT).show();
                }
                if (selectedId3 == kelas1.getId()){
                    skelas = kelas1.getText().toString();
                } else if (selectedId3 == kelas2.getId()) {
                    skelas = kelas2.getText().toString();
                }
                else if (selectedId3 == kelas3.getId()) {
                    skelas = kelas3.getText().toString();
                }
                else {
                    Toast.makeText(TambahAnak.this, "Belum memilih", Toast.LENGTH_SHORT).show();
                }
                String sidcustomer = nisn.getText().toString();
                String snamacustomer = nama.getText().toString();
                String ssekolah = txt_hasil.getText().toString();
                String spin = password.getText().toString();
                String scpass = c_pass.getText().toString();
                ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                retrofit2.Call<ResponseAnakModel> savedata = api.sendAnak(id_keluarga,snamacustomer,sjksiswa,ssekolah,skelas,spin,scpass);

                savedata.enqueue(new Callback<ResponseAnakModel>() {
                    @Override
                    public void onResponse(Call<ResponseAnakModel> call, Response<ResponseAnakModel> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1"))
                        {
                            Toast.makeText(TambahAnak.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }
                        else if (kode.equals("3")){
                            Toast.makeText(TambahAnak.this, "Data anda tidak cocok", Toast.LENGTH_SHORT).show();
                        }
                        else if(kode.equals("4")){
                            Toast.makeText(TambahAnak.this, "Data Anak Tidak Lebih dari 2 anak", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(TambahAnak.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAnakModel> call, Throwable t) {
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(TambahAnak.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    }

