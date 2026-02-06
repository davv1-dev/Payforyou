package com.apipagamentos.payforyou.service;

import com.apipagamentos.payforyou.dto.DadosAutenticacao;
import com.apipagamentos.payforyou.model.Usuario;
import com.apipagamentos.payforyou.repository.UsuarioRespository;
import jakarta.validation.Valid;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService  implements UserDetailsService {
    @Autowired
    private UsuarioRespository respository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return respository.findByLogin(username);
    }
    public Usuario cadastrarUsuario(@Valid DadosAutenticacao usuarioNovo){
        String senhaCriptografada = passwordEncoder.encode(usuarioNovo.senha());
        Usuario usuarioASerSalvo = new Usuario(usuarioNovo.login(),senhaCriptografada);
        respository.save(usuarioASerSalvo);
        return  usuarioASerSalvo;
    }

}
