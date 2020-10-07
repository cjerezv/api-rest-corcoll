package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOMensajePrivado implements Serializable {

    private Integer id;
    private Integer idUsuarioEmisor;
    private Integer idUsuarioReceptor;
    private String mensaje;
    private String tsFechaCreacion;
    private DTOUsuario usuarioEmisor;
    private DTOUsuario usuarioReceptor;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuarioEmisor() {
        return idUsuarioEmisor;
    }

    public void setIdUsuarioEmisor(Integer idUsuarioEmisor) {
        this.idUsuarioEmisor = idUsuarioEmisor;
    }

    public Integer getIdUsuarioReceptor() {
        return idUsuarioReceptor;
    }

    public void setIdUsuarioReceptor(Integer idUsuarioReceptor) {
        this.idUsuarioReceptor = idUsuarioReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTsFechaCreacion() {
        return tsFechaCreacion;
    }

    public void setTsFechaCreacion(String tsFechaCreacion) {
        this.tsFechaCreacion = tsFechaCreacion;
    }

    public DTOUsuario getUsuarioEmisor() {
        return usuarioEmisor;
    }

    public void setUsuarioEmisor(DTOUsuario usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
    }

    public DTOUsuario getUsuarioReceptor() {
        return usuarioReceptor;
    }

    public void setUsuarioReceptor(DTOUsuario usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }

    private static final long serialVersionUID = 1L;

}
