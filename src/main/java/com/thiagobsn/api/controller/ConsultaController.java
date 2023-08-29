package com.thiagobsn.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagobsn.api.domain.consulta.dto.DadosAgendamentoConsulta;
import com.thiagobsn.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import com.thiagobsn.api.domain.consulta.service.AgendaDeConsultasService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    public final AgendaDeConsultasService agendaDeConsultasService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@Valid @RequestBody DadosAgendamentoConsulta dados) {
        var dto = agendaDeConsultasService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

}
