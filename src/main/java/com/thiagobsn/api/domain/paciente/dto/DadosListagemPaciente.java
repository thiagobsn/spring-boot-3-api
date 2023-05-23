package com.thiagobsn.api.domain.paciente.dto;

import com.thiagobsn.api.domain.paciente.entity.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String telefone, String CPF) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCPF());
    }
}
