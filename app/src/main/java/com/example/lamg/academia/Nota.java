package com.example.lamg.academia;

import android.content.ContentValues;

/**
 * Created by lamg on 23/11/16.
 */
public class Nota {
    String idAlumno, idCurso, nota;

    public Nota(String idAlumno, String idCurso, String nota){
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
        this.nota = nota;
    }

    public ContentValues toContentValues(){
        ContentValues v = new ContentValues();
        v.put(NotaEntry.ID_ALUMNO, idAlumno);
        v.put(NotaEntry.ID_CURSO, idCurso);
        v.put(NotaEntry.NOTA, nota);
        return v;
    }
}
