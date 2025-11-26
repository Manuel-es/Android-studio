package com.example.calculadorapro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {
    private TextView textViewResultado;
    private int num1, num2;
    private String operacion;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        asinarComponentes();
        mostrarResultado();
    }

    private void asinarComponentes() {
        textViewResultado = findViewById(R.id.textViewResultado);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
        // Recogemos los datos del intent
        Intent intent = getIntent();
        num1 = intent.getIntExtra("NUM1", -1);
        num2 = intent.getIntExtra("NUM2", -1);
        operacion = intent.getStringExtra("OPERACION");
    }

    private void mostrarResultado() {
        double resultado = 0;
        switch (operacion) {
            case "SUMA":
                resultado = num1 + num2;
                break;
            case "RESTA":
                resultado = num1 - num2;
                break;
            case "MULTIPLICACION":
                resultado = num1 * num2;
                break;
            case "DIVISION":
                resultado = (double) num1 / num2;
                break;
        }
        // Lo muestra en el textView
        textViewResultado.setText(String.valueOf(resultado));
    }

    public void guardar(View view) {
        prefs.edit().putString("Resultado", textViewResultado.getText().toString()).apply();
        Toast.makeText(getApplicationContext(), R.string.resultadoGuardado, Toast.LENGTH_SHORT).show();
    }

    public void mostrar(View view) {
        String resultadoGuardado = prefs.getString("Resultado", null);
        // Si no hay nada guardado
        if (resultadoGuardado == null) {
            Toast.makeText(getApplicationContext(), R.string.noResultados, Toast.LENGTH_SHORT).show();
            return;
        }
        // Muestra el guardado
        String mensajeResultado = getString(R.string.mostrarResultadoGuardado) + " " + resultadoGuardado;
        Toast.makeText(getApplicationContext(), mensajeResultado, Toast.LENGTH_SHORT).show();
    }

}
