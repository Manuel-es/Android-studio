package com.example.flamenco;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FlamencoActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView descripcion;
    private String palo;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flamenco);
        asignarComponentes();
        mostrarInfoPalo();
    }

    private void asignarComponentes() {
        imagen = findViewById(R.id.imageViewPalo);
        descripcion = findViewById(R.id.textViewDescripcion);
    }

    private void mostrarInfoPalo() {
        Intent intent = getIntent();

        palo = intent.getStringExtra("palo");

        switch (palo) {
            case "sevillanas":
                mp = MediaPlayer.create(this, R.raw.sevillana);
                imagen.setImageResource(R.drawable.sevillana);
                break;
            case "bulerias":
                mp = MediaPlayer.create(this, R.raw.buleria);
                imagen.setImageResource(R.drawable.buleria);
                break;
            case "fandangos":
                mp = MediaPlayer.create(this, R.raw.fandango);
                imagen.setImageResource(R.drawable.fandango);
                break;
        }
    }

    public void reproducir(View view) {
        mp.start();
    }

    public void volver(View view) {
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}