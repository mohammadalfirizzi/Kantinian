package com.example.myapplication.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.myapplication.login;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String KEY_USERNAME = "telp_ortu";
    public static final String KEY_PASSWORD = "password_login";
    public static final String KEY_EMAIL = "email_login";
    public static final String KEY_CUSTOMER = "nama_customer";
    public static final String KEY_ID = "id_customer";
    public static final String KEY_ORTU = "nama_ortu";
    public static final String KEY_SEKOLAH = "nama_sekolah";
    public static final String KEY_IDLOGIN = "id_login";
    public static final String KEY_IDKELUARGA = "id_keluarga";
    public static final String KEY_TELPALT = "telp_alternatif";
    public static final String KEY_KODETEMP = "kode_template";
    public static final String KEY_POSISI = "posisi";
    private static final String is_login = "logginstatus";
    private final String SHARE_NAME = "logginsession";
    private final int MODE_PRIVATE = 0;
    private final Context _context;

    public SessionManager(Context context){
        this._context = context;
        sp = context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(String email, String user, String customer, String id_customer, String nama_ortu, String nama_sekolah, String password_login, String id_login, String id_keluarga, String telp_alternatif, String kode_template){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_USERNAME,user);
        editor.putString(KEY_CUSTOMER,customer);
        editor.putString(KEY_ID, id_customer);
        editor.putString(KEY_ORTU, nama_ortu);
        editor.putString(KEY_SEKOLAH, nama_sekolah);
        editor.putString(KEY_PASSWORD, password_login);
        editor.putString(KEY_IDLOGIN, id_login);
        editor.putString(KEY_IDKELUARGA, id_keluarga);
        editor.putString(KEY_TELPALT, telp_alternatif);
        editor.putString(KEY_KODETEMP, kode_template);
        editor.commit();
    }

    public void simpan(String nisn){
        editor.putString(KEY_ID, nisn);
        editor.commit();
    }

    public void widodo(int posisi){
        editor.putString(KEY_POSISI, String.valueOf(posisi));
        editor.commit();
    }

    public HashMap getDetailLogin(){
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_EMAIL, sp.getString(KEY_EMAIL,null));
        map.put(KEY_USERNAME, sp.getString(KEY_USERNAME,null));
        map.put(KEY_CUSTOMER, sp.getString(KEY_CUSTOMER,null));
        map.put(KEY_ID, sp.getString(KEY_ID,null));
        map.put(KEY_ORTU, sp.getString(KEY_ORTU,null));
        map.put(KEY_SEKOLAH, sp.getString(KEY_SEKOLAH,null));
        map.put(KEY_PASSWORD, sp.getString(KEY_PASSWORD,null));
        map.put(KEY_IDLOGIN, sp.getString(KEY_IDLOGIN,null));
        map.put(KEY_IDKELUARGA, sp.getString(KEY_IDKELUARGA,null));
        map.put(KEY_TELPALT, sp.getString(KEY_TELPALT,null));
        map.put(KEY_KODETEMP, sp.getString(KEY_KODETEMP,null));
        map.put(KEY_POSISI, sp.getString(KEY_POSISI,"0"));
        return map;

    }

    public void checkLogin(){
        if (!this.Loggin()){
            Intent Login = new Intent(_context, login.class);
            Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(Login);
        }
    }

    public void logout(){
        editor.clear();
        editor.commit();
        SharedPreferences preferences = _context.getSharedPreferences("checkbox", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember","false");
        editor.apply();
    }

    public Boolean Loggin(){
        return sp.getBoolean(is_login,false);
    }
}
