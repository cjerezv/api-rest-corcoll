package com.api.corcoll.domain.utils;

import org.springframework.stereotype.Service;

@Service
public class Utils {

    public String valorDesdeFiltro(String filtro, String campo) {
        String[] aFilter = (filtro.replaceAll("\\((.+?)\\)", "$1")).split(",");
        for(String variable: aFilter) {
            String[] aCampo = variable.split("==");
            if(aCampo[0].equals(campo)) {
                return aCampo[1];
            }
        }
        return "";
    }

    public boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }





}
