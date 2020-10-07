package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOColeccion implements Serializable {

    private Integer id;
    private Integer idSubCategoria;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String marca;
    private String tsFechaCreacion;
    private Boolean tengoColeccion;
    private DTOSubCategoria dtoSubCategoria;
    private List<DTODetalleColeccion> dtoDetalleColeccionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(Integer idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTsFechaCreacion() {
        return tsFechaCreacion;
    }

    public void setTsFechaCreacion(String tsFechaCreacion) {
        this.tsFechaCreacion = tsFechaCreacion;
    }

    public Boolean getTengoColeccion() {
        return tengoColeccion;
    }

    public void setTengoColeccion(Boolean tengoColeccion) {
        this.tengoColeccion = tengoColeccion;
    }

    public DTOSubCategoria getDtoSubCategoria() {
        return dtoSubCategoria;
    }

    public void setDtoSubCategoria(DTOSubCategoria dtoSubCategoria) {
        this.dtoSubCategoria = dtoSubCategoria;
    }

    public List<DTODetalleColeccion> getDtoDetalleColeccionList() {
        return dtoDetalleColeccionList;
    }

    public void setDtoDetalleColeccionList(List<DTODetalleColeccion> dtoDetalleColeccionList) {
        this.dtoDetalleColeccionList = dtoDetalleColeccionList;
    }

    private static final long serialVersionUID = 1L;

}
