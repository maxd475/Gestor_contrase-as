package com.example.Max_Robles;
import com.example.Max_Robles.DatosAdicionales;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Button;
import android.widget.EditText;
import android.widget. ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Datos_Agregador extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_agregador);


        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTitle = adapter.getItem(position);
                mostrarDetallesEnVentanaEmergente(selectedTitle);
            }
        });

        Button addDataButton = findViewById(R.id.addDataButton);

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarVentanaEmergenteAgregarDatos();
            }
        });
    }
//este metodo falta arreglar por que tiene que jalar los datos de la clase DatabaseHelper y mostrarlos en una ventana emergente
    private void mostrarDetallesEnVentanaEmergente(String selectedTitle) {
// Crear una instancia de DatosAdicionales para acceder a los datos
        DatosAdicionales datosAdicionales = new DatosAdicionales(this);

        // Obtener los datos del registro correspondiente
        DatosAdicionales registro = datosAdicionales.obtenerRegistroPorTitulo(selectedTitle);

        if (registro != null) {
            // Construir el mensaje a mostrar en la ventana emergente
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("Título: ").append(registro.getTitulo()).append("\n");
            mensaje.append("Usuario: ").append(registro.getUsuario()).append("\n");
            mensaje.append("Contraseña: ").append(registro.getContrasena()).append("\n");
            mensaje.append("Sitio web: ").append(registro.getSitioWeb());

            // Crear una ventana emergente (AlertDialog) para mostrar los detalles
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Detalles del Título");
            builder.setMessage(mensaje.toString());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();
        } else {
            // Mostrar un mensaje si no se encontraron datos
            Toast.makeText(this, "No se encontraron detalles para el título seleccionado", Toast.LENGTH_SHORT).show();
        }

    }

    private void mostrarVentanaEmergenteAgregarDatos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.listado_vista_general_principal, null);
        builder.setView(dialogView);
        builder.setTitle("Agregar Datos");

        // Configurar el botón de guardar en el diálogo
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Aquí puedes implementar la lógica para guardar los datos

                // Puedes obtener los valores de los campos de EditText en dialogView
                EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
                EditText usernameEditText = dialogView.findViewById(R.id.usernameEditText);
                EditText passwordEditText = dialogView.findViewById(R.id.passwordEditText);
                EditText websiteEditText = dialogView.findViewById(R.id.websiteEditText);

                // Obtener los valores ingresados por el usuario
                String title = titleEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String website = websiteEditText.getText().toString();

                // Agregar el título al adaptador
                adapter.add(title);

                // Notificar al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();

                // Cerrar el diálogo
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}