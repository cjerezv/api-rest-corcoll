package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOComentario implements Serializable {

    private Integer id;
    private Integer idColeccionUsuario;
    private Integer idUsuario;
    private Integer idUsuario2;
    private String comentario;
    private String tsFechaCreacion;
    private DTOUsuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdColeccionUsuario() {
        return idColeccionUsuario;
    }

    public void setIdColeccionUsuario(Integer idColeccionUsuario) {
        this.idColeccionUsuario = idColeccionUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(Integer idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTsFechaCreacion() {
        return tsFechaCreacion;
    }

    public void setTsFechaCreacion(String tsFechaCreacion) {
        this.tsFechaCreacion = tsFechaCreacion;
    }

    public DTOUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DTOUsuario usuario) {
        this.usuario = usuario;
    }

    private static final long serialVersionUID = 1L;
}
