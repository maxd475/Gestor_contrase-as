package com.example.Max_Robles;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MiBaseDeDatos";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla y columnas
    public static final String NOMBRE_TABLA = "Usuarios";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_CONTRASENA = "contrasena";



    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE =
            "CREATE TABLE " + NOMBRE_TABLA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    COLUMN_CONTRASENA + " TEXT NOT NULL);";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes manejar las actualizaciones de la base de datos, como agregar nuevas columnas o tablas
    }






}

