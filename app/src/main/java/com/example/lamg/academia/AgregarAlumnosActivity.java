package com.example.lamg.academia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AgregarAlumnosActivity extends ActionBarActivity {

    BD bd;
    ImageView foto;
    InputStream fotoStream;
    String avatar = "predeterminado.jpg";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        bd = new BD(getApplicationContext());
        foto = (ImageView)findViewById(R.id.FotoI);

        final CheckBox t = (CheckBox)findViewById(R.id.TrabajadorCheck);
        t.setOnClickListener(
            new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickTrabajador(t.isChecked());
                }
            }
        );

        findViewById(R.id.AgregarB).setOnClickListener(
            new Button.OnClickListener(){
                @Override public void onClick(View v){
                    onClickAgregar(t.isChecked());
                }
            }
        );

        findViewById(R.id.FotoB).setOnClickListener(
                new Button.OnClickListener(){
                    @Override public void onClick(View v){
                        onClickFoto();
                    }
                }
        );

        this.setTitle("Agregue alumnos");
    }

    void onClickFoto(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Bitmap bitmap;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            try {
                avatar = data.getData().toString();

                fotoStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(fotoStream);
                foto.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                Toast.makeText(getBaseContext(), "Error al tomar foto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void onClickAgregar(boolean b) {
        String dni = Util.getCT(R.id.DNIText, this);
        String direccion = Util.getCT(R.id.DireccionText, this);
        String nombre = Util.getCT(R.id.NombreText, this);
        String telefono = Util.getCT(R.id.TelefonoText, this);
        String edad = Util.getCT(R.id.EdadText, this);

        boolean v = !(dni.equals("") || direccion.equals("") || nombre.equals("") ||
                telefono.equals("") || edad.equals("") || avatar.equals(""))
                && dni.length() == 11 && edad.length() <= 3;
        if (v && b) {
            String cif = Util.getCT(R.id.CIFText, this);
            String nombreE = Util.getCT(R.id.NombreEText, this);
            String telefonoE = Util.getCT(R.id.TelefonoEText, this);
            String direccionE = Util.getCT(R.id.DireccionEText, this);
            v = cif != "" && nombreE != "" && telefonoE != "" && direccionE != "";
            if(v) {
                Trabajador a = new Trabajador(dni, direccion, nombre, telefono, edad, avatar,
                        cif, nombreE, telefonoE, direccionE);
                long r = bd.saveTrabajador(a);
                notificar(r);
            }
        } else if (v) {
            Alumno a = new Alumno(dni, direccion, nombre, telefono, edad, avatar);
            long r = bd.saveAlumno(a);
            notificar(r);
        } else {
            Toast.makeText(getBaseContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
        }
    }

    void notificar(long r){
        if(r == -1)
            Toast.makeText(getBaseContext(), "El DNI ya existe", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(getBaseContext(), "Usuario agregado", Toast.LENGTH_SHORT).show();
            clearText();
            foto.setImageBitmap(null);
        }
    }

    void clearText(){
        int[] ids = new int[]{R.id.DNIText, R.id.DireccionText,R.id.NombreText,
        R.id.TelefonoText, R.id.EdadText, R.id.CIFText, R.id.NombreEText,
        R.id.TelefonoEText, R.id.DireccionEText};
        Util.clearEditTexts(ids, this);

    }

    void onClickTrabajador(boolean b) {
        findViewById(R.id.CIFText).setEnabled(b);
        findViewById(R.id.NombreEText).setEnabled(b);
        findViewById(R.id.TelefonoEText).setEnabled(b);
        findViewById(R.id.DireccionEText).setEnabled(b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alumnos, menu);
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
