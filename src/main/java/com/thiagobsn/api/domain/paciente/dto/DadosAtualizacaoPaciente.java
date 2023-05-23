package com.thiagobsn.api.domain.paciente.dto;

import com.thiagobsn.api.domain.medico.dto.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(

        @NotNull Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {

}