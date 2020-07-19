package com.api.corcoll.domain.data.repository.CategoriaDao;

import com.api.corcoll.database.CorCollConnection;
import com.api.corcoll.domain.data.model.DTOCategoria;
import com.api.corcoll.domain.data.model.DTOSubCategoria;
import com.api.corcoll.domain.data.request.categoria.AgregarSubCategoriaRequest;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaDaoImpl implements ICategoriaDao  {

    @Override
    public DTOCategoria agregarCategoria(DTOCategoria dtoCategoria) throws SQLException {

        String query="insert into categoria " +
                "(nombre, descripcion) " +
                "values " +
                "(?,?);";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = conn.prepareStatement(query);
        int i =1;
        System.out.println("antes del try");
        System.out.println("valor descripcion :" + dtoCategoria.getDescripcion());
        try{
            System.out.println("en el try");
            ps.setString(i++, dtoCategoria.getNombre());
            ps.setString(i++, dtoCategoria.getDescripcion());
            ps.executeUpdate();
            System.out.println("Categoria agregada correctamente");
        }catch (SQLException e) {
            System.out.println("Error al agregar categoria");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            ps.close();
            conn.close();
        }
        return dtoCategoria;
    }

    @Override
    public AgregarSubCategoriaRequest agregarSubCategoria(AgregarSubCategoriaRequest body) throws SQLException {

        String query="insert into sub_categoria " +
                "(id_categoria, nombre, descripcion) " +
                "values " +
                "(?,?,?);";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        int i = 1;
        try{
            ps = conn.prepareStatement(query);
            ps.setInt(i++, body.getIdCategoria());
            ps.setString(i++, body.getNombre());
            ps.setString(i++, body.getDescripcion());
            ps.executeUpdate();
            System.out.println("Sub categoria agregada correctamente");
        }catch (SQLException e) {
            System.out.println("Error al agregar sub categoria");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            ps.close();
            conn.close();
        }
        return body;
    }

    @Override
    public List<DTOCategoria> obtenerCategorias() throws SQLException {
        String query="select id_categoria, nombre, descripcion " +
                "from categoria " +
                "order by id_categoria;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOCategoria> listaCategorias= new ArrayList<>();
        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                DTOCategoria dtoCategoria = new DTOCategoria();
                dtoCategoria.setId(rs.getInt("id_categoria"));
                dtoCategoria.setNombre(rs.getString("nombre"));
                dtoCategoria.setDescripcion(rs.getString("descripcion"));
                listaCategorias.add(dtoCategoria);
            }
            System.out.println("Lista de categorias obtenida correctamente");
        }catch (SQLException e) {
            System.out.println("Error al obtener categorias");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaCategorias;
    }

    @Override
    // método publico que retorna una lista (List) de un objeto (DTOCategoria) y lanza una excepcion del tipo SQLException
    public List<DTOSubCategoria> obtenerSubCategorias() throws SQLException {

        // declaramos un string que contiene la query necesaria para obtener los datos
        String query="select id_sub_categoria, id_categoria, nombre, descripcion " +
                "from sub_categoria " +
                "order by id_sub_categoria;";

        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        // creamos un objeto del tipo de retorno del metodo para almacenar la lista de objetos (sub_categorias)
        List<DTOSubCategoria> listaSubCategorias= new ArrayList<>();
        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                DTOSubCategoria dtoSubCategoria = new DTOSubCategoria();
                dtoSubCategoria.setId(rs.getInt("id_sub_categoria"));
                dtoSubCategoria.setNombre(rs.getString("nombre"));
                dtoSubCategoria.setDescripcion(rs.getString("descripcion"));
                dtoSubCategoria.setIdCategoria(rs.getInt("id_categoria"));
                // DTOCategoria dtoCategoria = new DTOCategoria();
                // dtoCategoria.setId(rs.getInt("id_categoria"));
                // dtoSubCategoria.setDtoCategoria(dtoCategoria);
                listaSubCategorias.add(dtoSubCategoria);
            }
            System.out.println("Lista de sub categorias obtenida correctamente");
        }catch (SQLException e) {
            System.out.println("Error al obtener sub categorias");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaSubCategorias;
    }

    @Override
    public List<DTOSubCategoria> obtenerSubCategoriasPorCategoria(int idCategoria) throws SQLException {

        String query="select id_sub_categoria, id_categoria, nombre, descripcion " +
                "from sub_categoria " +
                "where id_categoria = ? " +
                "order by id_sub_categoria;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTOSubCategoria> listaSubCategorias= new ArrayList<>();
        try{
            ps = conn.prepareStatement(query);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()){
                DTOSubCategoria dtoSubCategoria = new DTOSubCategoria();
                dtoSubCategoria.setId(rs.getInt("id_sub_categoria"));
                dtoSubCategoria.setNombre(rs.getString("nombre"));
                dtoSubCategoria.setDescripcion(rs.getString("descripcion"));
                dtoSubCategoria.setIdCategoria(rs.getInt("id_categoria"));
                // DTOCategoria dtoCategoria = new DTOCategoria();
                // dtoCategoria.setId(rs.getInt("id_categoria"));
                // dtoSubCategoria.setDtoCategoria(dtoCategoria);
                listaSubCategorias.add(dtoSubCategoria);
            }
            System.out.println("Lista de sub categorias filtradas obtenida correctamente");
        }catch (SQLException e) {
            System.out.println("Error al obtener sub categorias filtradas");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaSubCategorias;
    }

    @Override
    public Boolean existeSubCategoria(int idSubCategoria) throws SQLException {
        String query ="select id_sub_categoria from categoria where id_sub_categoria=?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean resp=false;
        try{
            ps = conn.prepareStatement(query);
            ps.setInt(1,idSubCategoria);
            rs = ps.executeQuery();
            if(rs.next()){
                resp=true;
            }
            System.out.println("Consulta si existe amistad realizada con éxito");
        }catch (SQLException e){
            System.out.println("Error al consultar si existe sub categoria");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            rs.close();
            ps.close();
            conn.close();
        }

        return resp;

    }

    @Override
    public DTOCategoria obtenerCategoria(int idCategoria) throws SQLException {
        String query="select id_categoria, nombre, descripcion " +
                "from categoria " +
                "where id_categoria = ?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOCategoria dtoCategoria= new DTOCategoria();
        try{
            ps = conn.prepareStatement(query);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()){
                dtoCategoria.setId(rs.getInt("id_categoria"));
                dtoCategoria.setNombre(rs.getString("nombre"));
                dtoCategoria.setDescripcion(rs.getString("descripcion"));
            }
            System.out.println("Categoria obtenida correctamente");
        }catch (SQLException e) {
            System.out.println("Error al obtener categoria");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return dtoCategoria;
    }

    @Override
    public DTOSubCategoria obtenerSubCategoria(int idSubCategoria) throws SQLException {
        String query="select id_sub_categoria, id_categoria, nombre, descripcion " +
                "from sub_categoria " +
                "where id_sub_categoria = ?;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOSubCategoria dtoSubCategoria= new DTOSubCategoria();
        try{
            ps = conn.prepareStatement(query);
            ps.setInt(1, idSubCategoria);
            rs = ps.executeQuery();
            while (rs.next()){
                dtoSubCategoria.setId(rs.getInt("id_sub_categoria"));
                dtoSubCategoria.setIdCategoria(rs.getInt("id_categoria"));
                dtoSubCategoria.setNombre(rs.getString("nombre"));
                dtoSubCategoria.setDescripcion(rs.getString("descripcion"));
            }
            System.out.println("Subcategoria obtenida correctamente");
        }catch (SQLException e) {
            System.out.println("Error al obtener subcategoria");
            System.out.println("Mensaje error : " +e.getMessage());
            e.printStackTrace();
        }finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return dtoSubCategoria;
    }


}
