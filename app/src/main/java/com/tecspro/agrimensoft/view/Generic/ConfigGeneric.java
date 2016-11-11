package com.tecspro.agrimensoft.view.Generic;

import java.util.List;

/**
 * Created by rodrigo on 10/11/16.
 */

public abstract class ConfigGeneric {
    protected String ENTITY = "";
    protected String FORM = "";
    protected String DETAIL = "";
    protected String SIMPLE_NAME_FORM = "";
    protected List<Field> Formulario;

    public ConfigGeneric(){
        createForm();
    }

    public String getDETAIL() {
        return DETAIL;
    }

    public void setDETAIL(String DETAIL) {
        this.DETAIL = DETAIL;
    }

    public String getENTITY() {
        return ENTITY;
    }

    public void setENTITY(String ENTITY) {
        this.ENTITY = ENTITY;
    }

    public String getFORM() {
        return FORM;
    }

    public void setFORM(String FORM) {
        this.FORM = FORM;
    }

    public String getSIMPLE_NAME_FORM() {
        return SIMPLE_NAME_FORM;
    }

    public void setSIMPLE_NAME_FORM(String SIMPLE_NAME_FORM) {
        this.SIMPLE_NAME_FORM = SIMPLE_NAME_FORM;
    }

    public List<Field> getFormulario() {
        return Formulario;
    }

    public void setFormulario(List<Field> formulario) {
        Formulario = formulario;
    }
    protected abstract void createForm();
}
