package com.api.corcoll.domain.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DTOUsuario implements Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private String username;
    private String fechaNacimiento;
    private Integer edad;
    private String sexo;
    private String email;
    private String password;
    private String fotoPerfil;
    private Integer cantidadColecciones;
    private Integer enabled;
    private String descripcion;
    private List<DTOColeccion> dtoColeccionList;
    private List<DTOAmistad> dtoAmistadList;
    private List<DTOMensajePrivado> dtoMensajePrivadoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getCantidadColecciones() {
        return cantidadColecciones;
    }

    public void setCantidadColecciones(Integer cantidadColecciones) {
        this.cantidadColecciones = cantidadColecciones;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DTOColeccion> getDtoColeccionList() {
        return dtoColeccionList;
    }

    public void setDtoColeccionList(List<DTOColeccion> dtoColeccionList) {
        this.dtoColeccionList = dtoColeccionList;
    }

    public List<DTOAmistad> getDtoAmistadList() {
        return dtoAmistadList;
    }

    public void setDtoAmistadList(List<DTOAmistad> dtoAmistadList) {
        this.dtoAmistadList = dtoAmistadList;
    }

    public List<DTOMensajePrivado> getDtoMensajePrivadoList() {
        return dtoMensajePrivadoList;
    }

    public void setDtoMensajePrivadoList(List<DTOMensajePrivado> dtoMensajePrivadoList) {
        this.dtoMensajePrivadoList = dtoMensajePrivadoList;
    }

    private static final long serialVersionUID = 1L;
}
