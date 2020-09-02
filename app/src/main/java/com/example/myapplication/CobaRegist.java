package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.example.myapplication.Model.ResponseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CobaRegist extends AppCompatActivity {
    EditText id_customer, nama_customer, kode_template,
            nama_ortu, telp_ortu, email_ortu, password_login,c_pass;
    Button btnsave;
    RadioGroup jk_customer, jk_ortu, kelas;
    RadioButton lk, pr, kelas1, kelas2, kelas3, lk1, pr1;
    TextView txt_hasil,namajenis,namajenis2, namajeniskelas;
    Spinner spinner_pendidikan;
    ProgressDialog pDialog;
    Adapter adapter;
    List<Data> listPendidikan = new ArrayList<Data>();
    public static final String url = "http://kantinian.id/androidapi/viewsekolah.php";

    private static final String TAG = CobaRegist.class.getSimpleName();

    public static final String TAG_ID = "id_sekolah";
    public static final String TAG_PENDIDIKAN = "nama_sekolah";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_regist);
        namajenis = (TextView) findViewById(R.id.namajenis);
        namajenis2 = (TextView) findViewById(R.id.namajenis2);
        namajeniskelas = (TextView) findViewById(R.id.namajeniskelas);
//        nama = (EditText) findViewById(R.id.edt_nama);
//        pass = (EditText) findViewById(R.id.edt_pass);
        id_customer = (EditText) findViewById(R.id.nisn);
        nama_customer = (EditText) findViewById(R.id.anak);
        kode_template = (EditText) findViewById(R.id.pin);
        nama_ortu = (EditText) findViewById(R.id.namaortu);
        email_ortu = (EditText) findViewById(R.id.emailortu);
        telp_ortu = (EditText) findViewById(R.id.nomorortu);
        password_login = (EditText) findViewById(R.id.passwordortu);
        c_pass = (EditText) findViewById(R.id.passwordortu2);
        btnsave = (Button) findViewById(R.id.btn_insertdata);
        jk_customer = (RadioGroup) findViewById(R.id.edt_jk);
        jk_ortu = (RadioGroup) findViewById(R.id.edt_jk_ortu);
        kelas = (RadioGroup) findViewById(R.id.kelas);
        lk = (RadioButton) findViewById(R.id.lk);
        pr = (RadioButton) findViewById(R.id.pr);
        lk1 = (RadioButton) findViewById(R.id.lk2);
        pr1 = (RadioButton) findViewById(R.id.pr2);
        kelas1 = (RadioButton) findViewById(R.id.kelas1);
        kelas2 = (RadioButton) findViewById(R.id.kelas2);
        kelas3 = (RadioButton) findViewById(R.id.kelas3);
        txt_hasil = (TextView) findViewById(R.id.txt_hasil);
        spinner_pendidikan = (Spinner) findViewById(R.id.spinner_pendidikan);

        spinner_pendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                txt_hasil.setText(listPendidikan.get(position).getId_sekolah());
                txt_hasil.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        adapter = new Adapter(CobaRegist.this, listPendidikan);
        spinner_pendidikan.setAdapter(adapter);

        callData();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = jk_customer.getCheckedRadioButtonId();
                int selectedId2 = jk_ortu.getCheckedRadioButtonId();
                int selectedId3 = kelas.getCheckedRadioButtonId();
                String sjksiswa = namajenis.getText().toString();
                String sjkortu = namajenis2.getText().toString();
                String skelas = namajeniskelas.getText().toString();
                if (selectedId == lk.getId()){
                    sjksiswa = lk.getText().toString();
                } else if (selectedId == pr.getId()) {
                    sjksiswa = pr.getText().toString();
                }
                else {
                    Toast.makeText(CobaRegist.this, "Belum memilih", Toast.LENGTH_SHORT).show();
                }
                if (selectedId2 == lk1.getId()){
                    sjkortu = lk1.getText().toString();
                } else if (selectedId2 == pr1.getId()) {
                    sjkortu = pr1.getText().toString();
                }
                else {
                    Toast.makeText(CobaRegist.this, "Belum memilih", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CobaRegist.this, "Belum memilih", Toast.LENGTH_SHORT).show();
                }
                String sidcustomer = id_customer.getText().toString();
                String snamacustomer = nama_customer.getText().toString();
                String ssekolah = txt_hasil.getText().toString();
                String spin = kode_template.getText().toString();
                String snamaortu = nama_ortu.getText().toString();
                String semail = email_ortu.getText().toString();
                String stelp = telp_ortu.getText().toString();
                String spass = password_login.getText().toString();
                String scpass = c_pass.getText().toString();

                ApiRequest api = RetroClient.getClient().create(ApiRequest.class);

                retrofit2.Call<ResponseModel> savedata = api.sendBiodata(sidcustomer,snamacustomer,sjksiswa,ssekolah,skelas,
                        spin,snamaortu,sjkortu,semail,stelp,spass,scpass);
                savedata.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1"))
                        {
                            Toast.makeText(CobaRegist.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }
                        else if (kode.equals("3")){
                            Toast.makeText(CobaRegist.this, "Data anda tidak cocok", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(CobaRegist.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });

    }private void callData() {
        listPendidikan.clear();

        pDialog = new ProgressDialog(CobaRegist.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        // Creating volley request obj
        JsonArrayRequest jArr = new JsonArrayRequest(url,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                Data item = new Data();

                                item.setId_sekolah(obj.getString(TAG_ID));
                                item.setNama_sekolah(obj.getString(TAG_PENDIDIKAN));

                                listPendidikan.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                        hideDialog();
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(CobaRegist.this, error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}