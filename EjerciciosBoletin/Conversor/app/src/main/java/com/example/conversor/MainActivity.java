package com.example.conversor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText editTextCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextCantidad = findViewById(R.id.editTextTextCantidad);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
    }

    public void convertir(View view) {
        if (editTextCantidad.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.noCantidad, Toast.LENGTH_LONG).show();
            return;
        }

        prefs.edit().putInt("cantidad", Integer.parseInt(editTextCantidad.getText().toString())).apply();
        Intent intentActivity = new Intent(this, CantidadActivity.class);
        startActivity(intentActivity);
    }
}