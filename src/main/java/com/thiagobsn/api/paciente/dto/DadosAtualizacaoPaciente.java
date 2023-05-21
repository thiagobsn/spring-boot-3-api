package com.thiagobsn.api.paciente.dto;

import com.thiagobsn.api.medico.dto.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(

        @NotNull Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {

}