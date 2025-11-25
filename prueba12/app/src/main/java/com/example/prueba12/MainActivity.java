package com.example.prueba12;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE_NAME = "MiConfiguracionApp";
    private static final String KEY_NOMBRE_USUARIO = "NOMBRE_GUARDADO";

    // Variables de la UI
    private EditText editTextDato;
    private TextView textViewResultado;
    private Button buttonGuardar;
    private Button buttonMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDato = findViewById(R.id.editTextDato);
        textViewResultado = findViewById(R.id.textViewResultado);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonMostrar = findViewById(R.id.buttonMostrar);

        cargarDatoYMostrar();

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDato();
            }
        });

        buttonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatoYMostrar();

                String datoRecuperado = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
                        .getString(KEY_NOMBRE_USUARIO, "No hay dato guardado (o es el valor por defecto)");
                Toast.makeText(getApplicationContext(), "Dato recuperado: " + datoRecuperado, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Guarda el dato del EditText en SharedPreferences.
     */
    private void guardarDato() {
        String dato = editTextDato.getText().toString();

        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(KEY_NOMBRE_USUARIO, dato);

        editor.apply();

        Toast.makeText(getApplicationContext(), "Dato '" + dato + "' guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    /**
     * Recupera el dato guardado de SharedPreferences y lo muestra en el TextView.
     */
    private void cargarDatoYMostrar() {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);

        String datoRecuperado = prefs.getString(KEY_NOMBRE_USUARIO, "No se encontró ningún dato guardado.");

        textViewResultado.setText("Último dato persistente: " + datoRecuperado);
    }
}