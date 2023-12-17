package com.example.Max_Robles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatosAdicionales extends DatabaseHelper {

    private static final String COLUMN_TITULO_DATOS = "";
    private static final String COLUMN_USUARIO_DATOS = "";
    private static final String COLUMN_CONTRASENA_DATOS = "";
    private static final String COLUMN_SITIO_WEB_DATOS = "";
    private static final String COLUMN_ID_DATOS = "";

    private long id;
    private String titulo;
    private String usuario;
    private String contrasena;
    private String sitioWeb;

    public DatosAdicionales(Context context) {
        super(context);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    // Método para agregar un nuevo registro
    public long agregarRegistro(String titulo, String usuario, String contrasena, String sitioWeb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO_DATOS, titulo);
        values.put(COLUMN_USUARIO_DATOS, usuario);
        values.put(COLUMN_CONTRASENA_DATOS, contrasena);
        values.put(COLUMN_SITIO_WEB_DATOS, sitioWeb);

        long newRowId = db.insert(NOMBRE_TABLA_DATOS_ADICIONALES, null, values);
        db.close();

        return newRowId;
    }

    // Método para obtener un registro por título
    public DatosAdicionales obtenerRegistroPorTitulo(String tituloBuscado) {
        SQLiteDatabase db = this.getReadableDatabase();
        DatosAdicionales datosAdicionales = null;

        String[] projection = {
                COLUMN_ID_DATOS,
                COLUMN_TITULO_DATOS,
                COLUMN_USUARIO_DATOS,
                COLUMN_CONTRASENA_DATOS,
                COLUMN_SITIO_WEB_DATOS
        };

        String selection = COLUMN_TITULO_DATOS + " = ?";
        String[] selectionArgs = {tituloBuscado};

        Cursor cursor = db.query(
                NOMBRE_TABLA_DATOS_ADICIONALES,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                datosAdicionales = new DatosAdicionales(this.context);
                datosAdicionales.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID_DATOS)));
                datosAdicionales.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMN_TITULO_DATOS)));
                datosAdicionales.setUsuario(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_DATOS)));
                datosAdicionales.setContrasena(cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASENA_DATOS)));
                datosAdicionales.setSitioWeb(cursor.getString(cursor.getColumnIndex(COLUMN_SITIO_WEB_DATOS)));
            }
            cursor.close();
        }
        db.close();
        return datosAdicion
