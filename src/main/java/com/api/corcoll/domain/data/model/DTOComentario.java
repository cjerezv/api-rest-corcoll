package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOComentario implements Serializable {

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
    private Integer idUsuario2;

    @Getter
    @Setter
    private String comentario;

    @Getter
    @Setter
    private String tsFechaCreacion;

    @Getter
    @Setter
    private DTOUsuario usuario;

    private static final long serialVersionUID = 1L;
}
