package com.tecspro.agrimensoft.view.Producto;

import android.os.Bundle;

import com.tecspro.agrimensoft.bo.ProductoBo;
import com.tecspro.agrimensoft.dto.GenericDto;
import com.tecspro.agrimensoft.dto.Producto;
import com.tecspro.agrimensoft.view.Generic.GenericDetailFragment;

/**
 * Created by rodrigo on 11/11/16.
 */

public class ProductoDetailFragment extends GenericDetailFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entity = (Producto) getArguments().getSerializable(ENTITY);
        if(entity == null){
            entity = new Producto();
        }
        config = new ConfigProducto();
        genericBo = new ProductoBo();
    }


}
