package com.infosys.oauth.serviciooauth.services;

import com.infosys.oauth.serviciooauth.models.Usuario;

public interface IUsuarioService {
    Usuario findByUsername(String username);
}
