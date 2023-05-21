package com.thiagobsn.api.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.thiagobsn.api.paciente.dto.DadosAtualizacaoPaciente;
import com.thiagobsn.api.paciente.dto.DadosCadastroPaciente;
import com.thiagobsn.api.paciente.dto.DadosDetalhamentoPaciente;
import com.thiagobsn.api.paciente.dto.DadosListagemPaciente;
import com.thiagobsn.api.paciente.entity.Paciente;
import com.thiagobsn.api.paciente.repository.PacienteRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("pacientes")
@RequiredArgsConstructor
public class PacienteController {

    public final PacienteRepository pacienteRepository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10,sort = "nome") Pageable pageable) {
        var page = pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> buscar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@Valid @RequestBody DadosCadastroPaciente dadosCadastroPaciente, UriComponentsBuilder uriBuilder) {
        var paciente  = pacienteRepository.save(new Paciente(dadosCadastroPaciente));
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@Valid @RequestBody DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        var paciente = pacienteRepository.getReferenceById(dadosAtualizacaoPaciente.id());
        paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
        paciente = pacienteRepository.save(paciente);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        pacienteRepository.save(paciente);
        
        return ResponseEntity.noContent().build();
    }
    
}
