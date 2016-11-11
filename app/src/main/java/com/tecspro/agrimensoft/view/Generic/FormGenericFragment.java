package com.tecspro.agrimensoft.view.Generic;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tecspro.agrimensoft.R;
import com.tecspro.agrimensoft.bo.GenericBo;
import com.tecspro.agrimensoft.config.Globals;
import com.tecspro.agrimensoft.dto.GenericDto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rodrigo on 08/11/16.
 */

public abstract class FormGenericFragment extends Fragment{
    public static final String ENTITY = "ENTITY";
    private String ERROR = "ERROR";
    private String SUCCESS = "EXITO";
    protected List<EditText> listCamposEditar;
    protected GenericDto entity = null;
    protected Map map = null;
    protected LinearLayout formulario;
    protected ConfigGeneric config;
    protected Toolbar toolbar;
    protected GenericBo genericBo;
    List<TextView> listLabels;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.form_generic, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        constructor();
        inicializar();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_form, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                relacionarValoresEditText();
                try {
                    genericBo.createOrUpdate(entity);
                    getActivity().getFragmentManager().beginTransaction().remove(this).commit();
                    getActivity().onBackPressed();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Globals.getAppContext(), ERROR, Toast.LENGTH_SHORT);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    protected void relacionarValoresEditText(){
        View v = null;
        EditText campo = null;
        Map<Integer, String> valores = new HashMap<>();
        int contador = 0;
        for(int i=0; i< formulario.getChildCount(); i++) {
            v = formulario.getChildAt(i);
            if( v instanceof EditText ) {
                valores.put(contador, ((EditText) v).getText().toString());
                contador++;
            }
        }
        Method getter = null;
        contador = 0;
        for (Field f:config.getFormulario()) {
            try {
                getter = entity.getClass().getDeclaredMethod("set"+ Globals.ucFirst((String) f.getName()),f.getType());

                getter.invoke(entity,valores.get(contador));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            contador++;

        }
    }

    public void constructor(){
        formulario = (LinearLayout) getActivity().findViewById(R.id.form);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_form_new_generic);

        getEntity();
        EditText txtCampo = null;
        TextView label = null;
        Method getter = null;
        listLabels = new ArrayList<TextView>();
        for (Field f:config.getFormulario()) {
            label = new TextView(Globals.getAppContext());
            label.setText(f.getName());
            txtCampo = new EditText(Globals.getAppContext());
            try {
                getter = entity.getClass().getMethod("get"+ Globals.ucFirst((String) f.getName()));
                txtCampo.setText(getter.invoke(entity).toString());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            formulario.addView(label);
            formulario.addView(txtCampo);
        }

        /*map = entity.getCampos();

        EditText txtCampo = null;
        TextView label = null;
        Iterator it = map.keySet().iterator();
        List<TextView> listLabels = new ArrayList<TextView>();
        listCamposEditar = new ArrayList<EditText>();
        Method getter = null;
        while(it.hasNext()){
            Integer key = (Integer) it.next();

            label = new TextView(Globals.getAppContext());
            label.setText(map.get(key).toString());
            txtCampo = new EditText(Globals.getAppContext());
            try {
                getter = entity.getClass().getMethod("get"+ Globals.ucFirst((String) map.get(key)));
                txtCampo.setText(getter.invoke(entity).toString());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }*/
    }


    public abstract void getEntity();

    public abstract void inicializar();


}
