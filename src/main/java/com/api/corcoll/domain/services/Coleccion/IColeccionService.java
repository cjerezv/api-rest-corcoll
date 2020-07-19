package com.api.corcoll.domain.services.Coleccion;

import com.api.corcoll.domain.data.model.*;
import java.sql.SQLException;
import java.util.List;

public interface IColeccionService {

    public List<DTOColeccion> obtenerColecciones(String idSubCategoria) throws SQLException;

    public DTOColeccion obtenerColeccion(int idColeccion) throws SQLException;

    public DTOColeccion obtenerColeccion2(int idColeccion, int idUsuario) throws SQLException;

    public DTOColeccionUsuario obtenerColeccionUsuario(int idColeccionUsuario) throws SQLException;

    public DTOColeccionUsuario agregarColeccionUsuario(int idUsuario, int idColeccion) throws SQLException;

    public int obtenerTamañoColeccion(int idColeccion) throws SQLException;

    public void elminarColeccionUsuario(int idColeccion) throws SQLException;

    public List<DTODetalleColeccion> obtenerDetallesColeccion(int idColeccion) throws SQLException;

    public DTODetalleColeccionUsuario obtenerDetalleColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException;

    public List<DTODetalleColeccionUsuario> obtenerDetallesColeccionUsuario(int idColeccionUsuario) throws SQLException;

    public List<DTOColeccionUsuario> verMisColecciones(int idUsuario) throws SQLException;

    public List<DTOColeccionUsuario> verColeccionesAmigo(int idUsuario) throws SQLException;

    public void actualizarFotoColeccionUsuario(int idColeccionUsuario, String foto) throws SQLException;

    public void actualizarFotoDetalleColeccionUsuario(int idDetalleColeccionUsuario, String foto) throws SQLException;

    public void editarDescripcionDetalleColeccionUsuario(int idDetalleColeccionUsuario, String descripcion) throws SQLException;

    public void agregarComentarioColeccionUsuarioAmigo(int idColeccionUsuario, int idUsuario, String comentario) throws SQLException;

    public List<DTOComentario> obtenerListaComentariosColeccionUsuario(int idColeccionUsuario) throws SQLException;

    public int obtenerIdColeccionEnMisColecciones(int idColeccion, int idUsuario) throws  SQLException;




}


