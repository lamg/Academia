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


public class ReportesActivity extends ActionBarActivity {

    BD bd;
    Cursor c;
    ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        bd = new BD(getApplicationContext());

        findViewById(R.id.buscarCRB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickBuscarCRB();
                }
            }
        );

        lw = (ListView)findViewById(R.id.reportesCursoV);

        lw.setOnItemClickListener(
                new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int p, long l) {
                        onClickLista(a,v,p,l);
                    }
                }
        );
    }

    void onClickLista(AdapterView<?> a, View v, int p, long l){
        SQLiteCursor d = (SQLiteCursor)a.getItemAtPosition(p);
        String s = d.getString(0);

        Intent i = new Intent(getApplicationContext(),ReporteNotasActivity.class);
        i.putExtra(CursoEntry._ID, s);
        startActivity(i);
    }

    void onClickBuscarCRB(){
        if(c != null) c.close();
        String titulo = Util.getCT(R.id.reporteCursoET, this);
        boolean v = !(titulo.equals(""));
        if(v){
            c = bd.buscarCurso(titulo);
            SimpleCursorAdapter a = new SimpleCursorAdapter(
                    getApplicationContext(),
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
            Toast.makeText(getApplicationContext(), "Debe introducir un titulo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reportes, menu);
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
