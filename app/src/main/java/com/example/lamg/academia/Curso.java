package com.example.lamg.academia;

import android.content.ContentValues;

/**
 * Created by lamg on 23/11/16.
 */
public class Curso {
    String codigo, titulo, duracion, fechaInicio, fechaTerminacion,
    dniProfesor, nombreProfesor, direccionProfesor, telefonoProfesor;

    public Curso(String codigo, String titulo, String duracion, String fechaInicio,
                 String fechaTerminacion, String dniProfesor, String nombreProfesor,
                 String direccionProfesor, String telefonoProfesor){
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.fechaInicio = fechaInicio;
        this.fechaTerminacion = fechaTerminacion;
        this.dniProfesor = dniProfesor;
        this.nombreProfesor = nombreProfesor;
        this.direccionProfesor = direccionProfesor;
        this.telefonoProfesor = telefonoProfesor;
    }

    public ContentValues toContentValues(){
        ContentValues v = new ContentValues();
        v.put(CursoEntry.CODIGO, codigo);
        v.put(CursoEntry.TITULO, titulo);
        v.put(CursoEntry.DURACION, duracion);
        v.put(CursoEntry.FECHA_INICIO, fechaInicio);
        v.put(CursoEntry.FECHA_TERMINACION, fechaTerminacion);
        v.put(CursoEntry.DNI_PROFESOR, dniProfesor);
        v.put(CursoEntry.NOMBRE_PROFESOR, nombreProfesor);
        v.put(CursoEntry.DIRECCION_PROFESOR, direccionProfesor);
        v.put(CursoEntry.TELEFONO_PROFESOR, telefonoProfesor);
        return v;
    }

}
