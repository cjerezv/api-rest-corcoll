package com.api.corcoll.domain.services.Usuario;

import com.api.corcoll.domain.data.entity.Usuario;

public interface IEUsuarioService {

    public Usuario findByUsername(String username);
}
