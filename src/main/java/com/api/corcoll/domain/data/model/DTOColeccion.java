package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOColeccion implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer idSubCategoria;

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
    private String marca;

    @Getter
    @Setter
    private String tsFechaCreacion;

    @Getter
    @Setter
    private Boolean tengoColeccion;

    @Getter
    @Setter
    private DTOSubCategoria dtoSubCategoria;

    @Getter
    @Setter
    private List<DTODetalleColeccion> dtoDetalleColeccionList;

    private static final long serialVersionUID = 1L;

}
