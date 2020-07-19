package com.api.corcoll.domain.services.Util;

import com.api.corcoll.domain.data.model.DTOUsuario;

import java.sql.SQLException;

public interface IUtilService {

    public Boolean existeSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException;

    public Boolean tengoSolicitudPendiente(int idUsuario1, int idUsuario2) throws SQLException;

    public Boolean existeAmistad(int idUsuario1, int idUsuario2) throws SQLException;

    public Boolean existeColeccion(int idColeccion) throws SQLException;

    public Boolean existeColeccionUsuario(int idUsuario, int idColeccion) throws SQLException;

    public Boolean existeColeccionUsuarioParaTraerDetalle(int idUsuario, int idColeccionUsuario) throws SQLException;

    public Boolean existeReaccion(int idUsuario, int idColeccion) throws SQLException;

    public Boolean existeUsername(String username) throws SQLException;

    public Boolean existeCorreo(String email) throws SQLException;

    public void agregarRolUsuario (int idUsuario) throws SQLException;

    public DTOUsuario obtenerIdUsuario(String username) throws SQLException;

    public int obtenerIdColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException;



}
