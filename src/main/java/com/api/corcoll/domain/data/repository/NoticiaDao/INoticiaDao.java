package com.api.corcoll.domain.data.repository.NoticiaDao;

import com.api.corcoll.domain.data.model.DTONoticia;

import java.sql.SQLException;
import java.util.List;

public interface INoticiaDao {

    public List<DTONoticia> obtenerNoticias() throws SQLException;

}
