package com.tecspro.agrimensoft.bo;

import com.tecspro.agrimensoft.dao.IGenericDao;
import com.tecspro.agrimensoft.dao.Sqlite.GenericSqlite;
import com.tecspro.agrimensoft.dto.IGeneric;

import java.util.List;
import java.util.Map;

/**
 * Created by rodrigo on 11/11/16.
 */

public class GenericBo<T> implements IGenericDao<T> {

    protected GenericSqlite genericSqlite;


    @Override
    public T createOrUpdate(T entity) throws Exception {
        return (T) genericSqlite.createOrUpdate(entity);
    }

    @Override
    public T retriveById(Class<T> clazz, int id) throws Exception {
        return (T) genericSqlite.retriveById(clazz,id);
    }

    @Override
    public List<T> retrieveAll(Class<T> clazz) throws Exception {
        return genericSqlite.retrieveAll(clazz);
    }

    @Override
    public int delete(T entity) throws Exception {
        return genericSqlite.delete(entity);
    }
}
