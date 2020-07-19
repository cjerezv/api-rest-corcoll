package com.api.corcoll.domain.services.Categoria;

import com.api.corcoll.domain.data.model.DTOCategoria;
import com.api.corcoll.domain.data.model.DTOSubCategoria;
import com.api.corcoll.domain.data.request.categoria.AgregarSubCategoriaRequest;
import com.api.corcoll.domain.data.response.categoria.AgregarSubCategoriaResponse;

import java.sql.SQLException;
import java.util.List;

public interface ICategoriaSevice {

    public DTOCategoria agregarCategoria(DTOCategoria dtoCategoria) throws SQLException;

    public AgregarSubCategoriaResponse agregarSubCategoria(AgregarSubCategoriaRequest body) throws SQLException;

    public List<DTOCategoria> obtenerCategorias() throws SQLException;

    public List<DTOSubCategoria> obtenerSubCategorias(String idCategoria) throws SQLException;

    public List<DTOSubCategoria> obtenerSubCategoriasPorCategoria() throws SQLException;

    public DTOCategoria obtenerCategoria(int idCategoria) throws SQLException;

    public DTOSubCategoria obtenerSubCategoria(int idSubCategoria) throws SQLException;
}
