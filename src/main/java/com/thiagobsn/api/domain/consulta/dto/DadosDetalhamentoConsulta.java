package com.thiagobsn.api.domain.consulta.dto;

import java.time.LocalDateTime;

import com.thiagobsn.api.domain.consulta.entity.Consulta;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }

}
