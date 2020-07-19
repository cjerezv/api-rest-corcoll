package com.api.corcoll.domain.data.repository.ColeccionDao;

import com.api.corcoll.database.CorCollConnection;
import com.api.corcoll.domain.data.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColeccionDaoImpl implements IColeccionDao {

    @Autowired
    CorCollConnection con;

    @Override
    public List<DTOColeccion> obtenerColecciones() throws SQLException {
        String query = "select id_coleccion, id_sub_categoria, nombre, descripcion, " +
                "imagen, marca, fecha_creacion " +
                "from coleccion " +
                "order by nombre;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOColeccion> listaColeccion = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOColeccion dtoColeccion = new DTOColeccion();
                dtoColeccion.setId(rs.getInt("id_coleccion"));
                dtoColeccion.setIdSubCategoria(rs.getInt("id_sub_categoria"));
                dtoColeccion.setNombre(rs.getString("nombre"));
                dtoColeccion.setDescripcion(rs.getString("descripcion"));
                dtoColeccion.setImagen(rs.getString("imagen"));
                dtoColeccion.setMarca(rs.getString("marca"));
                dtoColeccion.setTsFechaCreacion(rs.getString("fecha_creacion"));
                DTOSubCategoria dtoSubCategoria = new DTOSubCategoria();
                dtoSubCategoria.setId(rs.getInt("id_sub_categoria"));
                dtoColeccion.setDtoSubCategoria(dtoSubCategoria);
                listaColeccion.add(dtoColeccion);
            }
            System.out.println("Lista de colecciones obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener colecciones");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaColeccion;
    }

    @Override
    public List<DTOColeccion> obtenerColeccionesCategorizadas(int idSubCategoria) throws SQLException {
        String query = "select id_coleccion, id_sub_categoria, nombre, descripcion, " +
                "imagen, marca, fecha_creacion " +
                "from coleccion " +
                "where id_sub_categoria=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOColeccion> listaColeccion = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idSubCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOColeccion dtoColeccion = new DTOColeccion();
                dtoColeccion.setId(rs.getInt("id_coleccion"));
                dtoColeccion.setIdSubCategoria(rs.getInt("id_sub_categoria"));
                dtoColeccion.setNombre(rs.getString("nombre"));
                dtoColeccion.setDescripcion(rs.getString("descripcion"));
                dtoColeccion.setImagen(rs.getString("imagen"));
                dtoColeccion.setMarca(rs.getString("marca"));
                dtoColeccion.setTsFechaCreacion(rs.getString("fecha_creacion"));
                DTOSubCategoria dtoSubCategoria = new DTOSubCategoria();
                dtoSubCategoria.setId(rs.getInt("id_sub_categoria"));
                dtoColeccion.setDtoSubCategoria(dtoSubCategoria);
                listaColeccion.add(dtoColeccion);
            }
            System.out.println("Lista de colecciones categorizadas obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener colecciones categorizadas");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaColeccion;
    }

    @Override
    public DTOColeccion obtenerColeccion(int idColeccion) throws SQLException {
        String query = "select id_coleccion, id_sub_categoria, nombre, " +
                "descripcion, imagen, marca, fecha_creacion " +
                "from coleccion " +
                "where id_coleccion=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOColeccion dtoColeccion = new DTOColeccion();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccion);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoColeccion.setId(rs.getInt("id_coleccion"));
                dtoColeccion.setIdSubCategoria(rs.getInt("id_sub_categoria"));
                dtoColeccion.setNombre(rs.getString("nombre"));
                dtoColeccion.setDescripcion(rs.getString("descripcion"));
                dtoColeccion.setImagen(rs.getString("imagen"));
                dtoColeccion.setMarca(rs.getString("marca"));
                dtoColeccion.setTsFechaCreacion(rs.getString("fecha_creacion"));
                DTOSubCategoria dtoSubCategoria = new DTOSubCategoria();
                dtoSubCategoria.setId(rs.getInt("id_sub_categoria"));
                dtoColeccion.setDtoSubCategoria(dtoSubCategoria);
            }
            System.out.println("Coleccion obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener coleccion");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return dtoColeccion;
    }

    @Override
    public DTOColeccionUsuario obtenerColeccionUsuario(int idColeccionUsuario) throws SQLException {
        String query = "select id_coleccion_usuario, id_usuario, id_coleccion, nombre, " +
                "descripcion, imagen, marca, fecha_creacion " +
                "from coleccion_usuario " +
                "where id_coleccion_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOColeccionUsuario dtoColeccionUsuario = new DTOColeccionUsuario();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccionUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoColeccionUsuario.setId(rs.getInt("id_coleccion_usuario"));
                dtoColeccionUsuario.setIdUsuario(rs.getInt("id_usuario"));
                dtoColeccionUsuario.setIdColeccion(rs.getInt("id_coleccion"));
                dtoColeccionUsuario.setNombre(rs.getString("nombre"));
                dtoColeccionUsuario.setDescripcion(rs.getString("descripcion"));
                dtoColeccionUsuario.setImagen(rs.getString("imagen"));
                dtoColeccionUsuario.setMarca(rs.getString("marca"));
                dtoColeccionUsuario.setTsFechaCreacion(rs.getString("fecha_creacion"));
            }
            System.out.println("Coleccion usuario obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return dtoColeccionUsuario;
    }

    @Override
    public DTOColeccion agregarColeccionUsuario(int idUsuario, DTOColeccion dtoColeccion) throws SQLException {
        String query = "insert into coleccion_usuario (" +
                "id_usuario, id_coleccion, nombre, descripcion, imagen, marca, fecha_creacion) " +
                "values (" +
                "?,?,?,?,?,?,now());";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        try {
            int i = 2;
            ps.setInt(1, idUsuario);
            ps.setInt(i++, dtoColeccion.getId());
            ps.setString(i++, dtoColeccion.getNombre());
            ps.setString(i++, dtoColeccion.getDescripcion());
            ps.setString(i++, dtoColeccion.getImagen());
            ps.setString(i++, dtoColeccion.getMarca());
            ps.executeUpdate();
            System.out.println("Coleccion usurio agregada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
        return dtoColeccion;
    }

    @Override
    public int obtenerTamañoColeccion(int idColeccion) throws SQLException {
        return 0;
    }

    @Override
    public void elminarColeccionUsuario(int idColeccion) throws SQLException {
        String query = "delete from coleccion_usuario " +
                "where id_coleccion = ?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccion);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al eliminar coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public Boolean existeColeccion(int idColeccion) throws SQLException {
        String query = "select * from coleccion where id_coleccion=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("Si existe coleccion");
            } else {
                System.out.println("No existe coleccion");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar si existe coleccion");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return resp;
    }

    @Override
    public Boolean existeColeccionUsuario(int idUsuario, int idColeccion) throws SQLException {
        String query = "select id_coleccion from coleccion_usuario " +
                "where id_usuario=? and id_coleccion=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idColeccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("Si existe coleccion usuario, id usuario :" + idUsuario + ", id coleccion :" + idColeccion);
            } else {
                System.out.println("No existe coleccion usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar si existe coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return resp;
    }

    @Override
    public Boolean existeColeccionUsuarioParaTraerDetalle(int idUsuario, int idColeccionUsuario) throws SQLException {
        String query = "select id_coleccion_usuario from coleccion_usuario " +
                "where id_usuario=? and id_coleccion_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idColeccionUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("Si existe coleccion usuario, id usuario :" + idUsuario + ", id coleccionUsuario :" + idColeccionUsuario);
            } else {
                System.out.println("No existe coleccion usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar si existe coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return resp;
    }

    @Override
    public int obtenerIdColeccionUsuario(int idUsuario, int idColeccion) throws SQLException {
        String query = "select id_coleccion_usuario from coleccion_usuario " +
                "where id_usuario=? and id_coleccion=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer idColeccionUsuario = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idColeccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                idColeccionUsuario = rs.getInt("id_coleccion_usuario");
                System.out.println("Id coleccion usuario obtenido correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id de coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return idColeccionUsuario;
    }

    @Override
    public int obtenerIdColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException {
        String query = "select id_coleccion_usuario from detalle_coleccion_usuario " +
                "where id_detalle_coleccion_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer idColeccionUsuario = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idDetalleColeccionUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                idColeccionUsuario = rs.getInt("id_coleccion_usuario");
                System.out.println("Id coleccion usuario obtenido correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id de coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return idColeccionUsuario;
    }

    @Override
    public DTODetalleColeccionUsuario obtenerDetalleColeccionUsuario(int idDetalleColeccionUsuario) throws SQLException {
        String query = "select id_detalle_coleccion_usuario, id_coleccion_usuario, id_usuario, id_coleccion, nombre, " +
                "descripcion, imagen, numero, fecha_creacion " +
                "from detalle_coleccion_usuario " +
                "where id_detalle_coleccion_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTODetalleColeccionUsuario dtoDetalleColeccionUsuario = new DTODetalleColeccionUsuario();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idDetalleColeccionUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoDetalleColeccionUsuario.setId(rs.getInt("id_detalle_coleccion_usuario"));
                dtoDetalleColeccionUsuario.setIdColeccionUsuario(rs.getInt("id_coleccion_usuario"));
                dtoDetalleColeccionUsuario.setIdUsuario(rs.getInt("id_usuario"));
                dtoDetalleColeccionUsuario.setIdColeccion(rs.getInt("id_coleccion"));
                dtoDetalleColeccionUsuario.setNombre(rs.getString("nombre"));
                dtoDetalleColeccionUsuario.setDescripcion(rs.getString("descripcion"));
                dtoDetalleColeccionUsuario.setImagen(rs.getString("imagen"));
                dtoDetalleColeccionUsuario.setNumero(rs.getInt("numero"));
                dtoDetalleColeccionUsuario.setTsFechaCreacion(rs.getString("fecha_creacion"));
            }
            System.out.println("Detalle coleccion usuario obtenido correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener detalle coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return dtoDetalleColeccionUsuario;
    }

    @Override
    public List<DTODetalleColeccion> obtenerDetallesColeccion(int idColeccion) throws SQLException {
        String query = "select id_detalle_coleccion, id_coleccion, nombre, " +
                "descripcion, imagen, numero, fecha_creacion " +
                "from detalle_coleccion " +
                "where id_coleccion=? " +
                "order by numero;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTODetalleColeccion> listaDetallesColeccion = new ArrayList<DTODetalleColeccion>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccion);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTODetalleColeccion dtoDetalleColeccion = new DTODetalleColeccion();
                dtoDetalleColeccion.setId(rs.getInt("id_detalle_coleccion"));
                dtoDetalleColeccion.setIdColeccion(rs.getInt("id_coleccion"));
                dtoDetalleColeccion.setNombre(rs.getString("nombre"));
                dtoDetalleColeccion.setDescripcion(rs.getString("descripcion"));
                dtoDetalleColeccion.setImagen(rs.getString("imagen"));
                dtoDetalleColeccion.setNumero(rs.getInt("numero"));
                dtoDetalleColeccion.setTsFechaCreacion(rs.getString("fecha_creacion"));
                listaDetallesColeccion.add(dtoDetalleColeccion);
            }
            System.out.println("Detalles coleccion obtenidos correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener detalles coleccion");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaDetallesColeccion;
    }

    @Override
    public List<DTODetalleColeccionUsuario> obtenerDetallesColeccionUsuario(int idColeccionUsuario) throws SQLException {
        String query = "select id_detalle_coleccion_usuario, id_coleccion_usuario, id_usuario, " +
                "id_coleccion, nombre, descripcion, imagen, numero, fecha_creacion " +
                "from detalle_coleccion_usuario " +
                "where id_coleccion_usuario=? " +
                "order by numero;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTODetalleColeccionUsuario> listaDetallesColeccionUsuario = new ArrayList<DTODetalleColeccionUsuario>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccionUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTODetalleColeccionUsuario dtoDetalleColeccionUsuario = new DTODetalleColeccionUsuario();
                dtoDetalleColeccionUsuario.setId(rs.getInt("id_detalle_coleccion_usuario"));
                dtoDetalleColeccionUsuario.setIdColeccionUsuario(rs.getInt("id_coleccion_usuario"));
                dtoDetalleColeccionUsuario.setIdUsuario(rs.getInt("id_usuario"));
                dtoDetalleColeccionUsuario.setIdColeccion(rs.getInt("id_coleccion"));
                dtoDetalleColeccionUsuario.setNombre(rs.getString("nombre"));
                dtoDetalleColeccionUsuario.setDescripcion(rs.getString("descripcion"));
                dtoDetalleColeccionUsuario.setImagen(rs.getString("imagen"));
                dtoDetalleColeccionUsuario.setNumero(rs.getInt("numero"));
                dtoDetalleColeccionUsuario.setTsFechaCreacion(rs.getString("fecha_creacion"));
                listaDetallesColeccionUsuario.add(dtoDetalleColeccionUsuario);
            }
            System.out.println("Detalles coleccion obtenidos correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener detalles coleccion");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaDetallesColeccionUsuario;
    }

    @Override
    public List<DTODetalleColeccionUsuario> obtenerDetallesColeccionUsuarioAmigo(int idColeccionUsuario) throws SQLException {
        return null;
    }

    @Override
    public void insertarDetalleColeccionUsuario(int idUsuario, int idColeccionUsuario, DTODetalleColeccion dtoDetalleColeccion) throws SQLException {
        String query = "insert into detalle_coleccion_usuario (" +
                "id_coleccion_usuario, id_usuario, id_coleccion, " +
                "nombre, descripcion, imagen, numero, fecha_creacion) " +
                "values (" +
                "'" + idColeccionUsuario + "','" + idUsuario + "',?,?,?,?,?,now());";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        try {
            int i = 1;
            ps.setInt(i++, dtoDetalleColeccion.getIdColeccion());
            ps.setString(i++, dtoDetalleColeccion.getNombre());
            ps.setString(i++, dtoDetalleColeccion.getDescripcion());
            ps.setString(i++, dtoDetalleColeccion.getImagen());
            ps.setInt(i++, dtoDetalleColeccion.getNumero());
            ps.executeUpdate();
            System.out.println("Detalle coleccion usurio agregado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar detalle coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
        //return dtoDetalleColeccion;
    }

    @Override
    public List<DTOColeccionUsuario> verMisColecciones(int idUsuario) throws SQLException {
        String query = "select id_coleccion_usuario, id_usuario, id_coleccion, nombre, " +
                "descripcion, imagen, marca, fecha_creacion " +
                "from coleccion_usuario " +
                "where id_usuario=? " +
                "order by fecha_creacion;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOColeccionUsuario> misColecciones = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOColeccionUsuario dtoColeccionUsuario = new DTOColeccionUsuario();
                dtoColeccionUsuario.setId(rs.getInt("id_coleccion_usuario"));
                dtoColeccionUsuario.setIdUsuario(rs.getInt("id_usuario"));
                dtoColeccionUsuario.setIdColeccion(rs.getInt("id_coleccion"));
                dtoColeccionUsuario.setNombre(rs.getString("nombre"));
                dtoColeccionUsuario.setDescripcion(rs.getString("descripcion"));
                dtoColeccionUsuario.setImagen(rs.getString("imagen"));
                dtoColeccionUsuario.setMarca(rs.getString("marca"));
                dtoColeccionUsuario.setTsFechaCreacion(rs.getString("fecha_creacion"));
                misColecciones.add(dtoColeccionUsuario);
            }
            System.out.println("Mis colecciones obtenidas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener mis colecciones");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return misColecciones;
    }

    @Override
    public List<DTOColeccionUsuario> verColeccionesAmigo(int idUsuario) throws SQLException {
        String query = "select id_coleccion_usuario, id_usuario, id_coleccion, nombre, " +
                "descripcion, imagen, marca, fecha_creacion " +
                "from coleccion_usuario " +
                "where id_usuario=? " +
                "order by fecha_creacion;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOColeccionUsuario> coleccionesAmigo = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOColeccionUsuario dtoColeccionUsuario = new DTOColeccionUsuario();
                dtoColeccionUsuario.setId(rs.getInt("id_coleccion_usuario"));
                dtoColeccionUsuario.setIdUsuario(rs.getInt("id_usuario"));
                dtoColeccionUsuario.setIdColeccion(rs.getInt("id_coleccion"));
                dtoColeccionUsuario.setNombre(rs.getString("nombre"));
                dtoColeccionUsuario.setDescripcion(rs.getString("descripcion"));
                dtoColeccionUsuario.setImagen(rs.getString("imagen"));
                dtoColeccionUsuario.setMarca(rs.getString("marca"));
                dtoColeccionUsuario.setTsFechaCreacion(rs.getString("fecha_creacion"));
                coleccionesAmigo.add(dtoColeccionUsuario);
            }
            System.out.println("Colecciones amigo obtenidas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener colecciones amigo");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return coleccionesAmigo;
    }

    @Override
    public void editarDescripcionDetalleColeccionUsuario(int idDetalleColeccionUsuario, String descripcion) throws SQLException {
        String query = "update detalle_coleccion_usuario " +
                "set descripcion = ? " +
                "where id_detalle_coleccion_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, descripcion);
            ps.executeUpdate();
            System.out.println("Descripcion de detalle coleccion usuario actualizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar descripcion de ");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public void actualizarFotoColeccionUsuario(int idColeccionUsuario, String foto) throws SQLException {
        String query = "update coleccion_usuario " +
                "set imagen = ? " +
                "where id_coleccion_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, foto);
            ps.setInt(2, idColeccionUsuario);
            ps.executeUpdate();
            System.out.println("Foto de coleccion usuario actualizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la foto de coleccion usuario");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public void actualizarFotoDetalleColeccionUsuario(int idDetalleColeccionUsuario, String foto) throws SQLException {
        String query = "update detalle_coleccion_usuario " +
                "set imagen = ? " +
                "where id_detalle_coleccion_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, foto);
            ps.setInt(2, idDetalleColeccionUsuario);
            ps.executeUpdate();
            System.out.println("imagen : " + foto);
            System.out.println("iddetallecoleccionusuario : " + idDetalleColeccionUsuario);
            System.out.println("Foto detalle coleccion usuario actualizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la foto detalle coleccion");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    /**
     * FALTA TERMINAR
     *
     * @param idColeccionUsuario
     * @param idUsuario
     * @param comentario
     * @throws SQLException
     */
    @Override
    public void agregarComentarioColeccionUsuarioAmigo(int idColeccionUsuario, int idUsuario, String comentario) throws SQLException {
        String query = "insert into comentario (" +
                "id_coleccion_usuario, id_usuario, comentario, fecha_de_creacion) " +
                "values (" +
                "?,?,?,now());";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        try {
            int i = 1;
            ps.setInt(i++, idColeccionUsuario);
            ps.setInt(i++, idUsuario);
            ps.setString(i++, comentario);
            ps.executeUpdate();
            System.out.println("Comentario a coleccion usurio amigo agregada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar comentario a coleccion usuario amigo");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public List<DTOComentario> obtenerListaComentariosColeccionUsuario(int idColeccionUsuario) throws SQLException {
        String query = "select id_comentario, id_coleccion_usuario,id_usuario, comentario, fecha_de_creacion " +
                "from comentario " +
                "where id_coleccion_usuario=? " +
                "order by fecha_de_creacion;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOComentario> comentariosColeccionUsuario = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccionUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOComentario dtoComentario = new DTOComentario();
                dtoComentario.setId(rs.getInt("id_comentario"));
                dtoComentario.setIdColeccionUsuario(rs.getInt("id_coleccion_usuario"));
                dtoComentario.setIdUsuario(rs.getInt("id_usuario"));
                dtoComentario.setComentario(rs.getString("comentario"));
                dtoComentario.setTsFechaCreacion(rs.getString("fecha_de_creacion"));
                comentariosColeccionUsuario.add(dtoComentario);
            }
            System.out.println("Comentarios de coleccion usuario obtenidas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener comentarios de coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return comentariosColeccionUsuario;
    }

    @Override
    public Integer obtenerCantidadReaccionesColeccion(int idColeccion) throws SQLException {
        String query = "select count(*) cantidadReacciones " +
                "from reaccion " +
                "where id_coleccion_usuario=? ;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cantidadReacciones = 0;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccion);
            rs = ps.executeQuery();

            if (rs.next()) {
                cantidadReacciones = rs.getInt("cantidadReacciones");
            }
            System.out.println("Cantidad de reacciones de coleccion obtenidas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener cantidad de reacciones de coleccion");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return cantidadReacciones;
    }

    @Override
    public Integer obtenerCantidadComentariosColeccion(int idColeccion) throws SQLException {
        String query = "select count(*) cantidadComentarios " +
                "from comentario " +
                "where id_coleccion_usuario=? ;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cantidadComentarios = 0;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccion);
            rs = ps.executeQuery();

            if (rs.next()) {
                cantidadComentarios = rs.getInt("cantidadComentarios");
            }
            System.out.println("Cantidad de comentarios de coleccion obtenidas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener cantidad de comentarios de coleccion");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return cantidadComentarios;
    }

    @Override
    public int obtenerIdColeccionEnMisColecciones(int idColeccion, int idUsuario) throws SQLException {
        String query = "select id_coleccion_usuario " +
                "from coleccion_usuario " +
                "where id_usuario = ? and id_coleccion = ?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idColeccionUsuario = 0;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idColeccion);
            rs = ps.executeQuery();

            if (rs.next()) {
                idColeccionUsuario = rs.getInt("id_coleccion_usuario");
            }
            System.out.println("Id coleccion usuario obtenido correctamente");
        } catch (SQLException e) {
            idColeccionUsuario = 0;
            System.out.println("Error al obtener cantidad de comentarios de coleccionid coleccion usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return idColeccionUsuario;
    }


}
