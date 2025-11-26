package com.example.centrorelajacion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
        ultimoModo();
    }

    public void modoSonido(View view) {
        Intent intentSonido = new Intent(this, SonidoActivity.class);
        startActivity(intentSonido);
        prefs.edit().putString("ultimoModo", "Sonido").apply();
    }

    public void modoVideo(View view) {
        Intent intentVideo = new Intent(this, VideoActivity.class);
        startActivity(intentVideo);
        prefs.edit().putString("ultimoModo", "Vídeo").apply();
    }

    private void ultimoModo() {
        Toast.makeText(this, "Tu último modo relajante fue: " + prefs.getString("ultimoModo", "Ninguno") + " \uD83C\uDF27", Toast.LENGTH_LONG).show();
    }
}