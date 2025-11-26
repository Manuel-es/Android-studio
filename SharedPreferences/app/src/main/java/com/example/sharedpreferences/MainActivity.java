package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private Button btnGuardar, btnMostrar;
    private TextView textViewResultado;

    private static final String PREFS_NAME = "MisPreferencias";
    private static final String CLAVE_NOMBRE = "nombre_usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnMostrar = findViewById(R.id.btnMostrar);
        textViewResultado = findViewById(R.id.textViewResultado);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString().trim();

                if (!nombre.isEmpty()) {
                    SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(CLAVE_NOMBRE, nombre);
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "Nombre guardado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, escribe un nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String nombreGuardado = preferences.getString(CLAVE_NOMBRE, "No hay nombre guardado");

                Toast.makeText(getApplicationContext(), "Nombre: " + nombreGuardado, Toast.LENGTH_LONG).show();

                textViewResultado.setText("Nombre guardado: " + nombreGuardado);
            }
        });
    }
}
