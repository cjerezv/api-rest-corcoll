package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOReaccion implements Serializable {

    private Integer id;
    private String tsFechaCreacion;
    private DTOUsuario dtoUsuario;
    private DTOColeccion dtoColeccion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTsFechaCreacion() {
        return tsFechaCreacion;
    }

    public void setTsFechaCreacion(String tsFechaCreacion) {
        this.tsFechaCreacion = tsFechaCreacion;
    }

    public DTOUsuario getDtoUsuario() {
        return dtoUsuario;
    }

    public void setDtoUsuario(DTOUsuario dtoUsuario) {
        this.dtoUsuario = dtoUsuario;
    }

    public DTOColeccion getDtoColeccion() {
        return dtoColeccion;
    }

    public void setDtoColeccion(DTOColeccion dtoColeccion) {
        this.dtoColeccion = dtoColeccion;
    }

    private static final long serialVersionUID = 1L;

}
