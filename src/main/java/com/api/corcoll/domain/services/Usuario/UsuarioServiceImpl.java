package com.api.corcoll.domain.services.Usuario;

import com.api.corcoll.domain.data.model.DTOComentario;
import com.api.corcoll.domain.data.model.DTOMensajePrivado;
import com.api.corcoll.domain.data.model.DTOUsuario;
import com.api.corcoll.domain.data.repository.UsuarioDao.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public DTOUsuario obtenerUsuario(int idUsuario) throws SQLException {
        return usuarioDao.obtenerUsuario(idUsuario);
    }


    @Override
    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        return usuarioDao.obtenerUsuarios();
    }


    @Override
    public List<DTOUsuario> obtenerSolicitudesAmistad(int idUsuario) throws SQLException {
        List<DTOUsuario> listadoUsuarios = new ArrayList<>();
        List<DTOUsuario> listaIdUsuariosSolicitudesRecibidas = usuarioDao.obtenerSolicitudesAmistad(idUsuario);
        for (DTOUsuario dtoUsuario : listaIdUsuariosSolicitudesRecibidas) {
            dtoUsuario = usuarioDao.obtenerPerfil(dtoUsuario.getId());
            listadoUsuarios.add(dtoUsuario);
        }
        return listadoUsuarios;
    }

    @Override
    public List<DTOUsuario> obtenerUsuariosAmigos(int idUsuario) throws SQLException {
        List<DTOUsuario> listadoUsuarios = new ArrayList<>();
        List<DTOUsuario> listaIdUsuariosAmigos = usuarioDao.obtenerUsuariosAmigos(idUsuario);
        for (DTOUsuario dtoUsuario : listaIdUsuariosAmigos) {
            dtoUsuario = usuarioDao.obtenerPerfil(dtoUsuario.getId());
            listadoUsuarios.add(dtoUsuario);
        }
        return listadoUsuarios;
    }

    @Override
    public List<DTOUsuario> obtenerUsuariosNoAmigos(int idUsuario) throws SQLException {

        return usuarioDao.obtenerUsuariosNoAmigos(idUsuario);
    }


    @Override
    public DTOUsuario obtenerPerfil(int idUsuario) throws SQLException {
        return usuarioDao.obtenerPerfil(idUsuario);
    }

    @Override
    public DTOUsuario obtenerPerfil(String username) throws SQLException {
        return usuarioDao.obtenerPerfil(username);
    }

    @Override
    public void enviarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        usuarioDao.enviarSolicitudAmistad(idUsuario1, idUsuario2);
    }

    @Override
    public void eliminarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        usuarioDao.eliminarSolicitudAmistad(idUsuario1, idUsuario2);
    }

    @Override
    public void aceptarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        usuarioDao.aceptarSolicitudAmistad(idUsuario1, idUsuario2);
    }

    @Override
    public void eliminarAmigo(int idUsuario1, int idUsuario2) throws SQLException {
        usuarioDao.eliminarAmigo(idUsuario1, idUsuario2);
    }

    @Override
    public void actualizarFotoPerfil(int idUsuario, String foto) throws SQLException {
        usuarioDao.actualizarFotoPerfil(idUsuario, foto);
    }

    @Override
    public DTOUsuario registrarUsuario(DTOUsuario dtoUsuario) throws SQLException {
        String pass = passwordEncoder.encode(dtoUsuario.getPassword());
        dtoUsuario.setPassword(pass);
        return usuarioDao.registrarUsuario(dtoUsuario);
    }

    @Override
    public void reaccionarAColeccion(int idUsuario, int idColeccion) throws SQLException {
        usuarioDao.reaccionarAColeccion(idUsuario, idColeccion);
    }

    @Override
    public void eliminarReaccionAColeccion(int idUsuario, int idColeccion) throws SQLException {
        usuarioDao.eliminarReaccionAColeccion(idUsuario, idColeccion);
    }

    @Override
    public void comentarColeccion(DTOComentario dtoComentario) throws SQLException {
        usuarioDao.comentarColeccion(dtoComentario);
    }

    @Override
    public DTOMensajePrivado enviarMensaje(DTOMensajePrivado dtoMensajePrivado) throws SQLException {
        return usuarioDao.enviarMensaje(dtoMensajePrivado);
    }

    @Override
    public List<DTOMensajePrivado> obtenerConversacion(int idUsuario1, int idUsuario2) throws SQLException {
        List<DTOMensajePrivado> listaConversacion = usuarioDao.obtenerConversacion(idUsuario1, idUsuario2);
        DTOUsuario emisor = usuarioDao.obtenerPerfil(idUsuario1);
        DTOUsuario receptor = usuarioDao.obtenerPerfil(idUsuario2);
        for (DTOMensajePrivado dtoMensajePrivado:listaConversacion) {
            if(dtoMensajePrivado.getIdUsuarioEmisor()==idUsuario1){
                dtoMensajePrivado.setUsuarioEmisor(emisor);
                dtoMensajePrivado.setUsuarioReceptor(receptor);
            }else{
                dtoMensajePrivado.setUsuarioEmisor(receptor);
                dtoMensajePrivado.setUsuarioReceptor(emisor);
            }
        }
        return  listaConversacion;
    }

}
