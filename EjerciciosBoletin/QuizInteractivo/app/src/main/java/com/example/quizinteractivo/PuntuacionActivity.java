package com.example.quizinteractivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PuntuacionActivity extends AppCompatActivity {
    private int puntuacion;
    private TextView textViewPuntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuacion);

        Intent intent = getIntent();
        puntuacion = intent.getIntExtra("puntuacion", 0);

        textViewPuntuacion = findViewById(R.id.textViewPuntuacion);
        mostrarPuntuacion();
    }

    private void mostrarPuntuacion() {
        textViewPuntuacion.append(String.valueOf(puntuacion) + "/3");
    }

    public void volverAJugar(View view) {
        startActivity(new Intent(this, PrimeraActivity.class));
    }

}
