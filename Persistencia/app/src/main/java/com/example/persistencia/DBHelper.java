package com.example.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Constantes de la Base de Datos
    public static final String DATABASE_NAME = "usuarios.db";
    public static final int DATABASE_VERSION = 1;

    // Constantes de la Tabla de Usuarios
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COLUMN_CORREO = "correo";
    public static final String COLUMN_PASSWORD = "password";

    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE_USUARIOS =
            "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_CORREO + " TEXT PRIMARY KEY, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL);";

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Ejecuta la sentencia de creación de la tabla
        db.execSQL(CREATE_TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se llama si se detecta una versión de base de datos superior
        // Elimina la tabla existente y la vuelve a crear
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }
}
