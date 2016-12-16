package com.example.lamg.academia;

import android.content.ContentValues;


/**
 * Created by lamg on 11/11/16.
 */
public class Trabajador extends Alumno {
    String cif, nombreEmpresa, telefonoEmpresa, direccionEmpresa;

    public Trabajador(String dni, String direccion, String nombre, String telefono, String edad,
                      String avatar,
                      String cif, String nombreEmpresa, String telefonoEmpresa,
                      String direccionEmpresa) {
        super(dni, direccion, nombre, telefono, edad, avatar);
        this.cif = cif;
        this.nombreEmpresa = nombreEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getCif(){
        return cif;
    }

    public String getNombreEmpresa(){
        return nombreEmpresa;
    }

    public String getTelefonoEmpresa(){
        return telefonoEmpresa;
    }

    public String getDireccionEmpresa(){
        return direccionEmpresa;
    }

    public ContentValues toContentValues(){
        ContentValues v = super.toContentValues();
        v.put(TrabajadorEntry.CIF, cif);
        v.put(TrabajadorEntry.NOMBRE_EMPRESA, nombreEmpresa);
        v.put(TrabajadorEntry.TELEFONO_EMPRESA, telefonoEmpresa);
        v.put(TrabajadorEntry.DIRECCION_EMPRESA, direccionEmpresa);
        return v;
    }
}

