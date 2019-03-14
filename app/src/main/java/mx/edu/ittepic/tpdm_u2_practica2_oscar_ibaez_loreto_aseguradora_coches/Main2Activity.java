package mx.edu.ittepic.tpdm_u2_practica2_oscar_ibaez_loreto_aseguradora_coches;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText campoNombre, campoDomicilio, campoTelefono, campoFecha;
    Button cancelar, guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        campoNombre = findViewById(R.id.campoNombre);
        campoDomicilio = findViewById(R.id.campoDomicilio);
        campoTelefono = findViewById(R.id.campoTelefono);
        campoFecha = findViewById(R.id.campoFecha);

        cancelar = findViewById(R.id.btnCancelar);
        guardar = findViewById(R.id.btnGuardar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodoInsertar();
            }
        });

    }// onCreate

    private void metodoInsertar() {
        String mensaje = "";
        Propietario propietario = new Propietario(this);

        boolean res = propietario.insertar(new Propietario(campoTelefono.getText().toString(),
                campoNombre.getText().toString(),
                campoDomicilio.getText().toString(),
                campoFecha.getText().toString()));

        if (res) {
            mensaje = "Propietario " + campoNombre.getText().toString() + " insertado correctamente.";
            campoNombre.setText("");
            campoTelefono.setText("");
            campoDomicilio.setText("");
        } else {
            mensaje = "Error: No se pudo guardar el propietario.\nSQLite: " + propietario.errores;
        }

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atención").setMessage(mensaje).setPositiveButton("OK", null).show();

    }// insertar
}// class
