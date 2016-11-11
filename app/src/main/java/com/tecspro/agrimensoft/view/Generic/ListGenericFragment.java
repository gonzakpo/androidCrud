package com.tecspro.agrimensoft.view.Generic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.tecspro.agrimensoft.R;
import com.tecspro.agrimensoft.bo.GenericBo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 04/11/16.
 */

public abstract class ListGenericFragment<T> extends Fragment {

    private View recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleItemRecyclerViewAdapter proyectoAdapter;
    FragmentManager fragmentManager = null;
    protected ConfigGeneric config;
    protected GenericBo genericBo;
    protected List<T> aEntitys;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        return inflater.inflate(R.layout.activity_list_generic, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        constructor();
        inicializar();

        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void constructor() {
        recyclerView = getView().findViewById(R.id.generic_list);
      //  mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
    }

    private void inicializar() {
        fragmentManager = getFragmentManager();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        buscarLista();
        proyectoAdapter = new SimpleItemRecyclerViewAdapter(aEntitys);

        recyclerView.setAdapter(proyectoAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> implements Filterable {

        private List<T> mValues;
        private List<T> mValues2;

        public SimpleItemRecyclerViewAdapter(List<T> aEntities) {
            mValues = aEntities;
            mValues2 = aEntities;
        }

        public void setList(List<T> list) {
            this.mValues = list;
            this.mValues2 = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.proyecto_list_content, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);
            //holder.mIdView.setText(String.valueOf(mValues.get(position).getIdProducto()));
            holder.mContentView.setText(mValues.get(position).toString());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onClick(View v) {
                    Fragment fragment = null;//new GenericDetailFragment();
                    try {
                        fragment = (Fragment) Class.forName(config.getDETAIL()).newInstance();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bundle bundle = new Bundle();
                bundle.putSerializable(GenericDetailFragment.ENTITY, (Serializable) mValues.get(position));
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();

                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public Filter getFilter() {

            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    final FilterResults oReturn = new FilterResults();
                    final List<T> results = new ArrayList<T>();

                    if (constraint != null) {
                        if (mValues2 != null & mValues2.size() > 0) {
                            for (final T g : mValues2) {
                                if (g.toString() != null && g.toString().toLowerCase().contains(constraint.toString()))
                                    results.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                    return oReturn;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mValues = (ArrayList<T>) results.values;
                    notifyDataSetChanged();

                }
            };
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            // public final TextView mIdView;
            public final TextView mContentView;
            public T mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                //  mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:


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
                ;
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment,config.SIMPLE_NAME_FORM).addToBackStack(null).commit();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public abstract void buscarLista();

}
