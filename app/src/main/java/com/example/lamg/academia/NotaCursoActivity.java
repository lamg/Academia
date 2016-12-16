package com.example.lamg.academia;

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
import android.widget.Toast;


public class NotaCursoActivity extends ActionBarActivity {

    String idA;
    BD bd;
    ListView lw;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_curso2);

        idA = getIntent().getStringExtra(AlumnoEntry._ID);
        bd = new BD(getApplicationContext());
        setTitle("Seleccione un curso");

        findViewById(R.id.buscarCB).setOnClickListener(
            new Button.OnClickListener() {
                @Override public void onClick(View v){
                    onClickBuscar();
                }
            }
        );

        lw = (ListView)findViewById(R.id.cursoV);
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

        Intent i = new Intent(getBaseContext(), AgregarNotaActivity.class);
        i.putExtra(CursoEntry.TABLE, s);
        i.putExtra(AlumnoEntry.TABLE, getIntent().getStringExtra(AlumnoEntry._ID));
        c.close();
        d.close();
        startActivity(i);
    }

    void onClickBuscar(){
        if(c != null) c.close();
        String curso = Util.getCT(R.id.buscarCT, this);
        boolean v = !(curso.equals(""));
        if(v){
            c = bd.buscarCurso(curso);

            //cargar la actividad para anhadir la nota
            SimpleCursorAdapter a = new SimpleCursorAdapter(
                    getBaseContext(),
                    android.R.layout.two_line_list_item,
                    c,
                    new String[]{ CursoEntry.TITULO, CursoEntry.CODIGO },
                    new int[]{android.R.id.text1, android.R.id.text2},
                    SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            lw.setAdapter(a);
            if (c.getCount() == 0) {
                c.close();
                Toast.makeText(getBaseContext(), "No se encontraron cursos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(),"Debe introducir un nombre", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nota_curso, menu);
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
