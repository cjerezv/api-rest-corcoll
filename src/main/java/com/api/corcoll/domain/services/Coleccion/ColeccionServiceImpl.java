package com.api.corcoll.domain.services.Coleccion;

import com.api.corcoll.domain.data.model.*;
import com.api.corcoll.domain.data.repository.CategoriaDao.ICategoriaDao;
import com.api.corcoll.domain.data.repository.ColeccionDao.IColeccionDao;
import com.api.corcoll.domain.data.repository.UsuarioDao.IUsuarioDao;
import com.api.corcoll.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColeccionServiceImpl implements IColeccionService {

    @Autowired
    private IColeccionDao coleccionDao;

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private ICategoriaDao categoriaDao;

    @Autowired
    private Utils utils;

    @Override
    public List<DTOColeccion> obtenerColecciones(String idSubCategoria) throws SQLException {
        if (idSubCategoria != null && utils.isNumeric(idSubCategoria) && Integer.parseInt(idSubCategoria) > 0) {
            int idSubCategoriaFiltro = Integer.parseInt(idSubCategoria);
            System.out.println("Obtiene colecciones categorizadas");
            return coleccionDao.obtenerColeccionesCategorizadas(idSubCategoriaFiltro);
        } else {
            System.out.println("Obtiene todas las colecciones");
            List<DTOColeccion> listaColecciones = coleccionDao.obtenerColecciones();
            System.out.println("imprime lista colecciones con los dto de categoria y sub categoria");
            System.out.println(listaColecciones);
            List<DTOColeccion> listaColeccionesResp = new ArrayList<>();

            for (DTOColeccion dtoColeccion: listaColecciones) {
                System.out.println("id primera coleccion: " + dtoColeccion.getId());
                System.out.println("id sub categoria: " + dtoColeccion.getIdSubCategoria());
                DTOSubCategoria dtoSubCategoria = categoriaDao.obtenerSubCategoria(dtoColeccion.getIdSubCategoria());
                System.out.println("id categoria: " + dtoSubCategoria.getIdCategoria());
                DTOCategoria dtoCategoria = categoriaDao.obtenerCategoria(dtoSubCategoria.getIdCategoria());
                dtoSubCategoria.setDtoCategoria(dtoCategoria);
                dtoColeccion.setDtoSubCategoria(dtoSubCategoria);
                listaColeccionesResp.add(dtoColeccion);
            }
            return listaColeccionesResp;
        }
    }

    @Override
    public DTOColeccion obtenerColeccion(int idColeccion) throws SQLException {
        return coleccionDao.obtenerColeccion(idColeccion);
    }

    @Override
    public DTOColeccion obtenerColeccion2(int idColeccion, int idUsuario) throws SQLException {
        DTOColeccion dtoColeccion = coleccionDao.obtenerColeccion(idColeccion);
        System.out.println("en servicio");
        System.out.println(idColeccion);
        System.out.println(idUsuario);
        Boolean tengoColeccion = coleccionDao.existeColeccionUsuario(idUsuario, idColeccion);
        System.out.println(tengoColeccion);
        dtoColeccion.setTengoColeccion(tengoColeccion);
        return dtoColeccion;
    }

    @Override
    public DTOColeccionUsuario obtenerColeccionUsuario(int idColeccionUsuario) throws SQLException {
        DTOColeccionUsuario dtoColeccionUsuario = coleccionDao.obtenerColeccionUsuario(idColeccionUsuario);
        int cantidadComentarios = coleccionDao.obtenerCantidadComentariosColeccion(idColeccionUsuario);
        int cantidadReacciones = coleccionDao.obtenerCantidadReaccionesColeccion(idColeccionUsuario);
        dtoColeccionUsuario.setCantidadComentarios(cantidadComentarios);
        dtoColeccionUsuario.setCantidadReacciones(cantidadReacciones);
        return dtoColeccionUsuario;
    }

    @Override
    public DTOColeccionUsuario agregarColeccionUsuario(int idUsuario, int idColeccion) throws SQLException {
        DTOColeccion dtoColeccion = coleccionDao.obtenerColeccion(idColeccion);
        System.out.println("Coleccion elegida por el usuario con id " + idUsuario + " es : " + dtoColeccion.getNombre());
        DTOColeccionUsuario dtoColeccionUsuario = new DTOColeccionUsuario();

        if (dtoColeccion.getId() != null && dtoColeccion.getId() > 0) {
            try {
                dtoColeccion = coleccionDao.agregarColeccionUsuario(idUsuario, dtoColeccion);
                dtoColeccionUsuario.setId(dtoColeccion.getId());
                dtoColeccionUsuario.setNombre(dtoColeccion.getNombre());
                dtoColeccionUsuario.setDescripcion(dtoColeccion.getDescripcion());
                dtoColeccionUsuario.setImagen(dtoColeccion.getImagen());
                dtoColeccionUsuario.setMarca(dtoColeccion.getMarca());
            } catch (Exception e) {
                e.getCause();
                return null;
            }
        }
        List<DTODetalleColeccion> listaDetalleColeccion = new ArrayList<>();
        listaDetalleColeccion = coleccionDao.obtenerDetallesColeccion(idColeccion);
        Integer idColeccionUsuario = coleccionDao.obtenerIdColeccionUsuario(idUsuario, idColeccion);
        try {
            for (DTODetalleColeccion dtoDetalleColeccion : listaDetalleColeccion) {
                coleccionDao.insertarDetalleColeccionUsuario(idUsuario, idColeccionUsuario, dtoDetalleColeccion);
            }
        } catch (Exception e) {
            e.getCause();
            return null;
        }
        return dtoColeccionUsuario;
    }

    @Override
    public int obtenerTamañoColeccion(int idColeccion) throws SQLException {
        return 0;
    }

    @Override
    public void elminarColeccionUsuario(int idColeccion) throws SQLException {

    }


    @Override
    public List<DTODetalleColeccion> obtenerDetallesColeccion(int idColeccion) throws SQLException {
        return coleccionDao.obtenerDetallesColeccion(idColeccion);
    }

    @Override
    public DTODetalleColeccionUsuario obtenerDetalleColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException {
        return coleccionDao.obtenerDetalleColeccionUsuario(idDetalleColeccionUsuario);
    }

    @Override
    public List<DTODetalleColeccionUsuario> obtenerDetallesColeccionUsuario(int idColeccionUsuario) throws SQLException {
        return coleccionDao.obtenerDetallesColeccionUsuario(idColeccionUsuario);
    }

    @Override
    public List<DTOColeccionUsuario> verMisColecciones(int idUsuario) throws SQLException {
        return coleccionDao.verMisColecciones(idUsuario);
    }

    @Override
    public List<DTOColeccionUsuario> verColeccionesAmigo(int idUsuario) throws SQLException {
        return coleccionDao.verColeccionesAmigo(idUsuario);
    }

    @Override
    public void actualizarFotoColeccionUsuario(int idColeccionUsuario, String foto) throws SQLException {
        coleccionDao.actualizarFotoColeccionUsuario(idColeccionUsuario, foto);
    }

    @Override
    public void actualizarFotoDetalleColeccionUsuario(int idDetalleColeccionUsuario, String foto) throws SQLException {
        coleccionDao.actualizarFotoDetalleColeccionUsuario(idDetalleColeccionUsuario, foto);
    }

    @Override
    public void editarDescripcionDetalleColeccionUsuario(int idDetalleColeccionUsuario, String descripcion) throws SQLException {
        coleccionDao.editarDescripcionDetalleColeccionUsuario(idDetalleColeccionUsuario, descripcion);
    }

    @Override
    public void agregarComentarioColeccionUsuarioAmigo(int idColeccionUsuario, int idUsuario, String comentario) throws SQLException {
        coleccionDao.agregarComentarioColeccionUsuarioAmigo(idColeccionUsuario, idUsuario, comentario);
    }

    @Override
    public List<DTOComentario> obtenerListaComentariosColeccionUsuario(int idColeccionUsuario) throws SQLException {
        List<DTOComentario> listaComentarios = coleccionDao.obtenerListaComentariosColeccionUsuario(idColeccionUsuario);
        for (DTOComentario dtoComentario : listaComentarios) {
            DTOUsuario usuario = usuarioDao.obtenerPerfil(dtoComentario.getIdUsuario());
            dtoComentario.setUsuario(usuario);
        }
        return listaComentarios;
    }

    @Override
    public int obtenerIdColeccionEnMisColecciones(int idColeccion, int idUsuario) throws SQLException {
        return coleccionDao.obtenerIdColeccionEnMisColecciones(idColeccion,idUsuario);
    }


}
