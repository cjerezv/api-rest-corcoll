package com.api.corcoll.domain.data.repository.NoticiaDao;

import com.api.corcoll.database.CorCollConnection;
import com.api.corcoll.domain.data.model.DTONoticia;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaDaoImpl implements INoticiaDao {
    @Override
    public List<DTONoticia> obtenerNoticias() throws SQLException {
        String query = "select id_noticia, titulo, descripcion, texto, " +
                "img_portada, fecha_creacion " +
                "from noticia " +
                "order by fecha_creacion desc;";
        Connection conn = CorCollConnection.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DTONoticia> listaNoticia = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DTONoticia dtoNoticia = new DTONoticia();
                dtoNoticia.setId(rs.getInt("id_noticia"));
                dtoNoticia.setTitulo(rs.getString("titulo"));
                dtoNoticia.setDescripcion(rs.getString("descripcion"));
                dtoNoticia.setTexto(rs.getString("texto"));
                dtoNoticia.setFoto(rs.getString("img_portada"));
                dtoNoticia.setTsFechaCreacion(rs.getString("fecha_creacion"));
                listaNoticia.add(dtoNoticia);

            }
            System.out.println("Lista de noticias obtenidas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al obtener noticias");
            System.out.println("Mensaje error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return listaNoticia;
    }
}
