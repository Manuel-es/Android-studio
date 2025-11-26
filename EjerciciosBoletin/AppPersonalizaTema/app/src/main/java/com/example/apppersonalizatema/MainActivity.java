package com.example.apppersonalizatema;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_TEMA = "TEMA";
    private SharedPreferences prefs;
    private RadioGroup radioGroupTema;

    private int temaPendiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences("PrefsTema", MODE_PRIVATE);
        configurarTema();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarComponentes();
        establecerSeleccionInicial();
        configurarRadioGroupListener();
    }

    private void asignarComponentes() {
        radioGroupTema = findViewById(R.id.radioGroup);
    }

    private void configurarRadioGroupListener() {
        radioGroupTema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonClaro) {
                    temaPendiente = AppCompatDelegate.MODE_NIGHT_NO;
                } else if (checkedId == R.id.radioButtonOscuro) {
                    temaPendiente = AppCompatDelegate.MODE_NIGHT_YES;
                } else {
                    temaPendiente = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                }
            }
        });
    }

    public void guardar(View view) {
        guardarTema(temaPendiente);
        String temaNombre = null;

        if (temaPendiente == AppCompatDelegate.MODE_NIGHT_NO) {
            temaNombre = "Claro";
        } else {
            temaNombre = "Oscuro \uD83C\uDF19";
        }

        Toast.makeText(getApplicationContext(), "Se ha activado el modo " + temaNombre, Toast.LENGTH_SHORT).show();
        reiniciarActivity();
    }

    private void guardarTema(int tema) {
        prefs.edit().putInt(KEY_TEMA, tema).apply();
    }

    private void configurarTema() {
        int tema = prefs.getInt(KEY_TEMA, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(tema);
    }

    private void establecerSeleccionInicial() {
        int temaActual = prefs.getInt(KEY_TEMA, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        temaPendiente = temaActual;

        if (temaActual == AppCompatDelegate.MODE_NIGHT_NO) {
            radioGroupTema.check(R.id.radioButtonClaro);
        } else if (temaActual == AppCompatDelegate.MODE_NIGHT_YES) {
            radioGroupTema.check(R.id.radioButtonOscuro);
        }
    }

    private void reiniciarActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}