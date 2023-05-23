package com.thiagobsn.api.domain.medico.dto;

import com.thiagobsn.api.domain.medico.entity.Endereco;
import com.thiagobsn.api.domain.medico.entity.Medico;
import com.thiagobsn.api.domain.medico.util.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String telefone, String crm,
        Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEndereco());
    }

}
