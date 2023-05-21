package com.thiagobsn.api.medico.dto;

import com.thiagobsn.api.medico.entity.Medico;
import com.thiagobsn.api.medico.util.Especialidade;

public record DadosListagemMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicos(Medico  medico){
       this(medico.getId(), medico.getNome(), medico.getEmail() , medico.getCrm(), medico.getEspecialidade());
    }
}
