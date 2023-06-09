package com.thiagobsn.api.domain.medico.entity;

import com.thiagobsn.api.domain.medico.dto.DadosAtualizacaoMedico;
import com.thiagobsn.api.domain.medico.dto.DadosCadastroMedico;
import com.thiagobsn.api.domain.medico.util.Especialidade;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;

    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.ativo = true;
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dadosAtualizacaoMedico) {

        if(dadosAtualizacaoMedico.nome() != null)
            this.nome = dadosAtualizacaoMedico.nome();

        if(dadosAtualizacaoMedico.telefone() != null)
            this.telefone = dadosAtualizacaoMedico.telefone();

        if(dadosAtualizacaoMedico.endereco() != null)
            this.endereco.atualizarInformacoes(dadosAtualizacaoMedico.endereco());

    }

    public void excluir() {
        this.ativo = false;
    }
    
}
