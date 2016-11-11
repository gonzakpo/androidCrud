package com.tecspro.agrimensoft.dao;

import java.util.List;

/**
 * Created by rodrigo on 05/10/16.
 */

public interface IGenericDao <T>{
    T createOrUpdate(T entity) throws Exception;

    T retriveById(Class<T> clazz, int id) throws Exception;

    List<T> retrieveAll(Class<T> clazz )  throws Exception;

    int delete(T entity) throws Exception;
}
