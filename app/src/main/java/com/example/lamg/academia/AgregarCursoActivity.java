package com.example.lamg.academia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class AgregarCursoActivity extends ActionBarActivity {

    BD bd;
    ImageView foto;
    InputStream fotoStream;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        bd = new BD(getApplicationContext());

        findViewById(R.id.agregarB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickAgregar();
                }
            }
        );
    }

    void onClickAgregar(){
        String codigo = getControlText(R.id.codigoT);
        String titulo = getControlText(R.id.tituloT);
        String duracion = getControlText(R.id.duracionT);
        DatePicker inicio = (DatePicker)findViewById(R.id.inicioD);
        String fechaInicio = String.valueOf(inicio.getDayOfMonth()) + "/"
                + String.valueOf(inicio.getMonth()) + "/" + String.valueOf(inicio.getYear());
        DatePicker fn = (DatePicker)findViewById(R.id.finalD);
        String fechaTerminacion = String.valueOf(fn.getDayOfMonth()) + "/"
                + String.valueOf(fn.getMonth()) + "/" + String.valueOf(fn.getYear());
        String dniProfesor = getControlText(R.id.dniT);
        String nombreProfesor = getControlText(R.id.nombreT);
        String direccionProfesor = getControlText(R.id.direccionT);
        String telefonoProfesor = getControlText(R.id.telefonoT);

        boolean v = !(codigo.equals("") || titulo.equals("") || duracion.equals("") ||
            fechaInicio.equals("") || fechaTerminacion.equals("") || dniProfesor.equals("") ||
            nombreProfesor.equals("") || direccionProfesor.equals("") ||
            telefonoProfesor.equals(""));
        if (v){
            Curso c = new Curso(codigo, titulo, duracion, fechaInicio, fechaTerminacion,
                    dniProfesor, nombreProfesor, direccionProfesor, telefonoProfesor);
            long r = bd.guardarCurso(c);
            if(r == -1) {
                Toast.makeText(getBaseContext(), "El codigo ya existe", Toast.LENGTH_SHORT).show();
            } else {
                clearText();
                Toast.makeText(getBaseContext(), "Curso agregado", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Faltan campos por llenar", Toast.LENGTH_LONG).show();
        }
    }

    void clearText(){
        int[] ids = new int[]{R.id.codigoT, R.id.tituloT,
                R.id.duracionT, R.id.dniT,R.id.nombreT,
                R.id.direccionT, R.id.telefonoT};
        Util.clearEditTexts(ids, this);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Bitmap bitmap;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            try {
                fotoStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(fotoStream);
                foto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Toast.makeText(getBaseContext(), "Error al tomar foto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void onClickFoto(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(i, REQUEST_CODE);
    }

    String getControlText(int id) {
        return ((EditText)findViewById(id)).getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cursos, menu);
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
