package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPreference {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String NISN = "nisn";
    public static final String POS = "posisi";
    private final String SHARE_NAME = "logginsession";
    private final int MODE_PRIVATE = 0;
    private final Context _context;

    public SharedPreference(Context context) {
        this._context = context;
        sp = context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }
    public void simpan(String nisn, String posisi){
        editor.putString(NISN, nisn);
        editor.putString(POS,posisi);
        editor.commit();
    }
    public HashMap getData(){
        HashMap<String, String> map = new HashMap<>();
        map.put(NISN, sp.getString(NISN,null));
        map.put(POS, sp.getString(POS, "0"));
        return map;
    }

}
