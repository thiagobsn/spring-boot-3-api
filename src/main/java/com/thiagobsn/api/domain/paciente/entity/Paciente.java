package com.thiagobsn.api.domain.paciente.entity;

import com.thiagobsn.api.domain.medico.entity.Endereco;
import com.thiagobsn.api.domain.paciente.dto.DadosAtualizacaoPaciente;
import com.thiagobsn.api.domain.paciente.dto.DadosCadastroPaciente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String CPF;

    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente cadastroPaciente) {

        this.ativo = true;
        this.nome = cadastroPaciente.nome();
        this.email = cadastroPaciente.email();
        this.telefone = cadastroPaciente.telefone();
        this.CPF = cadastroPaciente.CPF();
        this.endereco = new Endereco(cadastroPaciente.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente atualizacaoPaciente) {

        if(atualizacaoPaciente.nome() != null)
            this.nome = atualizacaoPaciente.nome();

        if(atualizacaoPaciente.telefone() != null)
            this.telefone = atualizacaoPaciente.telefone();

        if(atualizacaoPaciente.endereco() != null)
            this.endereco.atualizarInformacoes(atualizacaoPaciente.endereco());

    }

    public void excluir() {
        this.ativo = false;
    }

}
