package com.example.persistencia;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnRegistrar;
    private Button btnMostraru;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inicialización de los botones (Usando los IDs de tu layout)
        btnRegistrar = findViewById(R.id.Registrar);
        btnMostraru = findViewById(R.id.Mostraru); // Asumiendo que este ID es correcto
        btnLogin = findViewById(R.id.btnLogin);

        // 2. Configuración del Listener para el botón Registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega a la actividad 'Registro'
                startActivity(new Intent(MainActivity.this, Registro.class));
            }
        });

        // 3. Configuración del Listener para el botón Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega a la actividad 'Login'
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }
}