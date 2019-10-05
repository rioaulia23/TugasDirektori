package com.example.tugasdirektori.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    private static Pref instance;
    SharedPreferences prefs;
    Context ctx;

    private Pref(Context ctx) {
        this.ctx = ctx;
        this.prefs = ctx.getSharedPreferences("tugas", Context.MODE_PRIVATE);
    }

    public static Pref sharedIntance(Context ctx) {
        if (instance == null) {
            instance = new Pref(ctx);
        }
        return instance;
    }

    //simpan status
    public void setStatusInput(Boolean status) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("Status", status);
        editor.apply();
    }

    public boolean cekStatus() {
        return prefs.getBoolean("Status", false);
    }

    public String getNama() {
        return prefs.getString("Nama", "tidak ada");
    }

    //simpan nama
    public void setNama(String nama) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Nama", nama);
        editor.apply();
    }
}

