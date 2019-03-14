package mx.edu.ittepic.tpdm_u2_practica2_oscar_ibaez_loreto_aseguradora_coches;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Propietario {
    private BaseDatos base;
    private String telefono, nombre, domicilio, fecha;
    private String idseguro, descripcion;
    private int tipo;

    protected String errores;

    public Propietario(Activity activity) {
        base = new BaseDatos(activity, "seguro", null, 1);
    }// constructor

    //TELEFONO NOMBRE  DOMICILIO  FECHA
    public Propietario(String telefono, String nombre, String domicilio, String fecha) {

        this.nombre = nombre;
        this.domicilio = domicilio;

        this.telefono = telefono;
        this.fecha = fecha;
    }

    public Propietario(String idseguro, String descripcion, String fecha, int tipo, String telefono) {
        this.telefono = telefono;
        this.fecha = fecha;

        this.idseguro = idseguro;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public boolean insertar(Propietario propietario) {
        try {

            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO", propietario.getTelefono());
            datos.put("NOMBRE", propietario.getNombre());
            datos.put("DOMICILIO", propietario.getDomicilio());
            datos.put("FECHA", propietario.getFecha());

            long resultado = transaccionInsertar.insert("PROPIETARIO", "FECHA", datos);
            transaccionInsertar.close();

            if (resultado == -1) {
                return false;
            }

        } catch (SQLException e) {
            errores = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean insertarSeguro(Propietario propietario) {
        try {

            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("IDSEGURO", propietario.getIdseguro());
            datos.put("DESCRIPCION", propietario.getDescripcion());
            datos.put("FECHA", propietario.getFecha());
            datos.put("TIPO", propietario.getTipo());
            datos.put("TELEFONO", propietario.getTelefono());

            long resultado = transaccionInsertar.insert("SEGURO", "FECHA", datos);
            transaccionInsertar.close();

            if (resultado == -1) {
                return false;
            }

        } catch (SQLException e) {
            errores = e.getMessage();
            return false;
        }
        return true;
    }


    public Propietario[] consultar() {

        Propietario[] resultado = null;
        try {
            SQLiteDatabase transaccionConsultaGeneral = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROPIETARIO";

            Cursor c = transaccionConsultaGeneral.rawQuery(SQL, null);
            if (c.moveToFirst()) {
                resultado = new Propietario[c.getCount()];
                int pos = 0;
                do {
                    resultado[pos] = new Propietario(c.getString(0), c.getString(1),
                            c.getString(2), c.getString(3));


                    pos++;
                } while (c.moveToNext());
            }
            transaccionConsultaGeneral.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }


    public Propietario[] consultarSeguro(String telefono) {

        Propietario[] resultado = null;
        try {
            SQLiteDatabase transaccionConsultaGeneral = base.getReadableDatabase();
            String SQL = "SELECT * FROM SEGURO WHERE TELEFONO = '"+telefono+"'";

            Cursor c = transaccionConsultaGeneral.rawQuery(SQL, null);
            if (c.moveToFirst()) {
                resultado = new Propietario[c.getCount()];
                int pos = 0;
                do {
                    resultado[pos] = new Propietario(c.getString(0), c.getString(1),
                            c.getString(2), c.getString(3));



                    pos++;
                } while (c.moveToNext());
            }
            transaccionConsultaGeneral.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public boolean actualizar(Propietario propietario) {
        try {

            SQLiteDatabase transaccionActualzar = base.getWritableDatabase();

            ContentValues datos = new ContentValues();
            datos.put("TELEFONO", propietario.getTelefono());
            datos.put("NOMBRE", propietario.getNombre());
            datos.put("DOMICILIO", propietario.getDomicilio());

            long resultado = transaccionActualzar.update("PROPIETARIO", datos, "TELEFONO=?"
                    , new String[]{"" + propietario.getTelefono()});

            transaccionActualzar.close();

            if (resultado == -1) {
                return false;
            }
        } catch (SQLException e) {
            errores = e.getMessage();
            return false;
        }

        return true;
    }// actualizar


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdseguro() {
        return idseguro;
    }

    public void setIdseguro(String idseguro) {
        this.idseguro = idseguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
