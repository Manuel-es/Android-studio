package com.example.sqlitetelefono;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText loginTelefono; // Cambio a teléfono
    private EditText loginContra;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Usamos el ID del nuevo XML: loginTelefono
        loginTelefono = findViewById(R.id.loginTelefono);
        loginContra = findViewById(R.id.loginContra);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = loginTelefono.getText().toString();
                String contra = loginContra.getText().toString();

                if (telefono.isEmpty() || contra.isEmpty()) {
                    Toast.makeText(Login.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbHelper = new DBHelper(Login.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String query = "SELECT * FROM " + DBHelper.TABLE_USUARIOS +
                        // Buscamos por la columna TELEFONO
                        " WHERE " + DBHelper.COLUMN_TELEFONO + "=? AND " + DBHelper.COLUMN_PASSWORD + "=?";

                try (Cursor cursor = db.rawQuery(query, new String[]{telefono, contra})) {

                    if (cursor.moveToFirst()) {
                        Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Teléfono o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(Login.this, "Error de base de datos", Toast.LENGTH_SHORT).show();
                } finally {
                    db.close();
                }
            }
        });
    }
}
