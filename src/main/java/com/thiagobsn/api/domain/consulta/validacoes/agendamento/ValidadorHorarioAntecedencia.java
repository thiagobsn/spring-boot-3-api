package com.thiagobsn.api.domain.consulta.validacoes.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.thiagobsn.api.domain.consulta.dto.DadosAgendamentoConsulta;
import com.thiagobsn.api.exception.ValidacaoException;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var agora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30)
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia minima de 30 minutos.");
    }

}
