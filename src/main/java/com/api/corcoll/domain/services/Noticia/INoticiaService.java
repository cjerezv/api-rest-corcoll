package com.api.corcoll.domain.services.Noticia;

import com.api.corcoll.domain.data.model.DTONoticia;

import java.sql.SQLException;
import java.util.List;

public interface INoticiaService {
    public List<DTONoticia> obtenerNoticias() throws SQLException;
}
