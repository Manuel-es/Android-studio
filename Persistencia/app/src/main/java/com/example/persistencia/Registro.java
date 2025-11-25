package com.example.persistencia;


import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText correousu;
    private EditText contrausu;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro); // Asegúrate de que este layout exista

        // 1. Inicialización de los componentes de la interfaz
        correousu = findViewById(R.id.correo);
        contrausu = findViewById(R.id.contraseña);
        btnGuardar = findViewById(R.id.Guardar);

        // 2. Configuración del Listener para el botón Guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos de los EditText y convertirlos a String
                String correo = correousu.getText().toString().trim();
                String contra = contrausu.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (correo.isEmpty() || contra.isEmpty()) {
                    Toast.makeText(Registro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return; // Detiene la ejecución si hay campos vacíos
                }

                // 3. Inicializar DB y preparar la inserción
                DBHelper dbHelper = new DBHelper(Registro.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DBHelper.COLUMN_CORREO, correo);
                values.put(DBHelper.COLUMN_PASSWORD, contra);

                // 4. Insertar en la base de datos
                // La forma correcta de llamar al método insert es
            }
 });
         }
 }