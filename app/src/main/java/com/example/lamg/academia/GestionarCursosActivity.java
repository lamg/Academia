package com.example.lamg.academia;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class GestionarCursosActivity extends ActionBarActivity {

    BD bd;
    Cursor c;
    ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_cursos);

        findViewById(R.id.agregarCursoAB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickAgregarCursoAB();
                }
            }
        );

        findViewById(R.id.eliminarCursoAB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickEliminarCursoAB();
                }
            }
        );
    }

    @Override public void onBackPressed(){
        if (c != null) c.close();
        super.onBackPressed();
    }

    void onClickEliminarCursoAB(){
        Intent i = new Intent(getApplicationContext(), EliminarCursoActivity.class);
        startActivity(i);
    }

    void onClickAgregarCursoAB(){
        Intent i = new Intent(getApplicationContext(), AgregarCursoActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gestionar_cursos, menu);
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
