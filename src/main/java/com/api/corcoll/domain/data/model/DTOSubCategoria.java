package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOSubCategoria implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String descripcion;

    @Getter
    @Setter
    private String tsFechaCreacion;

    @Getter
    @Setter
    private int idCategoria;

    @Getter
    @Setter
    private DTOCategoria dtoCategoria;

    private static final long serialVersionUID = 1L;

}
