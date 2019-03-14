package mx.edu.ittepic.tpdm_u2_practica2_oscar_ibaez_loreto_aseguradora_coches;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROPIETARIO(TELEFONO VARCHAR(100) PRIMARY KEY NOT NULL," +
                " NOMBRE VARCHAR(200) NOT NULL, DOMICILIO VARCHAR(50), FECHA VARCHAR(50) )");

        db.execSQL("CREATE TABLE SEGURO( IDSEGURO VARCHAR(500) PRIMARY KEY NOT NULL," +
                "DESCRIPCION VARCHAR(200) NOT NULL, FECHA DATE, TIPO INTEGER," +
                "TELEFONO VARCHAR(100), FOREIGN KEY (TELEFONO) REFERENCES PROPIETARIO )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
