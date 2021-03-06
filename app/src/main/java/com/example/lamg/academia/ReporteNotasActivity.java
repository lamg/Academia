package com.example.lamg.academia;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class ReporteNotasActivity extends ActionBarActivity {

    BD bd;
    Cursor c;
    ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_notas);

        bd = new BD(getApplicationContext());
        lw = (ListView)findViewById(R.id.reporteNotaV);
        final String id_curso = getIntent().getStringExtra(CursoEntry._ID);
        findViewById(R.id.reporteNotaB).setOnClickListener(
                new Button.OnClickListener(){
                    @Override public void onClick(View v){
                        onClickReporteNotaB(id_curso);
                    }
                }
        );
    }

    void onClickReporteNotaB(String id_curso){
        if(c != null) c.close();
        String nota = Util.getCT(R.id.reporteNotaET, this);
        boolean v = !(nota.equals(""));
        if(v){
            c = bd.buscarAlumnos(id_curso, nota);
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
            Toast.makeText(getApplicationContext(), "Debe introducir una nota", Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void onBackPressed(){
        if (c != null) c.close();
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reporte_notas, menu);
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
