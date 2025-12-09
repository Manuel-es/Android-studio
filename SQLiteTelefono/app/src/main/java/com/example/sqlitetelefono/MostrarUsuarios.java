package com.example.sqlitetelefono;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MostrarUsuarios extends AppCompatActivity {

    private Button btnMostrar;
    public TextView Resultado;
    public String telefono; // Cambio de correo a teléfono
    public String contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarusuarios);

        btnMostrar = findViewById(R.id.Mostrar);
        Resultado = findViewById(R.id.Resultado);

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resultado.setText("");

                DBHelper dbHelper = new DBHelper(MostrarUsuarios.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                try (Cursor miCursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_USUARIOS, null)) {

                    if (miCursor.moveToFirst()) {
                        do {
                            // Asumimos que la columna 0 es TELEFONO y la 1 es PASSWORD
                            telefono = miCursor.getString(0);
                            contra = miCursor.getString(1);

                            Resultado.append("Teléfono: " + telefono + " - Contraseña: " + contra + "\n");
                        } while (miCursor.moveToNext());
                    } else {
                        Resultado.setText("No hay usuarios registrados.");
                    }

                } catch (Exception e) {
                    Resultado.setText("Error al leer la base de datos.");
                } finally {
                    db.close();
                }
            }
        });
    }
}
