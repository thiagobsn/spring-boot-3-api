package com.thiagobsn.api.paciente.dto;

import com.thiagobsn.api.medico.entity.Endereco;
import com.thiagobsn.api.paciente.entity.Paciente;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String telefone, String CPF,
        Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCPF(),
                paciente.getEndereco());
    }

}
