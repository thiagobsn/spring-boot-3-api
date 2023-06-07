package com.thiagobsn.api.domain.consulta.dto;

import java.time.LocalDateTime;

import com.thiagobsn.api.domain.medico.util.Especialidade;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(

        Long idMedico,

        @NotNull Long idPaciente,

        @NotNull @Future LocalDateTime data,

        Especialidade especialidade) {

}
