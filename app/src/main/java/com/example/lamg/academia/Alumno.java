package com.example.lamg.academia;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by lamg on 11/11/16.
 */
public class Alumno {
    protected String dni, direccion, nombre, telefono, avatar,edad;

    public Alumno(String dni, String direccion, String nombre, String telefono, String edad, String avatar){
        this.dni = dni;
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.avatar = avatar;
    }

    public String getDni(){
        return dni;
    }

    public String getDireccion(){
        return direccion;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getEdad(){
        return edad;
    }

    public String getAvatar() { return  avatar; }

    public ContentValues toContentValues() {
        ContentValues v = new ContentValues();
        v.put(AlumnoEntry.DNI, dni);
        v.put(AlumnoEntry.DIRECCION, direccion);
        v.put(AlumnoEntry.NOMBRE, nombre);
        v.put(AlumnoEntry.TELEFONO, telefono);
        v.put(AlumnoEntry.EDAD, edad);
        v.put(AlumnoEntry.AVATAR, avatar);
        return v;
    }
}


