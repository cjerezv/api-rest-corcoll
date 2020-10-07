package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOAmistad implements Serializable {

    private Integer id;
    private DTOEstado dtoEstado;
    private DTOUsuario dtoUsuario1;
    private DTOUsuario dtoUsuario2;
    private String tsFechaCreacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DTOEstado getDtoEstado() {
        return dtoEstado;
    }

    public void setDtoEstado(DTOEstado dtoEstado) {
        this.dtoEstado = dtoEstado;
    }

    public DTOUsuario getDtoUsuario1() {
        return dtoUsuario1;
    }

    public void setDtoUsuario1(DTOUsuario dtoUsuario1) {
        this.dtoUsuario1 = dtoUsuario1;
    }

    public DTOUsuario getDtoUsuario2() {
        return dtoUsuario2;
    }

    public void setDtoUsuario2(DTOUsuario dtoUsuario2) {
        this.dtoUsuario2 = dtoUsuario2;
    }

    public String getTsFechaCreacion() {
        return tsFechaCreacion;
    }

    public void setTsFechaCreacion(String tsFechaCreacion) {
        this.tsFechaCreacion = tsFechaCreacion;
    }

    private static final long serialVersionUID = 1L;
}
