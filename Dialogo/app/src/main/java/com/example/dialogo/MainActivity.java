package com.example.dialogo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity {

        // Opciones del diálogo
        CharSequence[] opciones = {"Turno de mañana", "Turno de tarde"};
        boolean[] seleccion = {false, false};  // selección múltiple

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button boton = findViewById(R.id.btnMostrar);

            boton.setOnClickListener(v -> mostrarDialogo());
        }

        private void mostrarDialogo() {

            AlertDialog.Builder constructor = new AlertDialog.Builder(this);

            constructor.setTitle("SELECCIONA TU CITA");
            constructor.setIcon(android.R.mipmap.sym_def_app_icon);

            constructor.setMultiChoiceItems(opciones, seleccion,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            seleccion[which] = isChecked;
                        }
                    });

            constructor.setPositiveButton("Aceptar", null);
            constructor.setNegativeButton("Cancelar", null);

            AlertDialog dialogo = constructor.create();
            dialogo.show();
        }

}

