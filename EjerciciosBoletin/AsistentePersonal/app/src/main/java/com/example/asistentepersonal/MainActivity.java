package com.example.asistentepersonal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText editTextNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNombre = findViewById(R.id.editTextNombre);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
    }

    public void guardar(View view) {
        if (editTextNombre.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "No has introducido ningún nombre :(", Toast.LENGTH_SHORT).show();
            return;
        }
        prefs.edit().putString("nombreGuardado", editTextNombre.getText().toString()).apply();
        Intent intentActivity = new Intent(this, AssistantActivity.class);
        startActivity(intentActivity);
    }
}