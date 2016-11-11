package com.tecspro.agrimensoft.dao.Sqlite;

import com.j256.ormlite.dao.Dao;
import com.tecspro.agrimensoft.config.Globals;
import com.tecspro.agrimensoft.dao.IGenericDao;
import com.tecspro.agrimensoft.dao.IProducto;
import com.tecspro.agrimensoft.dto.IGeneric;
import com.tecspro.agrimensoft.dto.Producto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rodrigo on 05/10/16.
 */

public class GenericSqlite<T> implements IGenericDao<T>{


    @Override
    public T createOrUpdate(T entity) throws Exception {
        try {
            Class<T> clazz = (Class<T>) entity.getClass();
            ORMLite.getInstance(Globals.getAppContext()).getDao(clazz).createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public T retriveById(Class<T> clazz, int id) throws Exception {
        try {
            Dao<T, Integer> dao = ORMLite.getInstance(Globals.getAppContext()).getDao(clazz);
            dao.queryForId(id);
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> retrieveAll(Class<T> clazz) throws Exception {
        try {
            Dao<T, Integer> dao = ORMLite.getInstance(Globals.getAppContext()).getDao(clazz);

            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int delete(T entity) throws Exception {
        Class<T> clazz = (Class<T>) entity.getClass();

        try {
            Dao<T, Integer> dao = ORMLite.getInstance(Globals.getAppContext()).getDao(clazz);
            return dao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
