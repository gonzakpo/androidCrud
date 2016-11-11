package com.tecspro.agrimensoft.dao.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tecspro.agrimensoft.dto.Producto;

import java.sql.SQLException;

/**
 * Created by rodrigo on 05/10/16.
 */

public class ORMLite extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "prueba1.db";
    private static final int DB_VERSION = 1;

    private ORMLite(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    private static ORMLite instance;

    public synchronized static ORMLite getInstance(Context context) {
        if (instance == null) {
            instance = new ORMLite(context, DATABASE_NAME, null, DB_VERSION);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Producto.class);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteAllTables(){
        try {
            TableUtils.createTable(connectionSource,Producto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource,
                          int currentVersion, int newVersion) {
        /*if (currentVersion <= 2) {
            try {
                getDao(Product.class).executeRawNoArgs("ALTER TABLE 'products' ADD COLUMN descripcion STRING;");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentVersion++;
        }

        if (currentVersion <= 3) {
            try {
                getDao(Client.class).executeRawNoArgs("ALTER TABLE 'clients' ADD COLUMN phoneNumber STRING;");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentVersion++;
        }*/

    }

}