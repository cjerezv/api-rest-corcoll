package com.api.corcoll.domain.services.Categoria;

import com.api.corcoll.domain.data.model.DTOCategoria;
import com.api.corcoll.domain.data.model.DTOSubCategoria;
import com.api.corcoll.domain.data.repository.CategoriaDao.ICategoriaDao;
import com.api.corcoll.domain.data.request.categoria.AgregarSubCategoriaRequest;
import com.api.corcoll.domain.data.response.categoria.AgregarSubCategoriaResponse;
import com.api.corcoll.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaSevice {

    @Autowired
    private ICategoriaDao categoriaDao;

    @Autowired
    private Utils utils;

    @Override
    public DTOCategoria agregarCategoria(DTOCategoria dtoCategoria) throws SQLException {
        return categoriaDao.agregarCategoria(dtoCategoria);
    }

    @Override
    public AgregarSubCategoriaResponse agregarSubCategoria(AgregarSubCategoriaRequest request) throws SQLException {

        try {
            AgregarSubCategoriaRequest body = categoriaDao.agregarSubCategoria(request);
            AgregarSubCategoriaResponse response = new AgregarSubCategoriaResponse();
            response.setNombre(body.getNombre());
            response.setDescripcion(body.getDescripcion());
            response.setIdCategoria(body.getIdCategoria());
            return response;
        } catch (Exception e) {
            e.getCause();
            return null;
        }
    }

    @Override
    public List<DTOCategoria> obtenerCategorias() throws SQLException {

        return categoriaDao.obtenerCategorias();
    }

    @Override
    public List<DTOSubCategoria> obtenerSubCategorias(String idCategoria) throws SQLException {

        if (idCategoria != null && utils.isNumeric(idCategoria) == true && Integer.parseInt(idCategoria) > 0) {
            int idCategoriaFiltro = Integer.parseInt(idCategoria);
            System.out.println("obtiene sub categorias filtradas por categorias");
            return categoriaDao.obtenerSubCategoriasPorCategoria(idCategoriaFiltro);
        } else {
            System.out.println("obtiene todas las sub categorias sin flitrar");
            return categoriaDao.obtenerSubCategorias();
        }
    }

    @Override
    public List<DTOSubCategoria> obtenerSubCategoriasPorCategoria() throws SQLException {
        return null;
    }

    @Override
    public DTOCategoria obtenerCategoria(int idCategoria) throws SQLException {
        return categoriaDao.obtenerCategoria(idCategoria);
    }

    @Override
    public DTOSubCategoria obtenerSubCategoria(int idSubCategoria) throws SQLException {
        return categoriaDao.obtenerSubCategoria(idSubCategoria);
    }


}
