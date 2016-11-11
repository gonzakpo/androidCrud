package com.tecspro.agrimensoft.view.Producto;

import com.tecspro.agrimensoft.view.Generic.ConfigGeneric;
import com.tecspro.agrimensoft.view.Generic.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 10/11/16.
 */

public class ConfigProducto extends ConfigGeneric {

    public ConfigProducto(){
        ENTITY = "com.tecspro.agrimensoft.dto.Producto";
        FORM = "com.tecspro.agrimensoft.view.Producto.FormProductoFragment";
        DETAIL = "com.tecspro.agrimensoft.view.Producto.ProductoDetailFragment";
        SIMPLE_NAME_FORM = FormProductoFragment.class.getSimpleName();

    }


    @Override
    protected void createForm() {
        List form = new ArrayList<>();
        Field field = new Field();
        field.setName("campo1");
        field.setType(new Class[]{String.class});
        form.add(field);
        this.setFormulario(form);
    }
}
