package mx.edu.ittepic.tpdm_u2_practica2_oscar_ibaez_loreto_aseguradora_coches;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView propietariosList;
    Propietario vector[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propietariosList = findViewById(R.id.listaPropietarios);

        propietariosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                invocarAsegurar(position);
            }
        });

    }// onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menupropietario:
                Intent agregarPropietario = new Intent(this, Main2Activity.class);
                startActivity(agregarPropietario);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Propietario proyectos = new Propietario(this);
        vector = proyectos.consultar();
        //vector = proyectos.consultarSeguro();

        String[] descripcionProyecto = null;

        if (vector == null) {
            descripcionProyecto = new String[1];
            descripcionProyecto[0] = "OJO: Sin propietarios capturados ...";
        } else {
            descripcionProyecto = new String[vector.length];

            for (int i = 0; i < vector.length; i++) {
                Propietario temp = vector[i];
                descripcionProyecto[i] = temp.getNombre();
            }// for
        }// else

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descripcionProyecto);
        propietariosList.setAdapter(adaptador);

        super.onStart();
    }


    private void invocarAsegurar(int position) {

        Intent ventanaDatos = new Intent(this, Main3Activity.class);

        ventanaDatos.putExtra("telefono", vector[position].getTelefono());
        ventanaDatos.putExtra("nombre", vector[position].getNombre());
        ventanaDatos.putExtra("domicilio", vector[position].getDomicilio());
        ventanaDatos.putExtra("fecha", vector[position].getFecha());

        startActivity(ventanaDatos);
    }


}// class
