package com.example.asistentepersonal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AssistantActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private TextView textViewSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);
        prefs = getSharedPreferences("MisPrefs", MODE_PRIVATE);
        textViewSaludo = findViewById(R.id.textViewSaludo);
        saludo();
    }

    public void saludo() {
        Calendar ahora = Calendar.getInstance();
        int hora = ahora.get(Calendar.HOUR_OF_DAY);
        String etapaDia;
        if (hora >= 6 && hora <= 11) {
            etapaDia = "Buenos días, ";
        } else if (hora <= 19) {
            etapaDia = "Buenas tardes, ";
        } else {
            etapaDia = "Buenas noches, ";
        }
        textViewSaludo.setText("¡" + etapaDia + prefs.getString("nombreGuardado", null) + "!");
    }

    public void volver(View view) {
        Intent intentVolver = new Intent(this, MainActivity.class);
        startActivity(intentVolver);
    }
}
