package com.tecspro.agrimensoft.dto;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rodrigo on 09/11/16.
 */

public abstract class GenericDto implements IGeneric{


    public abstract Map<Integer, String> getCampos();
}
