package com.example.lamg.academia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lamg on 24/11/16.
 */
public class Util {
    public static String guardarFoto(String dni, InputStream fotoStream, Context b){
        File dir = new File(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath()+"/AcademiaFotos");
        boolean v = (dir.exists() || dir.mkdir()) && dni != "" && fotoStream != null;
        String r = dir.getAbsolutePath() + "/predeterminado.jpg";
        if(v){
            r = dir.getAbsolutePath()+ "/" + dni + ".jpg";
            try {
                FileOutputStream o = new FileOutputStream(r);

                byte[] buffer = new byte[1024];
                int len = fotoStream.read(buffer);
                while (len != -1) {
                    o.write(buffer, 0, len);
                    len = fotoStream.read(buffer);
                }
                o.close();
                fotoStream.close();
            } catch (IOException e) {
                Toast.makeText(b, "Error al tomar/guardar foto", Toast.LENGTH_SHORT).show();
            }
        }
        return r;
    }

    public static String getCT(int id, Activity a) {
        return ((EditText)a.findViewById(id)).getText().toString();
    }

    public static void clearEditTexts(int[] ids, Activity a){
        for (int i = 0; i != ids.length; i++){
            ((EditText)a.findViewById(ids[i])).setText("");
        }
    }
}
