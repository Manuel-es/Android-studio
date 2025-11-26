package com.example.traductor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class TraduccionActivity extends AppCompatActivity {

    private TextView textoTraducido;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traduccion);

        asignarComponentes();
        mostrarTraduccion();
    }

    private void asignarComponentes() {
        textoTraducido = findViewById(R.id.textViewTraduccion);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
    }

    private void mostrarTraduccion() {
        Map<String, String> traducciones = Traduccion.getTraduccion();

        String traducir = prefs.getString("Palabra", null);
        String palabraTraducida = traducciones.get(traducir);

        textoTraducido.setText(palabraTraducida);
    }


}
