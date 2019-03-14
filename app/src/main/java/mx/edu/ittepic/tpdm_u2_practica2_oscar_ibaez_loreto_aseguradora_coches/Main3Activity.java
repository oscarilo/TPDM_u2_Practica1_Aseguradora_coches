package mx.edu.ittepic.tpdm_u2_practica2_oscar_ibaez_loreto_aseguradora_coches;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    CheckBox habilitar;
    EditText telefono, nombre, domicilio, fecha;
    Button btnCancelar, btnGuardar;
    Spinner tiposeguro;
    Propietario vector[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        habilitar = findViewById(R.id.habilitar);

        telefono = findViewById(R.id.telefono);
        nombre = findViewById(R.id.nombre);
        domicilio = findViewById(R.id.domicilio);
        fecha = findViewById(R.id.fecha);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnGuardar = findViewById(R.id.btnGuardar);

        tiposeguro = findViewById(R.id.tiposeguro);



        Bundle parametros = getIntent().getExtras();

        telefono.setText(parametros.getString("telefono"));
        nombre.setText(parametros.getString("nombre"));
        domicilio.setText(parametros.getString("domicilio"));
        fecha.setText(parametros.getString("fecha"));

        habilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validarCheck();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tiposeguro.getSelectedItemPosition() == 0) {
                    mensaje("Atención!", "Seleccione un tipo de seguro.");
                } else {
                    mostrarOpciones();
                }
            }
        });
        consultarDatos(parametros.getString("telefono"));

    }// onCreate

    private void mostrarOpciones() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atención").setMessage("¿Que operación desea realizar?").
                setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        metodoActualizar();
                    }
                })
                .setNegativeButton("Asegurar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        metodoInsertarSeguro();
                    }
                })
                .show();
    }

    private void consultarDatos(String telefono) {
        Propietario proyectos = new Propietario(this);
        vector = proyectos.consultarSeguro(telefono);
        //vector = proyectos.consultarSeguro();

        String[] descripcionProyecto = null;

        if (vector == null) {
            mensaje("¡IMPORTANTE!", "¡Usted no cuenta con ningun tipo de seguro!");
        } else {
            descripcionProyecto = new String[vector.length];

            for (int i = 0; i < vector.length; i++) {
                Propietario temp = vector[i];
                descripcionProyecto[i] =
                        temp.getNombre();

                System.out.println("DATOSALV2 " + descripcionProyecto[i]);
            }// for
            mensaje("Seguro asignado. ",descripcionProyecto[0]);
        }// else
    }

    private void metodoGuardarActualizar() {

        metodoActualizar();

    }


    private void metodoActualizar() {
        Propietario propietario = new Propietario(this);

        boolean res = propietario.actualizar(new Propietario(telefono.getText().toString(),
                nombre.getText().toString(),
                domicilio.getText().toString(),
                ""));

        if (res) {
            mensaje("Atención!", "Campos actualizados correctamente");

        } else {
            mensaje("Alerta!", "Error: No se pudo actualizar el propietario.\nSQLite: " + propietario.errores);
        }

    }

    private void metodoInsertarSeguro() {
        String mensaje = "";
        Propietario propietario = new Propietario(this);
        boolean respuesta = propietario.insertarSeguro(new Propietario("ID" + telefono.getText().toString(),
                "" + tiposeguro.getSelectedItem().toString(),
                "" + fecha.getText().toString(),
                tiposeguro.getSelectedItemPosition(),
                "" + telefono.getText().toString()));

        if (respuesta) {
            mensaje = "Se pudo asegurar a " + nombre.getText().toString() + ".";
        } else {
            mensaje = "Error: No se pudo crear seguro, respuesta SQLite: " + propietario.errores;
        }

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atención").setMessage(mensaje).setPositiveButton("OK", null).show();

    }

    private void mensaje(String titulo, String mensaje) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(titulo).setMessage(mensaje).setPositiveButton("OK", null).show();
    }


    private void validarCheck() {
        if (habilitar.isChecked()) {
            nombre.requestFocus();
            nombre.setSelection(nombre.getText().length());
            nombre.setEnabled(true);
            domicilio.setEnabled(true);
        } else {
            nombre.setEnabled(false);
            domicilio.setEnabled(false);
        }
    }


}// class
