package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarUsuario extends AppCompatActivity {

    private Button btnMostrar;
    private TextView Resultado;
    private String correo;
    private String contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarusuario); // Asegúrate de que este layout exista

        // 1. Inicialización de los componentes de la interfaz
        btnMostrar = findViewById(R.id.Mostrar);
        Resultado = findViewById(R.id.Resultado);

        // 2. Configuración del Listener para el botón Mostrar
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpiar resultados anteriores
                Resultado.setText("");

                // 3. Obtener la base de datos para lectura
                DBHelper dbHelper = new DBHelper(MostrarUsuario.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor miCursor = null;

                try {
                    // Consulta SQL para seleccionar todos los registros
                    String query = "SELECT " + DBHelper.COLUMN_CORREO + ", " + DBHelper.COLUMN_PASSWORD +
                            " FROM " + DBHelper.TABLE_USUARIOS;

                    miCursor = db.rawQuery(query, null);

                    // 4. Iterar sobre los resultados
                    if (miCursor.moveToFirst()) {
                        do {
                            // Obtener los datos por el índice de la columna (0=correo, 1=contraseña)
                            correo = miCursor.getString(0);
                            contra = miCursor.getString(1);

                            // Añadir el resultado al TextView
                            Resultado.append(correo + " - " + contra + "\n");
                        } while (miCursor.moveToNext());
                    } else {
                        Toast.makeText(MostrarUsuario.this, "No hay usuarios registrados.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MostrarUsuario.this, "Error al leer la base de datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    // 5. Cerrar el Cursor y la Base de Datos
                    if (miCursor != null) {
                        miCursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }
                }
            }
        });
    }
}
