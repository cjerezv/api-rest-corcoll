package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTONoticia implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String descripcion;

    @Getter
    @Setter
    private String texto;

    @Getter
    @Setter
    private String foto;

    @Getter
    @Setter
    private String tsFechaCreacion;

    private static final long serialVersionUID = 1L;

}
