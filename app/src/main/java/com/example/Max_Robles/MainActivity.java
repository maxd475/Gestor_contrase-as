package com.example.Max_Robles;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Max_Robles.Registrar_usuario;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText nombreUsuarioEditText, contrasenaEditText;
    private Button botonRegistrarse;
    private EditText usernameEditText, passwordEditText;
    private Button loginButton, createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createUserButton = findViewById(R.id.createUserButton);

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar_usuario registrador = new Registrar_usuario();
                registrador.mostrarCuadroDialogoRegistro(MainActivity.this);
            }
        });
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createUserButton = findViewById(R.id.createUserButton);

        // Configurar el listener para el botón de inicio de sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (validarCredenciales(username, password)) {
                    // Las credenciales son válidas, redirigir a la actividad principal
                    Intent intent = new Intent(MainActivity.this, Datos_Agregador.class);
                    startActivity(intent);
                    // o realizar cualquier acción que permita el acceso a la aplicación
                    // (por ejemplo, abrir otra actividad)
                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    // Puedes iniciar otra actividad o realizar acciones aquí
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para validar las credenciales con la base de datos SQLite
    private boolean validarCredenciales(String username, String password) {
        // Implementar la lógica de validación en la base de datos SQLite
        // Consulta la base de datos para verificar si el usuario y contraseña coinciden
        // con algún registro en la base de datos

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_NOMBRE,
                DatabaseHelper.COLUMN_CONTRASENA
        };

        String selection = DatabaseHelper.COLUMN_NOMBRE + " = ? AND " + DatabaseHelper.COLUMN_CONTRASENA + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                DatabaseHelper.NOMBRE_TABLA,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;

    }
}