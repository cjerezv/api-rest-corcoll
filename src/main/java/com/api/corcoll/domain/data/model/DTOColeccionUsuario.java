package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOColeccionUsuario implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer idColeccion;

    @Getter
    @Setter
    private Integer idUsuario;

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
    private Boolean reaccion = false;

    @Getter
    @Setter
    private Integer cantidadReacciones;

    @Getter
    @Setter
    private Integer cantidadComentarios;

    @Getter
    @Setter
    private DTOSubCategoria dtoSubCategoria;

    @Getter
    @Setter
    private DTOUsuario dtoUsuario;

    @Getter
    @Setter
    private List<DTODetalleColeccionUsuario> dtoDetalleColeccionUsuarioList;

    @Getter
    @Setter
    private List<DTOComentario> dtoComentarioList;

    @Getter
    @Setter
    private List<DTOReaccion> dtoReaccionList;

    private static final long serialVersionUID = 1L;
}
