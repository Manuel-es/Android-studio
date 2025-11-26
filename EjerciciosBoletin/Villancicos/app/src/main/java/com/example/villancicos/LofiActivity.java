package com.example.villancicos;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

    public class LofiActivity extends AppCompatActivity {

        private MediaPlayer mp;
        private SharedPreferences prefs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.lofi);
            asignarComponentes();
        }

        private void asignarComponentes() {
            // Asignamos el audio
            mp = MediaPlayer.create(this, R.raw.lofi);

            prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);

            mp.start();
            Toast.makeText(getApplicationContext(), "Reproduciendo: lofi", Toast.LENGTH_SHORT).show();
        }

        public void guardarFav(View view) {
            prefs.edit().putString("villancicoFav", "lofi").apply();
            Toast.makeText(getApplicationContext(), "\uD83C\uDF84Guardado como favorito\uD83C\uDF84", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPause() {
            super.onPause();
            // Detiene la reproducción si la Activity ya no está en primer plano
            if (mp != null && mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
        }
    }
