package com.tecspro.agrimensoft.config;

import android.app.Application;
import android.content.Context;

/**
 * Created by rodrigo on 05/10/16.
 */

public class Globals extends Application {
    private static Context context;



    public void onCreate() {
        super.onCreate();
        Globals.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Globals.context;
    }

    public static String ucFirst(String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
    }
}
