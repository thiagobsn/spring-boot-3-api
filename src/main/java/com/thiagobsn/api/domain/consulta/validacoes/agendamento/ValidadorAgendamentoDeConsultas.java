package com.thiagobsn.api.domain.consulta.validacoes.agendamento;

import com.thiagobsn.api.domain.consulta.dto.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta dados);
    
}
