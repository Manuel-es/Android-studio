package com.example.quizinteractivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TerceraActivity extends AppCompatActivity {
    private int puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta3);

        Intent intent = getIntent();
        puntuacion = intent.getIntExtra("puntuacion", 0);
    }

    private void puntuacionTotal(Intent intent) {
        startActivity(intent);
    }

    public void incorrecta(View view) {
        Intent intent = new Intent(this, PuntuacionActivity.class);
        intent.putExtra("puntuacion", puntuacion);
        puntuacionTotal(intent);
    }

    public void correcta(View view) {
        Intent intent = new Intent(this, PuntuacionActivity.class);
        puntuacion++;
        intent.putExtra("puntuacion", puntuacion);
        puntuacionTotal(intent);
    }
}