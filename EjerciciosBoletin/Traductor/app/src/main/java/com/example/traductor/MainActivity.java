package com.example.traductor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText texto;
    private SharedPreferences prefs;
    private Traduccion cargarTraducciones = new Traduccion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarComponentes();
    }

    private void asignarComponentes() {
        texto = findViewById(R.id.editTextPalabra);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
    }


    public void traducir(View view) {
        Map<String, String> traducciones = Traduccion.getTraduccion();
        String textoATraducir = texto.getText().toString();
        if (textoATraducir.trim().isEmpty()) {
            Toast.makeText(this, R.string.noTexto, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!traducciones.containsKey(textoATraducir)) {
            Toast.makeText(this, R.string.noExistePalabra, Toast.LENGTH_SHORT).show();
            return;
        }
        prefs.edit().putString("Palabra", textoATraducir).apply();
        cambiarActivity();

    }

    private void cambiarActivity() {
        startActivity(new Intent(this, TraduccionActivity.class));
    }
}