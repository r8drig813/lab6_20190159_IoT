package com.example.lab6_20190159;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class NuevoIngreso extends AppCompatActivity {
    FirebaseFirestore db;
    private EditText editTextTitulo;
    private EditText editTextMonto;
    private EditText editTextDescripcion;
    private EditText editTextFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo_ingreso);

        Button save = findViewById(R.id.submitButton);

        db = FirebaseFirestore.getInstance();



        save.setOnClickListener(v -> {
            Ingreso ingreso = new Ingreso();

            editTextTitulo = findViewById(R.id.editTextTitulo);
            editTextMonto = findViewById(R.id.editTextMonto);
            editTextDescripcion = findViewById(R.id.editTextDescripcion);
            editTextFecha = findViewById(R.id.editTextFecha);

            String titulo = editTextTitulo.getText().toString();
            String montoStr = editTextMonto.getText().toString();
            String descripcion = editTextDescripcion.getText().toString();
            String fechaStr = editTextFecha.getText().toString();

            ingreso.setTitulo(titulo);
            ingreso.setMonto(Double.parseDouble(montoStr));
            ingreso.setDescripcion(descripcion);
            ingreso.setFecha(fechaStr);

            db.collection("Ingreso")
                    .add(ingreso)
                    .addOnSuccessListener(unused -> {
                        Log.d("msg-test", "Data guardada exitosamente");

                        // Código para volver al fragmento IngresoFragment
                        Intent intent = new Intent(NuevoIngreso.this, MainActivityContainer.class);
                        intent.putExtra("fragment", "IngresoFragment");
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> e.printStackTrace());
        });
    }
}
