package com.tecspro.agrimensoft.view.Producto;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tecspro.agrimensoft.R;
import com.tecspro.agrimensoft.bo.GenericBo;
import com.tecspro.agrimensoft.bo.ProductoBo;
import com.tecspro.agrimensoft.config.Globals;
import com.tecspro.agrimensoft.dto.Producto;
import com.tecspro.agrimensoft.view.Generic.ConfigGeneric;
import com.tecspro.agrimensoft.view.Generic.FormGenericFragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rodrigo on 10/11/16.
 */

public class FormProductoFragment extends FormGenericFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config = new ConfigProducto();
    }


    @Override
    public void getEntity() {
        genericBo = new ProductoBo();

        Bundle mBundle = new Bundle();
        mBundle = getArguments();

        if(mBundle !=null){
            try {
                entity = (Producto) Class.forName(config.getENTITY()).cast(getArguments().get(ENTITY));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(entity != null){
                toolbar.setTitle(R.string.titulo_form_edit_generic);
            }
        }
        if(entity == null){
            try {
                entity =  (Producto) Class.forName(config.getENTITY()).newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void inicializar() {

    }
}
