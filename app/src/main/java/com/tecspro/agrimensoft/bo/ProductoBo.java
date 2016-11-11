package com.tecspro.agrimensoft.bo;

import com.tecspro.agrimensoft.dao.IProducto;
import com.tecspro.agrimensoft.dao.Sqlite.ProductoSqlite;
import com.tecspro.agrimensoft.dto.Producto;

import java.util.List;

/**
 * Created by rodrigo on 11/11/16.
 */

public class ProductoBo extends GenericBo<Producto> implements IProducto{
    public ProductoBo(){
        genericSqlite = new ProductoSqlite();
    }
}
