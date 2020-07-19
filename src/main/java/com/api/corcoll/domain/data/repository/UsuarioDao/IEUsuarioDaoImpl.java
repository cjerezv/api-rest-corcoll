package com.api.corcoll.domain.data.repository.UsuarioDao;

import com.api.corcoll.domain.data.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IEUsuarioDaoImpl extends CrudRepository<Usuario, Integer > {

    public Usuario findByUsername(String username);

}

