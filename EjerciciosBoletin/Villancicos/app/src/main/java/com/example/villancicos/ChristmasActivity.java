package com.example.villancicos;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

    public class ChristmasActivity extends AppCompatActivity {
        private MediaPlayer mp;
        private SharedPreferences prefs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.christmas);
            asignarComponentes();
        }

        private void asignarComponentes() {
            // Asignamos el audio
            mp = MediaPlayer.create(this, R.raw.christmas);

            prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);

            mp.start();
            Toast.makeText(getApplicationContext(), "Reproduciendo: christmas", Toast.LENGTH_SHORT).show();
        }

        public void guardarFav(View view) {
            prefs.edit().putString("villancicoFav", "christmas").apply();
            Toast.makeText(getApplicationContext(), "\uD83C\uDF84Guardado como favorito\uD83C\uDF84", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPause() {
            super.onPause();
            if (mp != null && mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
        }
    }