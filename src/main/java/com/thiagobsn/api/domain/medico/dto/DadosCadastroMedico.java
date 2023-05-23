package com.thiagobsn.api.domain.medico.dto;

import com.thiagobsn.api.domain.medico.util.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
    
    @NotBlank
    String nome, 

    @NotBlank
    @Email(message = "{erro.email}")
    String email,

    @NotBlank
    String telefone,
    
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm,
    
    @NotNull
    Especialidade especialidade, 

    @NotNull
    @Valid
    DadosEndereco endereco) {
    
}
