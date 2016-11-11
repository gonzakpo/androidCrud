package com.tecspro.agrimensoft.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rodrigo on 09/11/16.
 */
@DatabaseTable(tableName = "Producto")
public class Producto extends GenericDto implements Serializable{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String campo1 = "";

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return this.campo1;
    }

    @Override
    public Map<Integer, String> getCampos() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0,"campo1");

        return map;
    }




}
