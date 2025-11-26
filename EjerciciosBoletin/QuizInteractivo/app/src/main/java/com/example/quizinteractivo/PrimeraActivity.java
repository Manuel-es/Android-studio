package com.example.quizinteractivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

    public class PrimeraActivity extends AppCompatActivity {
        private int puntuacion = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pregunta1);
        }

        private void pregunta2(Intent intent) {
            startActivity(intent);
        }

        public void incorrecta(View view) {
            Intent intent = new Intent(this, SegundaActivity.class);
            intent.putExtra("puntuacion", puntuacion);
            pregunta2(intent);
        }

        public void correcta(View view) {
            Intent intent = new Intent(this, SegundaActivity.class);
            puntuacion++;
            intent.putExtra("puntuacion", puntuacion);
            pregunta2(intent);
        }
    }
