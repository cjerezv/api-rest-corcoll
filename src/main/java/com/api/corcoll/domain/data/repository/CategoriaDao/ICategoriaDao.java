package com.api.corcoll.domain.data.repository.CategoriaDao;

import com.api.corcoll.domain.data.model.DTOCategoria;
import com.api.corcoll.domain.data.model.DTOSubCategoria;
import com.api.corcoll.domain.data.request.categoria.AgregarSubCategoriaRequest;
import com.api.corcoll.domain.data.response.categoria.AgregarSubCategoriaResponse;

import java.sql.SQLException;
import java.util.List;

public interface ICategoriaDao {

    public DTOCategoria agregarCategoria(DTOCategoria dtoCategoria) throws SQLException;
    public AgregarSubCategoriaRequest agregarSubCategoria(AgregarSubCategoriaRequest body) throws SQLException;
    public List<DTOCategoria> obtenerCategorias() throws SQLException;
    public List<DTOSubCategoria> obtenerSubCategorias() throws SQLException;
    public List<DTOSubCategoria> obtenerSubCategoriasPorCategoria(int idCategoria) throws SQLException;
    public Boolean existeSubCategoria(int idSubCategoria) throws SQLException;
    public DTOCategoria obtenerCategoria(int idCategoria) throws SQLException;
    public DTOSubCategoria obtenerSubCategoria(int idSubCategoria) throws SQLException;

}
