package com.example.lamg.academia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO problema al guardar la foto

        //para que quepan todos los componentes

        //suscripcion a los eventos
        findViewById(R.id.AlumnosB).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v) {
                    onClickAlumnos(v);
                }
            }
        );

        findViewById(R.id.CursosB).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v) {
                    onClickCursos(v);
                }
            }
        );

        findViewById(R.id.ReportesB).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v){
                    onClickReportes(v);
                }
            }
        );

        findViewById(R.id.NotasB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v) {
                    onClickNotas(v);
                }
            }
        );
    }

    void onClickNotas(View v){
        Intent i = new Intent(getApplicationContext(), NotasActivity.class);
        startActivity(i);
    }

    void onClickAlumnos(View v){
        Intent i = new Intent(getApplicationContext(), GestionarAlumnosActivity.class);
        startActivity(i);
    }

    void onClickCursos(View v){
        Intent i = new Intent(getApplicationContext(), GestionarCursosActivity.class);
        startActivity(i);
    }

    void onClickReportes(View v){
        Intent i = new Intent(getApplicationContext(), ReportesActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
