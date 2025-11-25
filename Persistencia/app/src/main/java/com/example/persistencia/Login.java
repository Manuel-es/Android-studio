package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText loginCorreo;
    private EditText loginContra;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Asegúrate de que este layout exista

        // 1. Inicialización de los componentes
        loginCorreo = findViewById(R.id.LoginCorreo);
        loginContra = findViewById(R.id.LoginContra);
        btnLogin = findViewById(R.id.btnLogin);

        // 2. Configuración del Listener para el botón Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos y limpiar espacios
                String correo = loginCorreo.getText().toString().trim();
                String contra = loginContra.getText().toString().trim();

                // 3. Validación de campos vacíos
                if (correo.isEmpty() || contra.isEmpty()) {
                    Toast.makeText(Login.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 4. Conexión a la base de datos y consulta
                DBHelper dbHelper = new DBHelper(Login.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = null;

                try {
                    // Consulta SQL segura para verificar correo y contraseña
                    String selection = DBHelper.COLUMN_CORREO + " = ? AND " + DBHelper.COLUMN_PASSWORD + " = ?";
                    String[] selectionArgs = new String[]{correo, contra};

                    cursor = db.query(
                            DBHelper.TABLE_USUARIOS,       // Nombre de la tabla
                            null,                          // Columnas a devolver (null = todas)
                            selection,                     // Cláusula WHERE
                            selectionArgs,                 // Argumentos para la cláusula WHERE (?)
                            null, null, null               // Agrupación, filtro, orden
                    );

                    // Nota: Se usa db.query() en lugar de rawQuery() para mayor seguridad contra inyecciones SQL.
                    // Si prefieres usar rawQuery, sería:
                    /*
                    cursor = db.rawQuery(
                        "SELECT * FROM " + DBHelper.TABLE_USUARIOS +
                        " WHERE " + DBHelper.COLUMN_CORREO + "=? AND " + DBHelper.COLUMN_PASSWORD + "=?",
                        new String[]{correo, contra}
                    );
                    */

                    // 5. Verificar si se encontró el usuario
                    if (cursor.moveToFirst()) {
                        Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        // Redirigir a la siguiente actividad
                        // startActivity(new Intent(Login.this, MenuPrincipal.class));
                        // finish(); // Opcional: cierra esta actividad para que el usuario no pueda volver atrás con el botón 'atrás'
                    } else {
                        Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Login.this, "Error de base de datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    // 6. Cerrar el Cursor y la Base de Datos
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }
                }
            }
        });
    }
}
