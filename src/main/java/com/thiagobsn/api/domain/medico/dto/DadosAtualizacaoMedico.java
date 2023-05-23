package com.thiagobsn.api.domain.medico.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(

    @NotNull
    Long id,

    String nome,

    String telefone,

    DadosEndereco endereco) {

}
