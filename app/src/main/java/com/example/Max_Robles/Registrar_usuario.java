package com.example.Max_Robles;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar_usuario {

    public void mostrarCuadroDialogoRegistro(final Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.dialog_registro, null);
        dialogBuilder.setView(dialogView);

        final EditText nombreUsuarioEditText = dialogView.findViewById(R.id.editTextNombreUsuario);
        final EditText contrasenaEditText = dialogView.findViewById(R.id.editTextContrasena);

        dialogBuilder.setTitle("Registro de Usuario");
        dialogBuilder.setPositiveButton("Registrarse", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String nombreUsuario = nombreUsuarioEditText.getText().toString().trim();
                String contrasena = contrasenaEditText.getText().toString().trim();

                if (!nombreUsuario.isEmpty() && !contrasena.isEmpty()) {
                    // Crear una instancia de DatabaseHelper
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);

                    try {
                        // Obtener una instancia de escritura de la base de datos
                        SQLiteDatabase db = databaseHelper.getWritableDatabase();

                        // Crear un ContentValues para insertar los datos
                        ContentValues values = new ContentValues();
                        values.put(DatabaseHelper.COLUMN_NOMBRE, nombreUsuario);
                        values.put(DatabaseHelper.COLUMN_CONTRASENA, contrasena);

                        // Insertar los datos en la tabla Usuarios
                        long newRowId = db.insert(DatabaseHelper.NOMBRE_TABLA, null, values);

                        // Cerrar la base de datos
                        db.close();

                        if (newRowId != -1) {
                            // Los datos se insertaron correctamente
                            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        } else {
                            // Ocurrió un error al insertar los datos
                            Toast.makeText(context, "Error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        // Manejar cualquier excepción que pueda ocurrir al interactuar con la base de datos
                        e.printStackTrace();
                        Toast.makeText(context, "Error al interactuar con la base de datos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Validación de campos vacíos
                    Toast.makeText(context, "Por favor, ingresa nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
