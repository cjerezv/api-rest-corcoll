package com.api.corcoll.domain.data.repository.UsuarioDao;

import com.api.corcoll.database.CorCollConnection;
import com.api.corcoll.domain.data.model.DTOAmistad;
import com.api.corcoll.domain.data.model.DTOComentario;
import com.api.corcoll.domain.data.model.DTOMensajePrivado;
import com.api.corcoll.domain.data.model.DTOUsuario;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public DTOUsuario obtenerUsuario(int idUsuario) throws SQLException {
        String query = "select id_usuario, nombre, apellido, username, " +
                "fecha_nacimiento, edad, sexo, email, foto_perfil, " +
                "descripcion " +
                "from usuario " +
                "where id_usuario='" + idUsuario + "';";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOUsuario dtoUsuario = new DTOUsuario();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoUsuario.setId(rs.getInt("id_usuario"));
                dtoUsuario.setNombre(rs.getString("nombre"));
                dtoUsuario.setApellido(rs.getString("apellido"));
                dtoUsuario.setUsername(rs.getString("username"));
                dtoUsuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                dtoUsuario.setEdad(rs.getInt("edad"));
                dtoUsuario.setSexo(rs.getString("sexo"));
                dtoUsuario.setEmail(rs.getString("email"));
                dtoUsuario.setFotoPerfil(rs.getString("foto_perfil"));
                dtoUsuario.setDescripcion(rs.getString("descripcion"));
            }
            System.out.println("Usuario obtenido correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return dtoUsuario;
    }

    @Override
    public DTOUsuario obtenerIdUsuario(String username) throws SQLException {
        String query = "select id_usuario " +
                "from usuario " +
                "where username ='" + username + "';";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOUsuario dtoUsuario = new DTOUsuario();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoUsuario.setId(rs.getInt("id_usuario"));
            }
            System.out.println("Id usuario obtenido correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener id usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return dtoUsuario;
    }

    @Override
    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        String query = "select id_usuario, nombre, apellido, username, " +
                "fecha_nacimiento, edad, sexo, email, foto_perfil, " +
                "descripcion " +
                "from usuario;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOUsuario> listaUsuarios = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOUsuario dtoUsuario = new DTOUsuario();
                dtoUsuario.setId(rs.getInt("id_usuario"));
                dtoUsuario.setNombre(rs.getString("nombre"));
                dtoUsuario.setApellido(rs.getString("apellido"));
                dtoUsuario.setUsername(rs.getString("username"));
                dtoUsuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                dtoUsuario.setEdad(rs.getInt("edad"));
                dtoUsuario.setSexo(rs.getString("sexo"));
                dtoUsuario.setEmail(rs.getString("email"));
                dtoUsuario.setFotoPerfil(rs.getString("foto_perfil"));
                dtoUsuario.setDescripcion(rs.getString("descripcion"));
                listaUsuarios.add(dtoUsuario);
            }
            System.out.println("Lista de usuarios obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaUsuarios;
    }

    @Override
    public List<DTOUsuario> obtenerSolicitudesAmistad(int idUsuario) throws SQLException {
        String query = "select id_usuario_1 id " +
                "from amistad " +
                "where id_usuario_2=? and id_estado_amistad=1;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOUsuario> listaUsuarios = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOUsuario dtoUsuario = new DTOUsuario();
                dtoUsuario.setId(rs.getInt("id"));
                listaUsuarios.add(dtoUsuario);
            }
            System.out.println("Lista de usuarios que han enviado solicitud de amistad obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios que han enviado solicitud de amistad");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaUsuarios;
    }

    @Override
    public List<DTOUsuario> obtenerUsuariosAmigos(int idUsuario) throws SQLException {
        String query = "select id_usuario_1 id " +
                "from amistad " +
                "where id_usuario_2=? and id_estado_amistad=2  \n" +
                "union all select id_usuario_2 id \n" +
                "from amistad " +
                "where id_usuario_1=? and id_estado_amistad=2;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOUsuario> listaUsuarios = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOUsuario dtoUsuario = new DTOUsuario();
                dtoUsuario.setId(rs.getInt("id"));
                listaUsuarios.add(dtoUsuario);
            }
            System.out.println("Lista de usuarios amigos obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios amigos");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaUsuarios;
    }

    @Override
    public List<DTOUsuario> obtenerUsuariosNoAmigos(int idUsuario) throws SQLException {

        /*String query = "select u.id_usuario, u.username, u.nombre, u.apellido " +
                "from usuario u " +
                "where u.id_usuario not in ( " +
                "select a.id_usuario_2 id_us " +
                "from amistad a " +
                "where a.id_usuario_1=? " +
                "and id_estado_amistad=2 " +
                "union " +
                "select a.id_usuario_1 id_us " +
                "from amistad a " +
                "where a.id_usuario_2=? " +
                "and id_estado_amistad=2 " +
                "group by id_us) " +
                "and u.id_usuario!=?;";*/

        String query = "select u.id_usuario, u.username, u.nombre, u.apellido, u.foto_perfil \n" +
                "                from usuario u \n" +
                "                where u.id_usuario not in ( \n" +
                "                                select a.id_usuario_2 id_us \n" +
                "                                from amistad a \n" +
                "                                where a.id_usuario_1=? \n" +
                "                                and id_estado_amistad in (2,1)\n" +
                "                                union \n" +
                "                                select a.id_usuario_1 id_us \n" +
                "                                from amistad a \n" +
                "                                where a.id_usuario_2=? \n" +
                "                                and id_estado_amistad in (2,1)\n" +
                "                                group by id_us) \n" +
                "                and u.id_usuario!=?;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOUsuario> listaUsuarios = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idUsuario);
            ps.setInt(3, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOUsuario dtoUsuario = new DTOUsuario();
                dtoUsuario.setId(rs.getInt("id_usuario"));
                dtoUsuario.setNombre(rs.getString("nombre"));
                dtoUsuario.setApellido(rs.getString("apellido"));
                dtoUsuario.setUsername(rs.getString("username"));
                dtoUsuario.setFotoPerfil(rs.getString("foto_perfil"));
                listaUsuarios.add(dtoUsuario);
            }
            System.out.println("Lista de usuarios no amigos obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios no amigos");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaUsuarios;
    }

    @Override
    public void eliminarUsuario(int idUsuario) throws SQLException {
        String sql = "delete from usuario "
                + "where id_usuario = ?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.execute();
            System.out.println("Usuario eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public DTOUsuario obtenerPerfil(int idUsuario) throws SQLException {
        String query = "select id_usuario, nombre, apellido, username, " +
                "email, fecha_nacimiento, edad, sexo, foto_perfil, " +
                "descripcion " +
                "from usuario " +
                "where id_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOUsuario perfilUsuario = new DTOUsuario();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                perfilUsuario.setId(rs.getInt("id_usuario"));
                perfilUsuario.setNombre(rs.getString("nombre"));
                perfilUsuario.setApellido(rs.getString("apellido"));
                perfilUsuario.setUsername(rs.getString("username"));
                perfilUsuario.setEmail(rs.getString("email"));
                perfilUsuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                perfilUsuario.setEdad(rs.getInt("edad"));
                perfilUsuario.setSexo(rs.getString("sexo"));
                perfilUsuario.setFotoPerfil(rs.getString("foto_perfil"));
                perfilUsuario.setDescripcion(rs.getString("descripcion"));
            }
            System.out.println("Datos perfil usuario obtenidos correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener perfil de usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return perfilUsuario;
    }

    @Override
    public DTOUsuario obtenerPerfil(String username) throws SQLException {
        String query = "select id_usuario, nombre, apellido, username, " +
                "email, fecha_nacimiento, edad, sexo, foto_perfil, " +
                "descripcion " +
                "from usuario " +
                "where username=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOUsuario perfilUsuario = new DTOUsuario();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                perfilUsuario.setId(rs.getInt("id_usuario"));
                perfilUsuario.setNombre(rs.getString("nombre"));
                perfilUsuario.setApellido(rs.getString("apellido"));
                perfilUsuario.setUsername(rs.getString("username"));
                perfilUsuario.setEmail(rs.getString("email"));
                perfilUsuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                perfilUsuario.setEdad(rs.getInt("edad"));
                perfilUsuario.setSexo(rs.getString("sexo"));
                perfilUsuario.setFotoPerfil(rs.getString("foto_perfil"));
                perfilUsuario.setDescripcion(rs.getString("descripcion"));
            }
            System.out.println("Datos perfil usuario obtenidos correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener perfil de usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return perfilUsuario;
    }


    @Override
    public Boolean existeSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        String query = "select * from amistad " +
                "where (" +
                "id_usuario_1=? and id_usuario_2=? and id_estado_amistad=1) " +
                "or (" +
                "id_usuario_1=? and id_usuario_2=? and id_estado_amistad=1);";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario1);
            ps.setInt(2, idUsuario2);
            ps.setInt(3, idUsuario2);
            ps.setInt(4, idUsuario1);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("Ya se envió una solicitud de amistad");
            } else {
                System.out.println("No se ha enviado una solicitud de amistad");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar relación entre los usuarios");
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
    public Boolean existeAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        String query = "select * from amistad " +
                "where (" +
                "id_usuario_1=? and id_usuario_2=? and id_estado_amistad=2) " +
                "or (" +
                "id_usuario_1=? and id_usuario_2=? and id_estado_amistad=2);";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario1);
            ps.setInt(2, idUsuario2);
            ps.setInt(3, idUsuario2);
            ps.setInt(4, idUsuario1);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("Ambos usuarios ya son amigos");
            } else {
                System.out.println("No existe relacion de amistad entre los usuarios");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar relación entre los usuarios");
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
    public void enviarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        String query = "insert into amistad " +
                "(id_estado_amistad, id_usuario_1, id_usuario_2, fecha_creacion ) " +
                "values " +
                "(1,?,?,now());";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        DTOAmistad dtoAmistad = null;
        int i = 1;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(i++, idUsuario1);
            ps.setInt(i++, idUsuario2);
            ps.executeUpdate();
            System.out.println("Solicitud de amistad enviada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar sub categoria");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public Boolean tengoSolicitudPendiente(int idUsuario1, int idUsuario2) throws SQLException {
        String query = "select * from amistad where id_usuario_1=? and id_usuario_2=? and id_estado_amistad=1;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario2);
            ps.setInt(2, idUsuario1);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("Si tiene una solicitud del usuario");
            } else {
                System.out.println("No tiene una solicitud del usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar si existe solicitud pendiente");
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
    public void aceptarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        //String sql = "SET SQL_SAFE_UPDATES=0;";
        String query = "update amistad " +
                "set id_estado_amistad=2 " +
                "where (id_usuario_1=? and id_usuario_2=?) or (id_usuario_1=? and id_usuario_2=?);";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        //PreparedStatement ps2 = null;
        try {
            ps = conn.prepareStatement(query);
            //ps2 = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario1);
            ps.setInt(2, idUsuario2);
            ps.setInt(3, idUsuario2);
            ps.setInt(4, idUsuario1);
            //ps2.executeUpdate();
            ps.executeUpdate();
            System.out.println("Solicitud de amistad rechazada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al rechazar solicitud de amistad");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public void eliminarSolicitudAmistad(int idUsuario1, int idUsuario2) throws SQLException {
        //String sql = "SET SQL_SAFE_UPDATES=0;";
        String query = "delete from amistad where (id_usuario_1=? and id_usuario_2=? and id_estado_amistad=1) or (id_usuario_1=? and id_usuario_2=? and id_estado_amistad=1);";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        //PreparedStatement ps2 = null;
        try {
            ps = conn.prepareStatement(query);
            //ps2 = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario1);
            ps.setInt(2, idUsuario2);
            ps.setInt(3, idUsuario2);
            ps.setInt(4, idUsuario1);
            //ps2.executeUpdate();
            ps.executeUpdate();
            System.out.println("Solicitud de amistad rechazada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al rechazar solicitud de amistad");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public void eliminarAmigo(int idUsuario1, int idUsuario2) throws SQLException {
        //String sql = "SET SQL_SAFE_UPDATES=0;";
        String query = "delete from amistad " +
                "where (" +
                "id_usuario_1=? and id_usuario_2=?" +
                ") or (" +
                "id_usuario_1=? and id_usuario_2=?" +
                ");";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        //PreparedStatement ps2 = null;
        try {
            ps = conn.prepareStatement(query);
            //ps2 = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario1);
            ps.setInt(2, idUsuario2);
            ps.setInt(3, idUsuario2);
            ps.setInt(4, idUsuario1);
            //ps2.executeUpdate();
            ps.executeUpdate();
            System.out.println("Amigo eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al eliminar amigo");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public void actualizarFotoPerfil(int idUsuario, String foto) throws SQLException {
        String query = "update usuario " +
                "set foto_perfil = ? " +
                "where id_usuario=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, foto);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            System.out.println("Foto actualizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la foto");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public DTOUsuario registrarUsuario(DTOUsuario dtoUsuario) throws SQLException {
        String query = "insert into usuario (" +
                "nombre, apellido, username, fecha_nacimiento, edad, " +
                "sexo, email, password, foto_perfil, enabled" +
                ") values (" +
                "?,?,?,?,?," +
                "?,?,?,'profile.png',1" +
                ");";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        int i = 1;
        try {
            ps.setString(i++, dtoUsuario.getNombre());
            ps.setString(i++, dtoUsuario.getApellido());
            ps.setString(i++, dtoUsuario.getUsername());
            ps.setString(i++, dtoUsuario.getFechaNacimiento());
            ps.setInt(i++, dtoUsuario.getEdad());
            ps.setString(i++, dtoUsuario.getSexo());
            ps.setString(i++, dtoUsuario.getEmail());
            ps.setString(i++, dtoUsuario.getPassword());
            ps.executeUpdate();
            System.out.println("Usuario " + dtoUsuario.getUsername() + " agregado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
        return dtoUsuario;
    }

    @Override
    public void reaccionarAColeccion(int idUsuario, int idColeccion) throws SQLException {

        String query = "insert into reaccion (" +
                "id_coleccion_usuario, id_usuario, fecha_de_creacion" +
                ") values (" +
                "?,?,now() );";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idColeccion);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            System.out.println("Reaccion agregada con exito");
        } catch (SQLException e) {
            System.out.println("Error al agregar reaccion");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public Boolean existeReaccion(int idUsuario, int idColeccion) throws SQLException {
        String query = "select id_reaccion from reaccion " +
                "where id_usuario = ? and id_coleccion_usuario = ?;";
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
                System.out.println("Ya existe reaccion de id usuario " + idUsuario +
                        " a la coleccion de id " + idColeccion);
            } else {
                System.out.println("El usuario " + idUsuario +
                        " aún no tiene reacción a la coleecion con id " + idColeccion);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar existencia de reaccion");
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
    public void eliminarReaccionAColeccion(int idUsuario, int idColeccion) throws SQLException {

        String query = "delete from reaccion " +
                "where id_usuario = ? and id_coleccion_usuario = ?;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idColeccion);
            ps.execute();
            System.out.println("Reaccion eliminada con exito");
        } catch (SQLException e) {
            System.out.println("Error al eliminar reaccion");
            System.out.println("El error es : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }

    @Override
    public void comentarColeccion(DTOComentario dtoComentario) throws SQLException {
        String query = " insert into comentario (" +
                "id_coleccion_usuario, id_usuario, comentario, fecha_de_creacion" +
                ") values (" +
                "?,?,?,now() ) ;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        int i = 1;
        try {
            ps.setInt(i++, dtoComentario.getIdColeccionUsuario());
            ps.setInt(i++, dtoComentario.getIdUsuario());
            ps.setString(i++, dtoComentario.getComentario());
            ps.executeUpdate();
            System.out.println("Usuario con id " + dtoComentario.getIdUsuario() + " ha comentado la coleccion usuario id " + dtoComentario.getIdColeccionUsuario());
        } catch (SQLException e) {
            System.out.println("Error al comentar la coleccion de usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }

    }

    @Override
    public Boolean existeUsername(String username) throws SQLException {
        String query = "select username from usuario " +
                "where username = ?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("Username " + username + " ya está en uso");
            } else {
                System.out.println("El username " + username + " no ha sido utilizado");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar existencia de username");
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
    public Boolean existeCorreo(String email) throws SQLException {
        String query = "select email from usuario " +
                "where email = ?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp = false;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
                System.out.println("El email " + email + " ya está en uso");
            } else {
                System.out.println("El email " + email + " no ha sido utilizado");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar uso de email");
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
    public void agregarRolUsuario(int idUsuario) throws SQLException {

        String query = "insert into usuario_rol (" +
                "id_usuario, id_rol" +
                ") values (" +
                "?,2" +
                ");";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        try {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            System.out.println("Usuario con id " + idUsuario + " ahora tiene rol de usuario");
        } catch (SQLException e) {
            System.out.println("Error al agregar rol al usuario");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }

    }

    @Override
    public DTOMensajePrivado enviarMensaje(DTOMensajePrivado dtoMensajePrivado) throws SQLException {
        String query = "insert into mensaje_privado (" +
                "id_usuario_emisor, id_usuario_receptor, mensaje, fecha_creacion) " +
                "values (" +
                "?,?,?,now()) ;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        try {
            int i = 1;
            ps.setInt(i++, dtoMensajePrivado.getIdUsuarioEmisor());
            ps.setInt(i++, dtoMensajePrivado.getIdUsuarioReceptor());
            ps.setString(i++, dtoMensajePrivado.getMensaje());
            ps.executeUpdate();
            System.out.println("Mensaje enviado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al enviar mensaje");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
        return dtoMensajePrivado;
    }

    // PENDIENTE
    @Override
    public List<DTOMensajePrivado> obtenerAmigosConChat(int idUsuario1, int idUsuario2) throws SQLException {
        String query = "select distinct  id_usuario_emisor, id_usuario_receptor, mensaje, fecha_creacion " +
                "from mensaje_privado " +
                "where id_usuario_emisor = ? and id_usuario_receptor = ? " +
                "union all " +
                "select id_usuario_emisor, id_usuario_receptor, mensaje, fecha_creacion " +
                "from mensaje_privado " +
                "where id_usuario_emisor = ? and id_usuario_receptor = ? " +
                "order by fecha_creacion;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOMensajePrivado> listaConversacion = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario1);
            ps.setInt(2, idUsuario2);
            ps.setInt(3, idUsuario2);
            ps.setInt(4, idUsuario1);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOMensajePrivado dtoMensajePrivado = new DTOMensajePrivado();
                dtoMensajePrivado.setIdUsuarioEmisor(rs.getInt("id_usuario_emisor"));
                dtoMensajePrivado.setIdUsuarioReceptor(rs.getInt("id_usuario_receptor"));
                dtoMensajePrivado.setMensaje(rs.getString("mensaje"));
                dtoMensajePrivado.setTsFechaCreacion(rs.getString("fecha_creacion"));
                listaConversacion.add(dtoMensajePrivado);
            }
            System.out.println("Conversación obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener conversación");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaConversacion;
    }

    @Override
    public List<DTOMensajePrivado> obtenerConversacion(int idUsuario1, int idUsuario2) throws SQLException {
        String query = "select id_usuario_emisor, id_usuario_receptor, mensaje, fecha_creacion " +
                "from mensaje_privado " +
                "where id_usuario_emisor = ? and id_usuario_receptor = ? " +
                "union all " +
                "select id_usuario_emisor, id_usuario_receptor, mensaje, fecha_creacion " +
                "from mensaje_privado " +
                "where id_usuario_emisor = ? and id_usuario_receptor = ? " +
                "order by fecha_creacion;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOMensajePrivado> listaConversacion = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuario1);
            ps.setInt(2, idUsuario2);
            ps.setInt(3, idUsuario2);
            ps.setInt(4, idUsuario1);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTOMensajePrivado dtoMensajePrivado = new DTOMensajePrivado();
                dtoMensajePrivado.setIdUsuarioEmisor(rs.getInt("id_usuario_emisor"));
                dtoMensajePrivado.setIdUsuarioReceptor(rs.getInt("id_usuario_receptor"));
                dtoMensajePrivado.setMensaje(rs.getString("mensaje"));
                dtoMensajePrivado.setTsFechaCreacion(rs.getString("fecha_creacion"));
                listaConversacion.add(dtoMensajePrivado);
            }
            System.out.println("Conversación obtenida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener conversación");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaConversacion;
    }


}
