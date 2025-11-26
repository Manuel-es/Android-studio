package com.example.appguia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SevillaActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sevilla);
        asignarComponentes();
    }

    private void asignarComponentes() {
        mp = MediaPlayer.create(this, R.raw.sevilla);
    }

    public void reproducir(View view){
        mp.start();
    }

    public void inicio(View view) {
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        startActivity(new Intent(this, MainActivity.class));
    }
}
