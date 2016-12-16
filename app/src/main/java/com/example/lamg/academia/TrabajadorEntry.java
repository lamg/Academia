package com.example.lamg.academia;

import android.provider.BaseColumns;

/**
 * Created by lamg on 13/11/16.
 */

public abstract class TrabajadorEntry implements BaseColumns {
    public static final String CIF = "cif";
    public static final String NOMBRE_EMPRESA = "nombre_empresa";
    public static final String TELEFONO_EMPRESA = "telefono_empresa";
    public static final String DIRECCION_EMPRESA = "direccion_empresa";
    public static final String TABLE = "Trabajador";
}
