package com.tecspro.agrimensoft.view.Producto;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tecspro.agrimensoft.bo.GenericBo;
import com.tecspro.agrimensoft.bo.ProductoBo;
import com.tecspro.agrimensoft.dto.Producto;
import com.tecspro.agrimensoft.view.Generic.ConfigGeneric;
import com.tecspro.agrimensoft.view.Generic.ListGenericFragment;

import java.util.ArrayList;

/**
 * Created by rodrigo on 09/11/16.
 */

public class ListProductoFragment extends ListGenericFragment<Producto> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.config = new ConfigProducto();
        this.genericBo  = new ProductoBo();

    }


    @Override
    public void buscarLista() {
        try {
            aEntitys = genericBo.retrieveAll(Producto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(aEntitys ==null){
            aEntitys = new ArrayList<Producto>();
        }
    }

}
