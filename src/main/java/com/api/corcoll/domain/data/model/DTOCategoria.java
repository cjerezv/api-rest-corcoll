package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOCategoria implements Serializable {


    private Integer id;
    private String nombre;
    private String descripcion;
    private String tsFechaCreacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTsFechaCreacion() {
        return tsFechaCreacion;
    }

    public void setTsFechaCreacion(String tsFechaCreacion) {
        this.tsFechaCreacion = tsFechaCreacion;
    }

    private static final long serialVersionUID = 1L;

}
