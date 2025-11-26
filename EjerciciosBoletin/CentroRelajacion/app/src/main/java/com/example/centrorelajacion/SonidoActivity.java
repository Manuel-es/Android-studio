package com.example.centrorelajacion;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SonidoActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonido);
        mp = MediaPlayer.create(this, R.raw.sonido);
    }

    public void reproducir(View view) {
        mp.start();
    }

    public void pausar(View view) {
        mp.pause();
    }

    public void detener(View view) {
        mp.stop();
        mp.release();
        mp = MediaPlayer.create(this, R.raw.sonido);
    }
}