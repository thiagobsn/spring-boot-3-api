package com.thiagobsn.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thiagobsn.api.domain.consulta.dto.DadosAgendamentoConsulta;
import com.thiagobsn.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import com.thiagobsn.api.domain.consulta.service.AgendaDeConsultasService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    public final AgendaDeConsultasService agendaDeConsultasService;

    @GetMapping
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@Valid @RequestBody DadosAgendamentoConsulta dados) {
        var dto = agendaDeConsultasService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

}
