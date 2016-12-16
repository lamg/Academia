package com.example.lamg.academia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import org.xml.sax.SAXNotRecognizedException;

/**
 * Created by lamg on 13/11/16.
 */
public class BD extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Academia.db";

    public BD(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase d){
        String query = "CREATE TABLE " + AlumnoEntry.TABLE +
                "(" + AlumnoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //_ID
                AlumnoEntry.DNI + " TEXT NOT NULL," + // DNI
                AlumnoEntry.DIRECCION + " TEXT NOT NULL," + // DIRECCION
                AlumnoEntry.NOMBRE + " TEXT NOT NULL," + // NOMBRE
                AlumnoEntry.TELEFONO + " TEXT NOT NULL," + // TELEFONO
                AlumnoEntry.EDAD + " TEXT NOT NULL," + // EDAD
                AlumnoEntry.AVATAR + " TEXT NOT NULL," + // AVATAR
                "UNIQUE ("+AlumnoEntry.DNI+"))";
        d.execSQL(query);

        query = "CREATE TABLE " + TrabajadorEntry.TABLE +
                "(" + AlumnoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //_ID
                AlumnoEntry.DNI + " TEXT NOT NULL," + // DNI
                AlumnoEntry.DIRECCION + " TEXT NOT NULL," + // DIRECCION
                AlumnoEntry.NOMBRE + " TEXT NOT NULL," + // NOMBRE
                AlumnoEntry.TELEFONO + " TEXT NOT NULL," + // TELEFONO
                AlumnoEntry.EDAD + " TEXT NOT NULL," + // EDAD
                AlumnoEntry.AVATAR + " TEXT NOT NULL," + // AVATAR
                TrabajadorEntry.CIF + " TEXT NOT NULL," + // CIF
                TrabajadorEntry.NOMBRE_EMPRESA + " TEXT NOT NULL," + // NOMBRE_EMPRESA
                TrabajadorEntry.TELEFONO_EMPRESA + " TEXT NOT NULL," + // TELEFONO_EMPRESA
                TrabajadorEntry.DIRECCION_EMPRESA + " TEXT NOT NULL," + // DIRECCION_EMPRESA
                "UNIQUE ("+AlumnoEntry.DNI+"))";
        d.execSQL(query);

        query = "CREATE TABLE " + CursoEntry.TABLE +
                "(" + CursoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //_ID
                CursoEntry.CODIGO + " TEXT NOT NULL," + //CODIGO
                CursoEntry.TITULO + " TEXT NOT NULL," + //TITULO
                CursoEntry.DURACION + " TEXT NOT NULL," + //DURACION
                CursoEntry.FECHA_INICIO + " TEXT NOT NULL," + //FECHA_INICIO
                CursoEntry.FECHA_TERMINACION + " TEXT NOT NULL," + //FECHA_TERMINACION
                CursoEntry.DNI_PROFESOR + " TEXT NOT NULL," +
                CursoEntry.NOMBRE_PROFESOR + " TEXT NOT NULL," +
                CursoEntry.DIRECCION_PROFESOR + " TEXT NOT NULL," +
                CursoEntry.TELEFONO_PROFESOR + " TEXT NOT NULL," +
                "UNIQUE ("+CursoEntry.CODIGO+"))";
        d.execSQL(query);

        query = "CREATE TABLE " + NotaEntry.TABLE +
                "(" + NotaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //_ID
                NotaEntry.ID_ALUMNO + " INTEGER NOT NULL," +
                NotaEntry.ID_CURSO + " INTEGER NOT NULL," +
                NotaEntry.NOTA + " TEXT NOT NULL" + ")";
        d.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase d, int oldVersion, int newVersion){
        //nothing to do at this time
    }

    public long saveAlumno(Alumno a){
        SQLiteDatabase d = getWritableDatabase();
        return d.insert(AlumnoEntry.TABLE, null, a.toContentValues());
    }

    public long saveTrabajador(Trabajador a){
        SQLiteDatabase d = getWritableDatabase();
        return d.insert(TrabajadorEntry.TABLE, null, a.toContentValues());
    }

    public long guardarCurso(Curso a){
        SQLiteDatabase d = getWritableDatabase();
        return d.insert(CursoEntry.TABLE, null, a.toContentValues());
    }

    public long guardarNota(Nota a){
        SQLiteDatabase d = getWritableDatabase();
        long r = -1;
        if(buscarNota(a.idAlumno, a.idCurso).equals(""))
            r = d.insert(NotaEntry.TABLE, null, a.toContentValues());
        return r;
    }

    public Cursor buscarPorNombre(String nombre){
        String selection = AlumnoEntry.NOMBRE + " LIKE ?"; //WHERE nombre LIKE
        String[] selArgs = new String[]{"%" + nombre + "%"};
        SQLiteDatabase d = getReadableDatabase();
        //SELECT nombre FROM Alumnos WHERE nombre LIKE ?
        Cursor c = d.query(AlumnoEntry.TABLE, null, selection, selArgs,null, null, null);
        return c;
    }

    public Cursor buscarCurso(String titulo){
        String selection = CursoEntry.TITULO + " LIKE ?";
        String[] selArgs = new String[]{"%" + titulo + "%"};
        SQLiteDatabase d = getReadableDatabase();

        Cursor c = d.query(CursoEntry.TABLE, null, selection, selArgs, null, null, null);

        return c;
    }

    public String buscarCursoId(String id){
        String[] columns = new String[]{CursoEntry.TITULO};
        String selection = CursoEntry._ID + " = ?";
        String[] selArgs = new String[]{id};
        SQLiteDatabase d = getReadableDatabase();

        Cursor c = d.query(CursoEntry.TABLE, columns, selection, selArgs, null, null, null);
        String r = "";
        int n = c.getCount();
        if(n != 0) {
            c.moveToFirst();
            r = c.getString(0);
        }
        c.close();
        return r;
    }

    public String buscarAlumnoId(String id){
        return columnaAlumnoId(id, 0);
    }

    public String avatarAlumnoId(String id){
        return columnaAlumnoId(id, 1);
    }

    public String columnaAlumnoId(String id, int x){
        String[] columns = new String[]{AlumnoEntry.NOMBRE, AlumnoEntry.AVATAR};
        String selection = AlumnoEntry._ID + " = ?";
        String[] selArgs = new String[]{id};
        SQLiteDatabase d = getReadableDatabase();

        Cursor c = d.query(AlumnoEntry.TABLE, columns, selection, selArgs, null, null, null);
        String r = "";
        int n = c.getCount();
        if(n != 0) {
            c.moveToFirst();
            r = c.getString(x);
        }
        c.close();
        return r;
    }

    public String buscarNota(String alumnoId, String cursoId){
        String[] columns = new String[]{NotaEntry.NOTA};
        String selection = NotaEntry.ID_ALUMNO + " = ?" + " AND " + NotaEntry.ID_CURSO +" = ?";

        String[] selArgs = new String[]{alumnoId, cursoId};
        SQLiteDatabase d = getReadableDatabase();

        Cursor c = d.query(NotaEntry.TABLE, columns, selection, selArgs, null, null, null);
        int n = c.getCount();
        String r = "";
        if(n != 0) {
            c.moveToFirst();
            r = c.getString(0);
        }
        c.close();
        return r;
    }

    public void borrarNota(String alumnoId, String cursoId){
        SQLiteDatabase d = getWritableDatabase();
        String where = NotaEntry.ID_ALUMNO + " = ?" + " AND " + NotaEntry.ID_CURSO + " = ?";
        String[] args = new String[]{alumnoId, cursoId};
        d.delete(NotaEntry.TABLE,where, args);
    }

    public void eliminarAlumno(String id){
        SQLiteDatabase d = getWritableDatabase();
        String where = AlumnoEntry._ID + " = ?";
        String[] args = new String[]{id};
        d.delete(AlumnoEntry.TABLE, where, args);

        //borrar alumno asociado
        where = NotaEntry.ID_ALUMNO + " = ?";
        d.delete(NotaEntry.TABLE, where, args);
    }

    public void eliminarCurso(String id){
        SQLiteDatabase d = getWritableDatabase();
        String where = CursoEntry._ID + " = ?";
        String[] args = new String[]{id};
        d.delete(CursoEntry.TABLE, where, args);

        //borrar notas asociadas
        where = NotaEntry.ID_CURSO + " = ?";
        d.delete(NotaEntry.TABLE, where, args);
    }

    public Cursor buscarAlumnos(String id_curso, String nota){
        String q = "SELECT * FROM " + AlumnoEntry.TABLE +
                " LEFT JOIN " + NotaEntry.TABLE + " ON " +
                AlumnoEntry.TABLE+"."+AlumnoEntry._ID + " = " +
                NotaEntry.TABLE+"."+NotaEntry.ID_ALUMNO +
                " WHERE " + NotaEntry.ID_CURSO + " = ? AND " +
                NotaEntry.NOTA + " = ?";
        String[] selArgs = new String[]{id_curso, nota};
        SQLiteDatabase d = getReadableDatabase();

        Cursor c = d.rawQuery(q, selArgs);
        return c;
    }
}
