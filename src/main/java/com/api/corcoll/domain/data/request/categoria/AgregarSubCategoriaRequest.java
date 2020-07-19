package com.api.corcoll.domain.data.request.categoria;

import java.io.Serializable;

public class AgregarSubCategoriaRequest implements Serializable {

    private String nombre;
    private String descripcion;
    private Integer idCategoria;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    private static final long serialVersionUID = 1L;
}
