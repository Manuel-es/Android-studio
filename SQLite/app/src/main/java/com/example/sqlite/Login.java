package com.example.sqlite;

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

    private EditText loginCorreo;
    private EditText loginContra;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginCorreo = findViewById(R.id.loginCorreo);
        loginContra = findViewById(R.id.loginContra);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = loginCorreo.getText().toString();
                String contra = loginContra.getText().toString();

                if (correo.isEmpty() || contra.isEmpty()) {
                    Toast.makeText(Login.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbHelper = new DBHelper(Login.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String query = "SELECT * FROM " + DBHelper.TABLE_USUARIOS +
                        " WHERE " + DBHelper.COLUMN_CORREO + "=? AND " + DBHelper.COLUMN_PASSWORD + "=?";

                try (Cursor cursor = db.rawQuery(query, new String[]{correo, contra})) {

                    if (cursor.moveToFirst()) {
                        Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
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
