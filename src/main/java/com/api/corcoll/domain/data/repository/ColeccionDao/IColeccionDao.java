package com.api.corcoll.domain.data.repository.ColeccionDao;

import com.api.corcoll.domain.data.model.*;
import java.sql.SQLException;
import java.util.List;

public interface IColeccionDao {

    public List<DTOColeccion> obtenerColecciones() throws SQLException;

    public List<DTOColeccion> obtenerColeccionesCategorizadas(int idSubCategoria) throws SQLException;

    public DTOColeccion obtenerColeccion(int idColeccion) throws SQLException;

    public DTOColeccionUsuario obtenerColeccionUsuario(int idColeccionUsuario) throws SQLException;

    public DTOColeccion agregarColeccionUsuario(int idUsuario, DTOColeccion dtoColeccion) throws SQLException;

    public int obtenerTamañoColeccion(int idColeccion) throws SQLException;

    public void elminarColeccionUsuario(int idColeccion) throws SQLException;

    public Boolean existeColeccion(int idColeccion) throws SQLException;

    public Boolean existeColeccionUsuario(int idUsuario, int idColeccion) throws SQLException;

    public Boolean existeColeccionUsuarioParaTraerDetalle(int idUsuario, int idColeccionUsuario) throws SQLException;

    public int obtenerIdColeccionUsuario(int idUsuario, int idColeccion) throws SQLException;

    public int obtenerIdColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException;

    public DTODetalleColeccionUsuario obtenerDetalleColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException;

    public List<DTODetalleColeccion> obtenerDetallesColeccion(int idColeccion) throws SQLException;

    public List<DTODetalleColeccionUsuario> obtenerDetallesColeccionUsuario(int idColeccionUsuario) throws SQLException;

    public List<DTODetalleColeccionUsuario> obtenerDetallesColeccionUsuarioAmigo(int idColeccionUsuario) throws SQLException;

    public void insertarDetalleColeccionUsuario(int idUsuario, int idColeccionUsuario, DTODetalleColeccion dtoDetalleColeccion) throws SQLException;

    public List<DTOColeccionUsuario> verMisColecciones(int idUsuario) throws SQLException;

    public List<DTOColeccionUsuario> verColeccionesAmigo(int idUsuario) throws SQLException;

    public void editarDescripcionDetalleColeccionUsuario(int idUsuario, String descripcion) throws SQLException;

    public void actualizarFotoColeccionUsuario(int idColeccionUsuario, String foto) throws SQLException;

    public void actualizarFotoDetalleColeccionUsuario(int idDetalleColeccionUsuario, String foto) throws SQLException;

    public void agregarComentarioColeccionUsuarioAmigo(int idColeccionUsuario, int idUsuario, String comentario) throws SQLException;

    public List<DTOComentario> obtenerListaComentariosColeccionUsuario (int idColeccionUsuario) throws SQLException;

    public Integer obtenerCantidadReaccionesColeccion (int idColeccion) throws SQLException;

    public Integer obtenerCantidadComentariosColeccion (int idColeccion) throws SQLException;

    public int obtenerIdColeccionEnMisColecciones(int idColeccion, int idUsuario) throws  SQLException;


}

