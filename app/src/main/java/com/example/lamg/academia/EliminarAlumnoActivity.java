package com.example.lamg.academia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class EliminarAlumnoActivity extends ActionBarActivity {

    BD bd;
    Cursor c;
    ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_alumno);

        bd = new BD(getApplicationContext());
        lw = (ListView)findViewById(R.id.eliminarAlumnoV);

        findViewById(R.id.buscarAlumnoB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickBuscar();
                }
            }
        );

        lw.setOnItemClickListener(
                new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int p, long l) {
                        onClickLista(a,v,p,l);
                    }
                }
        );

    }

    @Override public void onBackPressed(){
        if (c != null) c.close();
        super.onBackPressed();
    }

    void onClickLista(AdapterView<?> a, View v, int p, long l){
        SQLiteCursor d = (SQLiteCursor)a.getItemAtPosition(p);
        String s = d.getString(0);
        dialogo_elimina(s);


    }

    public void dialogo_elimina(final String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Eliminar")
                .setMessage("Esta seguro que desea eliminar")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bd.eliminarAlumno(s);
                                Toast.makeText(getApplicationContext(), "Alumno eliminado", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {                          }
                        });

        builder.create().show();
    }

    void onClickBuscar(){
        if(c != null) c.close();
        String nombre = Util.getCT(R.id.eliminarET, this);
        boolean v = !(nombre.equals(""));
        if(v){
            c = bd.buscarPorNombre(nombre);
            SimpleCursorAdapter a = new SimpleCursorAdapter(
                    getApplicationContext(),
                    android.R.layout.two_line_list_item,
                    c,
                    new String[]{ AlumnoEntry.NOMBRE, AlumnoEntry.DNI },
                    new int[]{android.R.id.text1, android.R.id.text2},
                    SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            lw.setAdapter(a);
            if (c.getCount() == 0) {
                c.close();
                Toast.makeText(getBaseContext(), "No se encontraron alumnos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Debe introducir un nombre", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eliminar_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
