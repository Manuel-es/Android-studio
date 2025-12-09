package com.example.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private EditText correousu;
    private EditText contrausu;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        correousu = findViewById(R.id.correo);
        contrausu = findViewById(R.id.contraseña);
        btnGuardar = findViewById(R.id.Guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = correousu.getText().toString();
                String contra = contrausu.getText().toString();

                if (correo.isEmpty() || contra.isEmpty()) {
                    Toast.makeText(Registro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbHelper = new DBHelper(Registro.this);
                try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {

                    ContentValues values = new ContentValues();
                    values.put(DBHelper.COLUMN_CORREO, correo);
                    values.put(DBHelper.COLUMN_PASSWORD, contra);

                    long result = db.insert(DBHelper.TABLE_USUARIOS, null, values);

                    if (result == -1) {
                        Toast.makeText(Registro.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Registro.this, "Error de DB", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
