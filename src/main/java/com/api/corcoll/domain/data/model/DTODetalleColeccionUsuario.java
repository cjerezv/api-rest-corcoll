package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTODetalleColeccionUsuario implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer idColeccionUsuario;

    @Getter
    @Setter
    private Integer idUsuario;

    @Getter
    @Setter
    private Integer idColeccion;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String descripcion;

    @Getter
    @Setter
    private String imagen;

    @Getter
    @Setter
    private Integer numero;

    @Getter
    @Setter
    private String tsFechaCreacion;

    private static final long serialVersionUID = 1L;

}
