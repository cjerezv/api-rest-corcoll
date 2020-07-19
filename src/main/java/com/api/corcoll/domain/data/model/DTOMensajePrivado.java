package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOMensajePrivado implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer idUsuarioEmisor;

    @Getter
    @Setter
    private Integer idUsuarioReceptor;

    @Getter
    @Setter
    private String mensaje;

    @Getter
    @Setter
    private String tsFechaCreacion;

    @Getter
    @Setter
    private DTOUsuario usuarioEmisor;

    @Getter
    @Setter
    private DTOUsuario usuarioReceptor;

    // id_estado_emisor - id_estado_receptor

    private static final long serialVersionUID = 1L;

}
