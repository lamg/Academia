package com.example.lamg.academia;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AgregarNotaActivity extends ActionBarActivity {

    BD bd;
    String aId, cId;
    TextView notaTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nota);

        bd = new BD(getBaseContext());

        findViewById(R.id.registrarACNB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickRegistrar();
                }
            }
        );

        aId = getIntent().getStringExtra(AlumnoEntry.TABLE);
        cId = getIntent().getStringExtra(CursoEntry.TABLE);

        String nombreA = bd.buscarAlumnoId(aId);
        String tituloC = bd.buscarCursoId(cId);
        String nota = bd.buscarNota(aId, cId);
        String avatar = bd.avatarAlumnoId(aId);

        ((TextView)findViewById(R.id.alumnoTV)).setText(nombreA);
        ((TextView)findViewById(R.id.cursoTV)).setText(tituloC);

        notaTV = ((TextView)findViewById(R.id.notaTV));
        notaTV.setText(nota);
        try{
            Uri u = Uri.parse(avatar);
            InputStream fotoStream = getContentResolver().openInputStream(u);
            Bitmap b = BitmapFactory.decodeStream(fotoStream);
            ((ImageView)findViewById(R.id.avatarV)).setImageBitmap(b);
        } catch(FileNotFoundException e){
            Toast.makeText(getBaseContext(), "No se pudo cargar el avatar", Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.borrarNB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickBorrar();
                }
            }
        );
    }

    void onClickBorrar(){
        bd.borrarNota(aId, cId);
        Toast.makeText(getBaseContext(), "Nota borrada", Toast.LENGTH_SHORT).show();
        notaTV.setText("");
    }

    void onClickRegistrar(){
        String nota = Util.getCT(R.id.notaT,this);
        boolean b = nota.equals("2") || nota.equals("3") || nota.equals("4") || nota.equals("5");
        if(b){
            long r = bd.guardarNota(new Nota(aId, cId, nota));
            if(r != -1)
                Toast.makeText(getBaseContext(), "Nota agregada", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "Nota duplicada", Toast.LENGTH_SHORT).show();
            notaTV.setText(nota);
        } else {
            Toast.makeText(getBaseContext(), "Nota no valida", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar_nota, menu);
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
