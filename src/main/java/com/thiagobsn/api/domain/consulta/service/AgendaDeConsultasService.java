package com.thiagobsn.api.domain.consulta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagobsn.api.domain.consulta.dto.DadosAgendamentoConsulta;
import com.thiagobsn.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import com.thiagobsn.api.domain.consulta.entity.Consulta;
import com.thiagobsn.api.domain.consulta.repository.ConsultaRepository;
import com.thiagobsn.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import com.thiagobsn.api.domain.medico.entity.Medico;
import com.thiagobsn.api.domain.medico.repository.MedicoRepository;
import com.thiagobsn.api.domain.paciente.repository.PacienteRepository;
import com.thiagobsn.api.exception.ValidacaoException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AgendaDeConsultasService {

    public final ConsultaRepository consultaRepository;

    public final MedicoRepository medicoRepository;

    public final PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dadosAgendamento) {

        if (!pacienteRepository.existsById(dadosAgendamento.idPaciente()))
            throw new ValidacaoException("Id do paciente informado não exite.");

        if (dadosAgendamento.idMedico() != null && !medicoRepository.existsById(dadosAgendamento.idMedico()))
            throw new ValidacaoException("Id do medico informado não exite.");

        validadores.forEach(v -> v.validar(dadosAgendamento));

        var paciente = pacienteRepository.findById(dadosAgendamento.idPaciente()).get();
        var medico = escolherMedico(dadosAgendamento);

        if (medico == null)
            throw new ValidacaoException("Não existe médico disponível nessa data!");

        Consulta consulta = new Consulta(null, medico, paciente, dadosAgendamento.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamento) {

        if (dadosAgendamento.idMedico() != null)
            return medicoRepository.getReferenceById(dadosAgendamento.idMedico());

        if (dadosAgendamento.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatoria quando o medico não for escolhido.");

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamento.especialidade(),
                dadosAgendamento.data());
    }

}
