package com.thiagobsn.api.paciente;

import com.thiagobsn.api.medico.DadosEndereco;

public record DadosCadastroPaciente(String nome, String email, String telefone, String CPF, DadosEndereco endereco) {

}
