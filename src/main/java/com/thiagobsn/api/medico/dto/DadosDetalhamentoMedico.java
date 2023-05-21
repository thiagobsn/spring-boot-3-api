package com.thiagobsn.api.medico.dto;

import com.thiagobsn.api.medico.entity.Endereco;
import com.thiagobsn.api.medico.entity.Medico;
import com.thiagobsn.api.medico.util.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String telefone, String crm,
        Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEndereco());
    }

}
