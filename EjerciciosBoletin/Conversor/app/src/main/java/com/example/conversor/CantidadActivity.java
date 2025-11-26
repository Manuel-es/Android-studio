package com.example.conversor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CantidadActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private TextView txtCantDolares;
    private TextView txtCantRegion;
    private TextView txtFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cantidad);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
        mostrarDatos();
    }

    public void mostrarDatos() {
        txtCantDolares = findViewById(R.id.textViewCantDolares);
        txtCantRegion = findViewById(R.id.textViewCantRegion);
        txtFecha = findViewById(R.id.textViewFecha);

        int cantidad = prefs.getInt("cantidad", -1);
        NumberFormat importeFormatoDolares = NumberFormat.getCurrencyInstance(Locale.US);

        NumberFormat importeFormatoRegion = NumberFormat.getCurrencyInstance(Locale.getDefault());

        Date hoy = new Date();

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        String fecha = df.format(hoy);

        txtCantDolares.setText(importeFormatoDolares.format(cantidad));
        txtCantRegion.setText(importeFormatoRegion.format(cantidad));
        txtFecha.setText(fecha);
    }
}
