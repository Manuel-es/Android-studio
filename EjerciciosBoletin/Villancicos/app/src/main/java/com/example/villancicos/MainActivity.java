package com.example.villancicos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarComponentes();
        mostrarVillancicoFav();
    }

    private void asignarComponentes() {
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
    }

    private void mostrarVillancicoFav() {
        String villancicoFav = prefs.getString("villancicoFav", null);
        if (villancicoFav == null) {
            return;
        }
        Toast.makeText(getApplicationContext(), "\uD83C\uDF85Tu villancico favorito es: " + villancicoFav + "\uD83C\uDFB6", Toast.LENGTH_LONG).show();
    }

    public void villancicoLofi(View view) {
        startActivity(new Intent(this, LofiActivity.class));
    }

    public void villancicoChristmas(View view) {
        startActivity(new Intent(this, ChristmasActivity.class));
    }

    public void villancicoNoel(View view) {
        startActivity(new Intent(this, NoelActivity.class));
    }
}