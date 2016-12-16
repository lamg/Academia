package com.example.lamg.academia;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class GestionarAlumnosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_alumnos);

        findViewById(R.id.agregarAlumnoAB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickAgregarAlumnoAB();
                }
            }
        );

        findViewById(R.id.eliminarAlumnoAB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickEliminarAlumnoAB();
                }
            }
        );
    }

    void onClickAgregarAlumnoAB(){
        Intent i = new Intent(getApplicationContext(), AgregarAlumnosActivity.class);
        startActivity(i);
    }

    void onClickEliminarAlumnoAB(){
        Intent i = new Intent(getApplicationContext(), EliminarAlumnoActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gestionar_alumnos, menu);
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
