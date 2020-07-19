package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOAmistad implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private DTOEstado dtoEstado;

    @Getter
    @Setter
    private DTOUsuario dtoUsuario1;

    @Getter
    @Setter
    private DTOUsuario dtoUsuario2;

    @Getter
    @Setter
    private String tsFechaCreacion;

    private static final long serialVersionUID = 1L;
}
