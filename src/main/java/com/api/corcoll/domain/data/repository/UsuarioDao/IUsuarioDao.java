package com.api.corcoll.domain.data.repository.UsuarioDao;


import com.api.corcoll.domain.data.model.DTOComentario;
import com.api.corcoll.domain.data.model.DTOMensajePrivado;
import com.api.corcoll.domain.data.model.DTOUsuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDao {

    public DTOUsuario obtenerUsuario(int idUsuario) throws SQLException;

    public DTOUsuario obtenerIdUsuario(String username) throws SQLException;

    public List<DTOUsuario> obtenerUsuarios() throws SQLException;

    public List<DTOUsuario> obtenerSolicitudesAmistad(int idUsuario) throws SQLException;

    public List<DTOUsuario> obtenerUsuariosAmigos(int idUsuario) throws SQLException;

    public List<DTOUsuario> obtenerUsuariosNoAmigos(int idUsuario) throws SQLException;

    public void eliminarUsuario(int idUsuario) throws SQLException;

    public DTOUsuario obtenerPerfil(int idUsuario) throws SQLException;

    public DTOUsuario obtenerPerfil(String username) throws SQLException;

    public Boolean existeSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException;

    public Boolean existeAmistad(int idUsuario1, int idUsuario2) throws SQLException;

    public void enviarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException;

    public Boolean tengoSolicitudPendiente(int idUsuario1, int idUsuario2) throws SQLException;

    public void aceptarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException;

    public void eliminarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException;

    public void eliminarAmigo(int idUsuario1, int idUsuario2) throws SQLException;

    public void actualizarFotoPerfil(int idUsuario, String foto) throws SQLException;

    public DTOUsuario registrarUsuario(DTOUsuario dtoUsuario) throws SQLException;

    public void reaccionarAColeccion(int idUsuario, int idColeccion) throws SQLException;

    public Boolean existeReaccion(int idUsuario, int idColeccion) throws SQLException;

    public void eliminarReaccionAColeccion(int idUsuario, int idColeccion) throws SQLException;

    public void comentarColeccion(DTOComentario dtoComentario) throws SQLException;

    public Boolean existeUsername(String username) throws SQLException;

    public Boolean existeCorreo(String email) throws SQLException;

    public void agregarRolUsuario (int idUsuario) throws SQLException;

    public DTOMensajePrivado enviarMensaje(DTOMensajePrivado dtoMensajePrivado) throws SQLException;

    // PENDIENTE
    public List<DTOMensajePrivado> obtenerAmigosConChat(int idUsuario1, int idUsuario2) throws SQLException;

    public List<DTOMensajePrivado> obtenerConversacion(int idUsuario1, int idUsuario2) throws SQLException;


}
