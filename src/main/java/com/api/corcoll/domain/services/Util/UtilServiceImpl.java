package com.api.corcoll.domain.services.Util;

import com.api.corcoll.domain.data.model.DTOUsuario;
import com.api.corcoll.domain.data.repository.ColeccionDao.IColeccionDao;
import com.api.corcoll.domain.data.repository.UsuarioDao.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UtilServiceImpl implements IUtilService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IColeccionDao coleccionDao;

    @Override
    public Boolean existeSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        return usuarioDao.existeSolicitudAmistad(idUsuario1, idUsuario2);
    }

    @Override
    public Boolean tengoSolicitudPendiente(int idUsuario1, int idUsuario2) throws SQLException {
        return usuarioDao.tengoSolicitudPendiente(idUsuario1, idUsuario2);
    }

    @Override
    public Boolean existeAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        return usuarioDao.existeAmistad(idUsuario1, idUsuario2);
    }

    @Override
    public Boolean existeColeccion(int idColeccion) throws SQLException {
        return coleccionDao.existeColeccion(idColeccion);
    }

    @Override
    public Boolean existeColeccionUsuario(int idUsuario, int idColeccion) throws SQLException {
        return coleccionDao.existeColeccionUsuario(idUsuario, idColeccion);
    }

    @Override
    public Boolean existeColeccionUsuarioParaTraerDetalle(int idUsuario, int idColeccionUsuario) throws SQLException {
        return coleccionDao.existeColeccionUsuarioParaTraerDetalle(idUsuario, idColeccionUsuario);
    }

    @Override
    public Boolean existeReaccion(int idUsuario, int idColeccion) throws SQLException {
        return usuarioDao.existeReaccion(idUsuario, idColeccion);
    }

    @Override
    public Boolean existeUsername(String username) throws SQLException {
        return usuarioDao.existeUsername(username);
    }

    @Override
    public Boolean existeCorreo(String email) throws SQLException {
        return usuarioDao.existeCorreo(email);
    }

    @Override
    public void agregarRolUsuario(int idUsuario) throws SQLException {
        usuarioDao.agregarRolUsuario(idUsuario);

    }

    @Override
    public DTOUsuario obtenerIdUsuario(String username) throws SQLException {
        return usuarioDao.obtenerIdUsuario(username);
    }

    @Override
    public int obtenerIdColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException {
        return coleccionDao.obtenerIdColeccionUsuario(idDetalleColeccionUsuario);
    }

}
