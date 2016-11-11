package com.tecspro.agrimensoft.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rodrigo on 20/10/16.
 */

public class Preferencias {

    private String PREFERENCIAS_NAME= "MIS_PREFERENCIAS";
    private String ULTIMA_ACTUALIZACION = "ultimaActualizacion";
    private String ultimaFechaActualizacion;
    private String usuario;
    private String clave;


    public String getUltimaFechaActualizacion() {
        SharedPreferences prefs =Globals.getAppContext().getSharedPreferences(PREFERENCIAS_NAME,Context. MODE_PRIVATE);
        ultimaFechaActualizacion = prefs.getString(ULTIMA_ACTUALIZACION, "");

        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(String ultimaFechaActualizacion) {
        SharedPreferences.Editor editor = Globals.getAppContext().getSharedPreferences(PREFERENCIAS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(ULTIMA_ACTUALIZACION, ultimaFechaActualizacion);
        editor.commit();
    }



}
