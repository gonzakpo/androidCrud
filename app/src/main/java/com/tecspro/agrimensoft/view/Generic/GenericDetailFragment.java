package com.tecspro.agrimensoft.view.Generic;

import android.app.Fragment;
import android.app.FragmentManager;
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

import com.tecspro.agrimensoft.R;
import com.tecspro.agrimensoft.bo.GenericBo;
import com.tecspro.agrimensoft.config.Globals;
import com.tecspro.agrimensoft.dto.GenericDto;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rodrigo on 04/11/16.
 */

public abstract class GenericDetailFragment extends Fragment{
    public static final String ENTITY = "ENTITY";
    private List<TextView> listTextView;
    protected GenericDto entity;
    FragmentManager fragmentManager = null;
    LinearLayout formulario = null;
    protected Map<Integer, String> map;
    protected ConfigGeneric config;
    protected GenericBo genericBo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_detail_generic);
        return inflater.inflate(R.layout.generic_detail, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        constructor();
        inicializar();

    }
    private void constructor() {
        formulario = (LinearLayout) getActivity().findViewById(R.id.form);

        map = entity.getCampos();

        TextView label = null;
        Iterator it = map.keySet().iterator();
        List<TextView> listLabels = new ArrayList<TextView>();
        listTextView = new ArrayList<TextView>();
        Method getter = null;
        while(it.hasNext()){
            Integer key = (Integer) it.next();

            label = new TextView(Globals.getAppContext());
            label.setText(map.get(key).toString());
            try {
                getter = entity.getClass().getMethod("get"+ Globals.ucFirst((String) map.get(key)));
                label.setText(label.getText().toString()+": "+getter.invoke(entity).toString());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            formulario.addView(label);
        }

    }
    private void inicializar() {

        fragmentManager = getFragmentManager();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_modificar:
                Fragment fragment = null;
                try {
                    fragment = (Fragment) Class.forName(config.FORM).newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable(FormGenericFragment.ENTITY, (Serializable) entity);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
                return true;
            case R.id.action_eliminar:
                try {
                    genericBo.delete(entity);
                    getActivity().onBackPressed();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




}
