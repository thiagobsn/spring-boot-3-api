package com.thiagobsn.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thiagobsn.api.conf.TokenService;
import com.thiagobsn.api.domain.medico.dto.DadosTokenJWT;
import com.thiagobsn.api.domain.usuario.dto.DadosAutenticacao;
import com.thiagobsn.api.domain.usuario.entity.Usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@Valid @RequestBody DadosAutenticacao dadosAutenticacao) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    
}
