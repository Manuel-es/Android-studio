package com.example.sqlitetelefono;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private EditText telefonoUsu; // Cambio a teléfono
    private EditText contrausu;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        // Usamos el nuevo ID del XML: telefono_usuario
        telefonoUsu = findViewById(R.id.telefono_usuario);
        contrausu = findViewById(R.id.contraseña);
        btnGuardar = findViewById(R.id.Guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = telefonoUsu.getText().toString();
                String contra = contrausu.getText().toString();

                if (telefono.isEmpty() || contra.isEmpty()) {
                    Toast.makeText(Registro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbHelper = new DBHelper(Registro.this);
                try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {

                    ContentValues values = new ContentValues();
                    values.put(DBHelper.COLUMN_TELEFONO, telefono); // Usamos la columna TELEFONO
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