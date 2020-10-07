package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOColeccionUsuario implements Serializable {

    private Integer id;
    private Integer idColeccion;
    private Integer idUsuario;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String marca;
    private String tsFechaCreacion;
    private Boolean reaccion = false;
    private Integer cantidadReacciones;
    private Integer cantidadComentarios;
    private DTOSubCategoria dtoSubCategoria;
    private DTOUsuario dtoUsuario;
    private List<DTODetalleColeccionUsuario> dtoDetalleColeccionUsuarioList;
    private List<DTOComentario> dtoComentarioList;
    private List<DTOReaccion> dtoReaccionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Integer idColeccion) {
        this.idColeccion = idColeccion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public Boolean getReaccion() {
        return reaccion;
    }

    public void setReaccion(Boolean reaccion) {
        this.reaccion = reaccion;
    }

    public Integer getCantidadReacciones() {
        return cantidadReacciones;
    }

    public void setCantidadReacciones(Integer cantidadReacciones) {
        this.cantidadReacciones = cantidadReacciones;
    }

    public Integer getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(Integer cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }

    public DTOSubCategoria getDtoSubCategoria() {
        return dtoSubCategoria;
    }

    public void setDtoSubCategoria(DTOSubCategoria dtoSubCategoria) {
        this.dtoSubCategoria = dtoSubCategoria;
    }

    public DTOUsuario getDtoUsuario() {
        return dtoUsuario;
    }

    public void setDtoUsuario(DTOUsuario dtoUsuario) {
        this.dtoUsuario = dtoUsuario;
    }

    public List<DTODetalleColeccionUsuario> getDtoDetalleColeccionUsuarioList() {
        return dtoDetalleColeccionUsuarioList;
    }

    public void setDtoDetalleColeccionUsuarioList(List<DTODetalleColeccionUsuario> dtoDetalleColeccionUsuarioList) {
        this.dtoDetalleColeccionUsuarioList = dtoDetalleColeccionUsuarioList;
    }

    public List<DTOComentario> getDtoComentarioList() {
        return dtoComentarioList;
    }

    public void setDtoComentarioList(List<DTOComentario> dtoComentarioList) {
        this.dtoComentarioList = dtoComentarioList;
    }

    public List<DTOReaccion> getDtoReaccionList() {
        return dtoReaccionList;
    }

    public void setDtoReaccionList(List<DTOReaccion> dtoReaccionList) {
        this.dtoReaccionList = dtoReaccionList;
    }

    private static final long serialVersionUID = 1L;
}
