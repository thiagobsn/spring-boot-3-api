package com.thiagobsn.api.paciente.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagobsn.api.paciente.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Page<Paciente> findAllByAtivoTrue(Pageable pageable);
    
}
