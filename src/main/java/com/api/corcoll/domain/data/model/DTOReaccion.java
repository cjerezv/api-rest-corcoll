package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOReaccion implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String tsFechaCreacion;

    @Getter
    @Setter
    private DTOUsuario dtoUsuario;

    @Getter
    @Setter
    private DTOColeccion dtoColeccion;

    private static final long serialVersionUID = 1L;

}
