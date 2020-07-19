package com.api.corcoll.domain.services.Noticia;

import com.api.corcoll.domain.data.model.DTONoticia;
import com.api.corcoll.domain.data.repository.NoticiaDao.INoticiaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NoticiaServiceImpl implements INoticiaService {

    @Autowired
    private INoticiaDao noticiaDao;

    @Override
    public List<DTONoticia> obtenerNoticias() throws SQLException {
        return noticiaDao.obtenerNoticias();
    }
}
